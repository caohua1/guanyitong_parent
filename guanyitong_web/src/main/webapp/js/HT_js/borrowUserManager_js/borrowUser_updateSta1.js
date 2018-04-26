
var param = {
    id :null,
    companyName : null,
    charterNum : null,
    charterImage : null,
    legalPersonName : null,
    legalPhone : null,
    companyCreateTime : null,
    registerMoney : null,
    registerAddress : null,
    borrowMoney : null,
    apprroveName : null,
    legalIDCard : null,
    legalIDCardImageZ : null,
    legalIDCardImageF : null,
    XYJF : null,
    XYJFDescribe : null,
    address : null,
    companyDescribe : null,
    borrowUse : null,
    moneyInfo : null,
    ensureInfo :null,
    status :null,
    Type : null

}

$(function(){
    //点击确定修改
    $("#update").on("click",function () {
        param.id = $("#id").val();
        param.companyName = $("#companyName").val();
        param.charterNum = $("#charterNum").val();
        param.legalPersonName = $("#legalPersonName").val();
        param.legalPhone = $("#legalPhone").val();
        param.companyCreateTime =parserDate($("#companyCreateTime").val());
        param.registerMoney = $("#registerMoney").val();
        param.registerAddress = $("#registerAddress").val();
        param.borrowMoney = $("#borrowMoney").val();
        param.apprroveName = $("#apprroveName").val();
        param.legalIDCard = $("#legalIDCard").val();
        param.XYJF = $("#XYJF").val();
        param.XYJFDescribe = $("#XYJFDescribe").val();
        param.address = $("#address").val();
        param.borrowUse = $("#borrowUse").val();
        param.companyDescribe = $("#companyDescribe").val();
        param.moneyInfo = $("#moneyInfo").val();
        param.ensureInfo = $("#ensureInfo").val();
        param.status = $("#status").val();
        alert(param.status);
        param.Type = $("#Type").val();
        updateBorrowUser();
    });

    //点击取消
    $("#back").on("click",function () {
        window.history.back(-1);
    });
});

//修改产品标
function updateBorrowUser(){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"BorrowMoneyUser/updateBorrowUser.do",
        data: param,
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("修改成功");
                if(param.Type==1){
                    window.location.href =basePath + "toJsp/toborrowUserList.do";
                }else if(param.Type==2){
                    window.location.href = basePath+"toJsp/toborrowUserApprrove1.do";
                }else if(param.Type==3){
                    window.location.href = basePath+"toJsp/toborrowUserApprrove2.do";
                }
            } else {
                alert("修改失败");
            }
        },
        error : function(){
            alert("网络出现问题");
        }
    });
}


//身份证正面
function upload1(fileObj){

    //var fileObj = document.getElementById(id).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择图片");
        return;
    }
    var formFile = new FormData();
    formFile.append("action", "UploadVMKImagePath");
    formFile.append("file", fileObj[0]); //加入文件对象

    var data = formFile;
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        data : data,
        url: basePath+"api/upload.do",
        success: function (msg) {
            //alert(msg.data.filePath.match(/file(\S*)/)[1].substring(1));
            var selectedFile = fileObj[0];
            var reader = new FileReader();
            reader.readAsDataURL(selectedFile);
            reader.onload = function(e){
                $('#charterImage').attr('src', "http://127.0.0.1"+msg.data.readPath);
                $("#charterImage").val(msg.data.readPath);
                param.charterImage = msg.data.readPath;
            }
        },
        error: function () {
            alert("添加失败")
        }
    });
}
//身份证正面
function upload2(fileObj){

    //var fileObj = document.getElementById(id).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择图片");
        return;
    }
    var formFile = new FormData();
    formFile.append("action", "UploadVMKImagePath");
    formFile.append("file", fileObj[0]); //加入文件对象

    var data = formFile;
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        data : data,
        url: basePath+"api/upload.do",
        success: function (msg) {
            //alert(msg.data.filePath.match(/file(\S*)/)[1].substring(1));
            var selectedFile = fileObj[0];
            var reader = new FileReader();
            reader.readAsDataURL(selectedFile);
            reader.onload = function(e){
                $('#legalIDCardImageZ').attr('src', "http://127.0.0.1"+msg.data.readPath);
                $("#legalIDCardImageZ").val(msg.data.readPath);
                param.legalIDCardImageZ = msg.data.readPath;
            }
        },
        error: function () {
            alert("添加失败")
        }
    });
}

//身份证正面
function upload3(fileObj){

    //var fileObj = document.getElementById(id).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择图片");
        return;
    }
    var formFile = new FormData();
    formFile.append("action", "UploadVMKImagePath");
    formFile.append("file", fileObj[0]); //加入文件对象

    var data = formFile;
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        data : data,
        url: basePath+"api/upload.do",
        success: function (msg) {
            //alert(msg.data.filePath.match(/file(\S*)/)[1].substring(1));
            var selectedFile = fileObj[0];
            var reader = new FileReader();
            reader.readAsDataURL(selectedFile);
            reader.onload = function(e){
                $('#legalIDCardImageF').attr('src', "http://127.0.0.1"+msg.data.readPath);
                $("#legalIDCardImageF").val(msg.data.readPath);
                param.legalIDCardImageF = msg.data.readPath;
            }
        },
        error: function () {
            alert("添加失败")
        }
    });
}

//日期转换
var parserDate = function (date) {
    var t = Date.parse(date);
    if (!isNaN(t)) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
    } else {
        return null;
    }
}