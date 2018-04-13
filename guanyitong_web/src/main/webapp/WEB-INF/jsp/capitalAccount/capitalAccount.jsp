<%--
  Created by IntelliJ IDEA.
  User: 田梓豪
  Date: 2018/4/12
  Time: 18:08
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
    <title>资金账户管理 ：</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/capitalAccount_js/capitalAccount.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">



    <div class="acont-nav">资金账户管理 ：</div>

    <div class="acon-input">



        <div class="tjiaoP_a"><span>开通时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startTime"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endTime"></span></div>
        <div class="tjiaoP_a"><span>可用金额:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="minyuE"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="maxyuE"></span></div>

        <div class="tjiaoP_a"><span>累计收益:</span><span><input type="text" id="minLJSY"></span><span>至</span><span><input type="text" id="maxLJSY"></span></div>


        <div class="soua"><button id="select">搜索</button></div>


    </div>


    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <th>序号</th>
                <th>手机号</th>
                <th>银行卡号</th>
                <th>开户人</th>
                <th>账户总金额</th>
                <th>可用金额</th>
                <th>保护器金额</th>
                <th>体现中金额</th>
                <th>待回收金额</th>
                <th>累计收益</th>
                <th>开通时间</th>
                <th>操作</th>
            </tr>
            <tbody id="table"></tbody>
        </table>
        <!--分页-->
        <div class="fenye">
            <div class="pageTest"></div>
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
<script type="text/javascript" src="js/page.js"></script>
<script type="text/javascript">
    $('.pageTest').page({
        leng: 66,//分页总数
        activeClass: 'activP' , //active 类样式定义
        clickBack:function(page){
            console.log(page)
        }
    })
    // $('.pageTest').setLength(10)
</script>
