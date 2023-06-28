package test;

import test.entity.User;
import test.entity.UserBean;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: Demo03
 * @Description: 按照指定字段排序
 * @Author: XuWei
 * @Date: 2022-12-19 10:15
 */
public class Demo03 {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, 18, "张三", Date.from(LocalDateTime.of(2004, 10, 1, 10, 1, 0).toInstant(ZoneOffset.UTC)));
        User user2 = new User(2, 17, "李四", Date.from(LocalDateTime.of(2005, 8, 12, 1, 3, 10).toInstant(ZoneOffset.UTC)));
        User user3 = new User(3, 19, "王五", Date.from(LocalDateTime.of(2003, 9, 16, 13, 2, 30).toInstant(ZoneOffset.UTC)));
        User user4 = new User(4, 20, "周芷若", Date.from(LocalDateTime.of(2002, 12, 10, 16, 6, 19).toInstant(ZoneOffset.UTC)));

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
//        System.out.println(user);
//        Date date = Date.from(LocalDateTime.of(2004, 10, 1, 10, 1, 0).atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(date);

        List<UserBean> userBeanList = new ArrayList<>();
        UserBean userBean1 = new UserBean(1, 18, "张三", Date.from(LocalDateTime.of(2004, 10, 1, 10, 1, 0).toInstant(ZoneOffset.UTC)));
        UserBean userBean2 = new UserBean(2, 17, "李四", Date.from(LocalDateTime.of(2005, 8, 12, 1, 3, 10).toInstant(ZoneOffset.UTC)));
        UserBean userBean3 = new UserBean(3, 19, "王五", Date.from(LocalDateTime.of(2003, 9, 16, 13, 2, 30).toInstant(ZoneOffset.UTC)));
        UserBean userBean4 = new UserBean(4, 20, "周芷若", Date.from(LocalDateTime.of(2002, 12, 10, 16, 6, 19).toInstant(ZoneOffset.UTC)));

        userBeanList.add(userBean1);
        userBeanList.add(userBean2);
        userBeanList.add(userBean3);
        userBeanList.add(userBean4);


//        sortByComparator(userBeanList);
        sortByStream(userBeanList);
        sortByComparable(userList);

    }

    public static void sortByComparator(List<UserBean> list){
        System.out.println("排序前");
        for (UserBean o : list) {
            System.out.println(o);
        }

        System.out.println("排序后");
        Collections.sort(list, new Comparator<UserBean>() {

            @Override
            public int compare(UserBean o1, UserBean o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        for (UserBean userBean : list) {
            System.out.println(userBean);
        }
    }


    public static void sortByStream(List<UserBean> list){
        System.out.println("排序前");
        for (UserBean o : list) {
            System.out.println(o);
        }

        System.out.println("排序后");
        List<UserBean> userBeans = list.stream().sorted(Comparator.comparing(UserBean::getAge)).collect(Collectors.toList());
        for (UserBean userBean : userBeans) {
            System.out.println(userBean);
        }
    }


    public static void sortByComparable(List<User> list){
        System.out.println("排序前");
        for (User o : list) {
            System.out.println(o);
        }

        System.out.println("排序后");
        Collections.sort(list);
        for (User user : list) {
            System.out.println(user);
        }
    }
}
