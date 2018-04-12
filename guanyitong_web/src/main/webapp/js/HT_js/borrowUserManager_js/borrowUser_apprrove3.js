var param = {
    pageNum : null,
    pageSize : null,
    id : null,
    apprroveName : null,
    companyName : null,
    startTime : null,
    endTime : null,
    auditUserName : null,
    status : null,
    type : 3
}
$(function(){
      createTBody();
    //点击搜索
    $("#select").on("click",function () {
       //搜索从第一页开始
            $("#pageNum").text(1);
            createTBody();
    });
});

//初始化列表
function createTBody(){
    param.pageNum = $("#pageNum").html();
    param.pageSize = $("#pageSize").html();
    param.id = $("#borrowMoneyUserId").val();
    param.apprroveName = $("#apprroveName").val();
    param.companyName = $("#companyName").val();
    param.endTime = $("#endTime").val();
    param.startTime = $("#startTime").val();
    //param.auditUserName = $("#auditUserName").val();
    param.status = $("#select_id").val();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
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
                if(j<count || (j == 1 && count == 1)){

               /* <th>序号</th>
                    <th>用户ID</th>
                    <th>真实姓名</th>
                    <th>企业名称</th>
                    <th>申请额度</th>
                    <th>跟踪人员</th>
                    <th>认证状态</th>

                    <th>合同状态</th>
 <th>提交时间</th>
                    <th>额度申请状态</th>
                    <th>操作</th>*/
                    for (i in data) {
                        str += "<tr>"+
                            "<td>" +(j++) + "</td>"+
                            "<td>" + data[i].id + "</td>" +
                            "<td>" + data[i].apprroveName + "</td>" +
                            "<td>" + data[i].companyName + "</td>" +
                            "<td>" + data[i].borrowMoney + "</td>" +
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

                        }else{
                            str +="<td>" + "资料审核成功" + "</td>"+
                                "<td>" + "额度审核成功" + "</td>"+
                                "<td>" + "合同确认成功" + "</td>"

                        }

                        str +=   "<td>" + data[i].createTime + "</td>"+

                                 "<td><span><a  href=\"toborrowUserApprrove3_info.do?id="+ data[i].id+"\" >查看</a></span><span"+" class="+"quespan"+">确定审核人员</span></td>" +

                                 "</tr>";
                    }
                    tbody.innerHTML = str;
                    $('.pageTest').page({
                        leng:Math.ceil(count/pageSize),
                        activeClass: 'activP' , //active 类样式定义
                        clickBack :function(page) {
                            $("#pageNum").text(page);
                            createTBody();
                        }
                    });

                }else{//点击下一页没有数据
                    tbody.innerHTML = "此页暂无数据";
                }
            }else{
                tbody.innerHTML = "暂无数据";
            }
        },
        error: function () {
            alert("查询失败")
        }
    });


}
