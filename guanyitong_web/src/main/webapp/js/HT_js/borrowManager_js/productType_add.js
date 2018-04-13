var param = {
    name : null
};

$(function(){
      //点击添加
      $("#add").on("click",function(){
          toadd();
      });

});

//添加
function toadd(){
    param.name = $("#name").val();
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    if(param.name ==""){
        alert("请输入标种名");
        return false;
    }
    $.ajax({
        type: "post",
        url: basePath+"product/insertProduct.do",
        data: param,
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("添加成功");
                window.location.href = basePath + "toJsp/toproductType_list.do";
            } else {
                alert("添加失败");
            }
        }
    });

}

