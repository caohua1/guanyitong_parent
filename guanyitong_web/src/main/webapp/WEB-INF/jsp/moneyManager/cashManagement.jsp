<%--
  Created by IntelliJ IDEA.
  User: 田梓豪
  Date: 2018/4/18
  Time: 16:46
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
    <title>提现管理</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css"/>
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css"/>
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script src="${ctx}/js/HT_js/moneyManager_js/cashManagement.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">
    <div class="acont-nav">提现管理</div>
    <!--<div class="acont-ahref"><a href="iframe_acon.html">添加新用户</a></div>-->
    <div class="acon-input">
        <div><span>用户ID/手机号:</span><span><input type="text" id="userID_name"></span></div>
        <div><span>真实姓名:</span><span><input type="text" id="realName"></span></div>
        <div><span>身份证号:</span><span><input type="text" id="idCard"></span></div>
        <div><span>银行卡号:</span><span><input type="text" id="txNumber"></span></div>
        <div class="tjiaoP_a"><span>提取金额:</span><span><input type="text" id="minMoney"></span><span>至</span><span><input type="text"id="maxMoney"></span></div>
        <div class="tjiaoP_a"><span>申请时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startTime"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endTime"></span></div>
        <div><span>申请人:</span><span><input type="text" id="sqUser"></span></div>
        <div><span>提现人类型:</span>
            <span><select id="select_stu">
							<option checked="checked" value="2">请选择</option>
							<option value="0">出借人</option>
							<option value="1">借款人</option>
						</select></span>
        </div>
        <div class="soua"><button id="sou">搜索</button></div>
    </div>
    <!--审核未通过状态，再修改就是待审核状态-->
    <%--0待提现（体现中），1提现成功，2提现失败--%>
    <div class="menli">
        <ul>
            <li class="menlili" value="3">全部提现列表</li>
            <li class="menlili" value="0">待处理提现</li>
            <li class="menlili" value="1">成功的提现</li>
            <li class="menlili" value="2">失败的提现</li>
        </ul>
    </div>
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <th>序号</th>
                <th>用户ID/手机号</th>
                <th>真实姓名</th>
                <th>银行</th>
                <th>银行卡号</th>
                <th>开户人</th>
                <th>提现金额</th>
                <th>费率</th>
                <th>提现人类型</th>
                <th>提现时间</th>
                <th>申请人</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <tbody id="dataTab"></tbody>
        </table>
        <div class="bbyong">
            <div><span>总提现条数：</span><span id="count"></span></div>
            <div><span>总提现金额：</span><span id="totalJe"></span></div>
        </div>
        <%--分页--%>
        <div class="fenye">
            <div class="pageTest">
            </div>
            <div class="acon-yong">
                <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每页显示</span><span id="pageSize">5</span><span>条</span>
            </div>
        </div>
    </div>
    <div class="showhide" style="background-color: #13171b;color: #68677b;border-radius:10px;overflow:hidden">
        <div class="showhideng">
            <span>✖</span>
            <p>请确定打款成功！</p>
            <div class="quex">
                <div><span style="border-radius:10px;overflow:hidden">确定</span></div>
                <div><span style="border-radius:10px;overflow:hidden">取消</span></div>
            </div>
        </div>
    </div>

    <div class="showhide" style="background-color: #13171b;color: #68677b;border-radius:10px;overflow:hidden">
        <div class="showhideng">
            <span>✖</span>
            <p>请确定打款失败！</p>
            <div class="quex">
                <div><span style="border-radius:10px;overflow:hidden">确定</span></div>
                <div><span style="border-radius:10px;overflow:hidden">取消</span></div>
            </div>
        </div>
    </div>


</div>
<div class="zhezaocegn"></div>
</body>
</html>
