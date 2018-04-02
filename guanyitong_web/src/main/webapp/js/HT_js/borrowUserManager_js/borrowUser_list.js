$(function(){
    alert("fskjdlgj");
    createTBody();

    $('.tjiaoP_a>span').find('input').css({'width':'91px','height':'30px'});
    $('.quespan').click(function(){
        $('.showhide').show();
    });
    $('.showhideng>span').click(function(){
        $('.showhide').hide();
    });

});

//初始化列表
function createTBody(){
    /*var checkDate = $('#checkDate').val();
    var orderNo = $('#orderNo').val();
    var sortFiled = $('#sortFiled').val();
    var hotelSeq = $('#hotelSeq').val();*/
    var tbody=window.document.getElementById("tbody-result");
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/selectAllBorrowUser.do",
        data: {
           pageNum:1,
            pageSize:2
        },
        success: function (msg) {
                var str = "";
                var data = msg.data.list;
                 console.log(msg);
                var j = 1;
                for (i in data) {
                    str += "<tr>" +
                        "<td>" + (j++) + "</td>" +
                        "<td>" + data[i].id + "</td>" +
                        "<td>" + data[i].charterNum + "</td>" +
                        "<td>" + data[i].apprroveName + "</td>" +
                        "<td>" + data[i].registerAddress + "</td>" +
                        "<td>" + data[i].borrowMoney + "</td>" +
                        "<td>" + data[i].legalIDCard + "</td>" +
                        "<td>" + data[i].address + "</td>" +
                        "<td>" + data[i].companyDescribe + "</td>" +
                        "<td>" + data[i].causeBy + "</td>" +
                    "<td><select> <option>==请选择</option> <option>==请选择1</option> <option>==请选择2</option> </select></td>" +

                    "<td><span><a href='#'>查看</a></span><span"+" class="+"quespan"+">确定审核人员</span></td>" +

                        "</tr>";
                }
                tbody.innerHTML = str;
        },
        error: function () {
            alert("查询失败")
        }
    });
}
