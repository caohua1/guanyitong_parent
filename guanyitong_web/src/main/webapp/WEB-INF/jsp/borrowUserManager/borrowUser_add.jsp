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
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="<%=path%>/css/common_css/base.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/common_css/css.css" />
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">

    <div class="acont-nav">借款人资料：</div>
    <div class="angcon">
        <p><span>企业名称：</span><span><input type="text"></span></p>
        <p><span>营业执照号：</span><span><input type="text"></span></p>
        <p><span>营业执照图片：</span><span><button>点击上传图片</button></span></p>
        <p><span>法人代表：</span><span><input type="text"></span></p>
        <p><span>法人手机号：</span><span><input type="text"></span></p>
        <p><span>成立时间：</span><span><input type="text"></span></p>
        <p><span>注册资本：</span><span><input type="text"></span></p>
        <p><span>注册地址：</span><span><input type="text"></span></p>
        <p><span>借款金额：</span><span><input type="text"></span></p>
    </div>


    <div class="acont-nav">身份认证：</div>
    <div class="angcon">
        <p><span>真实姓名：</span><span><input type="text"></span></p>
        <p><span>法人身份证号：</span><span><input type="text"></span></p>
        <p><span>法人身份证图片：</span><span><button>点击上传图片</button></span><span><button>点击上传图片</button></span></p>

    </div>


    <div class="acont-nav">信用报告认证：</div>
    <div class="angcon">
        <p><span>征信积分：</span>
            <span><select>
						<option>0</option>
						<option>9</option>
					</select></span>
        </p>
        <div><span>征信备注：</span><span><textarea></textarea></span></div>


    </div>

    <div class="acont-nav">居住地认证：</div>
    <div class="angcon">
        <p><span>居住地：</span><span><input type="text"></span></p>

    </div>
    <div class="acont-nav">企业介绍：</div>
    <div class="angcontext">
        <textarea></textarea>
    </div>

    <div class="acont-nav">借款用途：</div>
    <div class="angcontext">
        <textarea></textarea>
    </div>

    <div class="acont-nav">资产信息：</div>
    <div class="angcontext">
        <button>添加资产信息</button>
    </div>

    <div class="acont-nav">保证信息：</div>
    <div class="angcontext">
        <button>添加保证信息</button>
    </div>


    <div class="aatj">
        <div><button>确定</button></div>
        <div><button>取消</button></div>
    </div>


</div>
</body>
</html>
<%--
$("#add").click(function(){
    var code = $("#code").val();
    var name1 = $("#name1").val();

    $.ajax({
        url:"tianjia.php", //要处理的页面
        data:{c:code,n:name1},  //要传过去的数据
        type:"POST",  //提交方式
        dataType:"TEXT", //返回的数据类型，TEXT字符串 JSON返回JSON XML返回XML；dataType中T要大写！！
        success: function(data){ //回调函数，data为形参，是从login-cl.php页面返回的值
            if(data.trim()=="OK") //trim()去空格
            alert("添加成功");
            else
            alert("添加失败");
        }

    });
})
--%>