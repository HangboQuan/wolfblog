<%--
  Created by IntelliJ IDEA.
  User: quanhangbo
  Date: 2020/1/4
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>腾云博客 &lsaquo; 找回密码</title>
    <link rel='stylesheet' id='dashicons-css' href='/plugin/login/dashicons.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='buttons-css' href='/plugin/login/buttons.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='login-css' href='/plugin/login/login.min.css' type='text/css' media='all'/>
    <meta name='robots' content='noindex,follow'/>
    <meta name="viewport" content="width=device-width"/>
    <link rel="stylesheet" href="/css/animate.css?version=1.5.0">
    <link rel="stylesheet" href="/css/jquery.toast.css?version=1.5.0">

</head>
<body class="login login-action-login wp-core-ui  locale-zh-cn">
<div id="login" class="login-body animated">
    <h1><a href="http://www.taobao.com" title="欢迎您光临本站！" tabindex="-1">腾云博客</a></h1>
    <p class="message register">找回密码！</p>

    <form name="forgetPasswordForm" id="forgetPasswordForm" action="/admin/login"
          method="post" novalidate="novalidate">
        <p>
            <label for="userName">用户名<br>
                <input type="text" name="userName" id="userName" class="input" value="" size="20">
            </label>
        </p>
        <p>
            <label for="userEmail">电子邮箱<br>
                <input type="email" name="userEmail" id="userEmail" class="input" value="" size="25">
            </label>
        </p>
        <p id="forget_mail">新密码将会通过邮件发送给您。</p>

        <br class="clear">
        <input type="hidden" name="redirect_to" value="">
        <p class="submit">
            <button type="button" id="forget-btn" class="button button-primary button-large" value="得到密码">得到密码</button>
        </p>
    </form>
    <div class="clear"></div>
    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        <%--忘记密码--%>
        $("#forget-btn").click(function () {
            var username = $("#userName").val();
            var email = $("#userEmail").val();
            if(username=="") {
                alert("用户名不可为空!");
            } else if(email==""){
                alert("邮箱不可为空!");
            } else {
                $.ajax({
                    async: false,//同步，待请求完毕后再执行后面的代码
                    type: "POST",
                    url: '/forgetVerify',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data: $("#forgetPasswordForm").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if(data.code==0) {
                            alert(data.msg);
                        } else {
                            alert(data.msg);
                            window.location.href="/admin/login";
                        }
                    },
                    error: function () {
                        alert("数据获取失败")
                    }
                })
            }
        })
    </script>
    <p id="nav">
        <a href="/login">登录</a> | <a href="/register">注册</a>
    </p>

    <p id="backtoblog"><a href="/">&larr; 返回到腾云博客</a></p>

</div>

<script>
    var heading = "提示";
</script>
<script src="/js/jquery.min.js?version=1.5.0"></script>
<script src="/js/jquery.toast.js?version=1.5.0"></script>
</body>
</html>
