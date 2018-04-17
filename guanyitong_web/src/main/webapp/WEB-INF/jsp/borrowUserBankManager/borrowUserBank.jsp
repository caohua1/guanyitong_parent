<%--
  Created by IntelliJ IDEA.
  User: 田梓豪
  Date: 2018/4/2
  Time: 11:07
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
    <title>添加用户银行卡</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script src="${ctx}/js/HT_js/borrowUserBank_js/borrowUserBank_list.js"></script>
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
    <div class="acont-nav">银行卡管理：</div>
    <div class="acont-ahref"><a href="${ctx}/toJsp/toAddBankCard.do">添加用户银行卡</a></div>
    <div class="acon-input">
        <div><span>用户ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
        <div><span>真实姓名:</span><span><input type="text" id="realName"></span></div>
        <div><span>身份证号:</span><span><input type="text" id="IDCardNumber"></span></div>
        <div><span>卡号:</span><span><input type="text" id="cardNo"></span></div>
        <div class="tjiaoP_a"><span>绑定时间:</span><span><input type="text" id="firstDate" onfocus="MyCalendar.SetDate(this)" class="input-text"></span><span>至</span><span><input type="text" id="lastDate" onfocus="MyCalendar.SetDate(this)" class="input-text"></span></div>
        <div class="soua"><button id="select">搜索</button></div>
    </div>
    <div class="acon-table">
        <table  border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>真实姓名</th>
                <th>身份证号</th>
                <th>开户行</th>
                <th>卡号</th>
                <th>作为还款银行卡</th>
                <th>绑定时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="result"></tbody>
        </table>
    </div>

    <div class="acon-yong">
        <span>用户总数：</span><span id = "count">0</span>
    </div>
        <%--分页--%>
        <div class="fenye">
            <div class="pageTest">
            </div>
            <div class="acon-yong">
                <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每页显示</span><span id="pageSize">1</span><span>条</span>
            </div>
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
</div>
</body>
</html>