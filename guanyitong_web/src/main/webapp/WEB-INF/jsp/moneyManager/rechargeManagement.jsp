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
    <title>充值记录管理</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script src="${ctx}/js/HT_js/moneyManager_js/rechargeManagement.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="huanyin"><span>财务管理</span><span>/</span><span>充值记录</span></div>
<div class="iframe_acont">
    <div class="acont-nav">充值记录管理页面 :</div>
    <div class="acon-input">
        <div><span style="font-weight: bold">手机号:</span><span><input type="text" id="phone"></span></div>
        <div><span style="font-weight: bold">真实姓名:</span><span><input type="text" id="realname"></span></div>
        <div><span style="font-weight: bold">流水号:</span><span><input type="text" id="serial"></span></div>
        <div class="tjiaoP_a"><span style="font-weight: bold">充值金额:</span><span><input type="text" id="firstMoney"></span><span>至</span><span><input type="text" id="lastMoney"></span></div>
        <div class="tjiaoP_a"><span style="font-weight: bold">充值时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="firstDate"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="lastDate"></span></div>
        <div><span style="font-weight: bold">充值状态:</span>
            <span><select id="select_stu">
                            <option checked="checked" value="2">请选择</option>
							<option value="1">充值成功</option>
							<option value="0">充值失败</option>
			</select></span>
        </div>
        <div class="soua"><button id="select">搜索</button></div>
    </div>
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <th>序号</th>
                <th>手机号</th>
                <th>真实姓名</th>
                <th>流水号</th>
                <th>充值金额</th>
                <th>实际到账金额</th>
                <th>充值时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <tbody id="tbody-result"></tbody>
        </table>
    </div>
    <div class="showhide" style="background-color: #13171b;color: #68677b;border-radius:10px;overflow:hidden">
        <div class="showhideng">
            <span>✖</span>
            <p>确定是审核人员？</p>
            <div class="quex">
                <div><span style="border-radius:10px;overflow:hidden">确定</span></div>
                <div><span style="border-radius:10px;overflow:hidden">取消</span></div>
            </div>
        </div>
    </div>


    <%--分页--%>
    <div class="fenye">
        <div class="pageTest">
        </div>
        <div class="acon-yong">
            <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每页显示</span><span id="pageSize">9</span><span>条</span>
        </div>
    </div>
</div>
</body>
</html>