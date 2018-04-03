
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
</head>

<body>

<!--国寿登陆导航-->

<div class="navliu">

</div>


<div class="index-cont">

    <div class="index-cont-left">
        <ul id="accordion" class="accordion accliu">

            <li>

                <div class="link"><i class="fa fa-paint-brush"></i>用户管理<i class="fa fa-chevron-down"></i></div>

                <ul class="submenu">

                    <li onclick="liClick(this)" url="<%=basePath%>toJsp/toborrowUserList.do">借款人认证资料管理</li>

                    <li onclick="liClick(this)" url="iframe_b.html">借款人认证资料审核</li>

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


    <div class="index-cont-right">
        <iframe  id="frame" src="<%=basePath%>toJsp/toborrowUserList.do">	</iframe>
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
        $(".index-cont-right").height(document.documentElement.clientHeight-210);



        $('.submenu>li').click(function(){
            $('.submenu>li').css({'background':'#424f62'});
            $(this).css({'background':'#2A323F'});
        })


    })


</script>