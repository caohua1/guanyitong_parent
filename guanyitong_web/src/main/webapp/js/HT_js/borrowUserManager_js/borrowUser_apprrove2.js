

$(function(){
    var status =0;
    //初始化
     initlist(status);

    //点击li按钮
    $("li").click(function(){ //从第一页开始
        if( $(this).val()==0){//待审核
            status = 0;
        }else if($(this).val()==1){//审核失败
            status = 1;
        }else if($(this).val()==2){//审核成功
            status = 2;
        }
        $("#pageNum").text(1);
        initlist(status);
    });
    //点击搜索
    $("#select").click(function(){//搜索从第一页开始
        $("#pageNum").text(1);
        initlist(status);
    });
    //点击查看
    $("#select").on('click', function (){
        //单机后要执行的操作
        var id = $(this).val();
        location.href=basePath +"toJsp/toborrowUserApprrove1_info?id =" +id;

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
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
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
            pageNum:pageNum,
            pageSize:pageSize,
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
            var count = msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0){
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<count || (count == 1 && j == 1)){
                    console.log(msg);
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
                        str += "<td><span><a  href=\"toborrowUserApprrove1_info.do?id="+ data[i].id+"\" >查看</a></span></td>" +
                        "</tr>";
                    }
                    tbody.innerHTML = str;
                    $('.pageTest').page({
                        leng:Math.ceil(count/pageSize),
                        activeClass: 'activP',
                        clickBack: function (pageNum) {
                            $("#pageNum").text(pageNum);
                            initlist(status);
                        }
                    });
                }else{//点击下一页没有数据
                    tbody.innerHTML = "此页暂无数据";
                }
            }else{
                tbody.innerHTML = "暂无数据";
            }

        },
        error: function () {
            alert("查询失败")
        }
    });
}



