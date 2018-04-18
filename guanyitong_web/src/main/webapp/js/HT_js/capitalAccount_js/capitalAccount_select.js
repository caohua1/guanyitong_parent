
var
$(function(){
    $('td').css({'padding':'20px 30px'});
    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('.quespan').click(function(){
        $('.showhide').show();
    })
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
    })
  var id = $("#id").html();
    createData(id)
})
function createData(id) {

    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    var SZ=null;
    var LX=null;
    var condition='1';
    var startTime=null;
    var endTime=null;
    var SZ = $("#selectSZ").val();
    var LX = $("#selectLX").val();

    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"AccountDetails/checkDetails.do",
        data:{
            userId:id,
            startTime:startTime,
            endTime:endTime,
            PmenType:SZ,
            type:LX,
            condition:1,

        },
        success:function (msg){
            console.log(msg);
            var str="" ;
             var data = msg.data;
            }
    })
}
function person(rechargeTime,rechargeMoney,rechargeType)
{
    this.rechargeTime=rechargeTime;
    this.rechargeMoney=rechargeMoney;
    this.rechargeType=rechargeType;
}
