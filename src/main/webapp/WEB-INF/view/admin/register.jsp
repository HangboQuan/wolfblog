<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org ">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>腾云博客 &lsaquo; 注册</title>
    <link rel='stylesheet' id='dashicons-css' href='/plugin/login/dashicons.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='buttons-css' href='/plugin/login/buttons.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='login-css' href='/plugin/login/login.min.css' type='text/css' media='all'/>
    <meta name='robots' content='noindex,follow'/>
    <meta name="viewport" content="width=device-width"/>
    <link rel="stylesheet" href="/css/animate.css">
    <link rel="stylesheet" href="/css/jquery.toast.css">

</head>
<body class="login login-action-login wp-core-ui  locale-zh-cn">
<div id="login" class="login-body animated">
    <h1><a href="https://www.taobao.com/" title="欢迎您光临本站！" tabindex="-1" >腾云博客</a></h1>
    <p class="message register">欢迎注册！</p>

    <form name="registerForm" id="registerForm" action="/admin/login"
          method="post" novalidate="novalidate">
        <p>
            <!--for属性规定label和那个控件元素绑定-->
            <label for="userName">用户名<br>
                <input type="text" name="username" id="userName" class="input" value="" size="20">
            </label>
        </p>
        <p>
            <label for="userEmail">电子邮箱<br>
                <input type="email" name="useremail" id="userEmail" class="input" value="" size="25">
            </label>
        </p>
        <p>
            <label for="userPass">密码<br>
                <input type="password" name="userpass" id="userPass" class="input" value="" size="25">
            </label>
        </p>
        <br class="clear">
        <p class="submit">
            <button type="button" id="register-btn" class="button button-primary button-large" value="注册">注册</button>
        </p>
    </form>
    <div class="clear"></div>
    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        <%--登录验证--%>
        $("#register-btn").click(function () {
            var username = $("#userName").val();
            var password = $("#userPass").val();

            if(username=="") {
                alert("用户名不可为空!");
            } else if(password==""){
                alert("密码不可为空!");
            } else {
                $.ajax({
                    async: false,//同步，待请求完毕后再执行后面的代码
                    type: "POST",
                    url: '/registerVerify',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data: $("#registerForm").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if(data.code==0) {
                            alert(data.msg);
                        } else {
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
        <a href="/login">登录</a> | <a href="/forget">忘记密码？</a>
    </p>

    <p id="backtoblog"><a href="/">&larr; 返回到腾云博客</a></p>

</div>

<script>
    var heading = "提示";
</script>
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery.toast.js"></script>
<script src="/js/jquery-ui.min.js"></script>

</body>
</html>
