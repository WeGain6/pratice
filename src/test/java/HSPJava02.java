package test.java;

import java.util.ArrayList;

/**
 * @ClassName: HSPJava02
 * @Description:
 * @Author: XuWei
 * @Date: 2023-02-16 15:05
 */
public class HSPJava02 {
    public static void main(String[] args) {

        System.out.println(factorial(10));
    }

    //计算n的阶乘
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n - 1) * n;
        }
    }
}
