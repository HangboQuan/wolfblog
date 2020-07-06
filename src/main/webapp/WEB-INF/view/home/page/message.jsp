<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="header-style">
    <style>
        .entry-title {
            background: #f8f8f8;
        }
    </style>
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
</rapid:override>


<rapid:override name="breadcrumb">
    <%--面包屑导航 start--%>
    <nav class="breadcrumb">
        <a class="crumbs" href="/">
            <i class="fa fa-home"></i>首页
        </a>
        <i class="fa fa-angle-right"></i>
        留言板
        <i class="fa fa-angle-right"></i>
        正文
    </nav>
    <%--面包屑导航 end--%>
</rapid:override>


<rapid:override name="left">
    <%--博客主体-左侧文章正文 start--%>
    <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
            <article class="post" style="min-height: 500px;">
                <header class="entry-header">
                    <h1 class="entry-title">
                           留言板
                    </h1>
                </header>
                <%--<div id="single-widget">
                    <div class="wow fadeInUp" data-wow-delay="0.3s">
                        <aside id="related_post-2" class="widget">
                            <h3 class="widget-title">
                                <span class="s-icon"></span>相关通知
                            </h3>
                            <div id="related_post_widget">
                                <ul>
                                    <c:forEach items="${conversations}" var="s">
                                        <li>
                                            ${s.content}&nbsp;${s.createdDate}&nbsp;${s.fromId}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="clear"></div>
                        </aside>

                    </div>
                    <div class="clear"></div>
                </div>--%>

                <%--<div class="authorbio wow fadeInUp">
                    <div id="related_post_widget">
                        <h3 class="widget-title">
                            <span class="s-icon">相关通知</span>
                        </h3>
                        <ul>
                            <c:forEach items="${conversations}" var="s">
                                <li>
                                        ${s.content}&nbsp;${s.createdDate}&nbsp;
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>--%>

                <section id="primary" class="content-area">
                    <main id="main" class="site-main" role="main">
                        <ul class="search-page">
                            <c:forEach items="${conversations}" var="a">
                                <li class="search-inf">
                                    <fmt:formatDate value="${a.createdDate}" pattern="yyyy年MM月dd日"/>
                                </li>
                                <li class="entry-title">
                                    ${a.content}
                                </li>
                            </c:forEach>
                        </ul>
                    </main>
                </section>
            </article><!-- #post -->



        </main>
        <!-- .site-main -->
    </div>


    <%--
    <div id="main">
        <div class="container">
            <ul class="letter-list">
                <!--#foreach($conversation in $conversations)-->
                <c:forEach items="${conversations}" var="conversation">
                <li id="conversation-item-10005_622873">
                    <a class="letter-link" href="/msg/detail?conversationId=${conversation.conversationId}"></a>
                    <div class="letter-info">
                        <span class="l-time"> ${conversation.createdDate}</span>
                        <div class="l-operate-bar">
                            <a href="javascript:void(0);" class="sns-action-del" data-id="10005_622873">
                                删除
                            </a>
                            <a href="/msg/detail?conversationId=${conversation.conversationId}">
                                共${conversation.id}条会话
                            </a>
                        </div>
                    </div>
                    <div class="chat-headbox">
                        <span class="msg-num">
                            ${conversation.id}
                        </span>
                        &lt;%&ndash;<a class="list-head" href="/user/${conversation.id}">
                            <img alt="头像" src="${conversation.id}">
                        </a>&ndash;%&gt;
                    </div>
                    <div class="letter-detail">
                        <a title="${conversation.id}" class="letter-name level-color-1">
                            ${conversation.id}
                        </a>
                        <p class="letter-brief">
                            <a href="/msg/detail?conversationId=${conversation.conversationId}">
                                ${conversation.content}
                            </a>
                        </p>
                    </div>
                </li>
                </c:forEach>
                <!--#end-->
            </ul>

        </div>
        <script type="text/javascript">
            $(function(){

                // If really is weixin
                $(document).on('WeixinJSBridgeReady', function() {

                    $('.weixin-qrcode-dropdown').show();

                    var options = {
                        "img_url": "",
                        "link": "http://nowcoder.com/j/wt2rwy",
                        "desc": "",
                        "title": "读《Web 全栈工程师的自我修养》"
                    };

                    WeixinJSBridge.on('menu:share:appmessage', function (argv){
                        WeixinJSBridge.invoke('sendAppMessage', options, function (res) {
                            // _report('send_msg', res.err_msg)
                        });
                    });

                    WeixinJSBridge.on('menu:share:timeline', function (argv) {
                        WeixinJSBridge.invoke('shareTimeline', options, function (res) {
                            // _report('send_msg', res.err_msg)
                        });
                    });

                    // $(window).on('touchmove scroll', function() {
                    //   if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
                    //     $('div.backdrop').show();
                    //     $('div.share-help').show();
                    //   } else {
                    //     $('div.backdrop').hide();
                    //     $('div.share-help').hide();
                    //   }
                    // });

                });

            })
        </script>
    </div>--%>

    <%--博客主体-左侧文章正文end--%>
</rapid:override>


<%--侧边栏 start--%>
<rapid:override name="right">
    <%@include file="../public/part/sidebar-3.jsp" %>
</rapid:override>
<%--侧边栏 end--%>



<%@ include file="../public/framework.jsp" %>