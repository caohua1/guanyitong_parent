
$(function(){
    var status =0;
     initlist(status);

    //点击li按钮
    $("li").click(function(){
        if( $(this).val()==0){//待审核
            status = 0;
        }else if($(this).val()==1){//审核失败
            status = 1;
        }else if($(this).val()==2){//审核成功
            status = 2;
        }
        initlist(status);
    });
    //点击搜索
    $("#select").click(function(){
        alert(status);
        initlist(status);
    });
});

//初始化列表
function initlist(status){
    var id = $("#borrowMoneyUserId").val();
    var apprroveName = $("#apprroveName").val();
    var legalIDCard = $("#legalIDCard").val();
    var companyName = $("#companyName").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var tbody = "";
    if(status ==0){
         tbody= window.document.getElementById("tbody-result1");
    } else if(status==1){
         tbody=window.document.getElementById("tbody-result2");
    }else if(status==2){
         tbody=window.document.getElementById("tbody-result3");
    }
    /**/
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/selectAllBorrowUser.do",
        data: {
            pageNum:1,
            pageSize:3,
            id:id,
            apprroveName:apprroveName,
            legalIDCard:legalIDCard,
            companyName:companyName,
            status:status,
            startTime:startTime,
            endTime:endTime
        },
        success: function (msg) {
            var str = "";
            $('#count').text( msg.data.count);
            var data = msg.data.pageInfo.list;
            console.log(msg);
            var j = 1;
            if(data !=null && data.length>0){
                for (i in data) {
                    str += "<tr>" +
                        "<td>" + (j++) + "</td>" +
                        "<td>" + data[i].id + "</td>" +
                        "<td>" + data[i].apprroveName + "</td>" +
                        "<td>" + data[i].legalIDCard + "</td>" +
                        "<td>" + data[i].companyName + "</td>" +
                        "<td>" + data[i].charterNum + "</td>"
                        if(status ==0){
                        str += "<td>" + "待审核资料：证书" + "</td>"
                        }
                        str +=  "<td>" + data[i].createTime + "</td>"

                        if(data[i].status == 0){
                            str +="<td>" + "待审核" + "</td>"
                        }else if(data[i].status ==1){
                            str +="<td>" + "审核失败" + "</td>"
                        }else if(data[i].status ==2){
                            str +="<td>" + "审核成功" + "</td>"
                        }

                        str += "<td>" + data[i].auditUserName + "</td>"
                        if(status==1){
                        str+="<td>" + data[i].causeBy + "</td>"
                        }
                        str += "<td><span><a href='javascript:selectById('"+data[i].id+"')'  >查看</a></span><span><a href='javascript:;'>修改</a></span></td>" +
                        "</tr>";
                }
                tbody.innerHTML = str;
            }else{
                tbody.innerHTML = "暂无数据";
            }

        },
        error: function () {
            alert("查询失败")
        }
    });
}


//点击查看
function selectById(id){
    window.location.href= basePath +"toJsp/toborrowUserApprrove1_info?id =" +id;
}