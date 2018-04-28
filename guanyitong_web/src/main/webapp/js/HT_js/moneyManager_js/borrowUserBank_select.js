$(function () {
    $("#fhui").click(function(){
        window.history.back(-1);
    });
    $("#yn").hide();
    if($("#yn").html()=="yes"){
        $("#shuoM").show();
    }else {
        $("#shuoM").hide();
    }
});

