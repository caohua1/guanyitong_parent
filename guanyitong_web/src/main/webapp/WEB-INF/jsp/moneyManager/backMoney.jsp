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
    <title>还款管理：</title>

    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/moneyManager_js/backMoney_list.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="huanyin"><span>财务管理</span><span>/</span><span>还款管理</span></div>
<div class="iframe_acont">
    <div class="acont-nav">还款管理：</div>
    <!--<div class="acont-ahref"><a href="iframe_acon.html">添加新用户</a></div>-->
    <div class="acon-input">
        <div><span style="font-weight: bold">用户ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
        <div><span style="font-weight: bold">真实姓名:</span><span><input type="text" id="realName"></span></div>
        <div><span style="font-weight: bold">身份证号:</span><span><input type="text" id="idcardNumber"></span></div>
        <div><span style="font-weight: bold">开户行:</span><span><input type="text" id="bankName"></span></div>
        <div><span style="font-weight: bold">卡号:</span><span><input type="text" id="cardNo"></span></div>
        <div><span style="font-weight: bold">还款本金:</span><span><input type="text" id="bj"></span></div>
        <div class="tjiaoP_a"><span style="font-weight: bold">还款时间:</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startTime"></span><span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endTime"></span></div>
        <div class="soua"><button id="select">搜索</button></div>
    </div>
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>用户姓名</th>
                <th>身份证号</th>
                <th>开户行</th>
                <th>卡号</th>
                <th>还款本息</th>
                <th>还款利息</th>
                <th>还款本金</th>
                <th>截止还款日期</th>
                <th>还款时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody-result">
            </tbody>
        </table>
    </div>
    <div class="acon-yong">
        <span>用户总数：</span><span id="count">0</span>
    </div>
   <%-- <div class="bbyong">
        <div><span>应还总本息：</span><span>xxxxxxx</span></div>
        <div><span>应还总本金：</span><span>xxx</span></div>
        <div><span>应还总利息：</span><span>xxxx</span></div>
    </div>--%>
    <%--<div class="showhide">
        <div class="showhideng">
            <span>✖</span>
            <p>还款成功？</p>
            <div class="quex">
                <div><span>确定</span></div>
                <div><span>取消</span></div>
            </div>
        </div>
    </div>--%>

    <div class="showhide">
        <div class="showhideng">
            <span id="x">✖</span>
            <p id="text">还款失败？</p>
            <div class="quex">
                <div><span id="toupdate">确定</span></div>
                <div><span id="back">取消</span></div>
            </div>
        </div>
    </div>

    <div class="fenye">
        <div class="pageTest">
        </div>
        <div class="acon-yong">
            <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每页显示</span><span id="pageSize">3</span><span>条</span>
        </div>
    </div>
</div>

<div class="zhezaocegn"></div>
</body>
</html>
<script>
    $(function(){
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        $('td').css({'padding':'20px 10px'});
        $('.quespann').click(function(){
            $('.showhide').eq(0).show();
            $('.zhezaocegn').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
            $('.zhezaocegn').hide();
        })

        $('.quespannn').click(function(){
            $('.showhide').eq(1).show();$('.zhezaocegn').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();$('.zhezaocegn').hide();
        })
        $('.zhezaocegn').height(document.documentElement.clientHeight);
    })


</script>
