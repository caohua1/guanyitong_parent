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
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_apprrove1_info.js"></script>
    <style>

    </style>
</head>
<body>
<!--公共的外层-->

<input type="hidden" value="${id}" id="borrowMoneyUserId"/>

<div class="iframe_acont">
    <div class="acont-nav">借款人资料：</div>

    <div class="bbyong"><span>用户ID</span><span>001</span></div>

    <div class="bbifra">
        <div>
            <span>企业名称：</span>
            <span id="companyName">xxxxxxxxxxx</span>
        </div>
        <div>
            <span>成立时间：</span>
            <span id="createTime">xxxxxxxxxxx</span>
        </div>
        <div>
            <span>营业执照号：</span>
            <span id="charterNum">xxxxxxxxxxx</span>
        </div>
        <div>
            <span>注册资本：</span>
            <span id="registerMoney">xxxxxxxxxxx</span>
        </div>
        <div>
            <span>注册地址：</span>
            <span id="registerAddress">xxxxxxxxxxx</span>
        </div>

        <div>
            <span>借款金额：</span>
            <span id="borrowMoney">xxxxxxxxxxx</span>
        </div>

        <div>
            <span>法人手机号：</span>
            <span id="legalPhone">xxxxxxxxxxx</span>
        </div>
        <div>
            <span>法人姓名：</span>
            <span id="legalPersonName">xxxxxxxxxxx</span>
        </div>

        <div>
            <span>营业执照图片：</span>
            <span id="charterImage"><img src="<%=path%>/img/2016012016231516.png"></span>
        </div>

    </div>

    <div class="acont-nav">身份认证：</div>
    <div class="bbifra">
        <div>
            <span>真实姓名：</span>
            <span id="apprroveName">xxxxxxxxxxx</span>
        </div>
        <div>
            <span>法人身份证号：</span>
            <span id="legalIDCard">xxxxxxxxxxx</span>
        </div>
        <div style="width: 80%;" class="faren">
            <div>法人身份证图片：</div>
            <div id="legalIDCardImageZ"><img src="<%=path%>/img/2016012016231516.png">
                <p>正面</p>
            </div>
            <div id="legalIDCardImageF"><img src="<%=path%>/img/2016012016231516.png">
                <p>背面</p>
            </div>
        </div>

    </div>

    <div class="acont-nav">信用认证：</div>
    <div class="bbifra">
        <div>
            <span>征信积分：</span>
            <span id="XYJF">xxxxxxxxxxx</span>
        </div>
        <div>
            <span>征信备注：</span>
            <span id="XYJFDescribe">xxxxxxxxxxx</span>
        </div>
    </div>


    <div class="acont-nav">居住地认证：</div>
    <div class="bbifra">
        <div>
            <span>居住地：</span>
            <span id="address">xxxxxxxxxxx</span>
        </div>

    </div>

    <div class="acont-nav">企业简介：</div>
    <div class="bbjianjie" id="companyDescribe">
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    </div>

    <div class="acont-nav">借款用途：</div>
    <div class="bbjianjie" id="borrowUse">
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    </div>

    <div class="acont-nav">资产信息：</div>
    <div class="bbjianjie" id="moneyInfo">
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    </div>

    <div class="acont-nav">保证信息：</div>
    <div class="bbjianjie" id="ensureInfo">
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    </div>

    <div class="aatj">
        <div><button class="quexeng" >审核通过</button></div>
        <div><a class="fan" href="javascript:history.go(-1)" id="back">返回</a></div>
        <div><button class="quexxiao" >审核不通过</button></div>

    </div>


    <div class="showhide">
        <div class="showhideng">
            <span>✖</span>
            <p>确定是审核人员？</p>
            <div class="quex">
                <div><span id="apprrove_success">确定</span></div>
                <div><span id="cancel">取消</span></div>
            </div>
        </div>
    </div>

    <div class="showtexthide">
        <div class="showhideng">
            <span>✖</span>
            <m>请填写审核不通过原因</m>
            <textarea placeholder="请输入不通过原因" id="causeBy"></textarea>
            <button class="bbque" id="apprrove_no">确定</button>
        </div>
    </div>

</div>
</body>
</html>
<script>
    $(function(){

        $('.fan').css({'color':'#434343'});
        $('.quexeng').click(function(){

            $('.showhide').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
        })

        $('.quexxiao').click(function(){

            $('.showtexthide').show();
        })
        $('.showhideng>span').click(function(){
            $('.showtexthide').hide();
        })

    })
</script>