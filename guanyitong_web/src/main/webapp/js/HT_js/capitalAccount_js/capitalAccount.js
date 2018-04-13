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
    alert("开始");
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"moneyManage/selectUserMoney.do",
        data:{
            pageNum:1,
            pageSize:6,
            staTime:startTime,
            enTime:endTime,
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
                    "<td>"+data[i].ZMoney+"</td>"+
                    "<td>"+data[i].yuE+"</td>"+
                    "<td>"+data[i].BHMoney+"</td>"+
                    "<td>"+data[i].TQZMoney+"</td>"+
                    "<td>"+data[i].DHKMoney+"</td>"+
                    "<td>"+data[i].LJSY+"</td>"+
                    "<td>"+data[i].createTime+"</td>"+
                    "<td><span><a href="+basePath+"RechargeSheetVo/selectByrid.do?id="+data[i].userId+">查看明细</a></span></td>"+
                    "</tr>"
            }
        }
    })
}