package test.duotai;

/**
 * @ClassName: AbstractClass
 * @Description:
 * @Author: XuWei
 * @Date: 2023-03-02 22:06
 */
public abstract class AbstractClass {

    private String a;

    private int b;

    public void a(){
        System.out.println("AbstractClass");
    }

    public abstract void b();

    public void c(){
        System.out.println("AbstractClass");
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
