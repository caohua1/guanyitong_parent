<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆页面</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <style>
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            background-image: url('<%=path%>/img/admin-login-bg.jpg');
        }
        input{
            color: palevioletred;
            border-radius:8px;
            overflow:hidden;
        }
        select{
            color: palevioletred;
            border-radius:8px;
            overflow:hidden;
        }
        .border_radius { border-radius:5px; color:#B00000; }
    </style>



</head>
<body>

<div class="loginliu">
    <div style="padding-top: 300px;padding-left: 42.8%">
        <p><b style="color: red;font-size: 20px" > ${message}</b></p>
    </div>
    <form action="<%=basePath%>login.do" commandName="employee" method="post" id="focus">
        <div class="loadliu">
            <p><span><img src="<%=path%>/img/yong.png"></span><span><input type="text" name="ephone"  id="ephone" placeholder="用户名不能为空">

</span></p>
            <p><span><img src="<%=path%>/img/pass.png"></span><span><input type="password" name="epassword" id="epassword" placeholder="密码不能为空"></span></p>
            <div class="loadliung">
                <span><input type="submit" value="登陆" id="tologin"></span>
                <span><input type="reset" value="取消"></span>
            </div>
        </div>
    </form>

</div>
</body>
</html>
<script>
    $(function(){
        $('.loginliu').height(document.documentElement.clientHeight);


        //提交表单

        $('#tologin').click(function(){
            var ephone = $("#ephone").val();
            var epassword = $('#epassword').val();
            if(ephone =="" && epassword !="" ){
                return false;
            }else if(ephone !="" && epassword ==""){
                return false;
            }else if(ephone =="" && epassword ==""){
                return false;
            }else{
                $('form').submit();
            }
        });
    })


</script>
