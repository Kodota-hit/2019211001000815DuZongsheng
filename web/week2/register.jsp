<%--
  Created by IntelliJ IDEA.
  User: tomunemori
  Date: 2021/3/11
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>

    <script>

        $.validator.addMethod("dateFormat",function (value,element) {
            var DATE_FORMAT = /^(\d{4})-(0\d{1}|[12]\d{1}|3[01])-(0\d{1}|1[0-2])$/;
            if(DATE_FORMAT.test(value)) return true;
            else return false;
            }," The date format should be yyyy-dd-mm");

    </script>

    <script>

        $().ready(function() {
            $("#signupForm").validate({
                rules: {

                    username: {
                        required: true,
                        minlength: 2
                    },

                    password: {
                        required: true,
                        minlength: 8
                    },
                    confirm_password: {
                        required: true,
                        minlength: 8,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    birthday: {
                        required: true,
                        dateFormat: true
                    },

                },
                messages: {

                    username: {
                        required: " Please input username",
                        minlength: " At least 2 characters"
                    },
                    password: {
                        required: " Please input password",
                        minlength: " At least 8 characters"
                    },
                    confirm_password: {
                        required: " Please input password again",
                        minlength: " At least 8 characters",
                        equalTo: " The two passwords are inconsistent"
                    },
                    birthday: {
                        required: " Please input your birthday,surprise on the day :D",
                        dateFormat:" The date format incorrect，it should be yyyy-dd-mm"
                    },

                }
            });
        });
    </script>
    <style>
        .error{
            color:red;
        }
    </style>
</head>
<body>
<form  id="signupForm" method="post" action="../jdbc">
    <fieldset>
        <legend>Register</legend>

        <p>
            <label for="username">username</label>
            <input id="username" name="username" type="text">
        </p>
        <p>
            <label>gender</label>
            <input id="gender_m" name="gender" value="boy" type="radio" required>boy
            <input id="gender_f" name="gender" value="girl" type="radio" >girl

        </p>
        <p>
            <label for="password">password</label>
            <input id="password" name="password" type="password">
        </p>
        <p>
            <label for="confirm_password">repeat password</label>
            <input id="confirm_password" name="confirm_password" type="password">
        </p>
        <p>
            <label for="email">Email</label>
            <input id="email" name="email" type="email">
        </p>
        <p>
            <label for="birthday">Birthday</label>
            <input id="birthday" name="birthday" type="text">
        </p>


        <p>
            <input class="submit" type="submit" value="Register">
        </p>
    </fieldset>
</form>
</body>
</html>

