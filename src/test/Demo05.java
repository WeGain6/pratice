package test;

import test.entity.UserBean;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: Demo05
 * @Description: 去除集合中指定字段重复的元素
 * @Author: XuWei
 * @Date: 2022-12-19 11:35
 */
public class Demo05 {

    private static List<UserBean> userBeanList;

    static {
        UserBean userBean1 = new UserBean(1, 18, "张三", Date.from(LocalDateTime.of(2004, 10, 1, 10, 1, 0).toInstant(ZoneOffset.UTC)));
        UserBean userBean2 = new UserBean(2, 17, "李四", Date.from(LocalDateTime.of(2005, 8, 12, 1, 3, 10).toInstant(ZoneOffset.UTC)));
        UserBean userBean5 = new UserBean(5, 17, "张三", Date.from(LocalDateTime.of(2005, 8, 12, 1, 3, 10).toInstant(ZoneOffset.UTC)));
        UserBean userBean3 = new UserBean(3, 19, "王五", Date.from(LocalDateTime.of(2003, 9, 16, 13, 2, 30).toInstant(ZoneOffset.UTC)));
        UserBean userBean4 = new UserBean(4, 20, "周芷若", Date.from(LocalDateTime.of(2002, 12, 10, 16, 6, 19).toInstant(ZoneOffset.UTC)));
        UserBean userBean6 = new UserBean(6, 22, "周芷若", Date.from(LocalDateTime.of(2002, 12, 10, 16, 6, 19).toInstant(ZoneOffset.UTC)));
        userBeanList = Arrays.asList(userBean1, userBean2, userBean5, userBean3, userBean4, userBean6);
    }

    public static void main(String[] args) {

        //第二种舍弃前插；后两种舍弃后插
        List<UserBean> userBeans = handleRepeatByHashMap(userBeanList);
//        List<UserBean> userBeans = handleRepeatByTreeSet(userBeanList);
//        List<UserBean> userBeans = handleRepeatByStream(userBeanList);

        for (UserBean userBean : userBeans) {
            System.out.println(userBean);
        }
    }


    public static List<UserBean> handleRepeatByHashMap(List<UserBean> list){
        if (null == list || list.size() == 0){
            return list;
        }
        List<UserBean> newUserBeanList = new ArrayList<>();
        Map<String, UserBean> userBeanMap = new HashMap<>();
        list.forEach(userBean -> userBeanMap.put(userBean.getName(), userBean));
        userBeanMap.forEach((s, userBean) -> newUserBeanList.add(userBean));
        return newUserBeanList;
    }


    public static List<UserBean> handleRepeatByTreeSet(List<UserBean> list){
        TreeSet<UserBean> treeSet = new TreeSet<>(new Comparator<UserBean>() {
            @Override
            public int compare(UserBean o1, UserBean o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        treeSet.addAll(list);
        return new ArrayList<>(treeSet);
    }


    public static List<UserBean> handleRepeatByStream(List<UserBean> list){
        if (null == list || list.size() == 0){
            return list;
        }
        ArrayList<UserBean> userBeans = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserBean::getName))), ArrayList::new));
        return userBeans;
    }
}
