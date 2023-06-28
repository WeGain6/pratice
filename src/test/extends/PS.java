package test;

/**
 * @ClassName: test.PS
 * @Description:
 * @Author: XuWei
 * @Date: 2022-10-30 18:07
 */
public class PS {

    public static void main(String[] args) {
        Son son = new Son();
        son.test();
        System.out.println(son.a);
        son.c();
        Son.a();

        Parent parent = new Son();
        parent.c();
        if(parent instanceof Son ? true : false){
            Son s = (Son)parent;
            s.test();
        }
    }
}

class Parent{
    public String a = "123";

    public static void a(){
        System.out.println("aaa");
    }

    private static void b(){
        System.out.println("bbb");
    }

    public void c(){
        b();
    }
}

class Son extends Parent{

    public String b = "456";

    public void test(){
        System.out.println(a);
        System.out.println(super.a);
        a();
        c();
    }
}

