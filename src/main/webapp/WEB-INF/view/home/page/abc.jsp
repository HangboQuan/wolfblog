<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: quanhangbo
  Date: 2020/6/19
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>评论</th>
            <th>日期</th>
            <th>发起人</th>
        </tr>
        <c:forEach items="${conversations}" var="conversation">
            <tr>
                <td>${conversation.content}</td>
                <td>${conversation.createdDate}</td>
                <td>${conversation.fromId}</td>
            </tr>


        </c:forEach>

    </table>
</body>
</html>
