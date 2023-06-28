package test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName: Demo02
 * @Description:
 * @Author: XuWei
 * @Date: 2022-11-22 14:23
 */
public class Demo02 {
    public static void main(String[] args) throws ParseException {
        String str = "666";
        String str2 = "666";
        System.out.println(str == str2);
        System.out.println(str.equals(str2));
        System.out.println(Objects.equals(str, "666"));
        StringBuffer stringBuffer = new StringBuffer("666");
        StringBuffer stringBuffer1 = new StringBuffer("666");
        System.out.println(Objects.equals(stringBuffer, stringBuffer1));
        StringBuffer append = stringBuffer.append("666");
        System.out.println(Objects.equals(stringBuffer, append));


        double a = 3.04;
        System.out.println((int) a);
        int random = (int)(Math.random() * 7) + 3; //3~9
        System.out.println(random);
        System.out.println(Math.ceil(Math.random()));
        System.out.println(System.currentTimeMillis());
        String[] b = {"1", "2", "3", "4", "5", "6"};
        String[] c = new String[8];
        System.arraycopy(b, 0, c, 2, b.length);
        System.out.println(Arrays.toString(c));


        BigDecimal bigDecimal = new BigDecimal(0.1);
        System.out.println(bigDecimal);
        System.out.println(BigDecimal.valueOf(0.1));
        System.out.println(0.2 + 0.1);
        System.out.println(BigDecimal.valueOf(0.2).add(BigDecimal.valueOf(0.1)));
        System.out.println(BigDecimal.valueOf(0.2).add(bigDecimal));
        System.out.println(BigDecimal.valueOf(1.0).subtract(BigDecimal.valueOf(0.32)));
        System.out.println(BigDecimal.valueOf(1.015).multiply(BigDecimal.valueOf(100)));
        System.out.println(BigDecimal.valueOf(1.301).divide(BigDecimal.valueOf(100)));
        System.out.println(BigDecimal.valueOf(1.301).divide(BigDecimal.valueOf(100), 3, BigDecimal.ROUND_CEILING));


        Date date = new Date();
        System.out.println(date.getTime());
        Date date1 = new Date(date.getTime());
        System.out.println(date1);
        Date date2 = new Date();
        date2.setTime(date.getTime());
        System.out.println(date2);


        Date date3 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date3));
        System.out.println(format.format(date3.getTime()));
        String dataFormat = "2022-11-22";
        Date date4 = format.parse(dataFormat);
        System.out.println(format.format(date4));
    }
}
