$(function(){
    createData()
    $('td').css({'padding':'20px 10px'});
    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('.quespan').click(function(){
        $('.showhide').show();
    })
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
    })
    //点击搜索
    $("#select").click(function(){
        createData();
    });
})
function createData() {
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    var minyuE=$("#minyuE").val();
    var maxyuE=$("#maxyuE").val();
    var minLJSY=$("#minLJSY").val();
    var maxLJSY=$("#maxLJSY").val();
    var tbody=window.document.getElementById("table");
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"moneyManage/selectUserMoney.do",
        data:{
            pageNum:1,
            pageSize:6,
            startTime:startTime,
            endTime:endTime,
            minyuE:minyuE,
            maxyuE:maxyuE,
            minLJSY:minLJSY,
            maxLJSY:maxLJSY,
        },
        success:function (msg) {
            var str="" ;
            var data = msg.data.list;
            console.log(msg);

            var j=1;
            for(var i in data){
                str+="<tr>"+
                    "<td>"+(j++)+"</td>"+
                    "<td>"+data[i].username+"</td>"+
                    "<td>"+data[i].bankNum+"</td>"+
                    "<td>"+data[i].realName+"</td>"+
                    "<td>"+data[i].zmoney+"</td>"+
                    "<td>"+data[i].yuE+"</td>"+
                    "<td>"+data[i].bhmoney+"</td>"+
                    "<td>"+data[i].tqzmoney+"</td>"+
                    "<td>"+data[i].dhkmoney+"</td>"+
                    "<td>"+data[i].ljsy+"</td>"+
                    "<td>"+data[i].createTime+"</td>"+
                    "<td><span><a href="+basePath+"moneyManage/selectUserMoney.do?id="+data[i].userId+">查看明细</a></span></td>"+
                    "</tr>"
            }
            tbody.innerHTML=str;
        }
    })
}