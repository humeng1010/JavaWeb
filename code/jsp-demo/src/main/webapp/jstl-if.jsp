<%--
  Created by IntelliJ IDEA.
  User: humeng
  Date: 2022/3/16
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
        c:if  ：是来完成逻辑判断的，替换java if else
--%>
<c:if test="${status == 1}">
    <h1>启用</h1>
</c:if>
<c:if test="${status != 1}">
    <h1>禁用</h1>
</c:if>

<c:forEach items="${brands}" var="brand">
    <tr align="center">
        <td>${brand.id}</td>
        <td>${brand.brandName}</td>
        <td>${brand.companyName}</td>
        <td>${brand.description}</td>
    </tr>
</c:forEach>
</body>
</html>
