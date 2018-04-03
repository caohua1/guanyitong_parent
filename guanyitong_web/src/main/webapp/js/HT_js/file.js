
function fileUpload(){
    $("input[name='myFile']")
    $.ajax({
        type: "post",
        dataType: "json",
        url: basePath+"BorrowMoneyUser/selectAllBorrowUser.do",
        data: {
            pageNum:1,
            pageSize:3
        },
        success: function (msg) {

        }
    });
}