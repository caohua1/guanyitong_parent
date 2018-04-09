$(function () {
    $("ul#lists").on("click","li",function(){
for (var item in UserBankcardList){
    if($(this).text()==UserBankcardList[item].borrowMoneyUserId){
    $("#btnShow").val(UserBankcardList[item].borrowMoneyUserId);
    $("#realName").val(UserBankcardList[item].realName);
    $("#IDCardNumber").val(UserBankcardList[item].IDCardNumber);
    }
}
    })
})
function dim(userId,event) {
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    event.stopPropagation();
    var offset = $(event.target).offset();
    $(".dianbot").css({top:offset.top+$(event.target).height+"px",left:offset.left});
    $(".dianbot").slideDown();
    $('#lists').html('');
    //通过后台接口请求数据

    $.ajax({
        url:basePath+"BankCardManagementr/selectDimId.do",
        type:"post",
        data:{
            borrowMoneyUserId:userId,
        },
        dataType:"json",
        success:function(msg){
            var result = msg.data;
            console.log(msg);
            for( i in result){
                var str = '<li class="text">'+ result[i].borrowMoneyUserId +'</li>';
                $(str).appendTo("#lists");
                listArry(new person(result[i].borrowMoneyUserId,result[i].realName,result[i].idcardNumber));
            }
        },
        error:function(){
            console.error('请求失败！')
        }
    })
}
$(document).click(function(){

    $(".dianbot").slideUp('slow');
})

$(".dianbot").click(function(){
    $(this).hide();
})
function person(borrowMoneyUserId,realName,IDCardNumber)
{
    this.borrowMoneyUserId=borrowMoneyUserId;
    this.realName=realName;
    this.IDCardNumber=IDCardNumber;
}
var UserBankcardList=new Array()
function listArry(userBankcard) {
    UserBankcardList.push(userBankcard);
}


function addUserBank() {
    //获取当前页面的url
    var local = window.location;
    var Path = local.protocol+"//"+local.host+"/";
    var YN='';
    if($('#YN').attr('checked')=='checked'){
        YN='yes';
    }else{
        YN='no';
    }
    $.ajax({
        url:Path+"BankCardManagementr/addUserBankcard.do",
        type:"post",
        data:{
            borrowMoneyUserId:$("#btnShow").val(),
            realName:$("#realName").val(),
            IDCardNumber:$("#IDCardNumber").val(),
            cardNo:$("#cardNo").val(),
            bankName:$("#bankName").val(),
            openAccountRegion:$("#openAccountRegion").val(),
            phone:$("#phone").val(),
            YN:YN,
        },
        dataType:"json",
        success:function(msg){
            var result = msg.data;
            console.log(msg);
            if(result==0){

            }
        },
        error:function(){
            console.error('请求失败！')
        }
    })
}

