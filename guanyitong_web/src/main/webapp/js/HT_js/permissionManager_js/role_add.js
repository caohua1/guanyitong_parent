var permissionIds ="";

$(function(){


    //复选框全选
    $("ul.categories input").click(function(){
        var children = $(this).next();
        // alert($(this).val());
        var isChecked = $(this).prop("checked");

        if(children){
            if(isChecked){
                children.find("input").prop("checked","checked");
            }else{
                children.find("input").removeProp("checked");
            }
        }

        //同级分类当前选中的个数
        var checkedCount = $(this).parent().parent().find(">li >input:checked").length;
        //同级分类当总的个数
        var count = $(this).parent().parent().find(">li >input").length;

        if(checkedCount == count){
            $(this).parent().parent().parent().find(">input").prop("checked","checked");
            // alert($(this).parent().parent().parent().find(">input").val());
        }else{
            $(this).parent().parent().parent().find(">input").removeProp("checked");

        }

    });


    //点击添加
    $("#add").on("click",function () {

        $.each($("input[name=lvl2]"),function(){
            if(this.checked){
                permissionIds += $(this).val() + ",";
            }else{
                $.each($(this).parent().children().children().find("input[name=lvl3]:checkbox:checked"),function () {
                    permissionIds += $(this).val() + ",";
                });
            }
        });
        toadd();
    });



});


//添加
function toadd(){
    var pname = $("#pname").val();
    if(pname == "" && permissionIds!=""){
        alert("请输入角色名称!");
        return false;
    }else if(pname != "" && permissionIds==""){
        alert("请选择至少一个权限!");
        return false;
    }else if(pname == "" && permissionIds==""){
        alert("请填写角色名称并选择至少一个权限!")
        return false;
    }

    //复选框的id，放到数组里

    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    $.ajax({
        type: "post",
        url: basePath+"post/insertPost.do",
        data: {
            pname :pname,
            permissionIds :permissionIds
        },
        dataType: "json",
        success: function (msg) {
            console.log(msg);
            if (msg.state == 0) {
                alert("添加成功");
                window.location.href = basePath + "toJsp/toRoleList.do";
            } else {
                alert("添加失败");
            }
        },
        error : function () {
            alert("网络出现问题");
        }
    });

}

