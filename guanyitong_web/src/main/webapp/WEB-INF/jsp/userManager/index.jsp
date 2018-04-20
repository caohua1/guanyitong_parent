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

<!--国寿登陆导航-->

<div class="navliu">
    <div><span><img src="img/e.png"></span><span>国寿金融后台管理</span></div>
    <!--<div id="inn"></div>-->
    <div></div>
    <div>
        <!--<span><a href="login.html">登陆</a></span>-->
        <input type="hidden" value="${employee}" id="employee"/>
        <span><span>当前的账号：</span><span>${employee.ephone}</span></span>
        <span id="logout">退出</span>
    </div>

</div>




<div class="index-cont">

    <div class="index-cont-left">
        <ul id="accordion" class="accordion accliu">


  <%--parentNameList--%>
      <c:forEach items="${parentNameList}" var="list">
          <li>
              <div class="link"><i class="fa fa-paint-brush"></i>${list}<i class="fa fa-chevron-down"></i></div>
              <ul class="submenu">
              <c:forEach items="${permissionList}" var="list1">

                  <c:if test="${list1.parentName == list}">
                      <li onclick="liClick(this)" url="<%=basePath%>${list1.permissionUrl}">${list1.permissionName}</li>
                  </c:if>

              </c:forEach>
              </ul>
          </li>
      </c:forEach>
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
                $('')
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