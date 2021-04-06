<%--
  Created by IntelliJ IDEA.
  User: tomunemori
  Date: 2021/3/7
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home Page</title>
  </head>
  <body>
  <%@include file="header.jsp"%>

  <h1>Welcome to my home page!</h1> <br/>

  <form method="get" target="_blank" action="search">
    <input type="text" name="txt" size=30/>
    <select name="search">
      <option value="baidu">Baidu</option>
      <option value="bing">Bing</option>
      <option value="google">Google</option>
    </select>
    <input type="submit" value="Search"/>
  </form>

  <a href="dzsServlet">My First Servlet -wekek2</a> <br/>
  <a href="register.jsp">Register Page -week3</a> <br/>
  <a href="config">Config parameter -week4</a> <br/>
  <a href="index.jsp">include -week5</a>
  <a href="login.jsp">Login page -week5</a>
  <a href="login.jsp">Redirect login -week6</a>

  <%@include file="footer.jsp"%>
  </body>
</html>
