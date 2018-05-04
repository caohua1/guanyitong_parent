var param = {
    id : null,
    ename :null,
    ephone :null,
    eidcard :null,
    epassword :null,
    eroleId :null

}

$(function(){
    //点击确定修改
    $("#toupdate").click(function () {
        param.id = $("#id").val();
        param.ename = $("#ename").val();
        param.ephone = $("#ephone").val();
        param.eroleId = $("#eroleId").val();
        param.epassword = $("#epassword").val();
        param.eidcard = $("#eidcard").val();
        updateEmployee();
    });

    //点击取消
    $("#back").on("click",function () {
        window.history.back(-1);
    });
});

//修改产品标
function updateEmployee(){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"employee/updateEmployee.do",
        data: param,
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("修改成功");
                window.location.href =basePath + "toJsp/toEmployeeList.do";
            } else {
                alert("修改失败");
            }
        },
        error : function(){
            alert("网络出现问题");
        }
    });
}

