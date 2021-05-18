<%--
  Created by IntelliJ IDEA.
  User: tomunemori
  Date: 2021/4/6
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.DuZongsheng.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%
    User u= (User) session.getAttribute("user");
%>

<h1>User Info</h1>

<table border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Birthday</th>
    </tr>
    <tr>
        <td><%=u.getUsername()%></td>
        <td><%=u.getPassword()%></td>
        <td><%=u.getEmail()%></td>
        <td><%=u.getGender()%></td>
        <td><%=u.getBirthday()%></td>
    </tr>

</table>

<a href="updateUser">Update User</a>

<%@include file="footer.jsp"%>