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
    <script  src="${ctx}/js/HT_js/borrowUserBank_js/lenderManageMent.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">

    <div class="acont-nav">查看出借人银行卡管理：</div>


    <div class="iframe_lcona">
        <div>用户ID：</div>
        <div>
            ${lender.userId}
        </div>
    </div>

    <div class="iframe_lcona">
        <div>真实姓名：</div>
        <div>
            ${lender.realName}
        </div>
    </div>

    <div class="iframe_lcona">
        <div>身份证号：</div>
        <div>
            ${lender.idCard}
        </div>
    </div>

    <div class="iframe_lcona">
        <div>卡号：</div>
        <div>
            ${lender.bankNum}
        </div>
    </div>

    <div class="iframe_lcona">
        <div>开户银行：</div>
        <div>
            ${lender.bankName}
        </div>
    </div>

    <div class="iframe_lcona">
        <div>开户地区：</div>
        <div>
            ${lender.bankAddress}
        </div>
    </div>
    <div class="aatj">
        <a href="${ctx}/toJsp/toLenderManageMent.do" style="color: #434343;line-height: 100px;">返回</a>
    </div>
</div>
</body>
</html>