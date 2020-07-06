function showMsg(text, icon, hideAfter) {
    if (heading == undefined) {
        var heading = "鎻愮ず";
    }
    $.toast({
        text: text,
        heading: heading,
        icon: icon,
        showHideTransition: 'fade',
        allowToastClose: true,
        hideAfter: hideAfter,
        stack: 1,
        position: 'top-center',
        textAlign: 'left',
        loader: true,
        loaderBg: '#ffffff'
    });
}

function showMsgAndReload(text, icon, hideAfter) {
    if (heading == undefined) {
        var heading = "鎻愮ず";
    }
    $.toast({
        text: text,
        heading: heading,
        icon: icon,
        showHideTransition: 'fade',
        allowToastClose: true,
        hideAfter: hideAfter,
        stack: 1,
        position: 'top-center',
        textAlign: 'left',
        loader: true,
        loaderBg: '#ffffff',
        afterHidden: function () {
            window.location.reload();
        }
    });
}

function showMsgAndRedirect(text, icon, hideAfter, redirectUrl) {
    if (heading == undefined) {
        var heading = "鎻愮ず";
    }
    $.toast({
        text: text,
        heading: heading,
        icon: icon,
        showHideTransition: 'fade',
        allowToastClose: true,
        hideAfter: hideAfter,
        stack: 1,
        position: 'top-center',
        textAlign: 'left',
        loader: true,
        loaderBg: '#ffffff',
        afterHidden: function () {
            window.location.href = redirectUrl;
        }
    });
}

$(document).ready(function () {
    var account = localStorage.getItem("account");
    var password = localStorage.getItem("password");
    if (account != null && localStorage.getItem("account") != null) {
        $("#login-name").val(localStorage.getItem("account"));
    }
    if (password != null && localStorage.getItem("password") != null) {
        $("#login-pwd").val(localStorage.getItem("password"));
    }
});

function btn_login() {
    var prevLink = document.referrer;
    $('#btn-login').button('loading');
    var name = $("#login-name").val();
    var pwd = $("#login-pwd").val();
    var rememberMe = $("#rememberMe").prop("checked");
    if (name == "" || pwd == "") {
        showMsg("璇疯緭鍏ュ畬鏁翠俊鎭紒", "info", 2000);
        $('#btn-login').button('reset');
    } else {
        $.ajax({
            type: 'POST',
            url: '/admin/getLogin',
            async: false,
            data: {
                'account': name,
                'password': pwd
            },
            success: function (data) {
                if (rememberMe) {
                    localStorage.setItem('account', $("#login-name").val());
                    localStorage.setItem('password', $("#login-pwd").val());
                } else {
                    localStorage.setItem('account','');
                    localStorage.setItem('password','');
                }
                if (data.code == 1) {
                    $.toast({
                        text: data.msg,
                        heading: heading,
                        icon: 'success',
                        showHideTransition: 'fade',
                        allowToastClose: true,
                        hideAfter: 1000,
                        stack: 1,
                        position: 'top-center',
                        textAlign: 'left',
                        loader: true,
                        loaderBg: '#ffffff',
                        afterHidden: function () {
                            if ($.trim(prevLink) == '') {
                                window.location.href = '/admin';
                            } else {
                                window.location.href = prevLink;
                            }
                        }
                    });
                } else {
                    $('.login-body').addClass('animate shake');
                    $.toast({
                        text: data.msg,
                        heading: heading,
                        icon: 'error',
                        showHideTransition: 'fade',
                        allowToastClose: true,
                        hideAfter: 2000,
                        stack: 1,
                        position: 'top-center',
                        textAlign: 'left',
                        loader: true,
                        loaderBg: '#ffffff',
                        afterHidden: function () {
                            $('.login-body').removeClass('animate shake');
                        }
                    });
                    $('#btn-login').button('reset');
                }
            }
        });
    }
}


function btn_register() {
    $('#btn-register').button('loading');
    var userName = $("#userName").val();
    var userPass = $("#userPass").val();
    var userEmail = $("#userEmail").val();
    if (userName == "" || userPass == "" || userEmail == "") {
        showMsg("用户名或者密码或者Email为空", "info", 2000);
        $('#btn-register').button('reset');
    } else {
        $.ajax({
            type: 'POST',
            url: '/admin/login',
            async: false,
            data: {
                'userName': userName,
                'userPass': userPass,
                'userEmail': userEmail
            },
            success: function (data) {
                if (data.code == 1) {
                    showMsgAndRedirect(data.msg, "success", 2000, "/admin/login");
                } else {
                    showMsg(data.msg, "error", 2000);
                    $('#btn-register').button('reset');
                    localStorage.setItem('account', userName);
                }
            }
        });
    }
}


function btn_forget() {
    $('#btn-forget').button('loading');
    var userName = $("#userName").val();
    var userEmail = $("#userEmail").val();
    if (userName == "" || userEmail == "") {
        showMsg("璇疯緭鍏ュ畬鏁翠俊鎭紒", "info", 2000);
        $('#btn-forget').button('reset');
    } else {
        $.ajax({
            type: 'POST',
            url: '/admin/getForget',
            async: false,
            data: {
                'userName': userName,
                'userEmail': userEmail
            },
            success: function (data) {
                if (data.code == 1) {
                    showMsgAndRedirect(data.msg, "success", 2000, "/admin/login");
                } else {
                    showMsg(data.msg, "error", 2000);
                    $('#btn-forget').button('reset');
                }
            }
        });
    }
}

$(document).keydown(function (event) {
    if (event.keyCode == 13) {
        btn_login();
    }
});