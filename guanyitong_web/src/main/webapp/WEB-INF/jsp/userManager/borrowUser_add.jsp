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
    <script src="<%=path%>/js/common_js/mydate.js"></script>
    <script  src="${ctx}/js/HT_js/borrowUserManager_js/borrowUser_add.js"></script>
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
        .pick-area{display: inline-block;}
        .angcon a{float:left;margin:10px;}
    </style>
</head>
<body>
<!--公共的外层-->
<div class="iframe_acont">

    <div class="acont-nav">添加借款人：</div>
    <form>
        <div class="angcon">
            <p><span>企业名称：</span><span><input type="text" id="companyName"></span><span class="bt"> *必填项</span></p>
            <p><span>营业执照号：</span><span><input type="text" id="charterNum"></span><span class="bt"> *必填项</span></p>
            <p><span>营业执照图片：</span><span><input type="file" name="file"  onchange="upload1(this.files)"></span><span class="bt"> *必填项</span></p>

            <p style="height: auto;line-height: 0;"><span></span><span id="myFile_div"><img src="" id="myImg" style="display: none;width: 100px;height: 100px;margin-left: 15px;"></span></p>
            <p><span>法人代表：</span><span><input type="text" id="legalPersonName"></span><span class="bt"> *必填项</span></p>
            <p><span>法人手机号：</span><span><input type="text" id="legalPhone" maxlength="11" onkeyup="this.value=this.value.replace(/^1[3|4|5|7|8]\d{10}$/g,'')" onblur="if(this.value.length<11){value='请输入正确的手机号码'}"></span><span class="bt"> *必填项</span><span style="color: blue;font-size: 10px">(输入11位手机号码)</span></p>
            <p><span>成立时间：</span><span><input type="text"  onfocus="MyCalendar.SetDate(this)" class="input-text" id="companyCreateTime"></span><span class="bt"> *必填项</span></p>
            <p><span>注册资本：</span><span><input type="text" id="registerMoney"></span><span class="bt"> *必填项</span></p>
            <p><span>注册地址：</span><span><input type="text" id="registerAddress"></span><span class="bt"> *必填项</span></p>
            <p><span>借款金额：</span><span><input type="text" id="borrowMoney" maxlength="8" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"></span><span class="bt"> *必填项</span><span style="color: blue;font-size: 10px">(只能输入数字,不能超过10000000)</span></p>
        </div>


        <div class="acont-nav">身份认证：</div>
        <div class="angcon anlin">
            <p><span>真实姓名：</span><span><input type="text" id="apprroveName"></span><span class="bt"> *必填项</span></p>
            <p><span>法人身份证号：</span><span><input type="text" id="legalIDCard" onblur="if((/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/).test(this.value)==false){value='身份证号的格式不正确'}"></span><span class="bt"> *必填项</span><span style="color: blue;font-size: 10px">(请输入15位或18位的身份证号,只能输入数字和字母)</span></p>

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
            <span class="bt"> *必填项</span>
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
           <p><span>居住地：</span><span><input type="text" id="address"></span><span class="bt"> *必填项</span></p>


        </div>

        <div class="acont-nav">企业介绍：</div>
        <div class="angcontext">
            <textarea class="area" id="companyDescribe" maxlength="200" placeholder="请输入文本内容"></textarea>
            <p style="padding-left: 220px;color: blue;font-size: 10px">最多可输入200字: 剩余:<span class="text_count" style="color: red"> 200</span>/200</p>
        </div>

        <div class="acont-nav">借款用途：</div>
        <div class="angcontext">
            <textarea class="area1" id="borrowUse" placeholder="请输入文本内容"></textarea>
            <p style="padding-left: 220px;color: blue;font-size: 10px">最多可输入200字: 剩余:<span class="text_count1" style="color: red"> 200</span>/200</p>
        </div>

        <div class="acont-nav">资产信息：</div>
        <div class="angcontext">
            <button id="toAddMoneyInfo">添加资产信息</button>
            <%--<textarea id="moneyInfo" style="display: none"></textarea>--%>
        </div>

        <div class="acont-nav">保证信息：</div>
        <div class="angcontext">
            <button id="toAddEnsureInfo">添加保证信息</button>
            <%--<textarea id="ensureInfo" style="display: none"></textarea>--%>
        </div>


        <div class="aatj">
            <div><input type="submit" value="添加借款人" id="add"></div>
            <div><input type="reset" value="取消"></div>
        </div>
    </form>


</div>
</body>
</html>
<script>
    $(".area").on("input propertychange",function(){
        var $this = $(this),
            _val = $(this).val(),
            count = "";
        if(_val.length>200){
            $this.val(_val.substring(0,200));
        }
        count= 200-$this.val().length;
        $(".text_count").text(count);
    });

    $(".area1").on("input propertychange",function(){
        var $this = $(this),
            _val = $(this).val(),
            count = "";
        if(_val.length>200){
            $this.val(_val.substring(0,200));
        }
        count= 200-$this.val().length;
        $(".text_count1").text(count);
    });


</script>
