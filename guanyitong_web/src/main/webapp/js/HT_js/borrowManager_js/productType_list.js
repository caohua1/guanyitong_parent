var param = {
    pageNum : null,
    pageSize : null

}
$(function(){
      createTBody();
    //点击搜索
    $("#select").on("click",function () {
        bBtn = true;
       //搜索从第一页开始
            $("#pageNum").text(1);
            createTBody();
    });
});

var bBtn = true;
//初始化列表
function createTBody(){
    param.pageNum = $("#pageNum").text();
    param.pageSize = $("#pageSize").text();
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"product/selectAllProducts.do",
        data: param,
        success: function (msg) {
            console.log(msg);
            var str = "";
            var count = msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0){
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<=count || (j == 1 && count == 1)){
                    for (i in data) {
                        str += "<tr>"+
                            "<td>" +(j++) + "</td>"+
                            "<td>" + data[i].name + "</td>"

                        if(data[i].status == 0){
                            str +="<td>" + "停用" + "</td>"+
                            "<td><span><a  href=\"toborrowUserApprrove3_info.do?id="+ data[i].id+"\" >查看</a></span></td>"

                        }else if(data[i].status ==1){
                            str +="<td>" + "可用" + "</td>"+
                                "<td><span><a  href='' >查看</a></span><span><a  href=\"/product/updateProduct.do?id="+ data[i].id+"\&status=0\" >停用</a></span></td>"
                        }

                    }
                    tbody.innerHTML = str;
                    if(bBtn) {
                        $('.pageTest').page({
                            leng: Math.ceil(count / pageSize),
                            activeClass: 'activP', //active 类样式定义
                            clickBack: function (page) {
                                $(this)[0].leng = Math.ceil(count / pageSize);
                                $("#pageNum").text(page);
                                createTBody();
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
