package test.duotai;

/**
 * @ClassName: Son
 * @Description:
 * @Author: XuWei
 * @Date: 2023-03-02 22:09
 */
public class Son extends AbstractClass implements InterfaceClass{

    private int c;

    @Override
    public void b() {
        super.a(); //这里调用的是抽象父类中的a方法
        System.out.println("Son");
    }


    public void a(String str){
        System.out.println(str);
    }

    @Override
    public void a(){
        System.out.println("Son");
    }


    public static void main(String[] args) {
//        Son son = new Son();
//        son.b();
        //多态只能调用父类属性，父类未被子类重写的方法以及子类重写的父类方法
        AbstractClass ac = new Son();
        ac.a();
        ac.b();
        ac.c();
        ac.getA();
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}
