$(function(){
    init();

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

    //点击取消
    $("#cancel").on("click",function(){
        $('.showhide').hide();
    });


});

//初始化页面
function init(){
    var id = $("#id").val();
        //tbody= window.document.getElementById("tbody-result1");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"product/selectProductInfoById.do",
        data: {
           id:id
        },
        success: function (msg) {
            var data = msg.data;
          if(msg.data!=null){
              if(data.borrowMoneyUserId !=null && data.borrowMoneyUserId !=''){
                  $("#borrowMoneyUserId").html(data.borrowMoneyUserId);
              }else{
                  $("#borrowMoneyUserId").html("暂无数据");
              }

              if(data.NO !=null ){
                  $("#NO").html(data.NO);
              }else{
                  $("#NO").html("暂无数据");
              }

              if(data.yield !=null ){
                  $("#yield").html(data.yield);
              }else{
                  $("#yield").html("暂无数据");
              }

              if(data.name !=null ){
                  $("#name").html(data.name);
              }else{
                  $("#name").html("暂无数据");
              }

              if(data.ZMoney !=null ){
                  $("#ZMoney").html(data.ZMoney);
              }else{
                  $("#ZMoney").html("暂无数据");
              }

              if(data.backMoneyType !=null ){
                  $("#backMoneyType").html(data.backMoneyType);
              }else{
                  $("#backMoneyType").html("暂无数据");
              }

              if(data.YesNo !=null ){
                  $("#YesNo").html(data.YesNo);
              }else{
                  $("#YesNo").html("暂无数据");
              }

              if(data.startRaiseTime !=null ){
                  $("#startRaiseTime").html(data.startRaiseTime);
              }else{
                  $("#startRaiseTime").html("暂无数据");
              }

              if(data.endRaiseTime !=null ){
                  $("#endRaiseTime").html(data.endRaiseTime);
              }else{
                  $("#endRaiseTime").html("暂无数据");
              }

              if(data.startBorrowTime !=null ){
                  $("#startBorrowTime").html(data.startBorrowTime);
              }else{
                  $("#startBorrowTime").html("暂无数据");
              }

              if(data.endBorrowTime !=null ){
                  $("#endBorrowTime").html(data.endBorrowTime);
              }else{
                  $("#endBorrowTime").html("暂无数据");
              }

              if(data.moneyUse !=null ){
                  $("#moneyUse").html(data.moneyUse);
              }else{
                  $("#moneyUse").html("暂无数据");
              }

              if(data.QSUse !=null ){
                  $("#QSUse").html(data.QSUse);
              }else{
                  $("#QSUse").html("暂无数据");
              }

              if(data.coverImage !=null && data.coverImage !=''){
                  $('#coverImage').show();
                  $('#coverImage').attr('src', "http://127.0.0.1"+data.coverImage);
              }else{
                  $('#img').html("暂无图片")
              }

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
    var id = $("#id").val();
    var causeBy = $("#causeBy").val();
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"product/updateProductInfo.do",
        data: {
            id:id,
            status : status,
            causeBy : causeBy
        },
        success: function (msg) {
            if(msg.state==0){
               alert("操作成功")
                window.location.href=basePath+"toJsp/toproductInfo_apprrove_manage.do";
            }else{
                alert("操作失败");
            }
        },
        error: function () {
            alert("网络错误")
        }
    });
}