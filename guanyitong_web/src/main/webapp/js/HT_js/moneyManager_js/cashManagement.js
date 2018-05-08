$(function(){
    $('.menli').css({'clear':'both','margin-top':'50px'});
    $('.menlili').css({'background':'#f3f3f3','width':'150px'});
    $('.menlili').eq(0).css({'color':'red','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'800'});
    $('.menlili').click(function(){
        $('.menlili').css({'color':'#434343','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'100'});
        $(this).css({'color':'red','transform':'scale(1)','-webkit-transform':'scale(1)','font-weight':'800'});
    })
    $('.acon-table').hide();
    $('.acon-table').eq(0).show();
    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('td').css({'padding':'20px 10px'});
    $('.quespann').click(function(){
        $('.showhide').eq(0).show();
        $('.zhezaocegn').show();
    })
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
        $('.zhezaocegn').hide();
    })

    $('.quespannn').click(function(){
        $('.showhide').eq(1).show();
        $('.zhezaocegn').show();
    })
    $('.showhideng>span').click(function(){
        $('.showhide').hide();$('.zhezaocegn').hide();
    })
    $('.zhezaocegn').height(document.documentElement.clientHeight);

    //点击搜索
    $("#sou").click(function(){
        bBtn = true;
        $("#pageNum").text(1);
        var Sta =null;//全部提现
        initlist(Sta);
    });

    var Sta =null;//全部提现
    //初始化
    initlist(Sta);
    //点击li按钮
    $("li").click(function() {
        if ($(this).val() == 0) {//待处理提现
            Sta = 0;
        } else if ($(this).val() == 1) {//成功提现
            Sta = 1;
        } else if ($(this).val() == 2) {//失败提现
            Sta = 2;
        }else {
            Sta=null;
        }
        bBtn = true;
        initlist(Sta);
    });
})
var bBtn = true;
function initlist(Sta){
    var dataTab=window.document.getElementById("dataTab");
    var stu;
    var borrowMoneyUserId;
    var username;
    var minMoney=0;
    var maxMoney=0;
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    if($("#userID_name").val().length==8){
        borrowMoneyUserId=$("#userID_name").val();
    }else if($("#userID_name").val().length==11){
        username=$("#userID_name").val();
    }
    var realName= $("#realName").val();
    var idCard= $("#idCard").val();
    var txNumber= $("#txNumber").val();
    minMoney= $("#minMoney").val();
    maxMoney=$("#maxMoney").val();
    var startTime= $("#startTime").val();
    var endTime= $("#endTime").val();
    var sqUser= $("#sqUser").val();
    var myselect=document.getElementById("select_stu");
    var myIndex= myselect.selectedIndex;
    if(myselect.options[myIndex].value==0){
        stu=0;
    }else if(myselect.options[myIndex].value==1){
        stu=1;
    }else{
        stu=null;
    }

    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url:basePath+"withdrawMoney/selectWithdrawal.do",
        data:{
            pageNum: pageNum,
            pageSize:pageSize,
            username:username,
            borrowMoneyUserId:borrowMoneyUserId,
            realName:realName,
            idCard:idCard,
            txNumber:txNumber,
            minMoney:minMoney,
            maxMoney:maxMoney,
            startTime:startTime,
            endTime:endTime,
            sqUser:sqUser,
            userType:stu,
            status:Sta
        },
        success: function (msg) {
            console.log(msg);
            var str = "";
            var data= msg.data.pageInfo.list;
            var count= msg.data.allCount;
            $('#count').text(count);
            var  Money=msg.data.totalMoney;
            $('#totalJe').text(Money);
            var j = (pageNum-1)*pageSize+1;
            if(data.length!=0){
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<=count || (j == 1 && count == 1)) {
                    for (var i in data) {
                        var userType = '';
                        var userId_name = '';
                        if (data[i].username != null) {
                            userId_name = data[i].username;
                        } else if (data[i].borrowMoneyUserId != null) {
                            userId_name = data[i].borrowMoneyUserId;
                        }
                        if (data[i].userType == 0) {
                            userType = '出借人';
                        } else if (data[i].userType == 1) {
                            userType = '借款人';
                        }
                        str += "<tr>" +
                            "<td>" + (j++) + "</td>" +
                            "<td>" + userId_name + "</td>" +
                            "<td>" + data[i].realName + "</td>" +
                            "<td>" + data[i].txBank + "</td>" +
                            "<td>" + data[i].txNumber + "</td>" +
                            "<td>" + data[i].realName + "</td>" +
                            "<td>" + data[i].txMoney + "</td>" +
                            "<td>" + data[i].sxf + "</td>" +
                            "<td>" + userType + "</td>" +
                            "<td>" + data[i].txTime + "</td>" +
                            "<td>" + data[i].sqUser + "</td>"
                            //<%--0待提现（体现中），1提现成功，2提现失败--%>
                            if(data[i].status==0){
                               str +=  "<td>" + "待提现" + "</td>"+
                                "<td><span class='quespannn'><a href=\"javascript:;\" onclick=\"toupdate('"+data[i].id+"','1','"+data[i].borrowMoneyUserId+"','"+data[i].dzMoney+"')\" >确认提现成功</a></span></td>"
                            }else if(data[i].status==1){
                                str +=  "<td>" + "提现成功" + "</td>"+
                                "<td>"+"----------"+"</td>"
                            }else if(data[i].status==2){
                                str +=  "<td>" + "提现失败" + "</td>"+
                                    "<td><span class='quespannn'><a href=\"javascript:;\" onclick=\"SQTX_TWO('"+data[i].id+"','"+data[i].borrowMoneyUserId+"','"+data[i].dzMoney+"')\">申请提现</a></span></td>"
                            }

                            "</tr>";
                    }
                    dataTab.innerHTML = str;
                    if(bBtn){
                        $('.pageTest').page({
                            leng:Math.ceil(count/pageSize),
                            activeClass: 'activP',
                            clickBack: function (pageNum) {
                                $(this)[0].leng = Math.ceil(count / pageSize);
                                $("#pageNum").text(pageNum);
                                initlist(Sta);
                            }
                        });
                    }
                    bBtn = false;
                }else{//点击下一页没有数据
                    tbody.innerHTML = "此页暂无数据";
                }
        }else{
             dataTab.innerHTML="暂无数据";
                if(bBtn) {
                    $('.pageTest').page({
                        leng: 0,
                        activeClass: 'activP' //active 类样式定义
                    });
                }
            }
        }
    })
}

//提现失败，再次申请提现
function SQTX_TWO(id,borrowMoneyUserId,dzMoney) {
    alert("borrowMoneyUserId"+borrowMoneyUserId)
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"withdrawMoney/insertWithdrawMoney.do",
        data: {
            id : id,
            borrowMoneyUserId:borrowMoneyUserId,
            dzMoney:dzMoney
        },
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("再次申请成功");
                initlist(Sta);
            } else {
                alert("再次申请失败");
            }
        },
        error : function(){
            alert("网络出现问题");
        }
    });
}

//提现成功，提现失败，修改状态
function toupdate(id,status,borrowMoneyUserId,dzMoney){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"withdrawMoney/updateStatus.do",
        data: {
            id : id,
            borrowMoneyUserId:borrowMoneyUserId,
            dzMoney:dzMoney
        },
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("操作成功");
                initlist(Sta);
            } else {
                alert("操作失败");
            }
        },
        error : function(){
            alert("网络出现问题");
        }
    });
}