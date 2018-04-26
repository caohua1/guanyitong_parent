var param = {
    pageNum : null,
    pageSize : null,
    type : 1,
    borrowMoneyUserId : null,
    NO : null,
    startBorrowTi : null,
    endBorrowTi : null,
    startTime : null,
    endTime : null,
    Sta : null,
    minBorrowMoney : null,
    maxBorrowMoney : null

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
    param.borrowMoneyUserId = $("#borrowMoneyUserId").val();
    param.NO = $("#NO").val();
    param.startBorrowTi = $("#startBorrowTime").val();
    param.endBorrowTi = $("#endBorrowTime").val();
    param.startTime = $("#startTime").val();
    param.endTime = $("#endTime").val();
    param.minBorrowMoney = $("#minBorrowMoney").val();
    param.maxBorrowMoney = $("#maxBorrowMoney").val();
    param.Sta = $("#select_id").val();
    if(param.Sta ==-1){
        param.Sta = "5,10,11,12";
    }
    if($("#backMoneyType").val()==0){
        param.backMoneyType = "按月还本还息";
    }else if($("#backMoneyType").val() ==1){
        param.backMoneyType = "先息后本";
    }else if($("#backMoneyType").val() ==2){
        param.backMoneyType = "一次性还本付息";
    }else{
        param.backMoneyType = null;
    }
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"product/selectProductInfoVo.do",
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
                                "<td>" + data[i].borrowMoneyUserId + "</td>"+
                                "<td>" + data[i].no + "</td>"+
                                "<td>" + data[i].name + "</td>"+
                                "<td>" +data[i].zmoney+ "</td>"+
                                "<td>" + data[i].startBorrowTime +"-" +data[i].endBorrowTime+ "</td>"+
                                "<td>" + data[i].createTime + "</td>"
                            if(data[i].status ==5){
                                str += "<td>" + "筹集完" + "</td>"+
                                    "<td>" + "待提现" + "</td>"+
                                    "<td><span><a  href=\"productinfo_manage_info.do?id="+ data[i].id+"\" >查看</a></span><span class=\"quespann\"><a href=\"javascript:;\" onclick=\"update('"+data[i].id+"','10')\">申请体现</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==10){
                                str += "<td>" + "筹集完" + "</td>"+
                                    "<td>" + "提现申请中" + "</td>"+
                                    "<td><span><a  href=\"productinfo_manage_info.do?id="+ data[i].id+"\" >查看</a></span><span class=\"quespannn\"><a href=\"javascript:;\" onclick=\"update('"+data[i].id+"','11' )\">确认提现</a></span>" +
                                    "</td>"
                            } else if(data[i].status ==11){
                                str += "<td>" + "筹集完" + "</td>"+
                                    "<td>" + "提现成功（待还款）" + "</td>"+
                                    "<td><span><a  href=\"productinfo_manage_info.do?id="+ data[i].id+"\" >查看</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==12){
                                str += "<td>" + "筹集完" + "</td>"+
                                    "<td>" + "提现失败" + "</td>"+
                                    "<td><span><a  href=\"productinfo_manage_info.do?id="+ data[i].id+"\" >查看</a></span><span class=\"quespann\"><a href=\"javascript:;\" onclick=\"update('"+data[i].id+"','10')\">申请体现</a></span>" +
                                    "</td>"
                            }


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

//下架操作
function update(id,status){
    $('.showhide').show();
    $('.zhezaocegn').show();
    if(status==10){
        $("#span").text("确定要申请提现？");
    }else if(status ==11){
        $("#span").text("确定已提现？");
    }

    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    //点击确定放弃
    $("#update").click(function () {
        $('.showhide').hide();
        $('.zhezaocegn').hide();
        $.ajax({
            type: "post",
            url: basePath+"borrowMoney/updateStatus.do",
            data: {
                id : id,
                status : status

            },
            dataType: "json",
            success: function (msg) {
                console.log(msg);
                if (msg.state == 0) {
                    alert("操作成功");
                    createTBody();
                } else {
                    alert("操作失败");
                    createTBody();
                }
            },
            error : function(){
                alert("网络出现问题");
            }
        });
    });

    //点击取消
    $("#quxiao").click(function () {
        $('.showhide').hide();
        $('.zhezaocegn').hide();
    });

    //点击x
    $("#x").click(function () {
        $('.showhide').hide();
        $('.zhezaocegn').hide();
    });

}

