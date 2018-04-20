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
    //点击搜索
    $("#sous").click(function(){
        var id = $("#id").html();
        createData(id);
    });
})

function createData(id) {

    var tbody=window.document.getElementById("dataBody");
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    var startTime= $("#startTime").val();
    var endTime= $("#endTime").val();
    var SZ = $("#selectSZ").val();
    var LX = $("#selectLX").val();
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"AccountDetails/checkDetails.do",
        data:{
            pageNum:pageNum,
            pageSize:pageSize,
            userId:id,
            startTime:startTime,
            endTime:endTime,
            type1:SZ,
            type2:LX,

        },
        success:function (msg){
            console.log(msg);
            var count= msg.data.count;
            var str="" ;
            //查询充值记录
            if(msg.data.pageInfo.rechargeMoneyPageInfo.list!=null){
                var rechargeMoneyData=msg.data.pageInfo.rechargeMoneyPageInfo.list;
                for(var i in rechargeMoneyData){
                    listArry(new capital(rechargeMoneyData[i].rechargeTime,rechargeMoneyData[i].rechargeMoney," ","充值",rechargeMoneyData[i].rechargeType));
                }
             //查询回款记录
            }if(msg.data.pageInfo.userDealBackMoneyRecordPageInfo.list!=null){
                var userDealBackMoneyRecord=msg.data.pageInfo.userDealBackMoneyRecordPageInfo.list;
                for(var i in userDealBackMoneyRecord){
                    listArry(new capital(userDealBackMoneyRecord[i].time,userDealBackMoneyRecord[i].bj," ","回款"+userDealBackMoneyRecord[i].bj,"回款"));
                }
             //查询出借记录
            }if(msg.data.pageInfo.userDealMoneyPageInfo.list!=null){
                var userDealMoney=msg.data.pageInfo.userDealMoneyPageInfo.list;
                for(var i in userDealMoney){
                    listArry(new capital(userDealMoney[i].createTime," ",userDealMoney[i].dealMoney,"出借"+userDealMoney[i].dealMoney,"出借"));
                }
            }
           for(var item in capitalAccountList){
               console.log(capitalAccountList[item]);
               str+="<tr>" +
                   "<td>" + capitalAccountList[item].Time + "</td>" +
                   "<td>" + capitalAccountList[item].deposit + "</td>" +
                   "<td>" + capitalAccountList[item].expend + "</td>" +
                   "<td>" + capitalAccountList[item].detail + "</td>" +
                   "<td>" + capitalAccountList[item].Type + "</td>" +
                   "</tr>";
           }
            tbody.innerHTML=str;
      },
      error: function () {
            alert("查询失败")
        }
    })
}
//Time:时间   deposit:存入    expend:支出    detail:明细    Type:类型
function capital(Time,deposit,expend,detail,Type){

    this.Time=Time;
    this.deposit=deposit;
    this.expend=expend;
    this.detail=detail;
    this.Type=Type;
}
var capitalAccountList=new Array()
function listArry(capital){
    capitalAccountList.push(capital);
}
