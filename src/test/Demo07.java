package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: Demo07
 * @Description: 日期操作封装方法
 * @Author: XuWei
 * @Date: 2022-12-20 09:49
 */
public class Demo07 {

    public static void main(String[] args) {
//        System.out.println(parse("2022-10-1 16:16", "yyyy-MM-dd HH:mm"));
//        System.out.println(format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        System.out.println(betweenDays(new Date(2000, 10, 10, 10, 10), new Date(2022, 10, 10, 10, 10)));
//        System.out.println(IsBetween(new Date(2010, 10, 1), new Date(2022, 10, 10), new Date(2022, 8, 2)));
//        System.out.println(setHMS(17, 45, 0));
//        System.out.println(getYesterday(new Date()));
//        System.out.println("1".equals(new Integer(1).toString()));

//        Byte a = 1;
//        byte b = 1;
//        Byte c = b;
//        byte d = c;
//        System.out.println(c.equals(a));
//        System.out.println(b == d);
//        System.out.println(b);
//        System.out.println(a);

//        Object o = "123";
//        Object o2 = "123";
//        System.out.println(o == o2);
//        String s = o.toString();
//        String s2 = o2.toString();
//        System.out.println(s == s2);
//        String s3 = new String();
//        s3 = "666";
//        String s4 = new String();
//        s4 = "666";
//        System.out.println(s3 == s4);
//        String s5 = new String("888");
//        String s6 = new String("888");
//        System.out.println(s5 == s6);
//        System.out.println(s.hashCode());
//        System.out.println(s2.hashCode());
//        System.out.println(s3.hashCode());
//        System.out.println(s4.hashCode());
//        System.out.println(o);
//        System.out.println(s);
//        Set set = new HashSet();
//        System.out.println(set.add(s));
//        System.out.println(set.add(s2));

//        String s7 = "123,456";
//        String s8 = "666";
//        String[] strings = s8.split(",");
//        for (String string : strings) {
//            System.out.println(string);
//        }
//        StringBuffer buffer = new StringBuffer();
//        System.out.println(buffer);
//        buffer.append("#{123}666#{888}哈哈哈");
//        Map<String, Object> msg = new HashMap<>();
//        msg.put("{123}", "阿伟");
//        msg.put("{888}", "无敌");
//        for (String key : msg.keySet()) {
//            if (buffer.indexOf("#" + key) > -1){
//                buffer.replace(buffer.indexOf("#" + key), buffer.indexOf("#" + key) + key.length() + 1, msg.get(key).toString());
//            }
//        }
//        System.out.println(buffer);


        HashMap<Object, Object> map = new HashMap<>();
        map.put("1", true ? "123" : "456");
        map.put("2", false ? "123" : "456");
        for (Object o : map.keySet()) {
            System.out.println(o.toString() + "值为：" + map.get(o));
        }
    }

    /**
     * @Description: 输入日期字符串按照字符串格式转化为日期类型
     * @param: dateStr
     * @param: formatStr
     * @return: java.util.Date
     * @Author: XuWei
     * @Date 2022/12/20 9:52
     */
    public static Date parse(String dateStr, String formatStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @Description: 输入日期类型和转换的格式，输出对应的字符串日期
     * @param: date
     * @param: formatStr
     * @return: java.lang.String
     * @Author: XuWei
     * @Date 2022/12/20 9:56
     */
    public static String format(Date date, String formatStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(date);
    }


    /**
     * @Description: 计算两个日期之间的天数间隔
     * @param: date1
     * @param: date2
     * @return: long
     * @Author: XuWei
     * @Date 2022/12/20 10:01
     */
    public static long betweenDays(Date date1, Date date2){
        long beweenMillisecond = date1.getTime() - date2.getTime();
        beweenMillisecond = beweenMillisecond > 0 ? beweenMillisecond : -beweenMillisecond;
        return beweenMillisecond / 86400000;
    }


    /**
     * @Description: 判断一个日期是否在某个时间期内
     * @param: dateStart
     * @param: dateEnd
     * @param: date
     * @return: boolean
     * @Author: XuWei
     * @Date 2022/12/20 10:05
     */
    public static boolean IsBetween(Date dateStart, Date dateEnd, Date date){
        return date.after(dateStart) && date.before(dateEnd);
    }


    /**
     * @Description: 自定义当前时间的时分秒
     * @param: hour
     * @param: minute
     * @param: second
     * @return: java.util.Date
     * @Author: XuWei
     * @Date 2022/12/20 10:11
     */
    public static Date setHMS(int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }


    /**
     * @Description: 获取指定日期的前一天日期
     * @param: date
     * @return: java.util.Date
     * @Author: XuWei
     * @Date 2022/12/20 10:15
     */
    public static Date getYesterday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

}
