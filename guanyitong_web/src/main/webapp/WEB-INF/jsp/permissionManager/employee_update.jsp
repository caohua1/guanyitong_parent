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
    <title></title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/xinzeng.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/permissionManager_js/employee_update.js"></script>
 <style>
     input{
         color: black;
         border-radius:8px;
         overflow:hidden;
     }
     button{
         color: black;
         border-radius:8px;
         overflow:hidden;
     }
     select{
         color: black;
         border-radius:8px;
         overflow:hidden;
     }

 </style>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">
    <input type="hidden" value="${id}" id="id"/>
    <div class="acont-nav">添加账号：</div>
    <form>
        <div class="angcon">
            <p><span>账号(手机号)：</span><span><input type="text" id="ephone" value="${employee.ephone}"></span></p>
            <p><span>姓名：</span><span><input type="text" id="ename" value="${employee.ename}"></span></p>
            <p><span>角色：</span>
                <span>

							<select id="eroleId">
								 <c:forEach items="${roles}" var="role">
                                     <c:if test="${employee.eroleId == role.id}">
                                         <option selected="selected" value="${role.id}">${role.pname}</option>
                                     </c:if>
                                     <c:if test="${employee.eroleId != role.id}">
                                         <option value="${role.id}">${role.pname}</option>
                                     </c:if>
                                 </c:forEach>
							</select>
						</span>
            </p>


            <!--<p style="height: auto;line-height: 0;"><span></span><spanid="myFile_div"><img src="" id="myImg" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span></p>-->

            <p><span>身份证号：</span><span><input type="text" id="eidcard" value="${employee.eidcard}"></span></p>
            <p><span>修改密码：</span><span><input type="text" id="epassword" value="${employee.epassword}"></span></p>

        </div>

    </form>

    <div class="aatj">
        <div><button id="toupdate">修改</button></div>
        <div><button id="select" onclick="javascript:history.go(-1)">返回</button></div>
    </div>

</div>
</body>
</html>
