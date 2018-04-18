$(function () {
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
        createTBody();
    });

    $("#fhui").click(function(){
        window.history.back(-1);
    });
});
var bBtn = true;
//初始化列表
function createTBody() {
    //根据标签id获取input的值
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var borrowMoneyUserId=$("#borrowMoneyUserId").val();
    var realName=$("#realName").val();
    var IDCardNumber=$("#IDCardNumber").val();
    var cardNo=$("#cardNo").val();
    var firstDate=$("#firstDate").val();
    var lastDate=$("#lastDate").val();
    //获取id为tbody-result的标签
    var tbody=window.document.getElementById("tbody-result");
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    //ajxa异步操作
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"BankCardManagementr/selectByUserBankcard.do",
        data:{
            pageNum:pageNum,
            pageSize:pageSize,
            borrowMoneyUserId:borrowMoneyUserId,
            realName:realName,
            IDCardNumber:IDCardNumber,
            cardNo:cardNo,
            firstDate:firstDate,
            lastDate:lastDate
        },
        success: function(msg){
            var str="" ;
            var count=msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            console.log(msg);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0) {
                $("#pageCount").text(Math.ceil(count/pageSize));
                if (j <= count || (j == 1 && count == 1)) {
                    for (var i in data) {
                        str += "<tr>" +
                            "<td>" + (j++) + "</td>" +
                            "<td>" + data[i].borrowMoneyUserId + "</td>" +
                            "<td>" + data[i].realName + "</td>" +
                            "<td>" + data[i].idcardNumber + "</td>" +
                            "<td>" + data[i].bankName + "</td>" +
                            "<td>" + data[i].cardNo + "</td>" +
                            "<td>" + data[i].YN + "</td>" +
                            "<td>" + data[i].submitTime + "</td>" +
                            "<td><span><a href=" + basePath + "BankCardManagementr/selectUserBankcardById.do?borrowMoneyUserId=" + data[i].borrowMoneyUserId + ">查看</a></span></td>" +
                            "</tr>";
                    }
                    result.innerHTML = str;
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
