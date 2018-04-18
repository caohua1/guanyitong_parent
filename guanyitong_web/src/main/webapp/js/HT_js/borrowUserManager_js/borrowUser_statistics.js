var param = {
    pageNum : null,
    pageSize : null,
    id : null,
    apprroveName : null,
    companyName : null,
    startTime : null,
    endTime : null,
    auditUserName : null,
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
    param.id = $("#borrowMoneyUserId").val();
    param.apprroveName = $("#apprroveName").val();
    param.companyName = $("#companyName").val();
    param.endTime = $("#endTime").val();
    param.startTime = $("#startTime").val();
    param.Sta = $("#select_id").val();
    if(param.Sta == -1){
        param.Sta = "0,1,2,3,4,5,6";
    }
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/selectAllBorrowUser.do",
        data: param,
        success: function (msg) {
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
                                "<td>" + data[i].id + "</td>" +
                                "<td>" + data[i].apprroveName + "</td>" +
                                "<td>" + data[i].companyName + "</td>" +
                                "<td>" + data[i].borrowMoney + "</td>" +
                                "<td>" + data[i].createTime + "</td>"+
                                "<td>" + data[i].auditUserName + "</td>"
                            if(data[i].status == 0){
                                str +="<td>" + "资料待审核" + "</td>"+
                                    "<td>" + "----------" + "</td>"+
                                    "<td>" + "----------" + "</td>"
                            }else if(data[i].status ==1){
                                str +="<td>" + "资料审核失败" + "</td>"+
                                    "<td>" + "----------" + "</td>"+
                                    "<td>" + "----------" + "</td>"
                            }else if(data[i].status ==2){
                                str +="<td>" + "资料审核成功" + "</td>"+
                                    "<td>" + "额度待审核" + "</td>"+
                                    "<td>" + "----------" + "</td>"

                            } else if(data[i].status ==3){
                                str +="<td>" + "资料审核成功" + "</td>"+
                                    "<td>" + "额度审核失败" + "</td>"+
                                    "<td>" + "----------" + "</td>"

                            } else if(data[i].status ==4){
                                str +="<td>" + "资料审核成功" + "</td>"+
                                    "<td>" + "额度审核成功" + "</td>"+
                                    "<td>" + "合同待审核" + "</td>"

                            }else if(data[i].status ==5){
                                str +="<td>" + "资料审核成功" + "</td>"+
                                    "<td>" + "额度审核成功" + "</td>"+
                                    "<td>" + "合同确认失败" + "</td>"

                            }else if(data[i].status ==6){
                                str +="<td>" + "资料审核成功" + "</td>"+
                                    "<td>" + "额度审核成功" + "</td>"+
                                    "<td>" + "合同确认成功" + "</td>"

                            }

                            str += "<td><span><a  href=\"toborrowUserApprrove3_info.do?id="+ data[i].id+"\" >查看</a></span></td>" +

                                "</tr>";

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
