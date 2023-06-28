package test.java;



import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @ClassName: Starts
 * @Description:
 * @Author: XuWei
 * @Date: 2023-02-01 13:45
 */
public class HSPJava01 {

    public static void main(String[] args) {
//        for (int i = 1; i <= 5; i++) {
//            for (int k = 0; k < 5 - i; k++) {
//                System.out.print(" ");
//            }
//            for (int j = 1; j <= 2 * i - 1; j++) {
//                if (i != 5){
//                    if (j == 1 || j == 2 * i - 1){
//                        System.out.print("*");
//                    }else {
//                        System.out.print(" ");
//                    }
//                }else {
//                    System.out.print("*");
//                }
//
//            }
//            System.out.println();
//        }


        /*
            1   1
            2   3
            3   5
            4   7    j = 2,4   i = 4   j = i || j + 2 = i
            5   9    j = 3     i = 5   j + 2 = i
            6   11             i = 6
            7   13
         */

//        int rowNum = 5;
//
//        for (int i = 1; i <= rowNum; i++) {
//
//            if ( i < rowNum / 2 + 1){
//                for (int j = 0; j < rowNum / 2 + 1 - i; j++) {
//                    System.out.print(" ");
//                }
//            }
//
//            for (int j = 1; j <= 2 * i - 1; j++) {
//                if ( i > rowNum / 2 + 1){
//                    if (i % 2 == 0){
//                        if (j == i || j + 2 == i){
//                            System.out.print("*");
//                        }else {
//                            System.out.print(" ");
//                        }
//                    }else {
//                        if (j + 2 == i){
//                            System.out.print("*");
//                        }else {
//                            System.out.print(" ");
//                        }
//                    }
//                }else{
//                    if (j == 1 || j == 2 * i - 1){
//                        System.out.print("*");
//                    }else {
//                        System.out.print(" ");
//                    }
//                }
//            }
//            System.out.println();
//        }


//        char c1 = '\u0020';
//        char c2 = 32;
//        char c3 = ' ';
//
//        System.out.println("c1" + c1 + "c1");
//        System.out.println("c2" + c2 + "c2");
//        System.out.println("c3" + c3 + "c3");


        //数组倒过来并打印输出
        int[] arr1 = {11, 22, 33, 44, 55, 66};
        invertedArray(arr1);

        //数组倒着打印
        int[] arr = {11, 22, 33, 44, 55, 66};
        invertedArray2(arr);


        //冒泡排序
        int[] arr2 = {18, 10, 8, 66, 160};
        bubbleSort(arr2);

        //查找
        //二分查找（非递归）
        int[] arr3 = {18, 10, 8, 66, 160};
        dichotomySearch(arr3);


        //二分查找（递归）
        System.out.println("请输入一个整数：");
        Scanner myScanner = new Scanner(System.in);
        int num = myScanner.nextInt();
        System.out.println(dichotomySearch(arr2, 0, arr2.length - 1, num));



        //动态生成不等列的二维数组
        //假设生成一个有4个一维数组元素（一维数组元素个数不超过4个）的二维数组
        /*
            {
                {0},
                {0, 0}.
                {0, 0, 0}.
                {0, 0, 0, 0}
            }
         */
        dynamicallyGenerateUnequalTwoDimensionalArray(4);



        //利用二维数组打印一个杨辉三角
        /*
            1
            1 1
            1 2 1
            1 3 3 1
            1 4 6 4 1
            1 5 10 10 5 1
            1 6 15 20 15 6 1
         */
        //假设打印10行
        printYangHuiTriangle(10);


        //插入一个数到有序数组中，最终结果还是有序数组
        int[] arr4 = {10, 23, 36, 88, 100};
        incrementalOrderedArray(arr4);


        //测试
        //专 \xe4\xb8\x93  （16进制编码（16进制字符串））
//        char c = (char) 0XE4B893;  //精度丢失
//        byte[] b = new byte[3];
//        b[0] = (byte) 0xE4;
//        b[1] = (byte) 0xB8;
//        b[2] = (byte) 0x93;
//        String bStr = new String(b);
//        System.out.println(bStr);


        //空格 \x20  （16进制编码（16进制字符串））
//        char c = '\u0020';
//        char c = 32;
//        byte[] b2 = new byte[1];
//        b2[0] = 0x20;
//        String s = new String(b2);
//        System.out.println("c" + s + "c");
//        System.out.println("c" + c + "c");

        //伟  \x20\xe4\xbc\x9f  （16进制编码（16进制字符串））
//        byte[] b3 = new byte[4];
//        b3[0] = 0x20;
//        b3[1] = (byte) 0xe4;
//        b3[2] = (byte) 0xbc;
//        b3[3] = (byte) 0x9f;
//        String s1 = new String(b3);
//        System.out.println(s1);
        //例如：专 这个汉字它的utf8编码可以写成\xe4\xb8\x93或\ue4b893（这是用16进制进行表示的），可以将其转化为byte[]，即分别存储0xe4、0xb8、0x93，
        //然后再调用String(byte[] b)构造器（构造器第二个参数来指定编码方式，没有则采用默认的编码方式）将其转化为对应字符串，从而得到对应字符或字符串
        // （注意：Java中char类型只占2个字节空间，所以某个字符在一些编码方式下得到的编码超过2个字节则不能直接通过char类型变量获得对应字符，会存在精度丢失）

    }

