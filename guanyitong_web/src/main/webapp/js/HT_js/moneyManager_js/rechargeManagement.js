$(function(){
    creatTable()
    $('td').css({'padding':'20px 30px'});
    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('.quespan').click(function(){
        $('.showhide').show();
    })
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
    })
    //点击搜索
    $("#select").click(function(){
        bBtn = true;
        $("#pageNum").text(1);
        creatTable();
    });
})
var bBtn = true;
function creatTable(){
    var stu;
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var username= $("#phone").val();
    var realname= $("#realname").val();
    var serial= $("#serial").val();
    var firstMoney= $("#firstMoney").val();
    var lastMoney= $("#lastMoney").val();
    var firstDate= $("#firstDate").val();
    var lastDate= $("#lastDate").val();
    if($("#select_stu").val()==1){
        stu=1;
    }else if($("#select_stu").val()==0){
        stu=0;
    }
    var tbody=window.document.getElementById("tbody-result");
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"RechargeSheetVo/selectRechargeSheetVo.do",
        data:{
            pageNum:pageNum,
            pageSize:pageSize,
            username:username,
            realName:realname,
            serial:serial,
            firstMoney:firstMoney,
            lastMoney:lastMoney,
            firstDate:firstDate,
            lastDate:lastDate,
            status:stu
        },
        success:function (msg){
            console.log(msg);
            var str="" ;
            var count = msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.PageInfo.list;
            if(data !=null && data.length>0) {
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<=count || (j == 1 && count == 1)) {
                    for (var i in data) {
                        var statu = '';
                        if (data[i].status == 1) {
                            statu = '充值成功';
                        } else if (data[i].status == 0) {
                            statu = '充值失败';
                        }
                        str += "<tr>" +
                            "<td>" + (j++) + "</td>" +
                            "<td>" + data[i].username + "</td>" +
                            "<td>" + data[i].realName + "</td>" +
                            "<td>" + data[i].serial + "</td>" +
                            "<td>" + data[i].rechargeMoney + "</td>" +
                            "<td>" + data[i].dzMoney + "</td>" +
                            "<td>" + data[i].rechargeTime + "</td>" +
                            "<td>" + statu + "</td>" +
                            "<td><span><a href=" + basePath + "RechargeSheetVo/selectByrid.do?id=" + data[i].id + ">查看</a></span></td>" +
                            "</tr>"
                    }
                    tbody.innerHTML = str;
                    tbody.innerHTML=str;
                    if(bBtn){
                        $('.pageTest').page({
                            leng:Math.ceil(count/pageSize),
                            activeClass: 'activP',
                            clickBack: function (pageNum) {
                                $(this)[0].leng = Math.ceil(count / pageSize);
                                $("#pageNum").text(pageNum);
                                creatTable();
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