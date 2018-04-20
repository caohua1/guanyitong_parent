$(function () {
    createTBody();
    //点击搜索
    $("#select").click(function(){
        bBtn = true;
        createTBody();
    });


});
var bBtn = true;
//初始化列表
function createTBody() {
/*<div><span>用户ID:</span><span><input type="text" id="borrowMoneyUserId"></span></div>
    <div><span>真实姓名:</span><span><input type="text" id="realName"></span></div>
    <div><span>身份证号:</span><span><input type="text" id="idcardNumber"></span></div>
    <div><span>开户行:</span><span><input type="text" id="bankName"></span></div>
    <div><span>卡号:</span><span><input type="text" id="cardNo"></span></div>
    <div><span>还款本金:</span><span><input type="text" id="bj"></span></div>*/
    //根据标签id获取input的值
    var pageSize = $("#pageSize").text();
    var pageNum = $("#pageNum").text();
    var borrowMoneyUserId=$("#borrowMoneyUserId").val();
    var realName=$("#realName").val();
    var IDCardNumber=$("#idcardNumber").val();
    var cardNo=$("#cardNo").val();
    var bankName=$("#bankName").val();
    var bj=$("#bj").val();
    var startTime=$("#startTime").val();
    var endTime=$("#endTime").val();
    //获取id为tbody-result的标签
    var tbody=window.document.getElementById("tbody-result");
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    //ajxa异步操作
    $.ajax({
        type:"post",
        dataType:"json",
        url:basePath+"backMoney/backMoneyList.do",
        data:{
            pageNum:pageNum,
            pageSize:pageSize,
            borrowMoneyUserId:borrowMoneyUserId,
            realName:realName,
            IDCardNumber:IDCardNumber,
            cardNo:cardNo,
            startTime :startTime,
            endTime : endTime,
            bj : bj,
            bankName : bankName
        },
        success: function(msg){
            var str="" ;
            var count=msg.data.count;
            var j = (pageNum-1)*pageSize+1;
            $('#count').text(count);
            console.log(msg);
            var data = msg.data.pageInfo.list;
            if(data !=null && data.length>0) {
                $("#pageCount").text(Math.ceil(count/pageSize));
                if (j <= count || (j == 1 && count == 1)) {
                    for (var i in data) {
                        str += "<tr>" +
                            "<td>" + (j++) + "</td>" +
                            "<td>" + data[i].borrowMoneyUserId + "</td>" +
                            "<td>" + data[i].realName + "</td>" +
                            "<td>" + data[i].idcardNumber + "</td>" +
                            "<td>" + data[i].bankName + "</td>" +
                            "<td>" + data[i].cardNo + "</td>" +
                            "<td>" + data[i].backMoney + "</td>" +
                            "<td>" + data[i].lx + "</td>" +
                            "<td>" + data[i].bj + "</td>" +
                            "<td>" + data[i].backTime + "</td>" +
                            "<td>" + data[i].realBackTime + "</td>"
                            if(data[i].status == 0){
                               str += "<td>" +  "待还款" + "</td>"+/*id,borrowMoneyUserId,status,productInfoId,count*/
                                   "<td><span><a href=\"toborrowUserApprrove1_info.do?productInfoId="+ data[i].productInfoId+"\" >查看</a></span>" +
                                   "<span><a href=\"javascript:;\" onclick=\"toupdate('"+data[i].id+"','1','"+data[i].borrowMoneyUserId+"'," +
                                   "'"+data[i].productInfoId+"','"+data[i].count+"')\">确认还款成功</a></span>" +
                                   "<span><a href=\"javascript:;\" onclick=\"toupdate('"+data[i].id+"','2','"+data[i].borrowMoneyUserId+"'," +
                                   "'"+data[i].productInfoId+"','"+data[i].count+"')\">确认还款失败</a></span></td>"
                            }else if(data[i].status ==1){
                                str += "<td>" +  "已还款" + "</td>"+
                                    "<td><span><a href=\"javascript:;\" >查看</a></span></td>"
                            }else if(data[i].status==2){
                                str += "<td>" +  "还款失败" + "</td>"+
                                    "<td><span><a href=\"javascript:;\" >查看</a></span></td>"
                            }
                            str+= "</tr>";
                    }
                    tbody.innerHTML = str;
                    if(bBtn){
                        $('.pageTest').page({
                            leng:Math.ceil(count/pageSize),
                            activeClass: 'activP',
                            clickBack: function (pageNum) {
                                $(this)[0].leng = Math.ceil(count / pageSize);
                                $("#pageNum").text(pageNum);
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

//确认还款成功还是失败
function toupdate(id,status,borrowMoneyUserId,productInfoId,count){
    alert(id);
    alert(status);
    alert(borrowMoneyUserId);
    alert(productInfoId);
    alert(count);
    $('.showhide').show();
    $('.zhezaocegn').show();
    if(status ==1){
        $("#text").text("还款成功？");
    }else if(status ==2){
        $("#text").text("还款失败？");
    }
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $("#toupdate").click(function () {
        $('.showhide').hide();
        $('.zhezaocegn').hide();
        $.ajax({
            type: "post",
            url: basePath+"backMoney/backMoney.do",
            data: {
                id : id,
                status : status,
                borrowMoneyUserId : borrowMoneyUserId,
                productInfoId : productInfoId,
                count : count
            },
            dataType: "json",
            success: function (msg) {
                console.log(msg);
                if (msg.state == 0) {
                    alert("操作成功");
                    bBtn = true;
                    createTBody();
                } else {
                    alert("操作失败");
                }
            },
            error : function(){
                alert("网络出现问题");
            }
        });
    });

    $("#back").click(function () {
        $('.showhide').hide();
        $('.zhezaocegn').hide();
    });

    $("#x").click(function () {
        $('.showhide').hide();
        $('.zhezaocegn').hide();
    });
}