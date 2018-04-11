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
    <title>借款额度审核（详情查看）</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_apprrove2_info.js"></script>
    <style>
        .faren>div:nth-child(1){
            text-align: left;
        }
    </style>
</head>
<body>
<!--公共的外层-->
<input type="hidden" value="${id}" id="borrowMoneyUserId"/>
<div class="iframe_acont">
    <div class="acont-nav">借款人资料：</div>

    <div class="bbyong">
        <div><span>用户借款ID：</span><span id="id">001</span></div>
        <div><span>认证资料审核：</span><span id="status1">已通过</span></div>
        <!--<div><span>额度申请审核</span><span>已通过</span></div>-->
    </div>

    <div class="bbyong">
        <div><span>原来额度：</span><span id="before_borrowMoney">xxxx</span></div>
        <div><span>申请额度：</span><span id="borrowMoney">xxxx</span></div>
        <div><span>还款状态：</span><span id="status2">还款中</span></div>
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
            <span ><img id="charterImage" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span>
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
            <div><img id="legalIDCardImageZ" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;">
                <p>正面</p>
            </div>
            <div><img id="legalIDCardImageF" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;">
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
        <div><button class="quexeng">审核通过</button></div>
        <div><a class="fan" href="javascript:history.go(-1)">返回</a></div>
        <div><button class="quexxiao">审核不通过</button></div>

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

<script>
    function test(){
        var selectedFile = $('#myFile').get(0).files[0];
        var reader = new FileReader();
        reader.readAsDataURL(selectedFile);
        reader.onload = function(e){
            var urlsrc = this.result;
            $('#myImg').show();
            $('#myImg').attr('src', urlsrc);
        }
        console.log(selectedFile);
    }
    function test2(){
        var selectedFile = $('#myFile2').get(0).files[0];
        var reader = new FileReader();
        reader.readAsDataURL(selectedFile);
        reader.onload = function(e){
            var urlsrc = this.result;
            $('#myImg2').show();
            $('#myImg2').attr('src', urlsrc);
        }
        console.log(selectedFile);
    }
    function test3(){
        var selectedFile = $('#myFile3').get(0).files[0];
        var reader = new FileReader();
        reader.readAsDataURL(selectedFile);
        reader.onload = function(e){
            var urlsrc = this.result;
            $('#myImg3').show();
            $('#myImg3').attr('src', urlsrc);
        }
        console.log(selectedFile);
    }

</script>