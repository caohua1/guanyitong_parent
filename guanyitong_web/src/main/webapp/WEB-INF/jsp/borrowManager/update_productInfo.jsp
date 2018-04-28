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
    <title>修改投标</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/borrowManager_js/update_productInfo.js"></script>
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
<input type="hidden" id="id" value="${id}"/>
<div class="iframe_acont">

    <div class="acont-nav">修改投标：</div>
    <!--/*根据输入 的内容匹配选择*/-->
    <div class="angcon">
        <p><span>用户借款ID：</span><span id="borrowMoneyUserId">${productInfos.borrowMoneyUserId}</span></p>
        <p><span>产品名称：</span><span><input type="text" id="NO" value="${productInfos.NO}"></span></p>



        <p><span>预期年化收益：</span><span><input type="text" id="yield" value="${productInfos.yield}"></span></p>

        <p><span>类型：</span>
            <span><select id="productId">

						 <c:forEach items="${products}" var="product">
                             <c:if test="${productInfos.productId==product.id}"><option value="${productInfos.productId}" selected="selected">${product.name}</option></c:if>
                             <option value="${product.id}">${product.name}</option>
                         </c:forEach>
					</select></span>
        </p>
        <p><span>借款金额：</span><span><input type="text" id="ZMoney"  value="<c:if test="${not empty productInfos.ZMoney}">${productInfos.ZMoney}</c:if>
                                                                             <c:if test="${empty productInfos.ZMoney}">${productInfos.ZMoney}</c:if>" disabled></span></p>
        <p><span>还款方式：</span>
            <span><select id="backMoneyType">
                       <c:if test="${productInfos.backMoneyType=='按月还本付息'}"><option value="1" selected="selected">按月还本付息</option></c:if>
                       <c:if test="${productInfos.backMoneyType=='先息后本'}"><option value="2" selected="selected">先息后本</option></c:if>
                       <c:if test="${productInfos.backMoneyType=='一次性还本付息'}"><option value="3" selected="selected">一次性还本付息</option></c:if>
						<option value="1">按月还本付息</option>
                        <option value="2">先息后本</option>
                        <option value="3">一次性还本付息</option>
					</select></span>
        </p>

        <p><span>筹标期限是否还息：</span>
            <span><select id="YesNo">
						<option <c:if test="${productInfos.yesNo=='是'}"> selected="selected"</c:if> value="1">是</option>
                        <option <c:if test="${productInfos.yesNo=='否'}"> selected="selected"</c:if> value="0">否</option>

					</select></span>
        </p>
        <p><span>筹标期限：：</span>
            <span><select id="raiseMoneyMonth">
						<option selected="selected" value="${productInfos.raiseMoneyMonth}">${productInfos.raiseMoneyMonth}</option>
                        <option value="12">12</option>
                        <option value="6">6</option>
                        <option value="3">3</option>
					</select></span>
        </p>
        <p><span>借款期限：</span>
            <span><select id="monthNum">
						<option selected="selected" value="${productInfos.monthNum}">${productInfos.monthNum}</option>
						<option value="12">12</option>
                        <option value="6">6</option>
                        <option value="3">3</option>

					</select></span>
        <p><span>资金用途：</span><span><input type="text" id="moneyUse" value="${productInfos.moneyUse}"></span></p>
        <p><span>起诉资金：</span><span><input type="text" id="QSUse" value="${productInfos.QSUse}"></span></p>

        <div style="line-height: 0;height: auto;">
            <div style="line-height: 50px;height: auto;"><span style="margin-right: 10px;">封面照片:</span><input type="file" name="file"  id="myFile" onchange="upload(this.files)"></div>
            <div id="myFile_div3" style="line-height: 0;height: auto;">
                <img src="http://127.0.0.1${productInfos.coverImage}" id="myImg" style="width: 100px;height: 100px;">
            </div>

        </div>
    </div>

    <div class="aatj">
        <div><button id="update">确定提交</button></div>
        <div><button id="select" onclick="javascript:history.go(-1)">返回</button></div>
    </div>
    

</div>
</body>
</html>
