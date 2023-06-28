package test.entity;

import java.util.Date;

/**
 * @ClassName: UserBean
 * @Description:
 * @Author: XuWei
 * @Date: 2022-12-19 10:16
 */
public class UserBean {

    private int id;

    private int age;

    private String name;

    private Date birthday;

    public UserBean() {
    }

    public UserBean(int id, int age, String name, Date birthday) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

}
