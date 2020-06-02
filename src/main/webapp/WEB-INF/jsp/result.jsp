<%@ page import="java.util.Date" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>重置密码</title>
    <style type="text/css">
        body {
            padding-top: 50px;
        }

        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }

        button {
            width: 100px;
            height: 30px;
            color: white;
            background-color: cornflowerblue;
            border-radius: 3px;
            border-width: 0;
            margin: 0;
            outline: none;
            font-family: KaiTi;
            font-size: 17px;
            text-align: center;
            cursor: pointer;
        }

        button:hover {
            background-color: blueviolet;
        }

        input {
            outline-style: none;
            border: 1px solid #ccc;
            border-radius: 3px;
            padding: 8px 8px;
            width: 160px;

        }

        input:focus {
            border-color: #66afe9;
            outline: 0;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
        }

    </style>
    <script type="text/javascript">
        window.onload = function () {
            var flag = document.getElementById("hiddenInput").value;
            var fail = document.getElementById("failureDivId");
            var success = document.getElementById("successDivId");
            if (flag == "true") {
                fail.style.display = "none";
                success.style.display = "inline";
            } else {
                fail.style.display = "inline";
                success.style.display = "none";
            }
        }
    </script>
</head>
<body>
<%
    String flag = (String) request.getAttribute("flag");
    String email = (String) request.getAttribute("email");
    String path = "/user/findPassword?email=" + email;
%>
<div class="container">
    <div class="starter-template">
        <input type = "hidden" id = "hiddenInput" name = "hiddenInput" value = "<%=flag %>" >
        <div id="successDivId">
            <i style="color: #ff0000;"></i><b
                style="font-family: arial; color: #00A600; font-size: 18px;">  重置密码成功</b>
            <p></p>
            <p></p>
            <p style="font-family: arial; font-size: 14px;">
                       请登录验证结果，如果存在错误
                <br>      请你点击：<a href="<%=path%>">【重新发送重置密码邮件】</a>。
            </p>
        </div>
        <div id="failureDivId">
            <i style="color: #ff0000;"></i><b
                style="font-family: arial; color: #00A600; font-size: 18px;">  重置密码失败</b>
            <p></p>
            <p></p>
            <p style="font-family: arial; font-size: 14px;">
                       重置密码失败，非常抱歉，请您重新发送重置邮件再次重置。
                <br>       请您点击：<a href="<%=path%>">【重新发送重置密码邮件】</a>。
            </p>
        </div>
    </div>
</div>
</body>
</html>