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
    borrowUse : null
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
            if(msg.state==0){
                alert("添加成功");
            }
        },
        error: function () {
            alert("添加失败")
        }
    });

}

//图片上传
function upload(){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"api/upload.do",
        data: param,
        success: function (msg) {
            /*var selectedFile = $('img').get(0).files[0];
            var reader = new FileReader();
            reader.readAsDataURL(selectedFile);
            reader.onload = function(e){
                var urlsrc = this.result;
                $('img').show();
               // $('img').attr('src', msg.data.);
            }*/
            console.log(msg);
        },
        error: function () {
            alert("添加失败")
        }
    });
}