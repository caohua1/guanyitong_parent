package util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import java.util.HashMap;
import java.util.Random;
import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;



public class SendCodeUtil {

    //向手机发送验证码
    public static HashMap<String,String> getMessageStatus(String phone)throws Exception{
        HashMap<String,String> m=new HashMap<String,String>();
        HttpClient client = new HttpClient();
        //https://api.netease.im/sms/sendtemplate.action
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        String code=generate();//生成6位数的验证码
        NameValuePair[] data ={ new NameValuePair("Uid", "****"),new NameValuePair("Key", "******"),new NameValuePair("smsMob",phone),new NameValuePair("smsText","您正在修改本站登录密码,本次验证码为:"+code+""+"有效时间为5分钟")};
        m.put("code", code);
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态
        m.put("result", result);
        post.releaseConnection();
        return m;
    }

    //随机生成6位数字的验证码
    public static String generate(){
        Random rad=new Random();
        return rad.nextInt(1000000)+"";
    }

    /**
     * 短信验证的第二种方法http://www.ihuyi.com/api/sms.html
     */
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
   // http://www.106jiekou.com/webservice/sms.asmx
    //http://106.ihuyi.cn/webservice/sms.php
    public static HashMap<String,String> getMessageStatus1(String phone) {
        HashMap<String,String> m=new HashMap<String,String>();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("utf-8");
        method.setRequestHeader("ContentType","text/xml; charset=utf-8");
        //application/x-www-form-urlencoded;charset=GBK
        //int mobile_code = (int)((Math.random()*9+1)*100000);
        String mobile_code=generate();//生成6位数的验证码
        String content = new String("您的验证码是                                                 ：" + mobile_code + "。请不要把验证码泄露给其他人。");
        m.put("mobile_code",mobile_code);
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "40749869 "),
                new NameValuePair("password", "123456ch"), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),*/
                new NameValuePair("mobile", phone),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
            }
            m.put("code",code);
            m.put("msg",msg);
            m.put("smsid",smsid);

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return m;
    }

}
