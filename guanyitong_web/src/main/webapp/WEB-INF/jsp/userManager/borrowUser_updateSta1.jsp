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
    <title></title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_updateSta1.js"></script>
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
    <%--//资料认证审核未通过状态，全部字段都可以修改--%>
    <div class="acont-nav">添加借款人：</div>
    <input type="hidden" id="id" value="${borrowMoneyUser.id}"/>
        <input type="hidden" id="status" value="${status}"/>
        <input type="hidden" id="Type" value="${Type}"/>
    <form>
        <div class="angcon">
            <p><span>企业名称：</span><span><input type="text" id="companyName" value="${borrowMoneyUser.companyName}"></span></p>
            <p><span>营业执照号：</span><span><input type="text" id="charterNum" value="${borrowMoneyUser.charterNum}"></span></p>
            <p><span>营业执照图片：</span><span><input type="file" name="file" value="修改图片" onchange="upload1(this.files)"></span></p>

            <p style="height: auto;line-height: 0;"><span></span><span id="myFile_div"><img src="http://127.0.0.1${borrowMoneyUser.charterImage}" id="charterImage" style="width: 100px;height: 100px;margin-left: 15px;"></span></p>
            <p><span>法人代表：</span><span><input type="text" id="legalPersonName" value="${borrowMoneyUser.legalPersonName}"></span></p>
            <p><span>法人手机号：</span><span><input type="text" id="legalPhone" value="${borrowMoneyUser.legalPhone}"></span></p>
            <p><span>成立时间：</span><span><input type="text"  onfocus="MyCalendar.SetDate(this)" class="input-text" id="companyCreateTime" value="${borrowMoneyUser.companyCreateTime}"></span></p>
            <p><span>注册资本：</span><span><input type="text" id="registerMoney" value="${borrowMoneyUser.registerMoney}"></span></p>
            <p><span>注册地址：</span><span><input type="text" id="registerAddress" value="${borrowMoneyUser.registerAddress}"></span></p>
            <p><span>借款金额：</span><span><input type="text" id="borrowMoney" value="${borrowMoneyUser.borrowMoney}"></span></p>
        </div>


        <div class="acont-nav">身份认证：</div>
        <div class="angcon anlin">
            <p><span>真实姓名：</span><span><input type="text" id="apprroveName" value="${borrowMoneyUser.apprroveName}"></span></p>
            <p><span>法人身份证号：</span><span><input type="text" id="legalIDCard" value="${borrowMoneyUser.legalIDCard}"></span></p>

            <div class="shcuan" style="line-height: 0;height: 0;">
                <div style="line-height: 50px;height: auto;">法人身份证图片：</div>
                <div style="line-height: 0;height: auto;">
                    <div style="line-height: 50px;height: auto;"><span style="margin-right: 10px;">正面照片:</span><input type="file" name="file" value="修改图片"  onchange="upload2(this.files)" ></div>
                    <div id="myFile_div2" style="line-height: 0;height: auto;">
                        <img src="http://127.0.0.1${borrowMoneyUser.legalIDCardImageZ}" id="legalIDCardImageZ" style="width: 100px;height: 100px;">
                    </div>

                </div>
                <div style="line-height: 0;height: auto;">
                    <div style="line-height: 50px;height: auto;"><span style="margin-right: 10px;">反面照片:</span><input type="file" name="file"  value="修改图片" onchange="upload3(this.files)"></div>
                    <div id="myFile_div3" style="line-height: 0;height: auto;">
                        <img src="http://127.0.0.1${borrowMoneyUser.legalIDCardImageF}" id="legalIDCardImageF" style="width: 100px;height: 100px;">
                    </div>

                </div>

            </div>

        </div>


        <div class="acont-nav">信用报告认证：</div>
        <div class="angcon">
            <p><span>征信积分：</span>
                <span><select id="XYJF">
                            <option selected="selected" value="${borrowMoneyUser.XYJF}"></option>
							<option>0</option>
							<option>9</option>
						</select></span>
            </p>
            <div><span>征信备注：</span><span><textarea id="XYJFDescribe">${borrowMoneyUser.XYJFDescribe}</textarea></span></div>


        </div>

        <div class="acont-nav">居住地认证：</div>
        <div class="angcon">
            <p><span>居住地：</span><span><input type="text" id="address" value="${borrowMoneyUser.address}"></span></p>

        </div>
        <div class="acont-nav">企业介绍：</div>
        <div class="angcontext">
            <textarea id="companyDescribe">${borrowMoneyUser.companyDescribe}</textarea>
        </div>

        <div class="acont-nav">借款用途：</div>
        <div class="angcontext">
            <textarea id="borrowUse">${borrowMoneyUser.borrowUse}</textarea>
        </div>

        <div class="acont-nav">资产信息：</div>
        <div class="angcontext">
            <textarea id="moneyInfo">${borrowMoneyUser.moneyInfo}</textarea>
        </div>

        <div class="acont-nav">保证信息：</div>
        <div class="angcontext">
            <textarea id="ensureInfo">${borrowMoneyUser.ensureInfo}</textarea>
        </div>


        <div class="aatj">
            <div><botton  id="update">修改</botton></div>
            <div><input type="reset" value="取消"></div>
            <div><button id="back" >返回</button></div>
        </div>
    </form>


</div>
</body>
</html>

