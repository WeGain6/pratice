package test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DateTimeHandler
 * @Description:
 * @Author: XuWei
 * @Date: 2022-12-20 14:52
 */
public class DateTimeHandler {

    private static Map<String, DateTimeFormatter> formatterMap;

    static {
        formatterMap = initFormatters();
    }


    public static void main(String[] args) {
//        System.out.println(parseDate("2022/06/06", DateTimeConstant.DATE_DEFAULT_PATTERN));
//        System.out.println(parseDateTime("2022/06/06 10:10:10", DateTimeConstant.DATE_TIME_DEFAULT_PATTERN));
//        System.out.println(formatDate(LocalDate.of(2022, 6, 6), DateTimeConstant.DATE_DEFAULT_PATTERN));
//        System.out.println(formatDateTime(LocalDateTime.of(2022, 6, 6, 6, 6, 6), DateTimeConstant.DATE_TIME_DEFAULT_PATTERN));
        System.out.println(getBetweenDays(LocalDateTime.of(2020, 10, 10, 10,10, 10), LocalDateTime.of(2022, 10, 10, 10, 10, 10)));
    }

    public static Map<String, DateTimeFormatter> initFormatters(){
        Map<String, DateTimeFormatter> formatterMap = new HashMap<>();
        formatterMap.put(DateTimeConstant.DATE_DEFAULT_PATTERN, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        formatterMap.put(DateTimeConstant.DATE_TIME_DEFAULT_PATTERN, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        return formatterMap;
    }


    /**
     * @Description: 将字符串日期按照对应的格式转化为LocalDate类型
     * @param: dateStr
     * @param: pattern
     * @return: java.time.LocalDate
     * @Author: XuWei
     * @Date 2022/12/20 15:10
     */
    public static LocalDate parseDate(String dateStr, String pattern){
        DateTimeFormatter formatter = formatterMap.get(pattern);
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * @Description: 将字符串日期按照对应的格式转化为LocalDateTime类型
     * @param: dateStr
     * @param: pattern
     * @return: java.time.LocalDateTime
     * @Author: XuWei
     * @Date 2022/12/20 15:10
     */
    public static LocalDateTime parseDateTime(String dateStr, String pattern){
        DateTimeFormatter formatter = formatterMap.get(pattern);
        return LocalDateTime.parse(dateStr, formatter);
    }


    /**
     * @Description: 将LocalDate类型按照指定格式转化为对应的日期字符串
     * @param: date
     * @param: pattern
     * @return: java.lang.String
     * @Author: XuWei
     * @Date 2022/12/20 15:11
     */
    public static String formatDate(LocalDate date, String pattern){
        DateTimeFormatter formatter = formatterMap.get(pattern);
        return formatter.format(date);
    }


    /**
     * @Description: 将LocalDateTime类型按照指定格式转化为对应的日期字符串
     * @param: date
     * @param: pattern
     * @return: java.lang.String
     * @Author: XuWei
     * @Date 2022/12/20 15:11
     */
    public static String formatDateTime(LocalDateTime date, String pattern){
        DateTimeFormatter formatter = formatterMap.get(pattern);
        return formatter.format(date);
    }


    public static long getBetweenDays(LocalDateTime localDateTime1, LocalDateTime localDateTime2){
        Duration duration = Duration.between(localDateTime1, localDateTime2);
        return duration.toDays();
    }
}
