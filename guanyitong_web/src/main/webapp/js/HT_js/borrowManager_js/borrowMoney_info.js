$(function(){

       //初始化，基本信息展示
       baseInfo();
       //点击菜单，切换
        $("li").click(function () {
            var value = $(this).val();
            if(value ==1){//基本信息
                baseInfo();
            }else if(value ==2){//出借人列表
                bBtn = true;
                $("#pageNum").text(1);
                dealUserList();
            }else if(value ==3){//还款计划,没有分页
                backMoney();
            }
        })

});

var bBtn = true;
//查询基本信息的接口
function baseInfo(){
    var id = $("#id").val();
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"product/selectProductInfoById.do",
        data: {
            id:id
        },
        success: function (msg) {
            var data = msg.data;

            if(msg.data!=null){

                if(data.no !=null && data.no !=''){
                    $("#NO").html(data.no);
                }else{
                    $("#NO").html("暂无数据");
                }
                if(data.borrowMoneyUserId !=null && data.borrowMoneyUserId !=''){
                    $("#borrowMoneyUserId").html(data.borrowMoneyUserId);
                }else{
                    $("#borrowMoneyUserId").html("暂无数据");
                }

                if(data.no !=null && data.no !=''){
                    $("#NO").html(data.no);
                }else{
                    $("#NO").html("暂无数据");
                }

                if(data.status !=null ){
                    if(data.status==2){
                        $("#productStatus").html("未开始筹集");
                    }else if(data.status==4){
                        $("#productStatus").html("筹集中");
                    }else if(data.status==5){
                        $("#productStatus").html("筹集完成（待提现）");
                    }else if(data.status==6){
                        $("#productStatus").html("筹集失败");
                    }else if(data.status==7){
                        $("#productStatus").html("再上架，筹集中");
                    }else if(data.status==8){
                        $("#productStatus").html("下架");
                    }else if(data.status==9){
                        $("#productStatus").html("已放弃");
                    }else if(data.status==10){
                        $("#productStatus").html("提现申请中");
                    }else if(data.status==11){
                        $("#productStatus").html("提现成功（待还款）");
                    }else if(data.status==12){
                        $("#productStatus").html("提现失败");
                    }else if(data.status==13){
                        $("#productStatus").html("还款中，未还款完成");
                    }else if(data.status==14){
                        $("#productStatus").html("还款完成");
                    }

                }else{
                    $("#status").html("暂无数据");
                }

                if(data.yield !=null ){
                    $("#yield").html(data.yield/100 + "%");
                }else{
                    $("#yield").html("暂无数据");
                }


                if(data.symoney !=null ){
                    $("#SYMoney").html(data.symoney);
                }else{
                    $("#SYMoney").html("暂无数据");
                }

                if(data.createTime !=null && data.createTime !=''){
                    $("#createTime").html(data.createTime);
                }else{
                    $("#createTime").html("暂无数据");
                }

                if(data.name !=null ){
                    $("#name").html(data.name);
                }else{
                    $("#name").html("暂无数据");
                }

                if(data.zmoney !=null ){
                    $("#ZMoney").html(data.zmoney);
                }else{
                    $("#ZMoney").html("暂无数据");
                }


                if(data.backMoneyType !=null ){
                    $("#backMoneyType").html(data.backMoneyType);
                }else{
                    $("#backMoneyType").html("暂无数据");
                }



                if(data.zmoney !=null && data.symoney !=null){
                    $("#jinDu").html((data.zmoney-data.symoney)*100/data.zmoney + "%");
                }else{
                    $("#jinDu").html("暂无数据");
                }

                if(data.ZUserCount !=null ){
                    $("#ZUserCount").html(data.ZUserCount);
                }else{
                    $("#ZUserCount").html("暂无数据");
                }

                if(data.raiseMoneyMonth !=null ){
                    $("#raiseMoneyMonth").html(data.raiseMoneyMonth);
                }else{
                    $("#raiseMoneyMonth").html("暂无数据");
                }

                if(data.monthNum !=null ){
                    $("#monthNum").html(data.monthNum);
                }else{
                    $("#monthNum").html("暂无数据");
                }

                if(data.YesNo !=null ){
                    $("#YesNo").html(data.YesNo);
                }else{
                    $("#YesNo").html("暂无数据");
                }


                if(data.moneyUse !=null ){
                    $("#moneyUse").html(data.moneyUse);
                }else{
                    $("#moneyUse").html("暂无数据");
                }


                if(data.coverImage !=null && data.coverImage !=''){
                    $('#coverImage').show();
                    $('#coverImage').attr('src', "http://127.0.0.1"+data.coverImage);
                }else{
                    $('#img').html("暂无图片")
                }

            }else{
                alert("网络错误");
            }
        },
        error: function () {
            alert("查询失败")
        }
    });
}

//查询出借人列表
function dealUserList(){
    var id =  $("#id").val();
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var tbody=window.document.getElementById("tbody-result1");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"borrowMoney/selectUserProductinfo.do",
        data: {
            productInfoId : id,
            pageNum : pageNum,
            pageSize : pageSize
        },
        success: function (msg) {
            console.log(msg);
            var str = "";
            var count = msg.data.count; //出借总人数
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0){
                $("#pageCount").text(Math.ceil(count/pageSize));
                if(j<=count || (j == 1 && count == 1)){
                    for (i in data) {
                        str += "<tr>"+
                            "<td>" +(j++) + "</td>"+
                            "<td>" + data[i].username + "</td>"+
                            "<td>" + data[i].dealMoney + "</td>"+
                            "<td>" + data[i].createTime + "</td></tr>"

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

//查询还款计划
function backMoney(){
    var borrowMoneyUserId = $("#borrowMoneyUserId").text();
    var id =  $("#id").val();
    var tbody=window.document.getElementById("tbody-result2");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"borrowMoney/selectAllBackMoney.do",
        data: {
            productInfoId : id,
            borrowMoneyUserId : borrowMoneyUserId
        },
        success: function (msg) {
            console.log(msg);
            var str = "";
            var j = 1;
            $("#productName").text($("#NO").text());
            $("#status1").text($("#productStatus").text())
            $("#yield1").text($("#yield").text());
            $("#bx").text(msg.data.allBjAndLx);
            $("#bj").text(msg.data.allBj);
            $("#lx").text(msg.data.allLx);
            var data = msg.data.backMoneyList;
            if(data !=null && data.length>0){
                    for (i in data) {
                        str += "<tr>"+
                            "<td>" +(j++) + "</td>"+
                            "<td>" + data[i].backTime + "</td>"+
                            "<td>" + data[i].backMoney + "</td>"+
                            "<td>" + data[i].lx + "</td>"+
                            "<td>" + data[i].bj + "</td>"+
                            "<td>" + data[i].realBackTime + "</td>"
                            if(data[i].status==0){
                                str += "<td>" + "未还款" + "</td>"
                            }else if(data[i].status==1){
                                str += "<td>" + "已还款" + "</td>"
                            }
                          str += "</tr>";
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