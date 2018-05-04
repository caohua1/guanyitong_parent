var param = {
    pageNum : null,
    pageSize : null,
    id : null

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
    var id = $("#id").val();
    if(id==-1){
        param.id = null;
    }else {
        param.id = id;
    }
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"post/selectAllPost.do",
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
                            "<td>" +data[i].pname+ "</td>"
                        if(data[i].pstatus == 0){
                            str += "<td><span><a  href=\"/post/toupdate.do?id="+ data[i].id+"\" >修改</a></span></td>"

                        }else if(data[i].pstatus ==1){
                            str += "<td><span><a  href=\"/post/toupdate.do?id="+ data[i].id+"\" >修改</a></span>" +
                                "<span><a   href=\"javascript:;\" onclick=\"todelete('"+data[i].id+"')\" >停用</a></span></td>"

                        }
                        str+="</tr>";
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

function todelete(id){
    $('.showhide').show();
    $('.zhezaocegn').show();
    $("#span").text("确定要停用此职位吗？");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    //点击确定放弃
   $("#update").click(function () {
        $('.showhide').hide();
        $('.zhezaocegn').hide();
        $.ajax({
            type: "post",
            url: basePath+"post/deletePost.do",
            data: {
                id : id
            },
            dataType: "json",
            success: function (msg) {
                console.log(msg);
                if (msg.state == 0) {
                    alert("删除成功");
                    createTBody();
                } else {
                    alert("删除失败");
                }
            },
            error : function(){
                alert("网络出现问题");
            }

          })
        });

        //点击取消
        $("#quxiao").click(function () {
            $('.showhide').hide();
            $('.zhezaocegn').hide();
        });

        //点击x
        $("#x").click(function () {
            $('.showhide').hide();
            $('.zhezaocegn').hide();
        });

}