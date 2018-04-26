
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
        bBtn = true;
        $("#pageNum").text(1);
         createTBody();

    });


});

var bBtn = true;
//初始化列表
function createTBody(){
    var id = $("#borrowMoneyUserId").val();
    var apprroveName = $("#apprroveName").val();
    var legalIDCard = $("#legalIDCard").val();
    var companyName = $("#companyName").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var Sta =  $("#select_id").val();
    if(Sta == -1){
        Sta = "0,1,2,3";
    }
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
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
                Sta:Sta,
                startTime:startTime,
                endTime:endTime
            },
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
                                   console.log(data[i].status);
                                   str += "<tr>"+
                                       "<td>" +(j++) + "</td>"+
                                       "<td>" + data[i].id + "</td>" +
                                       "<td>" + data[i].apprroveName + "</td>" +
                                       "<td>" + data[i].legalIDCard + "</td>" +
                                       "<td>" + data[i].companyName + "</td>" +
                                       "<td>" + data[i].charterNum + "</td>" +
                                       //待审核资料
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
                                   }
                                   if(data[i].auditUserName ==null || data[i].auditUserName==''){
                                       str += "<td><select> <option>==请选择</option> <option>==请选择1</option> <option>==请选择2</option> </select></td>"
                                   }else{
                                       str += "<td>"+data[i].auditUserName+"</td>"
                                   }

                                   if(data[i].status == 0){
                                      str += "<td><span><a  href=\"toborrowUserApprrove1_info.do?id="+ data[i].id+"\" >查看</a>" +
                                          "</span><span"+"class="+"quespan"+">确定审核人员</span></td>"
                                   }else if(data[i].status ==1){//资料认证审核未通过状态，全部字段都可以修改
                                       str += "<td><span><a  href=\"toborrowUserApprrove1_info.do?id="+ data[i].id+"\" >查看</a>" +
                                           "</span><span><a  href=\"toborrowUser_updateSta1.do?id="+data[i].id+"&status=0&Type=1\" >修改</a>" +
                                           "</span></td>"
                                   }else if(data[i].status ==2){
                                       str += "<td><span><a  href=\"toborrowUserApprrove1_info.do?id="+ data[i].id+"\" >查看</a>"
                                   }else if(data[i].status ==3){//额度申请审核未通过状态，再修改就是修改借款金额、借款期限，筹集期限
                                       str += "<td><span><a  href=\"toborrowUserApprrove1_info.do?id="+ data[i].id+"\" >查看</a>" +
                                           "</span><span><a  href=\"javascript:;\" onclick=\"toupdate('"+data[i].id+"','2')\" >修改</a>" +
                                           "</span></td>"

                                   }
                               "</tr>";

                               }
                                   tbody.innerHTML = str;

                            if(bBtn){
                                $('.pageTest').page({
                                    leng:Math.ceil(count/pageSize),
                                    activeClass: 'activP',
                                    clickBack: function (pageNum) {
                                        $(this)[0].leng = Math.ceil(count / pageSize);
                                        $("#pageNum").text(pageNum);
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


function toupdate(id,status){
    //弹框修改
    $(".showhide").show();
    $(".zhezaocegn").show();
    $("#updateMoney").click(function () {
        var borrowMoney = $("#borrowMoney").val();
        $(".updateMoney").hide();
        var local = window.location;
        var basePath = local.protocol+"//"+local.host+"/";
        $.ajax({
            type: "post",
            url: basePath+"BorrowMoneyUser/updateBorrowUser.do",
            data: {
                id :id,
                borrowMoney:borrowMoney,
                status :status
            },
            dataType: "json",
            success: function (msg) {
                console.log(msg);
                if (msg.state == 0) {
                    $(".showhide").hide();
                    $(".zhezaocegn").hide();
                    alert("修改成功");
                    createTBody();
                } else {
                    alert("修改失败");
                    $(".showhide").hide();
                    $(".zhezaocegn").hide();
                }
            },
            error : function(){
                alert("网络出现问题");
            }
        });
    });

    //点击取消
    $("#quxiao").click(function () {
        $(".showhide").hide();
        $(".zhezaocegn").hide();
    });

    //点击x
    $("#x").click(function () {
        $(".showhide").hide();
        $(".zhezaocegn").hide();
    });

}