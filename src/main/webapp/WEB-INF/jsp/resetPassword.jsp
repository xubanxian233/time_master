<%@ page import="java.util.Date" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
        button{
			width: 100px;
			height: 30px;
			color:white;
			background-color:cornflowerblue;
			border-radius: 3px;
			border-width: 0;
			margin: 0;
			outline: none;
			font-family: KaiTi;
			font-size: 17px;
			text-align: center;
			cursor: pointer;
		}
		button:hover{
			background-color: blueviolet;
		}
        input{
            outline-style: none ;
            border: 1px solid #ccc; 
            border-radius: 3px;
            padding: 8px 8px;
            width: 160px;

        }
        input:focus{
            border-color: #66afe9;
            outline: 0;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
        }

    </style>
    <script  type="text/javascript" >
        window.onload=function () {
            var flag = document.getElementById("hiddenInput").value;
            var fail=document.getElementById("failureDivId");
            var reset=document.getElementById("resetPasswordForm");
            if(flag == "true") {
                fail.style.display="none";
                reset.style.display="inline";
            } else {
                fail.style.display="inline";
                reset.style.display="none";
            }
        }
        function resetPasswordConfirm() {
            var pd1 = document.getElementById("password1").value;
            var pd2=document.getElementById("password2").value;
            if(pd1.length<6||pd1.length>25||pd2.length<6||pd2.length>25){
                alert("密码长度必须为6-25位");
                return false;
            }
            if (pd1!=pd2)
            {
                alert("两次密码不一致！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%
    int userId=(int)request.getAttribute("userId");
    String flag=(String)request.getAttribute("flag");
    Long time = new Date().getTime();
    String email=(String)request.getAttribute("email");
    String path="/user/findPassword?email="+email;
%>
<div class="container">
    <div class="starter-template">
        <form action="/user/resetPassword" class="form-horizontal"
              role="form" method="POST" style="border: 1px dotted silver;"
              id="resetPasswordForm" name="reset" >
            <input type = "hidden" id = "hiddenInput" name = "hiddenInput" value = "<%=flag %>" >
            <input type = "hidden" id = "userId" name = "userId" value = "<%=userId %>" >
            <br>
            <div class="form-group" style="margin-left: -204px; margin-top: 20px; margin-bottom: 20px;">
                <div style="display:inline;">
                    <label>密     码(注:密码长度至少6位)</label>
                </div>
                <div style="display:inline;">
                    <b style = "color:#FF0000;font-size:10px;">* </b>
                    <input type = "password" name = "password1" id = "password1" value = "">
                    
                </div>
            </div>

            <div class="form-group" style="margin-left: -80px; margin-top: 20px; margin-bottom: 20px;">
                <div style="display:inline;">
                    <label>确认密码</label>
                </div>
                <div style="display:inline; ">
                    <b style = "color:#FF0000;font-size:10px;">* </b>
                    <input type = "password" name = "password2" id = "password2" value = "">
                </div>
            </div>

            <input type="hidden" name="method" value="resetPasswordComplete">

            <div class="form-group" style="margin-bottom: 20px;">
                <div >
                    <button type = "submit"  onclick = "return resetPasswordConfirm();">确定</button>
                </div>
            </div>
        </form>
        <div id = "failureDivId">
            <i style="color: #ff0000;"></i><b
                style="font-family: arial; color: #00A600; font-size: 18px;">  重置密码链接已过期</b>
            <p></p>
            <p></p>
            <p style="font-family: arial; font-size: 14px;">
                       没有收到重置密码邮件，您可以到邮件垃圾箱里找找。
                <br>       或者点击：<a href = "<%=path%>">【重新发送重置密码邮件】</a>。
            </p>
        </div>
    </div>
</div>
</body>
</html>