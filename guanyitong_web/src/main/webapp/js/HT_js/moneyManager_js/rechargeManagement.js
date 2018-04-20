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
        creatTable();
    });
})

function creatTable(){
    var stu;
    var username= $("#phone").val();
    var realname= $("#realname").val();
    var serial= $("#serial").val();
    var firstMoney= $("#firstMoney").val();
    var lastMoney= $("#lastMoney").val();
    var firstDate= $("#firstDate").val();
    var lastDate= $("#lastDate").val();
    var myselect=document.getElementById("select_stu");
    var myIndex= myselect.selectedIndex;
    if(myselect.options[myIndex].value==0){
        stu=0;
    }else if(myselect.options[myIndex].value==1){
        stu=1;
    }else{
        stu=null;
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
            pageNum:1,
            pageSize:6,
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
            var str="" ;
            var data = msg.data.list;
            console.log(msg);
            var j=1;
            for (var i in data){
                var statu='';
                if(data[i].status==1){
                    statu='充值成功';
                }else if (data[i].status==0){
                    statu='充值失败';
                }
                str+="<tr>"+
                    "<td>"+(j++)+"</td>"+
                    "<td>"+data[i].username+"</td>"+
                    "<td>"+data[i].realName+"</td>"+
                    "<td>"+data[i].serial+"</td>"+
                    "<td>"+data[i].rechargeMoney+"</td>"+
                    "<td>"+data[i].dzMoney+"</td>"+
                    "<td>"+data[i].rechargeTime+"</td>"+
                    "<td>"+statu+"</td>"+
                    "<td><span><a href="+basePath+"RechargeSheetVo/selectByrid.do?id="+data[i].id+">查看</a></span></td>"+
                    "</tr>"
            }
            tbody.innerHTML=str;
        },
        error: function () {
            alert("查询失败")
        }
    });
}