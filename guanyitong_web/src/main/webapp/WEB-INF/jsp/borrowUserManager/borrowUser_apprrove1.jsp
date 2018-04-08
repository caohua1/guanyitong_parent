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
    <title>借款人认证资料</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_apprrove1.js"></script>
    <style>
        .acon-table{
            display: none;
        }

    </style>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">



    <div class="acont-nav">借款人认证资料审核列表：</div>
    <!--<div class="acont-ahref"><a href="iframe_acon.html">添加新用户</a></div>-->
    <div class="acon-input">
        <div><span>用户ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
        <div><span>真实姓名:</span><span><input type="text" id="apprroveName"></span></div>
        <div><span>身份证号:</span><span><input type="text" id="legalIDCard"></span></div>
        <div><span>企业名称:</span><span><input type="text" id="companyName"></span></div>
        <div class="tjiaoP_a"><span>提交时间:</span><span><input type="text" id="startTime" onfocus="MyCalendar.SetDate(this)" class="input-text"></span>
            <span>至</span><span><input type="text" id="endTime" onfocus="MyCalendar.SetDate(this)" class="input-text"></span></div>

        <%--<div><span>分配状态:</span>
            <span><select>
							<option>==请选择</option>
							<option>==请选择1</option>
							<option>==请选择2</option>
						</select></span>
        </div>--%>

        <div class="soua"><button id="select">搜索</button></div>


    </div>


    <!--审核未通过状态，再修改就是待审核状态-->
    <div class="menli">
        <ul>
            <li class="menlili" value="0">待审核</li>
            <li class="menlili" value="1">审核未通过</li>
            <li class="menlili" value="2">审核通过</li>
        </ul>


    </div>

    <!--待审核-->
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead id="thead_1">
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>真实姓名</th>
                <th>身份证号</th>
                <th>企业名称</th>
                <th>营业执照号</th>
                <th>待审核资料</th>
                <th>提交时间</th>
                <th>状态</th>
                <th>分配人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody-result1">
            </tbody>
        </table>
    </div>
    <!--审核未通过-->
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead id="thead2">
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>真实姓名</th>
                <th>身份证号</th>
                <th>企业名称</th>
                <th>营业执照号</th>
                <th>提交时间</th>
                <th>状态</th>
                <th>分配人</th>
                <th>审核失败原因</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody-result2">

            </tbody>
        </table>
    </div>

    <!--审核通过-->
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead id="thead3">
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>真实姓名</th>
                <th>身份证号</th>
                <th>企业名称</th>
                <th>营业执照号</th>
                <th>提交时间</th>
                <th>状态</th>
                <th>分配人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody-result3">

            </tbody>
        </table>
    </div>
    <div class="acon-yong">
        <span>用户总数：</span><span id="count">0</span>
    </div>
    <%--分页--%>
    <div class="fenye">
        <div class="pageTest">
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
</body>
</html>
<script>
    $(function(){
        $('.menlili').css({'background':'#f3f3f3','width':'150px'});
        $('.menlili').eq(0).css({'color':'red','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'800'});
        $('.menlili').click(function(){
            $('.menlili').css({'color':'#434343','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'100'});
            $(this).css({'color':'red','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'800'});
        })
        $('.acon-table').eq(0).show();
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        $('.quespan').click(function(){
            $('.showhide').show();
        })
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
        })

    })


</script>
<script>
    window.onload=function(){
        var obtn=document.getElementsByClassName('menlili');
        var odivo=document.getElementsByClassName('acon-table');
//			console.log(odivo);
        for(var i=0;i<obtn.length;i++){
            obtn[i].index=i;
            obtn[i].onclick=function(){
//					alert('ok');
                for(var i=0;i<obtn.length;i++){
                    odivo[i].style.display='none';
                }
                odivo[this.index].style.display='block';
            };
        }
    }
</script>