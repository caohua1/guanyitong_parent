<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>国寿金融</title>
    <link href="<%=path%>/css/common_css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=path%>/css/common_css/sfq.css" media="screen" type="text/css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="shortcut icon" href="<%=path%>/img/e.png" />

    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/index.js"></script>
    <script src="<%=path%>/js/common_js/jquery.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>

</head>

<body>

<!--国寿登陆导航-->

<div class="navliu">
    <div><span><img src="img/e.png"></span><span>国寿金融后台管理</span></div>
    <!--<div id="inn"></div>-->
    <div></div>
    <div>
        <!--<span><a href="login.html">登陆</a></span>-->
        <span><span>当前的账号：</span><span>00000</span></span>
        <span>退出</span>
    </div>

</div>




<div class="index-cont">

    <div class="index-cont-left">
        <ul id="accordion" class="accordion accliu">

            <li>

                <div class="link"><i class="fa fa-paint-brush"></i>员工管理<i class="fa fa-chevron-down"></i></div>

                <ul class="submenu">

                    <li onclick="liClick(this)" url="<%=basePath%>toJsp/toborrowUserList.do">借款人认证资料管理</li>

                    <li onclick="liClick(this)" url="<%=basePath%>toJsp/toborrowUserApprrove1.do">借款人认证资料审核</li>

                    <li onclick="liClick(this)" url="iframe_c.html">借款额度申请审核</li>

                    <li onclick="liClick(this)" url="iframe_d.html">合同管理</li>

                    <li onclick="liClick(this)" url="iframe_f.html">借款用户统计</li>

                    <li onclick="liClick(this)" url="iframe_e.html">出借注册用户统计</li>

                </ul>

            </li>

            <li>

                <div class="link"><i class="fa fa-code"></i>借贷管理<i class="fa fa-chevron-down"></i></div>

                <ul class="submenu">

                    <li onclick="liClick(this)" url="iframe_g.html">标种管理</li>

                    <li onclick="liClick(this)" url="iframe_h.html">投标管理</li>

                    <li onclick="liClick(this)" url="iframe_i.html">投标审核管理</li>

                    <li onclick="liClick(this)" url="iframe_j.html">借款管理</li>

                    <li onclick="liClick(this)" url="iframe_k.html">提现收款管理</li>

                    <li onclick="liClick(this)" url="iframe_kk.html">出借用户统计</li>

                </ul>

            </li>

            <li>

                <div class="link"><i class="fa fa-mobile"></i>财务管理<i class="fa fa-chevron-down"></i></div>

                <ul class="submenu">

                    <li onclick="liClick(this)" url="<%=basePath%>toJsp/toBankCardManage.do">借款人银行卡管理</li>

                    <li onclick="liClick(this)" url="iframe_m.html">出借人银行卡管理</li>

                    <li onclick="liClick(this)" url="iframe_n.html">还款管理</li>

                    <li onclick="liClick(this)" url="iframe_o.html">充值记录</li>

                    <li onclick="liClick(this)" url="iframe_p.html">提现管理</li>

                    <li onclick="liClick(this)" url="irame_q.html">资金账户管理</li>



                </ul>

            </li>



        </ul>

    </div>

    <div class="index-cont-rightt">
        <div class="huanyin"><span>员工管理</span><span>/</span><span>借款人认证资料管理</span></div>
        <!--<div class="jinru"><span>员工管理</span><span>/</span><span>借款人认证资料管理</span></div>-->
        <div class="index-cont-right">


            <iframe  id="frame" src="<%=basePath%>toJsp/toborrowUserList.do">
            </iframe>

        </div>
    </div>

</div>
</body>
</html>
<script>
    $(function(){
        liClick = function (element) {

            var src=$(element).attr("url");
            $("#frame").attr("src",src);
        }
        $(".index-cont-right").height(document.documentElement.clientHeight-140);
        $('.index-cont-left').height(document.documentElement.clientHeight-52);

        $('.index-cont-left').css({'background':'#2A323F'});
        $('.submenu>li').click(function(){
            $('.submenu>li').css({'background':'#424f62'});
            $(this).css({'background':'#2A323F'});
        })

        $(function(){
            $('.indexlogin').click(function(){
                $('.')
            })
        })


    })


</script>

<script language="JavaScript">
    //   function time1(){
    //       var inn=document.getElementById('inn');
    //
    //       var date = new Date();
    //
    //       var month = date.getMonth()+1;
    //       var year=date.getFullYear();
    //       var day=date.getDate();
    //       var week = date.getDay();
    //       var hour = date.getHours();
    //       var min = date.getMinutes();
    //       // var le=min.length;
    //       // alert(le);
    //
    //       var sec = date.getSeconds();
    //       if(min<10){
    //       	min='0'+min;
    //       }
    //       if(sec<10){
    //       	sec='0'+sec;
    //       }
    //       // if()
    //       var week1;
    //
    //       inn.innerHTML=year+'-'+month+'-'+day+' '+hour+':'+min+':'+sec;
    //       var innn=inn.innerHTML;
    //       setTimeout(time1,1000);
    //   }
    //   time1();
</script>