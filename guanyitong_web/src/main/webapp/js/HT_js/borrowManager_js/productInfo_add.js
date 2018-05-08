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

    //输入借款人borrowMOneyUserId后，借款金额，自动显示，并且不能修改
    $("#borrowMoneyUserId").blur(function(){
        $("#wu").hide();
        selectborrowMoney();
    });
    //点击添加
    $("#add").on("click",function () {
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
        if($("#startRaiseTime").val()!=""){
            param.startRaiseTime = parserDate($("#startRaiseTime").val());
        }else{
            param.startRaiseTime =null;
        }

        if($("#endRaiseTime").val()!=""){
            param.endRaiseTime = parserDate($("#endRaiseTime").val());
        }else{
            param.endRaiseTime =null;
        }
        if($("#startBorrowTime").val()!=""){
            param.startBorrowTime = parserDate($("#startBorrowTime").val());
        }else{
            param.startBorrowTime =null;
        }
        if($("#endBorrowTime").val()!=""){
            param.endBorrowTime = parserDate($("#endBorrowTime").val());
        }else{
            param.endBorrowTime =null;
        }
        //必填项
        if( param.borrowMoneyUserId == "" || param.NO==""|| param.yield==null|| param.ZMoney==""||param.productId==null ||
            param.raiseMoneyMonth ==null||param.backMoneyType==null||param.YesNo==null || param.monthNum==null||param.startBorrowTime==null||
            param.startRaiseTime==null||param.endBorrowTime==null||param.endRaiseTime==null){
            return false;
        }else{
            toadd();
        }

    });

});

//添加
function toadd(){

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


//查询借款用户得借款金额
function selectborrowMoney(){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    var id = $("#borrowMoneyUserId").val();

    $.ajax({
        type: "post",
        url: basePath+"borrowMoney/selectborrowMoneyById.do",
        data: {
            id : id
        },
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
               //显示此借款用户的
               $("#ZMoney").val(msg.data.borrowMoney);
            } else {
               //此用户不存在
                $("#wu").show();
                $("#wu").text("此借款用户不存在");
                $("#ZMoney").val("");
            }
        },
        error : function () {
            alert("网络出现问题");
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