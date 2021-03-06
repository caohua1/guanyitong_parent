<%--
  Created by IntelliJ IDEA.
  User: Mr_liu
  Date: 2018/3/30
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
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
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_list.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="huanyin"><span>员工管理</span><span>/</span><span>借款人认证资料管理</span></div>
<div class="iframe_acont">



    <div class="acont-nav">借款人列表：</div>
    <div class="acont-ahref"><a href="<%=basePath%>toJsp/toAddBorrowUser.do">添加借款人</a></div>
    <div class="acon-input">
        <div><span>用户借款ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
        <div><span>真实姓名:</span><span><input type="text" id="apprroveName"></span></div>
        <div><span>身份证号:</span><span><input type="text" id="legalIDCard"></span></div>
        <div><span>企业名称:</span><span><input type="text" id="companyName"></span></div>
        <div class="tjiaoP_a">
            <span>提交时间:</span>
            <span>
                <input type="text" id="startTime" onfocus="MyCalendar.SetDate(this)" class="input-text">
            </span>
            <span>至</span>
            <span>
                <input type="text" id="endTime" onfocus="MyCalendar.SetDate(this)" class="input-text">
            </span>
        </div>
        <div><span>分配人:</span>
            <span><select>
							<option>==请选择</option>
							<option>==请选择1</option>
							<option>==请选择2</option>
						</select></span>
        </div>
        <div><span>分配状态:</span>
            <span><select id="select_id">
                <%--0 认证信息待审核  1 认证审核失败 2 认证信息审核成功，借款额度待审核  3 借款额度审核失败  4 借款额度审核成功，合同待确认
                 5 合同确认失败  6 合同确认成功，产品待审核  --%>
                            <option selected="selected" value="-1">==请选择</option>
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


    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>用户ID</th>
                <th>真实姓名</th>
                <th>身份证号</th>
                <th>企业名称</th>
                <th>营业执照号</th>
                <th>待审核资料</th>
                <th>提交时间</th>
                <th>认证审核状态</th>
                <th>额度申请状态</th>
                <th>分配</th>
                <th>操作</th>

            </tr>
            </thead>
            <tbody id="tbody-result">
            </tbody>
        </table>

    </div>

    <div class="acon-yong">
        <span>用户总数：</span><span id = "count">0</span>
    </div>


   <%-- <div class="showhide">
        <div class="showhideng">
            <span>✖</span>
            <p>确定是审核人员？</p>
            <div class="quex">
                <div><span>确定</span></div>
                <div><span>取消</span></div>
            </div>
        </div>
    </div>--%>

    <div class="showhide" style="background-color: #13171b;color: #68677b;border-radius:10px;overflow:hidden">
        <div class="showhideng">
            <span id="x">✖</span>
            <div style="padding-top: 20px">
                <b style="color: red;font-size: 15px;padding-left: 20px">可修改借款金额</b>
            </div>
            <div style="padding-top: 40px;padding-left: 20px">
            借款金额：<input type="text" id="borrowMoney" style="height: 25px"/>
            </div>
            <div class="quex">
                <div><span id="updateMoney" style="border-radius:10px;overflow:hidden">确定</span></div>
                <div><span id="quxiao" style="border-radius:10px;overflow:hidden">取消</span></div>
            </div>
        </div>
    </div>


    <%--分页--%>
    <div class="fenye">
        <div class="pageTest">
        </div>
        <div class="acon-yong">
            <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span id="pageCount">1</span><span>页</span>/<span>每页显示</span><span id="pageSize">3</span><span>条</span>
        </div>
    </div>

</div>
</body>

<div class="zhezaocegn"></div>
</html>
<%--<script>
    $(function(){
        var pageCount = $('#pageCount').text();
        var pageNum = $('#pageNum').text();
        $('.pageTest').page({
            leng:pageCount,
            nowPage:pageNum,
            activeClass: 'activP'  //active 类样式定义
        });
    });
</script>--%>


<script>
    $(function(){
        $('.showhideng>p').css({'top':'12%'});
        $('.showhideng textarea').css({'top':'20%'});
        $('.quex').css({'top':'80%'});
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        /* $('.fangqij').click(function(){
             $('.showhide').show();
             $('.zhezaocegn').show();
         })*/
        $('.showhideng>span').click(function(){
            $('.showhide').hide();
            $('.zhezaocegn').hide();
        })
        $('.zhezaocegn').height(document.documentElement.clientHeight);
    })
</script>

