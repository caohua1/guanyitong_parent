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
    <script  src="${ctx}/js/HT_js/moneyManager_js/borrowMoney_info.js"></script>
</head>
<body>
<div class="iframe_acont">

   <input type="text" value="${id}"/>
    <input type="text" value="${status}"/>
    <div class="acont-nav">借款明细：</div>
    <div class="bbyong">
        <div><span>用户ID：</span><span id="borrowMoneyUserId">xxxxxxx</span></div>
    </div>

    <div class="bbyong">
        <div><span>产品名称：</span><span id="NO">xxxxxxx</span></div>
        <div><span>状态：</span><span id="status">筹集中</span></div>
        <div><span>历史年化收益率：</span><span style="margin-left: 20px;font-size: 20px;" id="yield">8.76%</span></div>
    </div>

    <ul class="ulfirame_j">
        <%--根据status判断是否显示 2未开始筹集 4筹集中 5筹集完（未提现）7再上架，筹集中 11筹集完（已提现）--%>
        <li class="ulfirame_jli">基本信息</li>
        <li class="ulfirame_jli">出借人列表</li>
        <li class="ulfirame_jli">还款计划</li>

    </ul>

    <div class="iframe_ja">



        <div class="iframe_jadiv">
            <div><span>发布时间：</span><span>2018.1.12 00:00:00</span></div>
            <div><span>类型：</span><span>xxxx</span></div>
            <div><span>总金额：</span><span>100万元</span></div>
            <div><span>起诉金额：</span><span>100元</span></div>
            <div><span>还款方式：</span><span>按月还本还息</span></div>
            <div><span>当前进度：</span><span>80%</span></div>
            <div><span>剩余金额：</span><span>20万元</span></div>
            <div><span>总出借人：</span><span>120人</span></div>
            <div><span>筹标期限：</span><span>6个月</span></div>
            <div><span>借款期限：</span><span>6个月</span></div>
            <div><span>筹标期限是否还息：</span><span>否</span></div>

        </div>


        <div class="iframe_jab">
            <div>资金用途：</div>
            <div>工厂扩张</div>

        </div>

        <div class="iframe_jadiv">
            <p><span>封面：</span><span><input type="file" name="file" id="myFile" onchange="test()"></span></p>

            <p style="height: auto;line-height: 0;"><span></span><span id="myFile_div"><img src="" id="myImg" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span></p>
        </div>


    </div>


    <!--出借人列表-->
    <div class="iframe_ja">

        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <th>序号</th>
                <th>出借人账号</th>
                <th>出借金额</th>
                <th>出借时间</th>

            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
            </tr>
        </table>

        <div class="acon-yong">
            <span>总计：</span><span>89</span><span>人</span>
        </div>
        <!--分页-->
        <div class="fenye">
            <div class="pageTest"></div>
        </div>
        <div><a style="line-height: 50px;color: #434343;" href="iframe_j.html">返回</a></div>





    </div>

    <!--还款计划-->
    <div class="iframe_ja">
        <div class="bbyong">
            <div><span>产品名称：</span><span>xxxxxxx</span></div>
            <div><span>状态：</span><span>筹集中</span></div>
            <div><span>历史年化收益率：</span><span style="margin-left: 20px;font-size: 20px;">8.76%</span></div>
        </div>
        <div class="bbyong">
            <div><span>应还本息：</span><span>xxxxxxx</span></div>
            <div><span>应还本金：</span><span>筹集中</span></div>
            <div><span>应还利息：</span><span>xxx</span></div>
        </div>

        <table style="clear: both;" border="1" cellpadding="0" cellspacing="0">
            <tr>
                <th>序号</th>
                <th>截止还款日期</th>
                <th>还款本息</th>
                <th>还款利息</th>
                <th>还款本金</th>
                <th>还款时间</th>
                <th>状态</th>
            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
                <td>xxxx</td>
                <td>xxxx</td>
                <td>还款成功</td>
            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
                <td>xxxx</td>
                <td>xxxx</td>
                <td>还款成功</td>
            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
                <td>xxxx</td>
                <td>xxxx</td>
                <td>还款成功</td>
            </tr>
            <tr>
                <td>1</td>
                <td>15256252689</td>
                <td>1,100.00元</td>
                <td>2018.1.12 00:00:00</td>
                <td>xxxx</td>
                <td>xxxx</td>
                <td>还款失败</td>
            </tr>
        </table>

        <div class="acon-yong">
            <span>总计：</span><span>89</span><span>人</span>
        </div>
        <!--分页-->
        <div class="fenye">
            <div class="pageTest"></div>
        </div>
        <div><a style="line-height: 50px;color: #434343;" href="iframe_j.html">返回</a></div>





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
