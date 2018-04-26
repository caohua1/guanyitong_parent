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
    <title>借款人合同审核（详情查看）</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_apprrove3_info.js"></script>
    <style>
        .faren>div:nth-child(1){
            text-align: left;
        }

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
<input type="hidden" value="${id}" id="borrowMoneyUserId"/>
<input type="hidden" value="${status}" id="status1"/>
<div class="iframe_acont">
    <div class="acont-nav">借款人资料：</div>

    <div class="bbyong">
        <div><span>用户借款ID</span> <span id="id">001</span></div>

    </div>

    <div class="bbyong">
        <div><span>✔</span>  <span>认证资料审核：</span><span id="status">已通过</span></div>
        <div><span>✔</span>  <span>额度申请审核：</span><span id="status_money">已通过</span></div>
        <div><span>✔</span>  <span>合约: </span><span id="status_heto">已签约</span></div>
        <div>
            <span>查看合同照片：</span>
            <span id="img1" ><img id="contractImage" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span>
        </div>
        <div><span>申请额度：</span><span id="borrowMoney"></span></div>
    </div>
    <div class="acont-nav">企业信息：</div>
    <div class="bbifra">
        <div>
            <span>企业名称：</span>
            <span id="companyName"></span>
        </div>
        <div>
            <span>成立时间：</span>
            <span id="companyCreateTime"></span>
        </div>
        <div>
            <span>营业执照号：</span>
            <span id="charterNum"></span>
        </div>
        <div>
            <span>注册资本：</span>
            <span id="registerMoney"></span>
        </div>
        <div>
            <span>注册地址：</span>
            <span id="registerAddress"></span>
        </div>

        <!--<div>
            <span>借款金额：</span>
            <span>xxxxxxxxxxx</span>
        </div>-->

        <div>
            <span>法人手机号：</span>
            <span id="legalPhone"></span>
        </div>
        <div>
            <span>营业执照图片：</span>
            <span id="img2"><img id="charterImage" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span>
        </div>
        <div>
            <span>法人姓名：</span>
            <span id="legalPersonName"></span>
        </div>



    </div>

    <div class="acont-nav">身份认证：</div>
    <div class="bbifra">
        <div>
            <span>真实姓名：</span>
            <span id="apprroveName"></span>
        </div>
        <div>
            <span>法人身份证号：</span>
            <span id="legalIDCard"></span>
        </div>

            <div style="width: 80%;" class="faren">
                <div>法人身份证图片：</div>
                <div id="img3"><img id="legalIDCardImageZ" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;">
                    <p>正面</p>
                </div>
                <div id="img4"><img id="legalIDCardImageF" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;">
                    <p>背面</p>
                </div>
            </div>


    </div>

    <div class="acont-nav">信用报告认证：</div>
    <div class="bbifra">
        <div>
            <span>征信积分：</span>
            <span id="XYJF"></span>
        </div>
        <div>
            <span>征信备注：</span>
            <span id="XYJFDescribe"></span>
        </div>
    </div>


    <div class="acont-nav">居住地认证：</div>
    <div class="bbifra">
        <div>
            <span>居住地：</span>
            <span id="address"></span>
        </div>

    </div>

    <div class="acont-nav">企业简介：</div>
    <div class="bbjianjie" id="companyDescribe">

    </div>

    <div class="acont-nav">借款用途：</div>
    <div class="bbjianjie" id="borrowUse">

    </div>

    <div class="acont-nav">资产信息：</div>
    <div class="bbjianjie" id="moneyInfo">

    </div>

    <div class="acont-nav">保证信息：</div>
    <div class="bbjianjie" id="ensureInfo">

    </div>

    <div class="aatj">
        <c:if test="${status==4}">
            <div><button class="quexeng">审核通过</button></div>
            <div><button class="quexxiao" style="width: 100px;">审核不通过</button></div>
        </c:if>
        <c:if test="${status==5}">
            <div><button class="quexeng">审核通过</button></div>
            <div><button class="quexxiao" style="width: 100px;">审核不通过</button></div>
        </c:if>
        <div>
            <button id="back">返回</button>
        </div>



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
//		$('.quexeng').click(function(){
//
//			$('.showhide').show();
//		})
//		$('.showhideng>span').click(function(){
//			$('.showhide').hide();
//		})
//
//		$('.quexxiao').click(function(){
//
//			$('.showtexthide').show();
//		})
//		$('.showhideng>span').click(function(){
//			$('.showtexthide').hide();
//		})

    })
</script>

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
