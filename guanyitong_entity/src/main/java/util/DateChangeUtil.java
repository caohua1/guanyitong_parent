package util;
import java.util.Calendar;
import java.util.Date;
public class DateChangeUtil {

    //加天数
    public static Date dateAddDays(Date date,int days){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DAY_OF_YEAR,days);//日期加10天
        Date dt1=rightNow.getTime();
        return dt1;
    }

    //加月份
    public static Date dateAddMonths(Date date,int months){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH,months);//日期加3个月
        Date dt1=rightNow.getTime();
        return dt1;
    }

    //加年份
    public static Date dateAddYears(Date date,int years){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.YEAR,years);//日期减1
        Date dt1=rightNow.getTime();
        return dt1;
    }
}
