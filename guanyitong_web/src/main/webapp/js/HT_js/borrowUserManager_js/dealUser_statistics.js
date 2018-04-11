var param = {
    pageNum : null,
    pageSize : null,
    username : null,
    startTime : null,
    endTime : null,
    status : null
}
$(function(){
      createTBody();
    //点击搜索
    $("#select").on("click",function () {
       //搜索从第一页开始
            $("#pageNum").text(1);
            createTBody();
    });
});

//初始化列表
function createTBody(){
    param.pageNum = $("#pageNum").html();
    param.pageSize = $("#pageSize").html();
    param.username = $("#username").val();
    param.endTime = $("#endTime").val();
    param.startTime = $("#startTime").val();
    param.status = $("#select_id").val();
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/selectAllUser.do",
        data: param,
        success: function (msg) {
            console.log(msg)
            var str = "";
            var count = msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0){
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<count || (j == 1 && count == 1)){
                    for (i in data) {
                        str += "<tr>"+
                            "<td>" +(j++) + "</td>"+
                            "<td>" + data[i].username + "</td>" +
                            "<td>" + data[i].registTime + "</td>"

                        if(data[i].status == 1){
                            str +="<td>" + "否" + "</td>"

                        }else if(data[i].status ==2){
                            str +="<td>" + "是" + "</td>"

                        }
                    }
                    tbody.innerHTML = str;
                    $('.pageTest').page({
                        leng:Math.ceil(count/pageSize),
                        activeClass: 'activP' , //active 类样式定义
                        clickBack :function(page) {
                            $("#pageNum").text(page);
                            createTBody();
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
