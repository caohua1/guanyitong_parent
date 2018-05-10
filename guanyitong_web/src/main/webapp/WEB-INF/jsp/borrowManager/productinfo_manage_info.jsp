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
    <title>查看投标</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/borrowManager_js/productinfo_manage_info.js"></script>
</head>
<body>
<!--公共的外层-->
<input type="hidden" value="${id}" id="id"/>
<input type="text" value="${status}" id="status"/>
<div class="iframe_acont">



    <div class="acont-nav">查看投标：</div>

    <div class="iframen_h">

        <div>
            <div>用户借款ID：</div>
            <div id="borrowMoneyUserId"></div>
        </div>

        <div>
            <div>产品名称：</div>
            <div id="NO"></div>
        </div>

        <div>
            <div>预期年化利益：</div>
            <div id="yield"></div>
        </div>

        <div>
            <div>类型：</div>
            <div id="name"></div>
        </div>

        <div>
            <div>借款金额：</div>
            <div id="ZMoney"></div>
        </div>

        <div>
            <div>还款方式：</div>
            <div id="backMoneyType"></div>
        </div>

        <div>
            <div>筹标期限是否还息：</div>
            <div id="YesNo"></div>
        </div>

        <div>
            <div>筹标期限：</div>
            <div><span id="startRaiseTime">x</span>至<span id="endRaiseTime"></span></div>
        </div>

        <div>
            <div>借款期限：</div>
            <div><span id="startBorrowTime"></span>至<span id="endBorrowTime"></span></div>
        </div>

        <div>
            <div>资金用途：</div>
            <div id="moneyUse"></div>
        </div>

        <div>
            <div>起诉用途：</div>
            <div id="QSUse"></div>
        </div>

        <%-- <div>

            <p><span style="width: 13%;display: inline-block;text-align: right;">封面：</span><span><input type="file" name="file" id="myFile" onchange="upload(this.files)"></span></p>

            <p style="height: auto;line-height: 0;"><span></span><span id="myFile_div"><img src="" id="myImg" style="display: none;width: 100px;height: 100px;margin-left: 90px;"></span></p>
        </div>--%>

        <div>
            <span>封面：</span>
            <span id="img"><img id="coverImage" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span>
        </div>

    </div>

    <div class="showhide" style="background-color: #13171b;color: #68677b;border-radius:10px;overflow:hidden">
        <div class="showhideng">
            <span>✖</span>
            <p>确定是审核人员？</p>
            <div class="quex">
                <div><span id="apprrove_success">确定</span></div>
                <div><span id="cancel">取消</span></div>
            </div>
        </div>
    </div>

    <div class="showtexthide" style="background-color: #13171b;color: #68677b;border-radius:10px;overflow:hidden">
        <div class="showhideng">
            <span>✖</span>
            <m>请填写审核不通过原因</m>
            <textarea placeholder="请输入不通过原因" id="causeBy" style="border-radius:10px;overflow:hidden"></textarea>
            <button class="bbque" id="apprrove_no" style="border-radius:10px;overflow:hidden">确定</button>
        </div>
    </div>




    <div class="aatj">
        <c:if test="${status==0}">
            <div><button class="quexeng"  >审核通过</button></div>
            <div><button class="quexxiao" >审核不通过</button></div>
        </c:if>
        <c:if test="${status==1}">
            <div><button class="quexeng" >再次审核通过</button></div>
            <div><button class="quexxiao" >再次审核不通过</button></div>
        </c:if>
        <div class="soua"><button id="select" onclick="javascript:history.go(-1)">返回</button></div>

    </div>



</div>
</body>
</html>
<%--<script>
    $(function(){
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        $('.quespan').click(function(){
            $('.showhide').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
        })

    })


</script>--%>
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
