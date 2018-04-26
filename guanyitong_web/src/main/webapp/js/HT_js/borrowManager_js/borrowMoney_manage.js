var param = {
    pageNum : null,
    pageSize : null,
    type : 0,
    borrowMoneyUserId : null,
    NO : null,
    startBorrowTi : null,
    endBorrowTi : null,
    startTime : null,
    endTime : null,
    backMoneyType : null,
    Sta : null

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
    param.backMoneyType = $("#backMoneyType").val();
    param.Sta = $("#select_id").val();
    if(param.Sta ==-1){
        param.Sta = "2,4,5,6,7,8,9,10,11,12,13,14";
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
            var str = "";
            var count = msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.pageInfo.list;
            console.log(data);
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
                                "<td>" + data[i].yield + "</td>"+
                                "<td>" +data[i].startRaiseTime +"-" +data[i].endRaiseTime + "</td>"+
                                "<td>" + data[i].startBorrowTime +"-" +data[i].endBorrowTime+ "</td>"+
                                "<td>" + data[i].backMoneyType  + "</td>"+
                                "<td>" + data[i].createTime + "</td>"
                            if(data[i].status == 2){
                                str +=  "<td>" + "审核完成，未开始筹集" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "<span><a  href=\"toUpdateProductInfo.do?id="+ data[i].id+"\" >修改</a></span>"+
                                    "<span><a  href=\"javascript:;\" onclick=\"select('"+data[i].id+"')\" >下架</a></span>"+
                                    "<span><a class='fangqij' href='javascript:;' onclick=\"fangqi('"+data[i].id+"')\">放弃</a></span>"+
                                    "</td>"

                            }else if(data[i].status == 4){
                                str +="<td>" + "筹集中" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "<span><a  href=\"javascript:;\" onclick=\"select('"+data[i].id+"')\" >下架</a></span></td>"
                            } else if(data[i].status ==5){
                                str +="<td>" + "筹集完（未提现）" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"\" >查看</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==6){
                                str +="<td>" + "筹集失败" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "<span><a  href=\"toUpdateProductInfo.do?id="+ data[i].id+"\" >修改</a></span>"+
                                    "<span><a class='fangqij' href=\"javascript:;\" onclick=\"fangqi('"+data[i].id+"')\" >放弃</a></span>"+
                                    "</td>"
                            }else if(data[i].status ==7){
                                str +="<td>" + "再上架，筹集中" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "</td>"
                            }
                            else if(data[i].status ==8){
                                str +="<td>" + "下架" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "<span><a  href=\"toUpdateProductInfo.do?id="+ data[i].id+"\" >修改</a></span>"+
                                    "<span><a class='fangqij' href=\"javascript:;\" onclick=\"fangqi('"+data[i].id+"')\" >放弃</a></span>"+
                                    "</td>"
                            }else if(data[i].status ==9){
                                str +="<td>" + "已放弃" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==10){
                                str +="<td>" + "提现申请中" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==11){
                                str +="<td>" + "筹集完,提现成功（待还款）" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==12){
                                str +="<td>" + "提现失败" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==13){
                                str +="<td>" + "还款中（未全部还款完成）" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "</td>"
                            }else if(data[i].status ==14){
                                str +="<td>" + "还款完成" + "</td>"+
                                    "<td><span><a  href=\"toborrowMoney_info.do?id="+ data[i].id+"&status="+data[i].status+"&borrowMoneyUserId="+data[i].borrowMoneyUserId+"\" >查看</a></span>" +
                                    "</td>"
                            }
                            str+="</tr>";
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
function select(id){
    $('.showhide').show();
    $('.zhezaocegn').show();
    var fangqiCauseBy = $("#fangqiCauseBy").val();
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    //点击确定放弃
    $("#toFangqi").click(function () {
        $.ajax({
            type: "post",
            url: basePath+"borrowMoney/updateStatus.do",
            data: {
                id : id,
                status : 8,
                fangqiCauseBy : fangqiCauseBy
            },
            dataType: "json",
            success: function (msg) {
                console.log(msg);
                if (msg.state == 0) {
                    alert("下架成功");
                    window.location.href = basePath + "toJsp/toborrowMoney_manage.do";
                } else {
                    alert("下架操作失败");
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

//放弃操作
function fangqi(id){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"borrowMoney/updateStatus.do",
        data: {
            id : id,
            status : 9
        },
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("已放弃");
                window.location.href = basePath + "toJsp/toborrowMoney_manage.do";
            } else {
                alert("操作失败");
            }
        },
        error : function(){
            alert("网络出现问题");
        }
    });
}