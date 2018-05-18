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
    <title>借款用户统计</title>


    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/common_css/page.css">
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_statistics.js"></script>
    <style>
        .acon-table{
            display: none;
        }
    </style>

</head>
<body>
<!--公共的外层-->
<div class="huanyin"><span>员工管理</span><span>/</span><span>借款用户统计</span></div>
<div class="iframe_acont">



    <div class="acont-nav">信用额度申请审核：</div>
    <!--<div class="acont-ahref"><a href="iframe_acon.html">添加新用户</a></div>-->
    <div class="acon-input">
        <div><span>用户ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
        <div><span>真实姓名:</span><span><input type="text" id="apprroveName"></span></div>
        <!--<div><span>身份证号:</span><span><input type="text"></span></div>-->
        <div><span>企业名称:</span><span><input type="text" id="companyName"></span></div>
        <div class="tjiaoP_a"><span>提交时间:</span>
            <span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="startTime"></span>
            <span>至</span><span><input type="text" onfocus="MyCalendar.SetDate(this)" class="input-text" id="endTime"></span>
        </div>
        <%--状态（0 认证信息待审核  1 认证审核失败 2 认证信息审核成功，借款额度待审核  3 借款额度审核失败
        4 借款额度审核成功，合同待确认  5 合同确认失败  6 合同确认成功，产品待审核 7待还款  8还款中 9还款完成  --%>
        <div><span>跟踪人员:</span>
            <span><select>
							<option>==请选择</option>
							<option>aaa</option>
							<option>bbb</option>
							<option>ccc</option>
						</select></span>
        </div>


        <div><span>状态:</span>
            <span><select id="select_id">
							<option id="selected" value="-1">==请选择</option>
							<option value="0">认证信息待审核</option>
							<option value="1">认证审核失败</option>
                            <option value="2">认证信息审核成功，借款额度待审核</option>
							<option value="3">借款额度审核失败</option>
                            <option value="4">借款额度审核成功，合同待确认</option>
							<option value="5">合同确认失败</option>
                            <option value="6">合同确认成功，产品待审核</option>

						</select></span>
        </div>

        <div class="soua"><button id="select">搜索</button></div>


    </div>



    <!--待审核-->
    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th> 真实姓名 </th>
                <th> 企业名称 </th>
                <th> 申请额度 </th>
                <th> 申请时间 </th>
                <th> 跟踪人员 </th>
                <th> 认证状态 </th>
                <th> 额度申请状态 </th>
                <th> 合同状态 </th>
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

    <div class="showhide" style="background-color: #13171b;color: #68677b;border-radius:10px;overflow:hidden">
        <div class="showhideng">
            <span>✖</span>
            <p>请确定合同是否已签订？？</p>

            <p><span>可选择上传合同照片：</span><span class="dbtn" >点击上传图片</span></p>

            <div class="quex">
                <div><span style="border-radius:10px;overflow:hidden">确定已签合同</span></div>
                <div><span style="border-radius:10px;overflow:hidden">取消</span></div>
            </div>
        </div>
    </div>

    <div class="zhezaocegn"></div>

</div>
</body>
</html>
<script>
    $(function(){

        $('.quex>div>span').css({'width':'110px'});
        $('.showhideng>p').eq(0).css({'top':'30%','width':'100%','text-align':'center'});
        $('.showhideng>p').eq(1).css({'top':'45%','width':'100%','text-align':'center'});
        $('.showhideng>p').eq(1).find('.dbtn').css({'color':'#08b1f8'});
        $('.menlili').css({'background':'#f3f3f3'});
        $('.menlili').eq(0).css({'color':'red','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'800'});
        $('.menlili').click(function(){
            $('.menlili').css({'color':'#434343','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'100'});
            $(this).css({'color':'red','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'800'});
        })
        $('.acon-table').eq(0).show();
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        $('.hetong').click(function(){
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

        for(var i=0;i<obtn.length;i++){
            obtn[i].index=i;
            obtn[i].onclick=function(){

                for(var i=0;i<obtn.length;i++){
                    odivo[i].style.display='none';
                }
                odivo[this.index].style.display='block';
            };
        }
    }
</script>

