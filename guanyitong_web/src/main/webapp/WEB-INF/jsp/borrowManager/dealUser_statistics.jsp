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
    <title>出借用户注册统计</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/common_css/page.css">
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/borrowManager_js/dealUser_statistics.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">



    <div class="acont-nav">出借用户统计：</div>
    <!--<div class="acont-ahref"><a href="iframe_acon.html">添加新用户</a></div>-->
    <div class="acon-input">
        <div><span>手机号:</span><span><input type="text" id="username"></span></div>
        <div><span>产品名称:</span><span><input type="text" id="NO"></span></div>
        <div><span>身份证号:</span><span><input type="text" id="idCard"></span></div>

        <div class="tjiaoP_a">
            <span>出借时间:</span>
            <span>
                <input type="text" id="startTime" onfocus="MyCalendar.SetDate(this)" class="input-text" style="width: 91px;height: 31px;">
            </span>
            <span>至</span>
            <span>
                <input type="text" id="endTime" onfocus="MyCalendar.SetDate(this)" class="input-text" >
            </span>
        </div>

        <div class="tjiaoP_a">
            <span>出借金额:</span><span>
            <input type="text" id="minMoney" style="width: 91px;height: 31px;"></span>
            <span>至</span>
            <span><input type="text" id="maxMoney"></span>
        </div>



        <div class="soua"><button id="select">搜索</button></div>


    </div>


    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>手机号</th>
                <th>产品名称</th>
                <th>类型</th>
                <th>筹集资金</th>
                <th>预期年化收益</th>
                <th>发布时间</th>
                <th>产品状态</th>
                <th>出借金额</th>
                <th>出借时间</th>
            </tr>
            </thead>
            <tbody id="tbody-result">
            </tbody>

        </table>
    </div>

    <div class="acon-yong">
        <span>统计：</span><span id="count">0</span><span>条</span>
    </div>
    <%--分页--%>
    <div class="fenye">
        <div class="pageTest">
        </div>
        <div class="acon-yong">
            <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每页显示</span><span id="pageSize">3</span><span>条</span>
        </div>
    </div>



</div>
</body>
</html>
