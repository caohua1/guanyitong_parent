var param = {
    companyName : null,
    charterNum : null,
    legalPersonName : null,
    legalPhone : null,
    companytime : null,
    registerMoney : null,
    registerAddress : null,
    borrowMoney : null,
    apprroveName : null,
    legalIDCard : null,
    XYJF : null,
    XYJFDescribe : null,
    address : null,
    companyDescribe : null,
    borrowUse : null,
    charterImage : null,
    legalIDCardImageZ : null,
    legalIDCardImageF : null

}

$(function(){
      //点击添加
      $("#toAdd").on("click",function(){
          add();
      });
});

//添加
function add(){
    param.companyName = $("#companyName").val();
    param.charterNum = $("#charterNum").val();
    param.legalPersonName = $("#legalPersonName").val();
    param.legalPhone = $("#legalPhone").val();
    param.companytime = $("#companyCreateTime").val();
    param.registerMoney = $("#registerMoney").val();
    param.registerAddress = $("#registerAddress").val();
    param.borrowMoney = $("#borrowMoney").val();
    param.apprroveName = $("#apprroveName").val();
    param.legalIDCard = $("#legalIDCard").val();
    param.XYJF = $("#XYJF").val(); //下拉菜单
    param.XYJFDescribe = $("#XYJFDescribe").val();
    param.address = $("#address").val();
    param.companyDescribe = $("#companyDescribe").val();
    param.borrowUse = $("#borrowUse").val();
    alert(param.companyName);
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/addBorrowMoneyUser.do",
        data: param,
        success: function (msg) {
            console.log(msg);
            if(msg.state==0){
                alert("添加成功");
            }
        },
        error: function () {
            alert("添加失败")
        }
    });

}

//图片上传,营业执照图片
function upload1(fileObj){

    //var fileObj = document.getElementById(id).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择图片");
        return;
    }
    var formFile = new FormData();
    formFile.append("action", "UploadVMKImagePath");
    formFile.append("file", fileObj[0]); //加入文件对象

    //第一种  XMLHttpRequest 对象
    //var xhr = new XMLHttpRequest();
    //xhr.open("post", "/Admin/Ajax/VMKHandler.ashx", true);
    //xhr.onload = function () {
    //    alert("上传完成!");
    //};
    //xhr.send(formFile);

    //第二种 ajax 提交

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

    //第一种  XMLHttpRequest 对象
    //var xhr = new XMLHttpRequest();
    //xhr.open("post", "/Admin/Ajax/VMKHandler.ashx", true);
    //xhr.onload = function () {
    //    alert("上传完成!");
    //};
    //xhr.send(formFile);

    //第二种 ajax 提交

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
                $('#myImg2').show();
                $('#myImg2').attr('src', "http://127.0.0.1"+msg.data.readPath);
                param.legalIDCardImageZ = msg.data.readPath;
            }
        },
        error: function () {
            alert("添加失败")
        }
    });
}

//身份证反面
function upload3(fileObj){

    //var fileObj = document.getElementById(id).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择图片");
        return;
    }
    var formFile = new FormData();
    formFile.append("action", "UploadVMKImagePath");
    formFile.append("file", fileObj[0]); //加入文件对象

    //第一种  XMLHttpRequest 对象
    //var xhr = new XMLHttpRequest();
    //xhr.open("post", "/Admin/Ajax/VMKHandler.ashx", true);
    //xhr.onload = function () {
    //    alert("上传完成!");
    //};
    //xhr.send(formFile);

    //第二种 ajax 提交

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
                $('#myImg3').show();
                $('#myImg3').attr('src', "http://127.0.0.1"+msg.data.readPath);
                param.legalIDCardImageF = msg.data.readPath;
            }
        },
        error: function () {
            alert("添加失败")
        }
    });
}