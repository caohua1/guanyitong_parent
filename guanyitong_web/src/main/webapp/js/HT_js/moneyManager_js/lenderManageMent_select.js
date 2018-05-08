$(function () {
    $('td').css({'padding': '20px 30px'});
    $('.tjiaoP_a>span').find('input').css({'width': '91px', 'height': '30px'});
    $('.quespan').click(function () {
        $('.showhide').show();
    })
    $('.showhideng>span').click(function () {
        $('.showhide').hide();
    })
    $("#fanH").click(function () {
        window.history.back(-1);
    });
})