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
    <title>资金账户管理明细 ：</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/moneyManager_js/capitalAccount_select.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">
    <div class="acont-nav">资金账户管理 ：</div>
    <div class="bbyong">
        <div><span id="id">${moneyManage.userId}</span></div>
        <div><span>手机号：</span><span>${moneyManage.username}</span></div>
        <div><span>银行卡号：</span><span>${moneyManage.bankNum}</span></div>
        <div><span>开户人：</span><span>${moneyManage.realName}</span></div>
    </div>



    <div class="acon-table" style="margin-bottom: 50px;">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <tr>
                <th>账户总金额</th>
                <th>可用金额</th>
                <th>保护期金额</th>
                <th>提取中金额</th>
                <th>待回收金额</th>
            </tr>
            <tr>
                <td>${moneyManage.ZMoney}</td>
                <td>${moneyManage.yuE}</td>
                <td>${moneyManage.BHMoney}</td>
                <td>${moneyManage.TQZMoney}</td>
                <td>${moneyManage.DHKMoney}</td>
            </tr>
        </table>
    <div style="border-top: 1px solid #ccc;"></div>


    <div class="acon-input">



        <div class="tjiaoP_a"><span>时间:</span><span><input type="text" id="startTime" onfocus="MyCalendar.SetDate(this)"></span><span>至</span><span><input type="text" id="endTime" onfocus="MyCalendar.SetDate(this)"></span></div>
        <div><span>收支类型:</span>
            <span><select id="selectSZ">
							<option value="-1">==请选择</option>
							<option value="0">收入</option>
							<option value="1">支出</option>
						</select></span>
        </div>
        <div><span>类型:</span>
            <span><select id="selectLX">
                            <option value="-1">===请选择</option>
							<option value="2">充值</option>
							<option value="3">回款</option>
							<option value="4">出借</option>
						</select></span>
        </div>
        <div class="soua"><button id="sous">搜索</button></div>
    </div>
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" id="tab">
            <thead>
            <tr>
                <th>时间</th>
                <th>存入</th>
                <th>支出</th>
                <th>明细</th>
                <th>类型</th>
            </tr>
            </thead>
            <tbody id="dataBody"></tbody>
        </table>
        <div class="acon-yong">
            <span>用户总数：</span><span id = "count">0</span>
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



</div>

</body>
</html>
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

