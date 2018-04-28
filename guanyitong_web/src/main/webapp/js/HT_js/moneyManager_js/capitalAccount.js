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
        bBtn = true;
        $("#pageNum").text(1);
        createData();
    });
})
var bBtn = true;
function createData() {
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    var minyuE=$("#minyuE").val();
    var maxyuE=$("#maxyuE").val();
    var minLJSY=$("#minLJSY").val();
    var maxLJSY=$("#maxLJSY").val();
    var tbody=window.document.getElementById("table");
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"moneyManage/selectUserMoney.do",
        data:{
            pageNum:pageNum,
            pageSize:pageSize,
            startTime:startTime,
            endTime:endTime,
            minyuE:minyuE,
            maxyuE:maxyuE,
            minLJSY:minLJSY,
            maxLJSY:maxLJSY,
        },
        success:function (msg) {
            console.log(msg);
            var str="" ;
            var count = msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0){
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<=count || (j == 1 && count == 1)){
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
                        "<td><span><a href="+basePath+"AccountDetails/skipDetails.do?userId="+data[i].userId+"&username="+data[i].username+"&bankNum="+data[i].bankNum+"&realName="+data[i].realName+"&ZMoney="+data[i].zmoney+"&yuE="+data[i].yuE+"&BHMoney="+data[i].bhmoney+"&TQZMoney="+data[i].tqzmoney+"&DHKMoney="+data[i].dhkmoney+">查看明细</a></span></td>"+
                        "</tr>"
                }
                tbody.innerHTML=str;
                if(bBtn){
                    $('.pageTest').page({
                        leng:Math.ceil(count/pageSize),
                        activeClass: 'activP',
                        clickBack: function (pageNum) {
                            $(this)[0].leng = Math.ceil(count / pageSize);
                            $("#pageNum").text(pageNum);
                            createData();
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