    //冒泡排序
    public static void bubbleSort(int[] arr2){
        int temp = 0;
        for (int i = 0; i < arr2.length - 1; i++) {
            for (int j = 0; j < arr2.length - 1 - i; j++) {
                if(arr2[j] > arr2[j + 1]){
                    temp = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < arr2.length; i++) {
            System.out.println(arr2[i]);
        }
    }

    //非递归二分查找
    public static void dichotomySearch(int[] arr2){
        int temp = 0;
        for (int i = 0; i < arr2.length - 1; i++) {
            for (int j = 0; j < arr2.length - 1 - i; j++) {
                if(arr2[j] > arr2[j + 1]){
                    temp = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp;
                }
            }
        }

        System.out.println("请输入一个整数：");
        Scanner myScanner = new Scanner(System.in);
        int num = myScanner.nextInt();

        int low = 0;
        int high = arr2.length - 1;
        int mid = 0;

        while(low <= high){
            mid = (low + high) / 2;
            if (num < arr2[mid]){
                high = mid - 1;
            }else if(num > arr2[mid]){
                low = mid + 1;
            }else{
                System.out.println("数组arr2中存在" + num + "，索引下标为" + mid);
                break;
            }
        }
        if (low > high){
            System.out.println("数组arr2中不存在" + num);
        }
    }


    //递归二分查找
    public static String dichotomySearch(int[] arr, int low, int high, int num){
        if(low > high){
            return "数组中不存在" + num;
        }
        int mid = (low + high) / 2;
        if (num < arr[mid]){
            high = mid - 1;
            return dichotomySearch(arr, low, high, num);
        }else if(num > arr[mid]){
            low = mid + 1;
            return dichotomySearch(arr, low, high, num);
        }else {
            return "数组中存在" + num + "，索引下标为" + mid;
        }
    }


    //动态生成不等的二维数组
    public static void dynamicallyGenerateUnequalTwoDimensionalArray(int dimension){
        //内层的一维数组动态去生成
        int[][] arr = new int[dimension][];

        for (int i = 0; i < arr.length; i++) {
            //定义二维数组对应元素的一维数组
            int[] iArr = new int[i + 1];

            for (int j = 0; j < iArr.length; j++) {
                Scanner myScanner = new Scanner(System.in);
                System.out.println("请输入二维数组第" + (i + 1) + "个元素的第" + (j + 1) + "个值：");
                iArr[j] = myScanner.nextInt();
            }
            arr[i] = iArr;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }


    //利用二维数组打印一个杨辉三角
    public static void printYangHuiTriangle(int layer){
        int[][] arr3 = new int[layer][];
        for (int i = 0; i < arr3.length; i++) {
            int[] iArr = new int[i + 1];
            arr3[i] = iArr;
            for (int j = 0; j < iArr.length; j++) {
                if (j == 0 || j == iArr.length - 1){
                    iArr[j] = 1;
                }else {
                    iArr[j] = arr3[i - 1][j - 1] + arr3[i - 1][j];
                }
            }
        }

        for (int i = 0; i < arr3.length; i++) {
            for (int j = 0; j < arr3[i].length; j++) {
                System.out.print(arr3[i][j] + " ");
            }
            System.out.println();
        }
    }


    //插入一个数到有序数组中，最终结果还是有序数组
    public static void incrementalOrderedArray(int[] arr4){
        boolean flag = false;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("请输入一个待插入的整数：");
        int num = myScanner.nextInt();

        int[] arr5 = new int[arr4.length + 1];
        for (int i = 0, j = 0; i < arr5.length; i++, j++) {
            if (!flag){
                if (j <= arr4.length - 1){
                    if (num > arr4[j]){
                        arr5[i] = arr4[j];
                    }else {
                        arr5[i] = num;
                        arr5[++i] = arr4[j];
                        flag = true;
                    }
                }else {
                    arr5[i] = num;
                }
            }else {
                arr5[i] = arr4[j];
            }
        }

        arr4 = arr5;

        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }
        System.out.println();
    }


    //数组倒过来并打印输出
    public static void invertedArray(int[] arr1){
        for (int i = 0; i < arr1.length / 2; i++) {
            int temp = arr1[i];
            arr1[i] = arr1[arr1.length - 1 - i];
            arr1[arr1.length - 1 - i] = temp;
        }

        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
    }

    //数组倒着打印
    public static void invertedArray2(int[] arr1){
        for (int i = 0; i < arr1.length; i++) {
            final int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000 - finalI * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(arr1[finalI]);
            }).start();
        }
    }
}
