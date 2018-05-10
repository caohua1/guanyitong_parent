<%--
  Created by IntelliJ IDEA.
  User: 田梓豪
  Date: 2018/4/3
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
    <title>查看用户银行卡</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/moneyManager_js/borrowUserBank_select.js"></script>
    <style type="text/css">
        .pageTest{ width: 1000px; height: 50px; margin-top: 100px;}
        .activP{
            background-color: #367fa9!important;
            color: #fff!important;
        }
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
    <div class="acont-nav">查看用户银行卡：</div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">用户ID：</span></div>
        <div><span style="color:#FF0000">${userBankcard.borrowMoneyUserId}</span></div>
    </div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">真实姓名：</span></div>
        <div><span style="color:#FF0000">${userBankcard.realName}</span></div>
    </div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">身份证号：</span></div>
        <div>
            <span style="color:#FF0000">${userBankcard.IDCardNumber}</span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">卡号：</span></div>
        <div>
            <span style="color:#FF0000">${userBankcard.cardNo}</span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">开户银行：</span></div>
        <div>
            <span style="color:#FF0000">${userBankcard.bankName}</span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">开户地区：</span></div>
        <div>
            <span style="color:#FF0000">${userBankcard.openAccountRegion}</span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div><span style="font-weight: bold">可联系手机号：</span></div>
        <div>
            <span style="color:#FF0000"> ${userBankcard.phone}</span>
        </div>
    </div>
    <div class="btnlcon">
        <span id="yn">${userBankcard.YN}</span>
        <span id="shuoM">说明： 此银行卡同时为在还款期作为扣款银行卡</span>
    </div>
    <div class="aatj">
        <button id="fhui">返回</button>
    </div>
</div>
</body>
</html>