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
    </style>


</head>
<body>

<div class="loginliu">
    <div style="padding-top: 300px;padding-left: 45%">
        <p><b style="color: red;font-size: 20px"> ${message}</b></p>
    </div>
    <form action="<%=basePath%>login.do" commandName="employee" method="post">
        <div class="loadliu">
            <p><span><img src="<%=path%>/img/yong.png"></span><span><input type="text" name="ephone"></span></p>
            <p><span><img src="<%=path%>/img/pass.png"></span><span><input type="password" name="epassword"></span></p>
            <div class="loadliung">
                <span><input type="submit" value="登陆" id="tologin"></span>
                <span><input type="reset" value="取消"></span>
            </div>
        </div>
    </form>

       <%-- <form:form action="emp-save" method="post" modelAttribute="employee">
            <!-- path 属性对应 HTML 表单标签的 name 属性 -->
            LastName：<form:input path="lastName"/> <br>
            Email：<form:input path="email"/> <br>
            Gender：<form:radiobuttons path="gender" items="${genders}"/> <br>
            Department：<form:select path="department" items="${dapartments}" itemLabel="departmentName" itemValue="id"></form:select><br>
            <input type="submit" value="Submit">
        </form:form>--%>

    <%--<form:form action="/login" commandName="user" method="post">
        用户名：<form:input path="ename"/> <form:errors path="ename" cssClass="error"/> <br/>
        密 &nbsp;&nbsp;码：<form:password path="epassword"/> <form:errors path="epassword" cssClass="error" /> <br/>
        <form:button name="button">submit</form:button>
    </form:form>--%>
</div>
</body>
</html>
<script>
    $(function(){
        $('.loginliu').height(document.documentElement.clientHeight);
       /* $("#tologin").click(function(){
            tologin();
        });*/

    })

     /*function tologin(){
         var local = window.location;
         var basePath = local.protocol+"//"+local.host+"/";
         var ephone =  $("#ephone").val();
         var epassword = $("#epassword").val();
         window.location.href = basePath + "login?ephone = "+ephone+"&epassword = "+epassword;
    }
*/
</script>
