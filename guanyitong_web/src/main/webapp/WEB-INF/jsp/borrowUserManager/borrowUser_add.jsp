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
    <script src="<%=path%>/js/common_js/jquery-1.8.3.min.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_add.js"></script>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">

    <div class="acont-nav">借款人资料：</div>
    <form>
        <div class="angcon">
            <p><span>企业名称：</span><span><input type="text" id="companyName"></span></p>
            <p><span>营业执照号：</span><span><input type="text" id="charterNum"></span></p>
            <p><span>营业执照图片：</span><span><input type="file" name="file"  onchange="upload1(this.files)"></span></p>

            <p style="height: auto;line-height: 0;"><span></span><span id="myFile_div"><img src="" id="myImg" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span></p>
            <p><span>法人代表：</span><span><input type="text" id="legalPersonName"></span></p>
            <p><span>法人手机号：</span><span><input type="text" id="legalPhone"></span></p>
            <p><span>成立时间：</span><span><input type="text" id="companyCreateTime"></span></p>
            <p><span>注册资本：</span><span><input type="text" id="registerMoney"></span></p>
            <p><span>注册地址：</span><span><input type="text" id="registerAddress"></span></p>
            <p><span>借款金额：</span><span><input type="text" id="borrowMoney"></span></p>
        </div>


        <div class="acont-nav">身份认证：</div>
        <div class="angcon anlin">
            <p><span>真实姓名：</span><span><input type="text" id="apprroveName"></span></p>
            <p><span>法人身份证号：</span><span><input type="text" id="legalIDCard"></span></p>

            <div class="shcuan" style="line-height: 0;height: 0;">
                <div style="line-height: 50px;height: auto;">法人身份证图片：</div>
                <div style="line-height: 0;height: auto;">
                    <div style="line-height: 50px;height: auto;"><span style="margin-right: 10px;">正面照片:</span><input type="file" name="file"  onchange="upload2(this.files)" ></div>
                    <div id="myFile_div2" style="line-height: 0;height: auto;">
                        <img src="" id="myImg2" style="display: none;width: 100px;height: 100px;">
                    </div>

                </div>
                <div style="line-height: 0;height: auto;">
                    <div style="line-height: 50px;height: auto;"><span style="margin-right: 10px;">反面照片:</span><input type="file" name="file"  onchange="upload3(this.files)"></div>
                    <div id="myFile_div3" style="line-height: 0;height: auto;">
                        <img src="" id="myImg3" style="display: none;width: 100px;height: 100px;">
                    </div>

                </div>

            </div>

        </div>


        <div class="acont-nav">信用报告认证：</div>
        <div class="angcon">
            <p><span>征信积分：</span>
                <span><select id="XYJF">
							<option>0</option>
							<option>9</option>
						</select></span>
            </p>
            <div><span>征信备注：</span><span><textarea id="XYJFDescribe"></textarea></span></div>


        </div>

        <div class="acont-nav">居住地认证：</div>
        <div class="angcon">
            <p><span>居住地：</span><span><input type="text" id="address"></span></p>

        </div>
        <div class="acont-nav">企业介绍：</div>
        <div class="angcontext">
            <textarea id="companyDescribe"></textarea>
        </div>

        <div class="acont-nav">借款用途：</div>
        <div class="angcontext">
            <textarea id="borrowUse"></textarea>
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
            <div><button id="toAdd">确定</button></div>
            <div><input type="reset" value="取消"></div>
        </div>
    </form>


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