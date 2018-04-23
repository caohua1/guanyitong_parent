var param = {
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
    coverImage :null,
    startRaiseTime : null,
    endRaiseTime : null,
    startBorrowTime : null,
    endBorrowTime : null

};

$(function(){
    //点击添加
    $("#add").on("click",function () {
        toadd();
    });

});

//添加
function toadd(){
    param.borrowMoneyUserId = $("#borrowMoneyUserId").val();
    param.NO = $("#NO").val();
    var yield = $("#yield").val();
    if(yield==null || yield ==""){
        param.yield = null;
    }else{
        param.yield = parseFloat(yield);
    }

    var productId = $("#productId").val();
    if(productId ==-1){
        param.productId = null;
    }else {
        param.productId = productId;
    }
    param.ZMoney = $("#ZMoney").val();
    var backMoneyType = $("#backMoneyType").val();

    if(backMoneyType ==1){
        param.backMoneyType = "按月还本付息";
    }else if(backMoneyType == 2){
        param.backMoneyType = "先息后本";
    }else if(backMoneyType == 3){
        param.backMoneyType = "一次性还本付息";
    }else{
        param.backMoneyType = null;
    }

    var yesno = $("#YesNo").val();

    if(yesno ==0){
        param.YesNo = "否"
    }else if(yesno ==1){
        param.YesNo = "是";
    }else{
        param.YesNo = null;
    }
    var raiseMoneyMonth = $("#raiseMoneyMonth").val();
    if(raiseMoneyMonth==-1){
        param.raiseMoneyMonth = null;
    }else {
        param.raiseMoneyMonth = raiseMoneyMonth;
    }
    var monthNum = $("#monthNum").val();
    if(monthNum==-1){
        param.monthNum = null;
    }else{
        param.monthNum = monthNum;
    }
    param.moneyUse = $("#moneyUse").val();
    param.QSUse = $("#QSUse").val();
    param.startRaiseTime = parserDate($("#startRaiseTime").val());
    alert($("#startRaiseTime").val());
    alert(parserDate($("#startRaiseTime").val()));
    param.endRaiseTime = parserDate($("#endRaiseTime").val());
    alert($("#endRaiseTime").val());
    alert(parserDate($("#endRaiseTime").val()));
    param.startBorrowTime = parserDate($("#startBorrowTime").val());
    param.endBorrowTime = parserDate($("#endBorrowTime").val());
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"product/insertProductinfo.do",
        data: param,
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("添加成功");
                window.location.href = basePath + "toJsp/toproductInfo_manage_list.do";
            } else {
                alert("添加失败");
            }
        },
        error : function () {
            alert("网络出现问题");
        }
    });

}

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
                $('#myImg').show();
                $('#myImg').attr('src', "http://127.0.0.1"+msg.data.readPath);
                param.coverImage = msg.data.readPath;
            }
        },
        error: function () {
            alert("添加失败")
        }
    });
}

var parserDate = function (date) {
    var t = Date.parse(date);
    if (!isNaN(t)) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
    } else {
        return null;
    }
};