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
    <title>借款明细(未开始筹集，筹集中，筹集完（已经提现，生成还款计划）)</title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/borrowManager_js/borrowMoney_info.js"></script>
</head>
<body>
<div class="iframe_acont">

   <input type="hidden" value="${id}" id="id"/> <%--产品标的id（productInfoId）--%>
    <input type="hidden" value="${status}" id="status"/> <%--产品标的状态--%>
    <div class="acont-nav">借款明细：</div>
    <div class="bbyong">
        <div><span>用户借款ID：</span><span id="borrowMoneyUserId">${borrowMoneyUserId}</span></div>
    </div>

    <div class="bbyong">
        <div><span>产品名称：</span><span id="NO"></span></div>
        <div><span>状态：</span><span id="productStatus"></span></div>
        <div><span>历史年化收益率：</span><span style="margin-left: 20px;font-size: 20px;" id="yield">8.76%</span></div>
    </div>

    <ul class="ulfirame_j">
        <%--根据status判断是否显示 2未开始筹集 4筹集中 5筹集完（未提现） 6筹集失败 7再上架，筹集中
          8下架 9已放弃 10提现申请中 11筹集完（已提现）12提现失败 13 还款中，未还款完成 14还款完）--%>
            <li class="ulfirame_jli" value="1">基本信息</li>

            <c:if test="${status>=4 and status <=10 and status !=9 and status ==12}"><%--筹集中或者筹集完（但没提现）才有出借人列表--%>
                <li class="ulfirame_jli" value="2">出借人列表</li>
            </c:if>
            <c:if test="${status >=11  and status !=12}"><%--提现成功的才有还款计划--%>
               <li class="ulfirame_jli" value="2">出借人列表</li>
               <li class="ulfirame_jli" value="3">还款计划</li>
            </c:if>


    </ul>

    <div class="iframe_ja" id="baseInfo" >



        <div class="iframe_jadiv" >
            <div><span>发布时间：</span><span id="createTime"></span></div>
            <div><span>类型：</span><span id="name"></span></div>
            <div><span>总金额：</span><span id="ZMoney"></span></div>
            <div><span>起诉金额：</span><span >100元</span></div>
            <div><span>还款方式：</span><span id="backMoneyType"></span></div>
            <div><span>当前进度：</span><span id="jinDu"></span></div>
            <div><span>剩余金额：</span><span id="SYMoney"></span></div>
            <div><span>总出借人：</span><span id="ZUserCount"></span></div>
            <div><span>筹标期限：</span><span id="raiseMoneyMonth"></span></div>
            <div><span>借款期限：</span><span id="monthNum"></span></div>
            <div><span>筹标期限是否还息：</span><span id="YesNo"></span></div>

        </div>


        <div class="iframe_jab">
            <div id="moneyUse">资金用途：</div>
            <div >工厂扩张</div>

        </div>

        <div class="iframe_jadiv">
            <div>
                <span >封面：</span>
                <span id="img"><img id="coverImage" src="" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span>
            </div>
        </div>


    </div>


    <!--出借人列表-->
    <div class="iframe_ja" id="dealUserList" >

        <table border="1" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>序号</th>
                <th>出借人账号</th>
                <th>出借金额</th>
                <th>出借时间</th>
            </tr>
            </thead>
           <tbody id="tbody-result1"></tbody>
        </table>

        <div class="acon-yong">
            <span>总计：</span><span id="count">0</span><span>人</span>
        </div>

        <%--分页--%>
        <div class="fenye">
            <div class="pageTest">
            </div>
            <div class="acon-yong">
                <span>第</span><span id="pageNum">1</span><span>页</span><span>/</span><span>总</span><span  id="pageCount">1</span><span>页</span>/<span>每页显示                      </span><span id="pageSize">3</span><span>条</span>
            </div>
        </div>
    </div>

    <!--还款计划-->
    <div class="iframe_ja" id="backMoney" >
        <div class="bbyong">
            <div><span>产品名称：</span><span id="productName"></span></div>
            <div><span>状态：</span><span id="status1"></span></div>
            <div><span>历史年化收益率：</span><span style="margin-left: 20px;font-size: 20px;" id="yield1">8.76%</span></div>
        </div>
        <div class="bbyong">
            <div><span>应还本息：</span><span id="bx">xxxxxxx</span></div>
            <div><span>应还本金：</span><span id="bj">筹集中</span></div>
            <div><span>应还利息：</span><span id="lx">xxx</span></div>
        </div>

        <table style="clear: both;" border="1" cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th>序号</th>
                <th>截止还款日期</th>
                <th>还款本息</th>
                <th>还款利息</th>
                <th>还款本金</th>
                <th>还款时间</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody id="tbody-result2">

            </tbody>
        </table>
    </div>



</div>
</body>
</html>
<script>
    $(function(){
        $('.iframe_ja').eq(0).show();
        $('th').css({'background':'#f3f3f3','padding':'20px 0px'});
        $('td').css({'padding':'20px 80px'});
        $('.ulfirame_jli').eq(0).css({'font-weight':'800'});
        $('.ulfirame_jli').click(function(){
            $('.ulfirame_jli').css({'font-weight':'100'});
            $(this).css({'font-weight':'800'});
        })
    })
</script>
<script>
    window.onload=function(){
        var obtn=document.getElementsByClassName('ulfirame_jli');
        var odivo=document.getElementsByClassName('iframe_ja');
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

