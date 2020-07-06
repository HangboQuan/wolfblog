package com.tencent.controller.admin;

import com.tencent.entity.User;
import com.tencent.service.IUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.tencent.utils.MyUtils.getIpAddr;
import static com.tencent.utils.MyUtils.strToMd5;

@Controller
public class AdminController {
    @Autowired
    private IUserService userService;

    //这里的代码没有写完，需要用到别的类
    @RequestMapping("/admin")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("/register")
    public String registerPage(HttpServletRequest request) {
        return "admin/register";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    //登录验证
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody//作用：是将controller的方法返回的对象通过适当的转换器转为指定的格式，写入到response对象的body区，通常用来返回json数据
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("account");
        String password = request.getParameter("password");

        String rememberme = request.getParameter("rememberme");

        System.out.println(username);
        //这里得bug就是只能使用用户名登录，而不能使用邮件的登录
        User user = userService.getUserByNameOrEmail(username);
        System.out.println(user);
        //这里设置session，将来在拦截器中再获取session，如果session里的值为空就拦截，否则放行

        //测试登录时密码是否可用
        String inputPass = strToMd5(password + user.getUserSalt());
        String dbPass = user.getUserPass();
        System.out.println("inputPass : dbPass" + inputPass.equals(dbPass));

        System.out.println((strToMd5("qhb1229" + "at202")));

        if (user == null) {
            map.put("code", 0);
            map.put("msg", "用户名无效!");
        } else if (!user.getUserPass().equals(strToMd5(password + user.getUserSalt()))) {
            map.put("code", 0);
            map.put("msg", "密码错误！");
        } else {
            //登录成功
            map.put("code", 1);
            map.put("msg", "");
            //这里设置session，将来在拦截器中再获取session，如果session里的值为空就拦截，否则放行
            request.getSession().setAttribute("user",user);
            if (rememberme != null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("account", username);
                //设置Coolie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
        }

        user.setUserLastLoginTime(new Date());
        user.setUserLastLoginIp(getIpAddr(request));
        userService.updateUser(user);
        request.getSession().setAttribute("user",user);
        String result = new JSONObject(map).toString();
        return result;
    }

    //找回密码
    @RequestMapping("/forget")
    public String forgetPassword(HttpServletRequest request){
        return "admin/forgetpassword";
    }
    @RequestMapping("/forgetVerify")
    @ResponseBody
    public String forgetPasswordVerify(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        String username = request.getParameter("userName");
        String useremail = request.getParameter("userEmail");
        //这里会再次出现一个bug,就是这个是根据用户名查找的，所以和输入的Email就没有关系，哪怕他输入一个错误的Email他照样能够查找出密码
        User user = userService.getUserByName(username);
        if(null == user){
            map.put("code",0);
            map.put("msg","该用户未注册");
        }else{
            map.put("code",1);
            map.put("msg","您的密码为"+user.getUserPass());
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    //注册验证
    @RequestMapping(value="/registerVerify",method = RequestMethod.POST)
    @ResponseBody
    public String registerVerify(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String useremail = request.getParameter("useremail");
        String userpass = request.getParameter("userpass");
        System.out.println(username);
        User user = userService.getUserByNameOrEmail(username);
        System.out.println(user);
        if (null != user && username.equals(user.getUserName())) {
            map.put("code", 0);
            map.put("msg", "该用户已经注册过！");
        } else {
            //表示注册成功
            map.put("code", 1);
            map.put("msg", "注册成功");
            User users = new User();
            users.setUserName(username);
            String salt = UUID.randomUUID().toString().substring(0,5);
            users.setUserSalt(salt);
            users.setUserPass(strToMd5(userpass + salt));
            users.setUserEmail(useremail);
            users.setUserRegisterTime(new Date());
            users.setUserLastLoginIp(getIpAddr(request));
            users.setUserLastLoginTime(new Date());
            users.setUserNickName("The Greatest Computer Scientist");
            users.setUserStatus(1);
            userService.insertUser(users);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    //退出登录
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }


}
