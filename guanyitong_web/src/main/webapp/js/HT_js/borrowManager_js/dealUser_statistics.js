var param = {
    pageNum : null,
    pageSize : null,
    username : null,
    name : null,
    startTi : null,
    endTi : null,
    minMoney : null,
    maxMoney : null,
    idCard : null
}
$(function(){
      createTBody();
    //点击搜索
    $("#select").on("click",function () {
        bBtn = true;
       //搜索从第一页开始
            $("#pageNum").text(1);
            createTBody();
    });
});

var bBtn = true;
//初始化列表

function createTBody(){
    param.pageNum = $("#pageNum").html();
    param.pageSize = $("#pageSize").html();
    param.username = $("#username").val();
    param.endTi = $("#endTime").val();
    param.startTi = $("#startTime").val();
    param.name = $("#name").val();
    param.minMoney = $("#minMoney").val();
    param.maxMoney = $("#maxMoney").val();
    param.idCard = $("#idCard").val();
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"deal/selectAllUserDeal.do",
        data: param,
        success: function (msg) {
            console.log(msg);
            var str = "";
            var count = msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0){
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<=count || (j == 1 && count == 1)){
                    for (i in data) {
                        str += "<tr>"+
                            "<td>" +(j++) + "</td>"+
                            "<td>" + data[i].username + "</td>" +
                            "<td>" + data[i].no + "</td>"+
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].zmoney + "</td>"+
                            "<td>" + data[i].yield + "</td>" +
                            "<td>" + data[i].pCreateTime + "</td>"
                            if(data[i].pStatus ==4){
                                str +=  "<td>" + "筹集中" + "</td>"
                            }else if(data[i].pStatus ==5){
                                str +=  "<td>" + "筹集完" + "</td>"
                            }else if(data[i].pStatus ==6){
                                str +=  "<td>" + "筹集失败" + "</td>"
                            }else if(data[i].pStatus ==7){
                                str +=  "<td>" + "上架" + "</td>"
                            }else if(data[i].pStatus ==8){
                                str +=  "<td>" + "下架" + "</td>"
                            }else if(data[i].pStatus ==9){
                                str +=  "<td>" + "已放弃" + "</td>"
                            }else if(data[i].pStatus ==10){
                                str +=  "<td>" + "提现申请中" + "</td>"
                            }else if(data[i].pStatus ==11){
                                str +=  "<td>" + "提现成功" + "</td>"
                            }else if(data[i].pStatus ==12){
                                str +=  "<td>" + "提现失败" + "</td>"
                            }else if(data[i].pStatus ==13){
                                str +=  "<td>" + "还款中，未还款完成" + "</td>"
                            }else if(data[i].pStatus ==5){
                                str +=  "<td>" + "还款完成" + "</td>"
                            }
                           str += "<td>" + data[i].dealMoney + "</td>"+
                            "<td>" + data[i].createTime + "</td>"
                    }
                    tbody.innerHTML = str;
                    if(bBtn) {
                        $('.pageTest').page({
                            leng: Math.ceil(count / pageSize),
                            activeClass: 'activP', //active 类样式定义
                            clickBack: function (page) {
                                $(this)[0].leng = Math.ceil(count / pageSize);
                                $("#pageNum").text(page);
                                createTBody();
                            }
                        });
                    }

                    bBtn = false;
                }else{//点击下一页没有数据
                    tbody.innerHTML = "此页暂无数据";
                }
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
    });


}
