package test;

import test.entity.UserBean;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @ClassName: Demo04
 * @Description: 过滤集合中指定元素
 * @Author: XuWei
 * @Date: 2022-12-19 11:07
 */
public class Demo04 {

    private static final List<UserBean> userBeanList = new ArrayList<>();


    static {
        UserBean userBean1 = new UserBean(1, 18, "张三", Date.from(LocalDateTime.of(2004, 10, 1, 10, 1, 0).toInstant(ZoneOffset.UTC)));
        UserBean userBean2 = new UserBean(2, 17, "李四", Date.from(LocalDateTime.of(2005, 8, 12, 1, 3, 10).toInstant(ZoneOffset.UTC)));
        UserBean userBean3 = new UserBean(3, 19, "王五", Date.from(LocalDateTime.of(2003, 9, 16, 13, 2, 30).toInstant(ZoneOffset.UTC)));
        UserBean userBean4 = new UserBean(4, 20, "周芷若", Date.from(LocalDateTime.of(2002, 12, 10, 16, 6, 19).toInstant(ZoneOffset.UTC)));

        userBeanList.add(userBean1);
        userBeanList.add(userBean2);
        userBeanList.add(userBean3);
        userBeanList.add(userBean4);
    }


    public static void main(String[] args) {

//        List<UserBean> userBeanList = filterByStream(Demo04.userBeanList);
//        List<UserBean> userBeanList = filterByFor(Demo04.userBeanList);
//        List<UserBean> userBeanList = filterByForEach(Demo04.userBeanList);
        List<UserBean> userBeanList = filterByIterator(Demo04.userBeanList);

        for (UserBean userBean : userBeanList) {
            System.out.println(userBean);
        }

    }


    public static List<UserBean> filterByStream(List<UserBean> list){
        return list.stream().filter(userBean -> Objects.nonNull(userBean))
                .filter(userBean -> userBean.getAge() >= 18)
                .collect(Collectors.toList());
    }

    public static List<UserBean> filterByFor(List<UserBean> list){
        List<UserBean> newUserBeanList = new ArrayList<>();
        for (UserBean userBean : list) {
            if (userBean.getAge() >= 18){
                newUserBeanList.add(userBean);
            }
        }
        return newUserBeanList;
    }


    public static List<UserBean> filterByForEach(List<UserBean> list){
        List<UserBean> newUserBeanList = new ArrayList<>();
        list.forEach(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean userBean) {
                if (userBean.getAge() >= 18){
                    newUserBeanList.add(userBean);
                }
            }
        });
        return newUserBeanList;
    }


    public static List<UserBean> filterByIterator(List<UserBean> list){
        List<UserBean> newUserBeanList = new ArrayList<>();
        Iterator<UserBean> iterator = list.iterator();
        while (iterator.hasNext()){
            UserBean userBean = iterator.next();
            if (userBean.getAge() >= 18){
                newUserBeanList.add(userBean);
            }
        }
        return newUserBeanList;
    }
}
