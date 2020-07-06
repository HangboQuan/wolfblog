# 项目介绍：
  ## 一.该项目实现了一个简单的博客系统，具体功能包括：
    后台：后台管理系统，具体包括：文章管理、分类管理、标签管理、用户管理、活动通知管理等
    前台：用户注册登录、搜索文章、评价文章、点赞文章、图片上传、文章分页展示、文章分享等
  使用ssm（spring+springmvc+mybaits）+redis+elk作为后台技术，使用layui+jsp+ajax作为前端技术
  ### 技术亮点：
    1.注册登录时利用ajax对不合适的输入做出提示，并使用MD5+salt对密码进行加密;
    2.使用elk替换了原来mysql的模糊查询方式,实现对文章的标题,摘要,全文的搜索功能,并高亮展示;
    3.点赞功能由Redis来实现,将点赞数率先存入redis,然后再更新到mysql中;
    4.异步处理,利用生产者消费者模式将用户的点赞信息异步通知给用户;
    
  项目预览地址：http://www.hbquan.top(腾云博客)
  ## 二.项目前台和后台部分功能预览
  ### 1.首页效果展示:
  ![image](https://github.com/ProgramMonkeyquan/wolfblog/blob/master/img/QQ_20200706114610.png)
  ### 2.文章详情页展示:
  ![image](https://github.com/ProgramMonkeyquan/wolfblog/blob/master/img/QQ_20200706114741.png)
  ### 3.后台文章管理展示:
  ![image](https://github.com/ProgramMonkeyquan/wolfblog/blob/master/img/QQ_20200706114923.png)
  ### 4.后台写文章展示:
  ![image](https://github.com/ProgramMonkeyquan/wolfblog/blob/master/img/QQ_20200706115018.png)
  ### 5.后台评论管理展示:
  ![image](https://github.com/ProgramMonkeyquan/wolfblog/blob/master/img/QQ_20200706115103.png)
  ### 6.后台用户管理展示:
  ![image](https://github.com/ProgramMonkeyquan/wolfblog/blob/master/img/QQ_20200706115143.png)
  ## 三.使用注意事项
  
       1.本人是一名大三学生,借博客项目来实战自己的技术,个人登录后会拥有所有的权限,所以请大家不要恶意删除任何东西,谢谢！
       2.开发工具准备：idea+maven+tomcat+redis+mysql+elk,尤其说明elasticsearch和logstash和kibana的版本号必须保持一致,我使用的是7.0.0版本,大家如果嫌官网下载慢的话,可以在我的百度网盘中获取,包含windows版和linux版,链接为:https://pan.baidu.com/s/13pnSXdGVWoDPMo0x1AtULw,提取码:8srb;另我使用的mysql版本为8.0.13,和5.0版本有点区别,请在db.properties中修改DriverClass,username,以及password.
       3.访问项目是没有带项目名称,即tomcat中要配置application context为/.
       4.记得修改图片上传目录,在UploadFileController.java中修改您合适的目录,比如我们把 uploads 目录放到 E盘根目录，比如有一张图片路径是 E:/uploads/2017/10/avatar.jpg, 我们想在项目中以 http://loclahost:8080/uploads/2017/10/avatar.jpg 方式访问,修改 rootPath 为你指定的 uploads 目录，如 String rootPath ="E:/uploads/";
       同理：在linux上也要将更改uploads目录,同时记得在linux中的在 tomcat/conf/server.xml 的 Host 标签内添加如下代码</br>.
       <Context path="/uploads" docBase="/www/uploads" debug="0" reloadable="true" /> 否则图片访问不了.
       5.该项目是基于言曌博客的改写,以及添加了很多新的功能,感谢言曌博客.
       6.由于本人水平有限,代码中难免出现bug,如果有意交流者,加qq：2409711401,或微信:18392710807
       7.最后希望大家学有所成,勿忘初心,期待您的star和fork!!!
       
  
  
  
  
  
	
