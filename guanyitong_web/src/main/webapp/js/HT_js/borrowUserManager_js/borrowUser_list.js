

$(function(){
    createTBody();

    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('.quespan').click(function(){
        $('.showhide').show();
    });
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
    });
    //点击搜索
    $("#select").click(function(){
         createTBody();
    });
});

//初始化列表
function createTBody(){
    var id = $("#borrowMoneyUserId").val();
    var apprroveName = $("#apprroveName").val();
    var legalIDCard = $("#legalIDCard").val();
    var companyName = $("#companyName").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var status = $("#select_id").val();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/selectAllBorrowUser.do",
        data: {
            pageNum:1,
            pageSize:3,
            id:id,
            apprroveName:apprroveName,
            legalIDCard:legalIDCard,
            companyName:companyName,
            status:status,
            startTime:startTime,
            endTime:endTime
        },
        success: function (msg) {
                var str = "";
                $('#count').text( msg.data.count);
                var data = msg.data.pageInfo.list;
                console.log(msg);
                console.log(msg.data.count);
                console.log(data);
                var j = 1;
                if(data !=null && data.length>0){
                    for (i in data) {
                        str += "<tr>" +
                            "<td>" + (j++) + "</td>" +
                            "<td>" + data[i].id + "</td>" +
                            "<td>" + data[i].apprroveName + "</td>" +
                            "<td>" + data[i].legalIDCard + "</td>" +
                            "<td>" + data[i].companyName + "</td>" +
                            "<td>" + data[i].charterNum + "</td>" +
                            "<td>" + data[i].legalIDCard + "</td>" +
                            "<td>" + data[i].createTime + "</td>"
                        if(data[i].status == 0){
                            str +="<td>" + "待审核" + "</td>"+
                                "<td>" + "待审核" + "</td>"
                        }else if(data[i].status ==1){
                            str +="<td>" + "审核失败" + "</td>"+
                                "<td>" + "待审核" + "</td>"
                        }else if(data[i].status ==2){
                            str +="<td>" + "审核成功" + "</td>"+
                                "<td>" + "待审核" + "</td>"
                        }else if(data[i].status ==3){
                            str +="<td>" + "审核成功" + "</td>"+
                                "<td>" + "审核失败" + "</td>"
                        }else if(data[i].status ==4){
                            str +="<td>" + "审核成功" + "</td>"+
                                "<td>" + "审核成功" + "</td>"
                        }

                        str += "<td><select> <option>==请选择</option> <option>==请选择1</option> <option>==请选择2</option> </select></td>" +

                            "<td><span><a href='#'>查看</a></span><span"+" class="+"quespan"+">确定审核人员</span></td>" +

                            "</tr>";
                    }
                    tbody.innerHTML = str;
                }else{
                    tbody.innerHTML = "暂无数据";
                }

        },
        error: function () {
            alert("查询失败")
        }
    });
}
