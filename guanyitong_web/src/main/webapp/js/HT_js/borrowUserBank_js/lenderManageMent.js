$(function () {
    creatTable();
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
        creatTable();
    });
});
var bBtn = true;
function creatTable() {
    var phone=$("#phone").val();
    var realName=$("#realName").val();
    var idCard=$("#idCard").val();
    var bankNum=$("#bankNum").val();
    var firstDate=$("#firstDate").val();
    var lastDate=$("#lastDate").val();
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result");
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"LenderManagement/selectLenderManagement.do",
        data:{
            pageNum:pageNum,
            pageSize:pageSize,
            username:phone,
            realName:realName,
            idCard:idCard,
            bankNum:bankNum,
            first:firstDate,
            last:lastDate,
        },
        success:function (msg) {
            console.log(msg);
            var str="" ;
            var count = msg.data.acount;
            $('#count').text(count);
            var j = (pageNum-1)*pageSize+1;
            var data = msg.data.PageInfo.list;
            if(data !=null && data.length>0) {
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<=count || (j == 1 && count == 1)) {
                    for (var i in data) {
                        str += "<tr>" +
                            "<td>" + (j++) + "</td>" +
                            "<td>" + data[i].username + "</td>" +
                            "<td>" + data[i].realName + "</td>" +
                            "<td>" + data[i].idCard + "</td>" +
                            "<td>" + data[i].bankName + "</td>" +
                            "<td>" + data[i].bankNum + "</td>" +
                            "<td>" + data[i].createTime + "</td>" +
                            "<td><span><a href=" + basePath + "LenderManagement/selectById.do?userId=" + data[i].userId + ">查看</a></span></td>" +
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