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
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserBank_js/borrowUserBank_add.js"></script>
    <style type="text/css">
        .pageTest{ width: 1000px; height: 50px; margin-top: 100px;}
        .activP{
            background-color: #367fa9!important;
            color: #fff!important;
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
            <input  id="btnShow" type="text" onkeyup="test(this.value,event)">
            <div class="dianbot">
                <ul id='lists'  onchange="test2()">
                </ul>
            </div>
        </div>
    </div>

    <div class="iframe_lcona">
        <div>真实姓名：</div>
        <div>
            <input type="text">
        </div>
    </div>

    <div class="iframe_lcona">
        <div>身份证号：</div>
        <div>
            <input type="text">
        </div>
    </div>

    <div class="iframe_lcona">
        <div>卡号：</div>
        <div>
            <input type="text">
        </div>
    </div>

    <div class="iframe_lcona">
        <div>开户银行：</div>
        <div>
            <input type="text">
        </div>
    </div>

    <div class="iframe_lcona">
        <div>开户地区：</div>
        <div>
            <input type="text">
        </div>
    </div>
    <div class="iframe_lcona">
        <div>可联系手机号：</div>
        <div>
            <input type="text">
        </div>
    </div>

    <div class="btnlcon">
        <span><input type="checkbox" checked="checked" onclick="return false;"></span>
        <span>同时为在还款期作为扣款银行卡</span>
    </div>

    <div class="aatj">
        <div><button>确定修改</button></div>
        <div><button>取消</button></div>
    </div>

</div>
</body>
</html>
