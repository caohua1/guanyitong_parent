<%--
  Created by IntelliJ IDEA.
  User: 田梓豪
  Date: 2018/4/10
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
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
    <title>查看出借人银行卡管理</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/moneyManager_js/lenderManageMent_select.js"></script>
    <style>
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            background-color:gainsboro;
        }
    </style>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">

    <div class="acont-nav">查看出借人银行卡管理：</div>


    <div class="iframe_lcona">
        <div><span style="font-weight: bold">用户ID：</span></div>
        <div>
            <span style="color:#FF0000">${lender.userId}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div><span style="font-weight: bold">真实姓名：</span></div>
        <div>
            <span style="color:#FF0000">${lender.realName}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div><span style="font-weight: bold">身份证号：</span></div>
        <div>
            <span style="color:#FF0000">${lender.idCard}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div><span style="font-weight: bold">卡号：</span></div>
        <div>
            <span style="color:#FF0000">${lender.bankNum}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div><span style="font-weight: bold">开户银行：</span></div>
        <div>
            <span style="color:#FF0000">${lender.bankName}</span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">开户地区：</span></div>
        <div>
            <span style="color:#FF0000">${lender.bankAddress}</span>
        </div>
    </div>
    <div class="aatj">
        <button id="fanH">返回</button>
    </div>
</div>
</body>
</html>