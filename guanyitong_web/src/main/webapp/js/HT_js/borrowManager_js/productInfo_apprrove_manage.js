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
        param.Sta = "0,1,2";
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
                        //0待审核  1审核未通过 2审核通过，未开始筹集
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
                            if(data[i].status == 0){
                                str +=  "<td>" + "待审核" + "</td>"+
                                    "<td><span><a  href=\"productinfo_manage_info.do?id="+ data[i].id+"&status="+data[i].status+"\" >查看</a></span></td>"

                            }else if(data[i].status ==1){
                                str +="<td>" + "审核未通过" + "</td>"+
                                    "<td><span><a  href=\"productinfo_manage_info.do?id="+ data[i].id+"&status="+data[i].status+"\" >查看</a></span></td>"
                            }else if(data[i].status ==2){
                                str +="<td>" + "审核通过，未开始筹集" + "</td>"+
                                    "<td><span><a  href=\"productinfo_manage_info.do?id="+ data[i].id+"&status="+data[i].status+"\" >查看</a></td>"
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
