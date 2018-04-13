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
    <title>投标审核管理</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/common_css/page.css">
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/borrowManager_js/borrowMoney_manage.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">



    <div class="acont-nav">借款管理：</div>

    <div class="acon-input">
        <div><span>用户借款ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
        <div><span>产品名称:</span><span><input type="text" id="NO"></span></div>
        <div class="tjiaoP_a"><span>借款期限:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startBorrowTime"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endBorrowTime"></span></div>

        <div class="tjiaoP_a"><span>发布时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text"></span></div>
        <div><span>还款方式:</span>
            <span><select id="backMoneyType">
							<option selected="selected" value="-1">==请选择</option>
							<option value="0">按月还本还息</option>
							<option value="1">先息后本</option>
                            <option value="2">一次性还本付息</option>
						</select></span>
        </div>
        <div><span>审核状态:</span>
            <span><select id="select_id">
							<option selected="selected" value="-1">==请选择</option>
							<option value="0">待审核</option>
							<option value="1">审核未通过</option>
                            <option value="2">审核通过</option>
						</select></span>
        </div>

        <div class="soua"><button id="select">搜索</button></div>

    </div>
    <!--标的审核通过之前是投标，审核通过后借款列表-->
    <!--审核未通过状态，在修改就是待审核状态-->

    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>产品名称</th>
                <th>类型</th>
                <th>借款金额</th>
                <th>预期年化收益</th>
                <th>筹标期限</th>
                <th>借款期限</th>
                <th>还款方式</th>
                <th>发布时间</th>
                <th>状态</th>
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
            <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每页显示</span><span id="pageSize">3</span><span>条</span>
        </div>
    </div>

    <div class="showhide">
        <div class="showhideng">
            <span>✖</span>
            <p>确定是审核人员？</p>
            <div class="quex">
                <div><span>确定</span></div>
                <div><span>取消</span></div>
            </div>
        </div>
    </div>


</div>

<div class="zhezaocegn"></div>
</body>
</html>
<script>
    $(function(){

        $('.showhideng>p').css({'top':'12%'});
        $('.showhideng textarea').css({'top':'20%'});
        $('.quex').css({'top':'80%'});
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        $('.fangqij').click(function(){
            $('.showhide').show();
            $('.zhezaocegn').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
            $('.zhezaocegn').hide();
        })
        $('.zhezaocegn').height(document.documentElement.clientHeight);

    })


</script>
<script type="text/javascript" src="js/page.js"></script>
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