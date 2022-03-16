<%--
  Created by IntelliJ IDEA.
  User: humeng
  Date: 2022/3/16
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello jsp</h1>
<%--jsp_service中--%>
<%
    System.out.println("hello jsp");
    int i = 3;
%>
<%--out.print("内容")--%>
<%="hello" + i%>
<%--jsp_service方法外，成员变量，成员方法--%>
<%!
    String name = "张三";

    void show() {

        System.out.println(name);

    }
%>
</body>
</html>
