$(function () {
    $("ul#lists").on("click","li",function(){
for (var item in UserBankcardList){
    if($(this).text()==UserBankcardList[item].id){
    $("#btnShow").val(addPreZero(UserBankcardList[item].id));
    $("#realName").val(UserBankcardList[item].apprroveName);
    $("#IDCardNumber").val(UserBankcardList[item].legalIDCard);
    }
}
    })
    $("#qXiao").click(function(){
        window.history.back(-1);
    });
})
function addPreZero(num){
    return ('0000000'+num).slice(-8);
}
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
                var str = '<li class="text">'+ addPreZero(result[i].id) +'</li>';
                $(str).appendTo("#lists");
                listArry(new person(result[i].id,result[i].apprroveName,result[i].legalIDCard));
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
function person(id,apprroveName,legalIDCard)
{
    this.id=id;
    this.apprroveName=apprroveName;
    this.legalIDCard=legalIDCard;
}
var UserBankcardList=new Array()
function listArry(userBankcard) {
    UserBankcardList.push(userBankcard);
}
function addUserBank() {
    if($("#btnShow").val()!=""&&$("#realName").val()!=""&&$("#IDCardNumber").val()!=""&&$("#cardNo").val()!=""&&$("#bankName").val()!=""&&$("#openAccountRegion").val()!=""&&$("#phone").val()!=""){
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
            if(result==1){
                var onlyChoseAlert = simpleAlert({
                    "content":"添加成功",
                    "buttons":{
                        "确定":function () {
                            window.history.back(-1);
                        }
                    }
                })
            }
        },
        error:function(){
            console.error('请求失败！')
        }
    })
    }else {
        var onlyChoseAlert = simpleAlert({
            "content":"请完善资料!",
            "buttons":{
                "确定":function () {
                    onlyChoseAlert.close();
                }
            }
        })
    }
}

