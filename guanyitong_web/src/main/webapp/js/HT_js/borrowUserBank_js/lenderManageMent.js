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
        creatTable();
    });
});
function creatTable() {
    var phone=$("#phone").val();
    var realName=$("#realName").val();
    var idCard=$("#idCard").val();
    var bankNum=$("#bankNum").val();
    var firstDate=$("#firstDate").val();
    var lastDate=$("#lastDate").val();
    var tbody=window.document.getElementById("tbody-result");
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"LenderManagement/selectLenderManagement.do",
        data:{
            pageNum:1,
            pageSize:2,
            username:phone,
            realName:realName,
            idCard:idCard,
            bankNum:bankNum,
            first:firstDate,
            last:lastDate,
        },
        success:function (msg) {
            var str="" ;
            var data = msg.data.list;
            console.log(msg);
            var j=1;
            for (var i in data){
                str+="<tr>"+
                    "<td>"+ (j++) +"</td>"+
                    "<td>"+data[i].username+"</td>"+
                    "<td>"+data[i].realName+"</td>"+
                    "<td>"+data[i].idCard+"</td>"+
                    "<td>"+data[i].bankName+"</td>"+
                    "<td>"+data[i].bankNum+"</td>"+
                    "<td>"+data[i].createTime+"</td>"+
                    "<td><span><a href="+basePath+"LenderManagement/selectById.do?userId="+data[i].userId+">查看</a></span></td>"+
                    "</tr>";
            }
            tbody.innerHTML=str;
        },
        error: function () {
            alert("查询失败")
        }
    });
}