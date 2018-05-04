<%--
  Created by IntelliJ IDEA.
  User: 田梓豪
  Date: 2018/4/4
  Time: 9:42
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
    <title>添加用户银行卡</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/simpleAlert.css"/>
    <script src="<%=path%>/js/common_js/simpleAlert.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/moneyManager_js/borrowUserBank_add.js"></script>
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
    <div class="acont-nav">添加用户银行卡：</div>
    <div class="iframe_lcona">
        <div>用户ID：</div>
        <div class="dianconl">
            <input  id="btnShow" type="test" onkeyup="dim(this.value,event)"><span><font color="#FF0000">*必填项</font></span>
            <div class="dianbot">
                <ul id='lists'>
                </ul>
            </div>
        </div>
    </div>
    <div class="iframe_lcona">
        <div>真实姓名：</div>
        <div>
            <input type="text" id="realName"><span><font color="#FF0000">*必填项</font></span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div>身份证号：</div>
        <div>
            <input type="text" id="IDCardNumber"><span><font color="#FF0000">*必填项</font></span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div>卡号：</div>
        <div>
            <input type="text" id="cardNo"><span><font color="#FF0000">*必填项</font></span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div>开户银行：</div>
        <div>
            <input type="text" id="bankName"><span><font color="#FF0000">*必填项</font></span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div>开户地区：</div>
        <div>
            <input type="text" id="openAccountRegion"><span><font color="#FF0000">*必填项</font></span>
        </div>
    </div>
    <div class="iframe_lcona">
        <div>可联系手机号：</div>
        <div>
            <input type="text" id="phone"><span><font color="#FF0000">*必填项</font></span>
        </div>
    </div>
    <div class="btnlcon">
        <span><input type="checkbox" checked="checked" onclick="check()" id="YN"></span>
        <span>同时为在还款期作为扣款银行卡</span>
    </div>
    <div class="aatj">
        <div><input type="button" onclick="addUserBank()" value="确定绑定"></div>
        <div><button id="qXiao">取消</button></div>
    </div>
</div>
</body>
</html>