
$(function(){
    createTBody(1);

    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('.quespan').click(function(){
        $('.showhide').show();
    });
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
    });
    //点击搜索
    $("#select").click(function(){
         createTBody(1);

    });


});

//初始化列表
function createTBody(pageNum){
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
    var pageSize = 3;
        $.ajax({
            type: "post",
            dataType: "json",
            url: basePath+"BorrowMoneyUser/selectAllBorrowUser.do",
            data: {
                pageNum:pageNum,
                pageSize:pageSize,
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
                var count = msg.data.count;
                var j = (pageNum-1)*pageSize+1;
                $('#count').text(count);
                var data = msg.data.pageInfo.list;
                    if(data !=null && data.length>0){
                        if(j<count || (j == 1 && count == 1)){
                            $('.pageTest').page({
                                leng:count/pageSize+1,
                                nowPage:pageNum,
                                activeClass: 'activP'  //active 类样式定义
                            });
                           for (i in data) {
                                      str += "<tr>"+
                                      "<td>" +(j++) + "</td>"+
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
                                clickBack=function(page){
                                    createTBody(pageNum);
                                }
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
