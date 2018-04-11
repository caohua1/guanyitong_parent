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
            console.log(msg)
          if(msg.data!=null){
              $("#id").html(id);
              //0 认证信息待审核  1 认证审核失败 2 认证信息审核成功，借款额度待审核  3 借款额度审核失败
              // 4 借款额度审核成功，合同待确认  5 合同确认失败  6 合同确认成功，产品待审核
              // 7 待还款 8 还款中 9 已还款

         /* <div><span>✔</span>  <span>认证资料审核：</span><span id="status">已通过</span></div>
              <div><span>✔</span>  <span>额度申请审核：</span><span id="status_money">已通过</span></div>
              <div><span>✔</span>  <span>合约</span><span id="status_heto">已签约</span></div>*/
              if(data.status ==0){
                 $("#status").html("待审核");
                 $("#status_money").html("待审核");
                 $("#status_heto").html("待签约");
              }else if(data.status==1){
                  $("#status").html("未通过");
                  $("#status_money").html("待审核");
                  $("#status_heto").html("待签约");
              }else if(data.status==3){
                  $("#status").html("已通过");
                  $("#status_money").html("未通过");
                  $("#status_heto").html("待签约");
              }else if(data.status==4){
                  $("#status").html("已通过");
                  $("#status_money").html("已通过");
                  $("#status_heto").html("待签约");
              }else if(data.status==5){
                  $("#status").html("已通过");
                  $("#status_money").html("已通过");
                  $("#status_heto").html("合同确认失败");
              }else {
                  $("#status").html("已通过");
                  $("#status_money").html("已通过");
                  $("#status_heto").html("已签约");
              }

              if(data.contractImage !=null && data.contractImage!=''){
                  $('#contractImage').show();
                  $('#contractImage').attr('src', "http://127.0.0.1"+data.contractImage);
              }else{
                  $("#img1").html("暂无图片");
              }

              if(data.companyName !=null && data.charterImage !=''){
                  $("#companyName").html(data.companyName);
              }else{
                  $("#companyName").html("暂无数据");
              }

              if(data.companyCreateTime !=null ){
                  $("#companyCreateTime").html(data.companyCreateTime);
              }else{
                  $("#companyCreateTime").html("暂无数据");
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
              }else{
                  $("#img2").html("暂无图片");
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
              }else{
                  $("#img3").html("正面：暂无图片");
              }
              if(data.legalIDCardImageF !=null && data.legalIDCardImageF !=''){
                  $('#legalIDCardImageF').show();
                  $('#legalIDCardImageF').attr('src', "http://127.0.0.1"+data.legalIDCardImageF);
              }else{
                  $("#img4").html("反面：暂无图片");
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
            }else{
                alert("网络错误");
            }
        },
        error: function () {
            alert("修改失败")
        }
    });
}