<%--
  Created by IntelliJ IDEA.
  User: tomunemori
  Date: 2021/4/20
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%@ page import="com.DuZongsheng.model.User" %>
<%
    User u= (User) session.getAttribute("user");
%>

<form  id="updateUserForm" method="post" action="updateUser">
    <fieldset>
        <legend>Update Info</legend>

        <p>
            <input type="hidden" name="id" value="<%=u.getId()%>">
            <label for="username">username</label>
            <input id="username" name="username" type="text" value="<%=u.getUsername()%>">
        </p>
        <p>
            <label>gender</label>
            <input id="gender_m" name="gender" value="boy" type="radio" <%="boy".equals(u.getGender())?"checked":""%>>boy
            <input id="gender_f" name="gender" value="girl" type="radio" <%="girl".equals(u.getGender())?"checked":""%>>girl

        </p>
        <p>
            <label for="password">password</label>
            <input id="password" name="password" type="password" value="<%=u.getPassword()%>">
        </p>
        <p>
            <label for="email">Email</label>
            <input id="email" name="email" type="email" value="<%=u.getEmail()%>">
        </p>
        <p>
            <label for="birthday">Birthday</label>
            <input id="birthday" name="birthday" type="text" value="<%=u.getBirthday()%>">
        </p>

        <p>
            <input class="submit" type="submit" value="Save Changes">
        </p>
    </fieldset>
</form>


<%@include file="footer.jsp"%>
