package test.duotai;

/**
 * @ClassName: InterfaceClass
 * @Description:
 * @Author: XuWei
 * @Date: 2023-03-02 22:08
 */
public interface InterfaceClass {

    default void a(){
        System.out.println("InterfaceClass");
    }

    void b();

}
