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
        bBtn = true;
        var id = $("#id").html();
        $("#pageNum").text(1);
        createData(id);
    });

})
//Time:时间   deposit:存入    expend:支出    detail:明细    Type:类型
function capital(Time,deposit,expend,detail,Type){
    this.Time=Time;
    this.deposit=deposit;
    this.expend=expend;
    this.detail=detail;
    this.Type=Type;
}
var bBtn = true;
function createData(id) {
    var capitalAccountList=new Array()
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
        success:function (msg) {
            console.log(msg);
            var count = msg.data.count;
            $('#count').text(count);
            var str = "";
            //查询充值记录
            if (msg.data.pageInfo.rechargeMoneyPageInfo != null) {
                if (msg.data.pageInfo.rechargeMoneyPageInfo.list != null) {
                    var rechargeMoneyData = msg.data.pageInfo.rechargeMoneyPageInfo.list;
                    for (var i in rechargeMoneyData) {
                        capitalAccountList.push(new capital(rechargeMoneyData[i].rechargeTime, rechargeMoneyData[i].rechargeMoney, " ", "充值", rechargeMoneyData[i].rechargeType));
                    }
                }
            }//查询回款记录
            if (msg.data.pageInfo.userDealBackMoneyRecordPageInfo != null) {
                if (msg.data.pageInfo.userDealBackMoneyRecordPageInfo.list != null) {
                    var userDealBackMoneyRecord = msg.data.pageInfo.userDealBackMoneyRecordPageInfo.list;
                    for (var i in userDealBackMoneyRecord) {
                        capitalAccountList.push(new capital(userDealBackMoneyRecord[i].time, userDealBackMoneyRecord[i].bj, " ", "回款" + userDealBackMoneyRecord[i].bj, "回款"));
                    }
                }
            } //查询出借记录
            if (msg.data.pageInfo.userDealMoneyPageInfo != null) {
                if (msg.data.pageInfo.userDealMoneyPageInfo.list != null) {
                    var userDealMoney = msg.data.pageInfo.userDealMoneyPageInfo.list;
                    for (var i in userDealMoney) {
                        capitalAccountList.push(new capital(userDealMoney[i].createTime, " ", userDealMoney[i].dealMoney, "出借" + userDealMoney[i].dealMoney, "出借"));
                    }
                }
            }
           if(capitalAccountList!=null){
            for (var item in capitalAccountList) {
                str += "<tr>" +
                    "<td>" + capitalAccountList[item].Time + "</td>" +
                    "<td>" + capitalAccountList[item].deposit + "</td>" +
                    "<td>" + capitalAccountList[item].expend + "</td>" +
                    "<td>" + capitalAccountList[item].detail + "</td>" +
                    "<td>" + capitalAccountList[item].Type + "</td>" +
                    "</tr>";
            }
            tbody.innerHTML = str;
            if (bBtn) {
                $('.pageTest').page({
                    leng: Math.ceil(count / pageSize),
                    activeClass: 'activP', //active 类样式定义
                    clickBack: function (page) {
                        $(this)[0].leng = Math.ceil(count / pageSize);
                        $("#pageNum").text(page);
                        createData(id);
                    }
                });
            }
              bBtn = false;
           }else{
                tbody.innerHTML = "暂无数据";
                if(bBtn) {
                    $('.pageTest').page({
                        leng: 0,
                        activeClass: 'activP' //active 类样式定义
                    });
                }
            }
      },
      error: function () {
            alert("查询失败")
        }
    })
}

