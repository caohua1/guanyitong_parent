
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
    <link rel="stylesheet" href="<%=path%>/css/common_css/xinzeng.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/page.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script src="<%=path%>/js/common_js/page.js"></script>
    <script  src="${ctx}/js/HT_js/permissionManager_js/role_list.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">



    <div class="acont-nav">角色管理：</div>
    <div class="acont-ahref"><a href="<%=basePath%>toJsp/toRoleAdd.do">添加角色</a></div>

    <div class="acon-input">
        <div><span>角色:</span>
            <span><select id="id">
							 <option selected="selected" value="-1">==请选择</option>
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.pname}</option>
                        </c:forEach>
						</select></span>
        </div>

        <div class="soua"><button id="select">搜索</button></div>

    </div>


    <div class="acon-table">
        <table border="1"  border="0" cellspacing="0" cellpadding="0" >
            <thead>
            <tr>
                <th>序号</th>
                <th>角色名称</th>
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

    <div class="showhide">
        <div class="showhideng">
            <span id="x">✖</span>
            <p id="span"></p>
            <div class="quex">
                <div><span id="update">确定</span></div>
                <div><span id="quxiao">取消</span></div>
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

        $('td').css({'padding':'20px 0'});
        $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
        $('.quespan').click(function(){
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
