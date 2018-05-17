$(function () {
    $("ul#lists").on("click","li",function(){
    for (var item in UserBankcardList){
    if($(this).text()==UserBankcardList[item].id){
    $("#btnShow").val(addPreZero(UserBankcardList[item].id));
    $("#realName").val(UserBankcardList[item].apprroveName);
    $("#IDCardNumber").val(UserBankcardList[item].legalIDCard);
    }
}
    })
    $("#qXiao").click(function(){
        window.history.back(-1);
    });
})
function isIdCardNo() {
    var num=$("#IDCardNumber").val()
    if(num!=""&&num!=null){
    num = num.toUpperCase();           //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
        //alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
        document.getElementById("IDCardInfo").innerHTML="身份证号长度不正确或不符合规定！";
        return false;
    }
    //验证前2位，城市符合
    var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
    if(aCity[parseInt(num.substr(0,2))]==null){
        document.getElementById("IDCardInfo").innerHTML="身份证号不正确或不符合规定！";
        return false;
    }
    //alert('城市:'+aCity[parseInt(num.substr(0,2))]);

    //下面分别分析出生日期和校验位
    var len, re; len = num.length;
    if (len == 15) {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);  //检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay; bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            document.getElementById("IDCardInfo").innerHTML="身份证号的出生日期不对！";
            return false;
        } else { //将15位身份证转成18位 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
            for(i = 0; i < 17; i ++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            num += arrCh[nTemp % 11];
            document.getElementById("IDCardInfo").innerHTML="正确";
            return true;
        }
    }
    if (len == 18) {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);  //检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay; bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            //alert(dtmBirth.getYear());
            //alert(arrSplit[2]);
            document.getElementById("IDCardInfo").innerHTML="身份证号的出生日期不对！";
            return false;
        }
        else { //检验18位身份证的校验码是否正确。 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            for(i = 0; i < 17; i ++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != num.substr(17, 1)) {
                //alert('18位身份证的校验码不正确！应该为：' + valnum);
                document.getElementById("IDCardInfo").innerHTML="18位身份证号的校验码不正确！";
                return false;
            }
            document.getElementById("IDCardInfo").innerHTML="正确";
            return true;
        }
    } return false;
    }else {

    }
}
function message(){
    var bankno=$("#cardNo").val();
    if(bankno!=""&&bankno!=null){
    var lastNum=bankno.substr(bankno.length-1,1);//取出最后一位（与luhm进行比较）
    var first15Num=bankno.substr(0,bankno.length-1);//前15或18位
    var newArr=new Array();
    for(var i=first15Num.length-1;i>-1;i--){    //前15或18位倒序存进数组
        newArr.push(first15Num.substr(i,1));
    }
    var arrJiShu=new Array();  //奇数位*2的积 <9
    var arrJiShu2=new Array(); //奇数位*2的积 >9

    var arrOuShu=new Array();  //偶数位数组
    for(var j=0;j<newArr.length;j++){
        if((j+1)%2==1){//奇数位
            if(parseInt(newArr[j])*2<9)
                arrJiShu.push(parseInt(newArr[j])*2);
            else
                arrJiShu2.push(parseInt(newArr[j])*2);
        }
        else //偶数位
            arrOuShu.push(newArr[j]);
    }

    var jishu_child1=new Array();//奇数位*2 >9 的分割之后的数组个位数
    var jishu_child2=new Array();//奇数位*2 >9 的分割之后的数组十位数
    for(var h=0;h<arrJiShu2.length;h++){
        jishu_child1.push(parseInt(arrJiShu2[h])%10);
        jishu_child2.push(parseInt(arrJiShu2[h])/10);
    }

    var sumJiShu=0; //奇数位*2 < 9 的数组之和
    var sumOuShu=0; //偶数位数组之和
    var sumJiShuChild1=0; //奇数位*2 >9 的分割之后的数组个位数之和
    var sumJiShuChild2=0; //奇数位*2 >9 的分割之后的数组十位数之和
    var sumTotal=0;
    for(var m=0;m<arrJiShu.length;m++){
        sumJiShu=sumJiShu+parseInt(arrJiShu[m]);
    }

    for(var n=0;n<arrOuShu.length;n++){
        sumOuShu=sumOuShu+parseInt(arrOuShu[n]);
    }

    for(var p=0;p<jishu_child1.length;p++){
        sumJiShuChild1=sumJiShuChild1+parseInt(jishu_child1[p]);
        sumJiShuChild2=sumJiShuChild2+parseInt(jishu_child2[p]);
    }
    //计算总和
    sumTotal=parseInt(sumJiShu)+parseInt(sumOuShu)+parseInt(sumJiShuChild1)+parseInt(sumJiShuChild2);

    //计算Luhm值
    var k= parseInt(sumTotal)%10==0?10:parseInt(sumTotal)%10;
    var luhm= 10-k;

    if(lastNum==luhm && lastNum.length != 0){
        document.getElementById("banknoInfo").innerHTML="验证通过";
        return true;
    }
    else{
        document.getElementById("banknoInfo").innerHTML="银行卡号必须符合校验";
        return false;
    }
    }else {
        document.getElementById("banknoInfo").innerHTML="请填写银行卡号";
    }
}
function addPreZero(num){
    return ('0000000'+num).slice(-8);
}
function dim(userId,event) {
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
                var str = '<li class="text">'+ addPreZero(result[i].id) +'</li>';
                $(str).appendTo("#lists");
                listArry(new person(result[i].id,result[i].apprroveName,result[i].legalIDCard));
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
function person(id,apprroveName,legalIDCard)
{
    this.id=id;
    this.apprroveName=apprroveName;
    this.legalIDCard=legalIDCard;
}
var UserBankcardList=new Array()
function listArry(userBankcard) {
    UserBankcardList.push(userBankcard);
}
function addUserBank() {
    if($("#btnShow").val()!=""&&$("#realName").val()!=""&&$("#IDCardNumber").val()!=""&&$("#cardNo").val()!=""&&$("#bankName").val()!=""&&$("#openAccountRegion").val()!=""&&$("#phone").val()!=""){
        var IDCardInfo=document.getElementById("IDCardInfo").innerHTML;
        if(IDCardInfo!="身份证号长度不正确或不符合规定！"&&IDCardInfo!="身份证号不正确或不符合规定！"&&IDCardInfo!="身份证号的出生日期不对！"&&IDCardInfo!="18位身份证号的校验码不正确！"){
            var banknoInfo=document.getElementById("banknoInfo").innerHTML;
        if(banknoInfo!="银行卡号必须符合校验"&&banknoInfo!="请填写银行卡号"){
    //获取当前页面的url
    var local = window.location;
    var Path = local.protocol+"//"+local.host+"/";
    var YN='';
    if($('#YN').attr('checked')=='checked'){
        YN='yes';
    }else{
        YN='no';
    }
    $.ajax({
        url:Path+"BankCardManagementr/addUserBankcard.do",
        type:"post",
        data:{
            borrowMoneyUserId:$("#btnShow").val(),
            realName:$("#realName").val(),
            IDCardNumber:$("#IDCardNumber").val(),
            cardNo:$("#cardNo").val(),
            bankName:$("#bankName").val(),
            openAccountRegion:$("#openAccountRegion").val(),
            phone:$("#phone").val(),
            YN:YN,
        },
        dataType:"json",
        success:function(msg){
            var result = msg.data;
            console.log(msg);
            if(result==1){
                var onlyChoseAlert = simpleAlert({
                    "content":"添加成功",
                    "buttons":{
                        "确定":function () {
                            window.history.back(-1);
                        }
                    }
                })
            }
        },
        error:function(){
            console.error('请求失败！')
        }
    })
        }else {
            var onlyChoseAlert = simpleAlert({
                "content":"请填写正确银行卡号",
                "buttons":{
                    "确定":function () {
                        onlyChoseAlert.close();
                    }
                }
            })
        }
        }else {
            var onlyChoseAlert = simpleAlert({
                "content":"请填写正确的身份证号!",
                "buttons":{
                    "确定":function () {
                        onlyChoseAlert.close();
                    }
                }
            })
        }
    }else {
        var onlyChoseAlert = simpleAlert({
            "content":"请完善资料!",
            "buttons":{
                "确定":function () {
                    onlyChoseAlert.close();
                }
            }
        })
    }
}

