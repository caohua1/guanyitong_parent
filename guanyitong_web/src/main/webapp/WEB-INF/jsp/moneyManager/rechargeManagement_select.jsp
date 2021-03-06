<%--
充值记录管理页面
  Created by IntelliJ IDEA.
  User: 田梓豪
  Date: 2018/4/11
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>充值明细</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="${ctx}/js/HT_js/moneyManager_js/rechargeManagement_select.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">
    <div class="acont-nav">充值明细：</div>
<c:forEach items="${rechargeSheet}" var="recharge">
    <div class="iframe_lcona">
        <div style="font-weight: bold">手机号：</div>
        <div><span style="color: #FF0000">${recharge.username}</span></div>



    </div>

    <div class="iframe_lcona">
        <div style="font-weight: bold">真实姓名：</div>
        <div>
            <span style="color: #FF0000"> ${recharge.realName}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div style="font-weight: bold">身份证号：</div>
        <div>
            <span style="color: #FF0000">${recharge.idCard}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div style="font-weight: bold">充值卡号：</div>
        <div>
            <span style="color: #FF0000">${recharge.bankNum}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div style="font-weight: bold">充值金额：</div>
        <div>
            <span style="color: #FF0000">${recharge.rechargeMoney}</span>
        </div>
    </div>

    <div class="iframe_lcona">
        <div style="font-weight: bold">实际到账金额：</div>
        <div>
            <span style="color: #FF0000">${recharge.dzMoney}</span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div style="font-weight: bold">充值时间：</div>
        <div>
            <span style="color: #FF0000">${recharge.rechargeTime}</span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div style="font-weight: bold">流水账号：</div>
        <div>
            <span style="color: #FF0000">${recharge.serial}</span>
        </div>
    </div>
    <!--<div class="btnlcon">

        <span>说明： 此银行卡同时为在还款期作为扣款银行卡</span>
    </div>-->
</c:forEach>
    <div class="aatj">
        <button id="back">返回</button>
    </div>

</div>
</body>
</html>
