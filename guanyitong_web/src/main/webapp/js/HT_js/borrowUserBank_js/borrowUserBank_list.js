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
        createTBody();
    });
});
//初始化列表
function createTBody() {
    //根据标签id获取input的值
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
            pageNum:1,
            pageSize:9,
            borrowMoneyUserId:borrowMoneyUserId,
            realName:realName,
            IDCardNumber:IDCardNumber,
            cardNo:cardNo,
            firstDate:firstDate,
            lastDate:lastDate
        },
        success: function(msg){
            var str="" ;
            var data = msg.data.list;
            console.log(msg);
            var j=1;
            //javaScript for循环遍历
            for (var i in data){
                str+="<tr>"+
                    "<td>" + (j++) + "</td>"+
                    "<td>" + data[i].borrowMoneyUserId + "</td>"+
                    "<td>" + data[i].realName + "</td>"+
                    "<td>" + data[i].idcardNumber + "</td>"+
                    "<td>" + data[i].bankName + "</td>"+
                    "<td>" + data[i].cardNo + "</td>"+
                    "<td>" + data[i].YN + "</td>"+
                    "<td>" + data[i].submitTime + "</td>"+
                    "<td><span><a href="+basePath+"BankCardManagementr/selectUserBankcardById.do?userId="+data[i]+">查看</a></span></td>"+
                    "</tr>";
            }
            result.innerHTML=str;
        },
        error: function () {
            alert("查询失败")
        }
    });

}
