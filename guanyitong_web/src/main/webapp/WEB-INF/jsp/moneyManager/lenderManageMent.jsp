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
    <title>出借人银行卡管理 ：</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/moneyManager_js/lenderManageMent.js"></script>
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
    <div class="acont-nav">出借人银行卡管理 ：</div>
    <!--<div class="acont-ahref"><a href="iframe_lcon.html">添加用户银行卡</a></div>-->
    <div class="acon-input">
        <div><span style="font-weight: bold">手机号:</span><span><input type="text" id="phone"></span></div>
        <div><span style="font-weight: bold">真实姓名:</span><span><input type="text" id="realName"></span></div>
        <div><span style="font-weight: bold">身份证号:</span><span><input type="text" id="idCard"></span></div>
        <div><span style="font-weight: bold">卡号:</span><span><input type="text" id="bankNum"></span></div>

        <div class="tjiaoP_a"><span style="font-weight: bold">绑定时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="firstDate"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="lastDate"></span></div>
        <div class="soua"><button id="select">搜索</button></div>
    </div>
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <th>序号</th>
                <th>手机号</th>
                <th>真实姓名</th>
                <th>身份证号</th>
                <th>开户行</th>
                <th>卡号</th>
                <th>绑定时间</th>
                <th>操作</th>
            </tr>
            <tbody id="tbody-result"></tbody>
        </table>
    </div>
    <div class="acon-yong">
        <span>用户总数：</span><span id = "count">0</span>
    </div>
    <div class="showhide">
        <div class="showhideng">
            <span>✖</span>
            <p>确定是审核人员？</p>
            <div class="quex">
                <div><span>确定</span></div>
                <div><span>取消</span></div>
            </div>
        </div>
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