$(function(){
    init();

    //点击取消
    $("#cancel").on("click",function(){
       $('.showhide').hide();
    });
    //点击确定，审核通过
    $("#apprrove_success").on("click",function(){
        $('.showhide').hide();
        var status = 2;
        toupdate(status);

    });

    //点击确定审核失败
    $("#apprrove_no").on("click",function () {
        $(".showtexthide").hide();
        var status = 1;
        toupdate(status);
    });

});

//初始化页面
function init(){
    var id = $("#borrowMoneyUserId").val();
        //tbody= window.document.getElementById("tbody-result1");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/selectBorrowMoneyUser.do",
        data: {
           id:id
        },
        success: function (msg) {
            var data = msg.data;
          if(msg.data!=null){
              $("#companyName").html(data.companyName);
              $("#createTime").html(data.createTime);
              $("#charterNum").html(data.charterNum);
              $("#registerMoney").html(data.registerMoney);
              $("#registerAddress").html(data.registerAddress);
              $("#borrowMoney").html(data.borrowMoney);
              $("#legalPhone").html(data.legalPhone);
              $("#legalPersonName").html(data.legalPersonName);
              //$("#charterImage").html(data.charterImage);
              $("#apprroveName").html(data.apprroveName);
              $("#legalIDCard").html(data.legalIDCard);
              //$("#legalIDCardImageZ").html(data.legalIDCardImageZ);
              //$("#legalIDCardImageF").html(data.legalIDCardImageF);
              $("#XYJF").html(data.XYJF);
              $("#XYJFDescribe").html(data.XYJFDescribe);
              $("#address").html(data.address);
              $("#companyDescribe").html(data.companyDescribe);
              $("#borrowUse").html(data.borrowUse);
              $("#moneyInfo").html(data.moneyInfo);
              $("#ensureInfo").html(data.ensureInfo);
          }else{
              alert("网络错误");
          }
        },
        error: function () {
            alert("查询失败")
        }
    });
}

//点击确定审核成功或者审核失败,修改状态
function toupdate(status) {
    var id = $("#borrowMoneyUserId").val();
    var causeBy = $("#causeBy").val();
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/updateStatus.do",
        data: {
            id:id,
            status : status,
            causeBy : causeBy
        },
        success: function (msg) {
            if(msg.state==0){
               alert("操作成功")
            }else{
                alert("网络错误");
            }
        },
        error: function () {
            alert("修改失败")
        }
    });
}