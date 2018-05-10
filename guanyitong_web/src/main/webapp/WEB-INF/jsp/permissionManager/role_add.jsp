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
    <script  src="${ctx}/js/HT_js/permissionManager_js/role_add.js"></script>
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
    .bt{
        color:red;
        font-size: 10px;
    }
</style>
</head>
<body>


<div class="iframe_acont">

    <div class="acont-nav">添加角色：</div>
    <form>
        <div class="angcon">
            <p><span>角色名称：</span><span><input type="text" id="pname"></span><span class="bt"> *必填项</span></p>
        </div>

        <div class="quanxian1">选择权限</div>
       <%-- <c:forEach items="${permissions}" var="permission">
            <div class="juese">
                <label for="scqx" class="juesee"><input type="checkbox" name="scqx" >${permission.permissionName}</label>
                <c:forEach items="${permission.map.get('permission')}" var="list">
                    <label><input type="checkbox"  class="auth_rules" />${list.permissionName}</label>
                </c:forEach>
            </div>

        </c:forEach>--%>

        <ul class="categories" style="padding-left: 90px;padding-top: 20px">
            <c:forEach items="${permissions}" var="permission">
                <li><input type="checkbox" name="lvl2" value="${permission.id}"/>${permission.permissionName}
                    <ul style="padding-left: 70px;padding-top: 15px;padding-bottom: 15px">
                    <c:forEach items="${permission.map.get('permission')}" var="list">
                            <li style="padding-top: 3px"><input type="checkbox" name="lvl3" value="${list.id}"/>${list.permissionName}</li>
                    </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>

        <div class="quanxian">请填写角色名称并选择至少一个权限!</div>


        <div class="aatj">
            <div><button id="add">确定</button></div>
            <div><input type="reset" value="取消"></div>
        </div>
    </form>


</div>

</body>
</html>



