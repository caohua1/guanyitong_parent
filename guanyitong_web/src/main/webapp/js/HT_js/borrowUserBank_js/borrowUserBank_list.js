$(function () {
    alert("页面加载完后的动作");
    createTBody();
    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('.quespan').click(function(){
        $('.showhide').show();
    });
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
    });

});
//初始化列表
function createTBody() {
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
            pageSize:2
        },
        success: function(msg){
            var str = "";
            var data = msg.data.list;
            alert("返回报文"+data);
            console.log(msg);
            var j=1;
            //javaScript for循环遍历
            for (i in data){
                alert("进入循环遍历"+data[i]);
                str+="<tr>"+
                    "<td>" + (j++) + "</td>"+
                    "<td>" + data[i].id + "</td>"+
                    "<td>" + data[i].realName + "</td>"+
                    "<td>" + data[i].IDCardNumber + "</td>"+
                    "<td>" + data[i].bankName + "</td>"+
                    "<td>" + data[i].cardNo + "</td>"+
                    "<td>" + data[i].YN + "</td>"+
                    "<td>" + data[i].submitTime + "</td>"+
                    "</tr>";
            }
            tbody.innerHTML = str;
        },
        error: function () {
            alert("查询失败")
        }
    });

}
