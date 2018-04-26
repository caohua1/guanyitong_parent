$(function(){
    init();

    //点击取消
    $("#cancel").on("click",function(){
       $('.showhide').hide();
    });
    //点击确定，审核通过
    $("#apprrove_success").on("click",function(){
        $('.showhide').hide();
        var status = 4;
        toupdate(status);
    });

    //点击确定审核失败
    $("#apprrove_no").on("click",function () {
        $(".showtexthide").hide();
        var status = 3;
        toupdate(status);
    });

    //点击返回
    $("#back").on("click",function () {
        window.history.back(-1);
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
            console.log(msg)
          if(msg.data!=null){
              $("#id").html(addPreZero(id));
              //0 认证信息待审核  1 认证审核失败 2 认证信息审核成功，借款额度待审核  3 借款额度审核失败
              // 4 借款额度审核成功，合同待确认  5 合同确认失败  6 合同确认成功，产品待审核
              // 7 待还款 8 还款中 9 已还款
              if(data.status ==0){
                 $("#status1").html("待审核");
                 $("#status2").html("待还款");
              }else if(data.status==1){
                  $("#status1").html("认证审核失败");
                  $("#status2").html("待还款");
              }else if(data.status==8){
                  $("#status1").html("审核成功");
                  $("#status2").html("还款中");
              }else if(data.status==9){
                  $("#status1").html("审核成功");
                  $("#status2").html("已还款完成");
              }else{
                  $("#status1").html("审核成功");
                  $("#status2").html("待还款");
              }

              if(data.companyName !=null && data.charterImage !=''){
                  $("#companyName").html(data.companyName);
              }else{
                  $("#companyName").html("暂无数据");
              }

              if(data.createTime !=null ){
                  $("#createTime").html(data.createTime);
              }else{
                  $("#createTime").html("暂无数据");
              }

              if(data.charterNum !=null ){
                  $("#charterNum").html(data.charterNum);
              }else{
                  $("#charterNum").html("暂无数据");
              }

              if(data.registerMoney !=null ){
                  $("#registerMoney").html(data.registerMoney);
              }else{
                  $("#registerMoney").html("暂无数据");
              }

              if(data.registerAddress !=null ){
                  $("#registerAddress").html(data.registerAddress);
              }else{
                  $("#registerAddress").html("暂无数据");
              }

              if(data.borrowMoney !=null ){
                  $("#borrowMoney").html(data.borrowMoney);
              }else{
                  $("#borrowMoney").html("暂无数据");
              }

              if(data.legalPhone !=null ){
                  $("#legalPhone").html(data.legalPhone);
              }else{
                  $("#legalPhone").html("暂无数据");
              }

              if(data.legalPersonName !=null ){
                  $("#legalPersonName").html(data.legalPersonName);
              }else{
                  $("#legalPersonName").html("暂无数据");
              }

              if(data.charterImage !=null && data.charterImage !=''){
                  $('#charterImage').show();
                  $('#charterImage').attr('src', "http://127.0.0.1"+data.charterImage);
              }

              if(data.apprroveName !=null ){
                  $("#apprroveName").html(data.apprroveName);
              }else{
                  $("#apprroveName").html("暂无数据");
              }

              if(data.legalIDCard !=null ){
                  $("#legalIDCard").html(data.legalIDCard);
              }else{
                  $("#legalIDCard").html("暂无数据");
              }

              if(data.legalIDCardImageZ !=null && data.legalIDCardImageZ !=''){
                  $('#legalIDCardImageZ').show();
                  $('#legalIDCardImageZ').attr('src', "http://127.0.0.1"+data.legalIDCardImageZ);
              }
              if(data.legalIDCardImageF !=null && data.legalIDCardImageF !=''){
                  $('#legalIDCardImageF').show();
                  $('#legalIDCardImageF').attr('src', "http://127.0.0.1"+data.legalIDCardImageF);
              }

              if(data.XYJF !=null ){
                  $("#XYJF").html(data.XYJF);
              }else{
                  $("#XYJF").html("暂无数据");
              }

              if(data.XYJFDescribe !=null ){
                  $("#XYJFDescribe").html(data.XYJFDescribe);
              }else{
                  $("#XYJFDescribe").html("暂无数据");
              }

              if(data.address !=null ){
                  $("#address").html(data.address);
              }else{
                  $("#address").html("暂无数据");
              }

              if(data.companyDescribe !=null ){
                  $("#companyDescribe").html(data.companyDescribe);
              }else{
                  $("#companyDescribe").html("暂无数据");
              }

              if(data.borrowUse !=null ){
                  $("#borrowUse").html(data.borrowUse);
              }else{
                  $("#borrowUse").html("暂无数据");
              }

              if(data.moneyInfo !=null ){
                  $("#moneyInfo").html(data.moneyInfo);
              }else{
                  $("#moneyInfo").html("暂无数据");
              }

              if(data.ensureInfo !=null ){
                  $("#ensureInfo").html(data.ensureInfo);
              }else{
                  $("#ensureInfo").html("暂无数据");
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
                window.location.href = basePath+"toJsp/toborrowUserApprrove2.do";
            }else{
                alert("操作失败");
                window.location.href = basePath+"toJsp/toborrowUserApprrove2.do";
            }
        },
        error: function () {
            alert("网络问题")
        }
    });
}


//补零
function addPreZero(num){
    return ('0000000'+num).slice(-8);
}