var param = {
    ename : null,
    ephone : null,
    eroleId : null,
    epassword :null,
    eidcard : null
};

$(function(){

    $( '#epassword1' ).blur( function(){
        var epassword1 = $("#password1").val();
        var epassword = $("#password").val();
        if(epassword1 != epassword) {
            alert("两次输入得密码不同");
        }
    });

    //点击添加
    $("#toadd").click(function () {
        param.ename = $("#ename").val();
        param.ephone = $("#ephone").val();
        var eroleId = $("#eroleId").val();
        if(eroleId ==-1){
            param.eroleId= null;
        }else{
            param.eroleId = eroleId;
        }
        param.epassword = $("#epassword").val();
        var epassword1 = $("#epassword1").val();
        if(epassword1 !=param.epassword){
            alert("两次密码不一致！");
            return false;
        }
        param.eidcard = $("#eidcard").val();
        if(param.ename==""||param.ephone==""||param.eroleId==null || param.epassword==""){
            return false;
        }else{
            toadd();
        }

    });

});


//添加
function toadd(){
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"employee/insertEmployee.do",
        data: param,
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("添加成功");
                window.location.href = basePath + "toJsp/toEmployeeList.do";
            } else {
                alert("添加失败");
            }
        },
        error : function () {
            alert("网络出现问题");
        }
    });

}

