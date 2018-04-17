var param = {
    id :null,
    borrowMoneyUserId : null,
    NO : null,
    yield : null,
    productId : null,
    ZMoney : null,
    backMoneyType : null,
    YesNo : null,
    raiseMoneyMonth : null,
    monthNum : null,
    moneyUse : null,
    QSUse : null,
    coverImage : null

}

$(function(){
    //点击确定修改
    $("#update").on("click",function () {
        param.id = $("#id").val();
        param.borrowMoneyUserId = $("#borrowMoneyUserId").html();
        param.NO = $("#NO").val();
        param.yield = $("#yield").val();

        param.productId = $("#productId").val();
        param.ZMoney = $("#ZMoney").val();
        var backMoneyType = $("#backMoneyType").val();
        if(backMoneyType==1){
            param.backMoneyType = "按月还本付息";
        }else if(backMoneyType ==2){
            param.backMoneyType = "先息后本";
        }else if(backMoneyType == 3){
            param.backMoneyType ="一次性还本付息";
        }
        var YesNo = $("#YesNo").val();
        if(YesNo == 1){
            param.YesNo = "是";
        }else if(YesNo ==0){
            param.YesNo = "否";
        }
        param.raiseMoneyMonth = $("#raiseMoneyMonth").val();
        param.monthNum = $("#monthNum").val();
        param.moneyUse = $("#moneyUse").val();
        param.QSUse = $("#QSUse").val();
        param.coverImage = $("#myImg").val();
        updateProductInfo();
    });

    //点击取消
    $("#back").on("click",function () {
        window.history.back(-1);
    });
});

//修改产品标
function updateProductInfo(){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"borrowMoney/updateProductInfo.do",
        data: param,
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("修改成功");
                window.location.href =basePath + "toJsp/toborrowMoney_manage.do";
            } else {
                alert("修改失败");
            }
        },
        error : function(){
            alert("网络出现问题");
        }
    });
}


//封面
function upload(fileObj){

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
                $('#myImg').attr('src', "http://127.0.0.1"+msg.data.readPath);
                $("#myImg").val(msg.data.readPath);
                alert($("#myImg").val());
                param.legalIDCardImageZ = msg.data.readPath;
            }
        },
        error: function () {
            alert("添加失败")
        }
    });
}