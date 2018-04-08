
function test(userId,event) {
    //获取当前页面的url
    var local = window.location;
    var basePath = local.protocol+"//"+local.host+"/";
    event.stopPropagation();
    var offset = $(event.target).offset();
    $(".dianbot").css({top:offset.top+$(event.target).height+"px",left:offset.left});
    $(".dianbot").slideDown();
    $('#lists').html('');
    //通过后台接口请求数据
    $.ajax({
        url:basePath+"BankCardManagementr/selectDimId.do",
        type:"post",
        data:{
            borrowMoneyUserId:userId,
        },
        dataType:"json",
        success:function(msg){
            var result = msg.data;
            console.log(msg);
            for( i in result){
                var str = '<li>'+ result[i].borrowMoneyUserId +'</li>';
                $(str).appendTo("#lists")
            }
        },
        error:function(){
            console.error('请求失败！')
        }
    })
}
$(document).click(function(){

    $(".dianbot").slideUp('slow');
})

$(".dianbot").click(function(){
    $(this).hide();
})