package com.tencent.controller.admin;

import com.tencent.entity.User;
import com.tencent.service.IUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //后台用户列表显示
    @RequestMapping(value = "")
    public ModelAndView userList(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.listUser();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("admin/user/index");
        return modelAndView;
    }

    //后台添加用户页面
    @RequestMapping("/insert")
    public ModelAndView insertUserview(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user/insert");
        return modelAndView;
    }
    //检查用户名是否存在
    @RequestMapping(value="/checkUserName",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>();
        //getParameter是从前端浏览器中获取到值
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        //用户名已存在，但不是当前用户(编辑用户的时候，不提示)
        if(user != null){
            if(user.getUserId() != id){
                map.put("code",1);
                map.put("msg","用户名已存在！");
            }
        }else{
            map.put("code",0);
            map.put("msg","");
        }
        String result = new JSONObject(map).toString();
        return result;
    }
    //检查Email是否存在
    @RequestMapping(value = "/checkUserEmail",method = RequestMethod.POST)
    @ResponseBody//作用是将java对象转为json格式的数据，比如使用jsp调用后台，后台生成java对象用于返回给页面，
    //前端页面来调用这个接口，到前端后自动转为json格式的数据
    public String checkUserEmail(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        //如果用户存在，但是不是当前用户
        if(user != null){
            if(user.getUserId() != id){
                map.put("code","1");
                map.put("msg","电子邮箱已存在");
            }
        }else{
            map.put("code",0);
            map.put("msg","");
        }
        String result  = new JSONObject(map).toString();
        return result;
    }
    //删除用户
    @RequestMapping(value="/delete/{id}")//@PathVariable的作用是：接受请求路径中占位符的值
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
    //编辑用户页面提示
    @RequestMapping("/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserById(id);
        System.out.println(user);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/admin/user/edit");
        return modelAndView;
    }
    //编辑用户提交
    @RequestMapping(value="/editSubmit",method = RequestMethod.POST)
    public String editUserSubmit(User user) {
        userService.updateUser(user);
        return "redirect:/admin/user";
    }
    //这个方法的bug是session.getAttribute("user")的值为null,会导致空指针异常
    @RequestMapping(value="/profile")
    public ModelAndView userProfileView(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User sessionUser = (User)session.getAttribute("user");
        User user = userService.getUserById(sessionUser.getUserId());
        modelAndView.addObject("user",user);
        modelAndView.setViewName("admin/user/profile");
        return modelAndView;
    }


}
