<%--
  Created by IntelliJ IDEA.
  User: tomunemori
  Date: 2021/6/15
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<%
    String message = (String) request.getAttribute("Efir");
    if(message!=null && !"".equals(message)){
 %>
     <script type="text/javascript">
         alert("<%=message%>");
     </script>
 <%} %>
<%
    message = (String) request.getAttribute("Esec");
    if(message!=null && !"".equals(message)){
 %>
     <script type="text/javascript">
         alert("<%=message%>");
     </script>
 <%} %>
<%
    message = (String) request.getAttribute("Ediv");
    if(message!=null && !"".equals(message)){
 %>
     <script type="text/javascript">
         alert("<%=message%>");
     </script>
 <%} %>
<%
    message = (String) request.getAttribute("Ename");
    if(message!=null && !"".equals(message)){
 %>
     <script type="text/javascript">
         alert("<%=message%>");
     </script>
 <%} %>


<%
    Cookie[] cookies=request.getCookies();
    String firstValue="";
    String secondValue="";
    String result="";
    String name="";
    String length="";
    if (cookies!=null){
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("cLength")){
                length=cookie.getValue();
            }
            if (cookie.getName().equals("cResult")){
                result=cookie.getValue();
            }
            if (cookie.getName().equals("cSecondValue")){
                secondValue=cookie.getValue();
            }
            if (cookie.getName().equals("cFirstValue")){
                firstValue=cookie.getValue();
            }
            if (cookie.getName().equals("cName")){
                name=cookie.getValue();
            }
        }
    }
%>

<script>
    function formReset() {
    document.getElementById("myform").reset();}
</script>

<body>
<form action="CalServlet" id="myform" method="post">
    <table>
        <tr>
            <td>
                First val:<input type="text" name="firstValue" id="firstValue" autocomplete="off" value="<%=firstValue%>">
            </td>
            <td>
                Enter a name:<input type="text" name="name" id="name" autocomplete="off" value="<%=name%>"/>
            </td>
        </tr>

        <tr>
            <td>
                Second val:<input type="text" name="secondValue" id="secondValue" autocomplete="off" value="<%=secondValue%>"/>
            </td>
            <td>
                Length:<input type="text" name="length" id="length" autocomplete="off" value="<%=length%>"/>
            </td>
        </tr>
        <tr>
            <td>
                Result:<input type="text" name="result" id="result" autocomplete="off" value="<%=result%>"/>
            </td>

        </tr>
    </table>
    <input type="submit" name="add"  value="Add" />
    <input type="submit" name="sub"  value="Subtract" />
    <input type="submit" name="mul"  value="Multiply" />
    <input type="submit" name="div"  value="Divide" />
    <input type="submit" name="calLen"  value="Computer Length" />
    <input type="reset" value="Reset"  onclick="formReset()"/><br><br>
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
