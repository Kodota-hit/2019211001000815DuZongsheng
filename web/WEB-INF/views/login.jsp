<%--
  Created by IntelliJ IDEA.
  User: tomunemori
  Date: 2021/3/30
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<%@include file="header.jsp"%>

<h1>Login</h1>
<%
    if(!(request.getAttribute("message")==null))
        out.println("<font size=\"4\" color=\"red\">"+request.getAttribute("message")+"</font>");
%>
<%
    Cookie[] allCookies=request.getCookies();
    String username="",password="",rememberMeVal="";
    if(allCookies!=null) {
        for(Cookie c:allCookies) {
            if(c.getName().equals("cUsername"))
                username=c.getValue();
            if(c.getName().equals("cPassword"))
                password=c.getValue();
            if(c.getName().equals("cRememberMe"))
                rememberMeVal=c.getValue();
        }
    }
%>
<form  id="LoginForm" method="post" action="login">
    <label for="username">UserName</label>
    <input id="username" name="username" type="text" value="<%=username%>"> <br/>

    <label for="username">Password</label>
    <input id="password" name="password" type="password" value="<%=password%>"> <br/>

    <input type="checkbox" name="rememberMe" value="1" <%=rememberMeVal.equals("")?"checked":""%>>Remember Me<br/>

    <input class="submit" type="submit" value="Login">
</form>

<%@include file="footer.jsp"%>

</body>
</html>
