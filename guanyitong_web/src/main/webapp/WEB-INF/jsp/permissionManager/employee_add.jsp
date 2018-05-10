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
    <title></title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/xinzeng.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/permissionManager_js/employee_add.js"></script>
   <style>
       input{
           color: black;
           border-radius:8px;
           overflow:hidden;
       }
       button{
           color: black;
           border-radius:8px;
           overflow:hidden;
       }
       select{
           color: black;
           border-radius:8px;
           overflow:hidden;
       }
       .bt{
           color:red;
           font-size: 10px;
       }
   </style>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">

    <div class="acont-nav">添加账号：</div>
    <form>
        <div class="angcon">
            <p><span>账号(手机号)：</span><span><input type="text" id="ephone"></span><span class="bt"> *必填项</span></p>
            <p><span>姓名：</span><span><input type="text" id="ename"></span><span class="bt"> *必填项</span></p>
            <p><span>角色：</span>
                <span>

							<select id="eroleId">
								<option selected="selected" value="-1">请选择==</option>
								 <c:forEach items="${roles}" var="role">
                                     <option value="${role.id}">${role.pname}</option>
                                 </c:forEach>
							</select><span class="bt"> *必填项</span>
						</span>
            </p>


            <!--<p style="height: auto;line-height: 0;"><span></span><spanid="myFile_div"><img src="" id="myImg" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span></p>-->

            <p><span>身份证号：</span><span><input type="text" id="eidcard"></span><span class="bt"> *必填项</span></p>
            <p><span>设置密码：</span><span><input type="text" id="epassword"></span><span class="bt"> *必填项</span></p>
            <p><span>确认密码：</span><span><input type="text" id="epassword1"></span><span class="bt"> *必填项</span></p>

        </div>


        <div class="aatj">
            <div><button id="toadd">确定</button></div>
            <div><input type="reset" value="取消"></div>
        </div>
    </form>


</div>
</body>
</html>
