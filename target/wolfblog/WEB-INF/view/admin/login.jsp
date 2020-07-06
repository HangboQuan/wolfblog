<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>腾云博客 &lsaquo; 登录</title>
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
    <h1><a href="http://qq.com" title="欢迎您光临本站！" tabindex="-1">腾云博客</a></h1>
    <p class="message register">腾云博客，欢迎登录！</p>

    <form name="loginForm" id="loginForm" action="/admin" method="post">
        <p>
            <label for="login-name">用户名或电子邮件地址<br/>
                <input type="text" name="account" id="login-name" class="input" size="20" required/>
            </label>
        </p>
        <p>
            <label for="login-pwd">密码<br/>
                <input type="password" name="password" id="login-pwd" class="input" size="20" required/>
            </label>
        </p>
        <div style="clear: both;"></div>
        <p class="forgetmenot">
            <label for="rememberme">
                <input name="rememberMe" type="checkbox" id="rememberMe"
                       checked="checked"> 记住我的登录信息
            </label>
        </p>
        <p class="submit">
            <button type="button" id="login-btn" class="button button-primary button-large" value="登录">登录</button>
        </p>
        <br>
    </form>
    <div class="clear"></div>
    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        <%--登录验证--%>
        $("#login-btn").click(function () {
            var username = $("#login-name").val();
            var password = $("#login-pwd").val();
            if(username=="") {
                alert("用户名不可为空!");
            } else if(password==""){
                alert("密码不可为空!");
            } else {
                $.ajax({
                    async: false,//同步，待请求完毕后再执行后面的代码
                    type: "POST",
                    url: '/loginVerify',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data: $("#loginForm").serialize(),
                    dataType: "json",
                   success: function (data) {
                        if(data.code==0) {
                            alert(data.msg);
                        } else {
                            window.location.href="/admin";
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
        <a href="/register">注册</a> |
        <a href="/forget">忘记密码？</a>
    </p>

    <p id="backtoblog"><a href="/">&larr; 返回到腾云博客</a></p>

</div>

<script>
    var heading = "提示";
</script>
<script src="/js/jquery.min.js?version=1.5.0"></script>
<script src="/js/jquery.toast.js?version=1.5.0"></script>
<script src="/js/bootstrap.min.js?version=1.5.0"></script>
</body>
</html>