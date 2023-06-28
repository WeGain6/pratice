package test;

import test.entity.Grande;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: Demo06
 * @Description: Stream流处理List的过滤，排序，分组，求和，平均值
 * @Author: XuWei
 * @Date: 2022-12-19 16:29
 */
public class Demo06 {

    private static List<Grande> grandeList;

    static {
        Grande grande1 = new Grande(201903001, "小红", "101", 98.0, 90.5, 86.5);
        Grande grande2 = new Grande(201903002, "小王", "102", 56.0, 88.5, 60.5);
        Grande grande3 = new Grande(201903003, "小明", "101", 90.0, 80.5, 76.0);
        Grande grande5 = new Grande(201903005, "小白", "102", 96.0, 65.5, 90.5);
        grandeList = Arrays.asList(grande1, grande2, grande3, grande5);
    }

    public static void main(String[] args) {
//        List<Grande> grandes = filterByStream(grandeList);
        List<Grande> grandes = sortByStream(grandeList);
        Map<String, List<Grande>> stringListMap = groupByStream(grandeList);
        System.out.println(stringListMap);
        Double avg = avgByStream(grandeList);
        System.out.println(avg);
        Double sum = sumByStream(grandeList);
        System.out.println(sum);
        System.out.println(grandes);
    }

    /**
     * @Description: 通过stream流实现对语文成绩大于60分的学生信息过滤获取
     * @param: grandeList
     * @return: java.util.List<test.entity.Grande>
     * @Author: XuWei
     * @Date 2022/12/20 8:47
     */
    public static List<Grande> filterByStream(List<Grande> grandeList){
        return grandeList.stream()
                .filter(Objects::nonNull)
                .filter(grande -> grande.getChineseScores() > 60)
                .collect(Collectors.toList());

    }


    /**
     * @Description: 通过stream流实现基于语文成绩对学生进行排序
     * @param: grandeList
     * @return: java.util.List<test.entity.Grande>
     * @Author: XuWei
     * @Date 2022/12/20 8:51
     */
    public static List<Grande> sortByStream(List<Grande> grandeList){
        return grandeList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingDouble(Grande::getChineseScores))
                .collect(Collectors.toList());
    }


    /**
     * @Description: 通过stream流按照指定字段进行分组处理
     * @param: grandeList
     * @return: java.util.Map<java.lang.String,java.util.List<test.entity.Grande>>
     * @Author: XuWei
     * @Date 2022/12/20 9:00
     */
    public static Map<String, List<Grande>> groupByStream(List<Grande> grandeList){
        return grandeList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Grande::getStuClass));
    }


    /**
     * @Description: 通过stream流实现对语文成绩的平均值计算
     * @param: grandeList
     * @return: java.lang.Double
     * @Author: XuWei
     * @Date 2022/12/20 9:05
     */
    public static Double avgByStream(List<Grande> grandeList){
        return grandeList.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Grande::getChineseScores)
                .average()
                .orElseThrow(IllegalStateException::new); //如果获取到的avg值不存在就抛出这个异常
    }


    /**
     * @Description: 通过stream流实现对英语总成绩的计算
     * @param: grandeList
     * @return: java.lang.Double
     * @Author: XuWei
     * @Date 2022/12/20 9:13
     */
    public static Double sumByStream(List<Grande> grandeList){
        return grandeList.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Grande::getEnglishScores)
                .sum();
    }


}
