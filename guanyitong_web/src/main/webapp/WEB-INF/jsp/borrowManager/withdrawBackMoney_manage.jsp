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
    <title>提现收款（标的列表）列表：</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/borrowManager_js/withdrawBackMoney_list.js"></script>
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



    <div class="acont-nav">提现收款（标的列表）列表：</div>
    <!--<div class="acont-ahref"><a href="iframe_acon.html">添加新用户</a></div>-->
    <div class="acon-input">
        <div><span>用户借款ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
        <div><span>产品名称:</span><span><input type="text" id="NO"></span></div>

        <div class="tjiaoP_a"><span>发布时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startTime"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endTime"></span></div>
        <div class="tjiaoP_a"><span>借款期限:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startBorrowTi"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endBorrowTi"></span></div>
        <div class="tjiaoP_a"><span>借款金额:</span><span><input type="text" id="minBorrowMoney"></span><span>至</span><span><input type="text" id="maxBorrowMoney"></span></div>

        <div><span>体现状态:</span>
            <span><select id="select_id">
                           <option value="-1" selected="selected">请选择</option>
                            <option value="5">筹集完，待提现</option>
							<option value="10">提现申请中</option>
							<option value="11">提现成功（待还款）</option>
							<option value="12">提现失败</option>
						</select></span>
        </div>


        <div class="soua"><button id="select">搜索</button></div>


    </div>


    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>产品名称</th>
                <th>类型</th>
                <th>筹集资金</th>
                <th>借款期限</th>
                <th>发布时间</th>
                <th>借款状态</th>
                <th>体现状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody-result">

            </tbody>
        </table>

    </div>

    <div class="acon-yong">
        <span>统计：</span><span id="count">0</span><span>条</span>
    </div>
    <%--分页--%>
    <div class="fenye">
        <div class="pageTest">
        </div>
        <div class="acon-yong">
            <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每             页显示</span><span id="pageSize">3</span><span>条</span>
        </div>
    </div>

    <div class="showhide">
        <div class="showhideng">
            <span id="x">✖</span>
            <p id="span"></p>
            <div class="quex">
                <div><span id="update">确定</span></div>
                <div><span id="quxiao">取消</span></div>
            </div>
        </div>
    </div>

   <%-- <div class="showhide">
        <div class="showhideng">
            <span>✖</span>
            <p></p>
            <div class="quex">
                <div><span>确定</span></div>
                <div><span>取消</span></div>
            </div>
        </div>
    </div>--%>


</div>
<div class="zhezaocegn"></div>
</body>
</html>
<script>
    $(function(){
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        $('td').css({'padding':'20px 30px'});
        $('.quespann').click(function(){
            $('.showhide').eq(0).show();
            $('.zhezaocegn').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
            $('.zhezaocegn').hide();
        })

        $('.quespannn').click(function(){
            $('.showhide').eq(1).show();
            $('.zhezaocegn').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
            $('.zhezaocegn').hide();
        })
        $('.zhezaocegn').height(document.documentElement.clientHeight);
    })


</script>

