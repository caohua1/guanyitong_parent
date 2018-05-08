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
    <title>添加标种</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script  src="${ctx}/js/HT_js/borrowManager_js/productInfo_add.js"></script>
    <style>
        input{
            color: black;
            border-radius:8px;
            overflow:hidden;
        }
        select{
            color: black;
            border-radius:8px;
            overflow:hidden;
        }
        .bt{
            color:red;
            font-size: 10px;
        }
    </style>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">

    <div class="acont-nav">借款人资料：</div>
    <!--/*根据输入 的内容匹配选择*/-->
    <form>
        <div class="angcon">
            <p><span>用户借款ID：</span><span><input type="text" id="borrowMoneyUserId" ><span class="bt"> *必填项</span></span></p>
            <p><span>产品名称：</span><span><input type="text" id="NO" ><span class="bt"> *必填项</span></span></p>



            <p><span>预期年化收益：</span><span><input type="text" id="yield" maxlength="5" onkeyup="this.value=this.value.replace(/[^\d\.]/g,'')"><span class="bt"> *必填项</span><span style="color: blue;font-size: 10px" >（只能输入数字和"."号，且最多输入四位有效数字）</span></span></p>

            <p><span>类型：</span>
                <span><select id="productId" >
						<option selected="selected" value="-1">请选择</option>
                    <c:forEach items="${products}" var="product" >
                        <option value="${product.id}">${product.name}</option>
                    </c:forEach>

					</select><span class="bt"> *必填项</span></span>
            </p>
            <p><span>借款金额：</span><span><input type="text" id="ZMoney" disabled><span style="display: none;color: blue;font-size: 10px" id="wu"></span><span class="bt"> *必填项</span></span></p>
            <p><span>还款方式：</span>
                <span><select id="backMoneyType" >
						<option value="-1" selected="selected">请选择</option>
						<option value="1">按月还本付息</option>
                        <option value="2">先息后本</option>
                        <option value="3">一次性还本付息</option>
					</select><span class="bt"> *必填项</span></span>
            </p>

            <p><span>筹标期限是否还息：</span>
                <span><select id="YesNo" >
						<option value="-1" selected="selected">请选择</option>
						<option value="1">是</option>
                        <option value="0">否</option>
					</select><span class="bt"> *必填项</span></span>
            </p>
            <p><span>筹标期限：</span>
                <span><select id="raiseMoneyMonth" >
						<option selected="selected" value="-1">请选择</option>
						<option value="12">12</option>
                        <option value="11">11</option>
                        <option value="10">10</option>
                        <option value="9">9</option>
                        <option value="8">8</option>
                        <option value="7">7</option>
                        <option value="6">6</option>
                        <option value="5">5</option>
                        <option value="4">4</option>
                        <option value="3">3</option>
                        <option value="2">2</option>
                        <option value="1">1</option>
					</select><span class="bt"> *必填项</span></span>
            </p>

            <p><span>筹标时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startRaiseTime"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endRaiseTime"><span class="bt"> *必填项</span></span>
            </p>
            <p><span>借款期限：</span>
                <span><select id="monthNum">
						<option value="-1" selected="selected">请选择</option>
						<option value="12">12</option>
                        <option value="6">6</option>
                        <option value="3">3</option>
					</select><span class="bt"> *必填项</span></span>
            </p>

            <p><span>借款时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startBorrowTime"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endBorrowTime"><span class="bt" >*必填项</span></span>
            </p>

            <p><span>资金用途：</span><span><input type="text" id="moneyUse"></span></p>
            <p><span>起诉资金：</span><span><input type="text" id="QSUse"></span></p>



            <%--<p><span>封面：</span><span><input type="file" name="file" id="myFile" onchange="upload(this)"></span></p>

            <p style="height: auto;line-height: 0;"><span></span><span id="myFile_div"><img src="" id="myImg" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span></p>--%>

            <div style="line-height: 0;height: auto;padding-left: 5.7%">
                <div style="line-height: 50px;height: auto;"><span style="margin-right: 10px;">封面:</span><input type="file" name="file"  onchange="upload(this.files)" ></div>
                <div id="myFile_div2" style="line-height: 0;height: auto;">
                    <img src="" id="myImg" style="display: none;width: 100px;height: 100px;">
                </div>

            </div>
        </div>



        <div class="aatj">
            <div><input type="submit" value="添加投标" id="add"></div>
            <div><input type="reset" value="取消"></div>
        </div>


    </form>

</div>
</body>
</html>
