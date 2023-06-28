/**
 * @ClassName: Main
 * @Description:
 * @Author: XuWei
 * @Date: 2022-10-14 16:21
 */
public class Main {

    public static void main(String[] args) {

        //--------------------------------分配问题---------------------------------------
        //1、测试贪心策略---assign cookies
        int[] a = {1, 8, 10, 18};
        int[] b = {1, 2, 10, 6, 20, 17};
        int counts = assignCookies(a, b);
        System.out.println("最多可以满足" + counts + "个小孩的饥饿度");


        //2、测试贪心策略---candy
        int[] scores = {1, 0, 3, 5, 6, 10, 7, 16, 8};
        int candies = candy(scores);
        System.out.println("最少需要的糖果数为" + candies);

        //------------------------------------------------------------------------------




        //--------------------------------区间问题----------------------------------------
        //3、测试贪心策略---non-overlapping Intervals
        int[][] areas = {{1,2}, {2,4}, {1,3}};
        int removeBoxs = nonOverlappingIntervals(areas);
        System.out.println("移动最少的盒子数量为" + removeBoxs);
    }

    //--------------------------------分配问题---------------------------------------
    /**
     * @Description: 贪心策略---Assign Cookies
     * @param: a
     * @param: b
     * @return: int
     * @Author: XuWei
     * @Date 2022/10/14 16:49
     */
    public static int assignCookies(int[] a, int[] b){

        int[] sortA = bubblingSort(a);
        int[] sortB = bubblingSort(b);

        int sizeA = 0, sizeB = 0;

        while (sizeA < sortA.length && sizeB < sortB.length){
            if (sortA[sizeA] <= sortB[sizeB]) {
                ++sizeA;
            }
            sizeB++;
        }

        return sizeA;
    }

    /**
     * @Description: 冒泡排序
     * @param: a
     * @return: int[]
     * @Author: XuWei
     * @Date 2022/10/14 16:48
     */
    public static int[] bubblingSort(int[] a){

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]){
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        return a;
    }


    /**
     * @Description: 贪心策略---Candy
     * @param: a
     * @return: int
     * @Author: XuWei
     * @Date 2022/10/14 17:10
     */
    public static int candy(int[] a){
        int[] candies = new int[a.length];

        int totalCandies = 0;

        //初始化每个小孩的糖果数为1
        for (int i = 0; i < a.length; i++) {
            candies[i] = 1;
        }

        //从左往右遍历，若右边比左边评分高，则糖果数为左边糖果数 + 1
        for (int i = 0; i < a.length - 1; i++) {
            if(a[i] < a[i + 1]){
                candies[i + 1] = candies[i] + 1;
            }
        }

        //从右往左遍历，若左边比右边评分高且左边糖果数要不大于右边糖果数，则糖果数为右边糖果数 + 1
        for (int i = a.length - 1; i > 0; i--) {
            if (a[i - 1] > a[i] && candies[i - 1] <= candies[i]){
                candies[i - 1] = candies[i] + 1;
            }
        }

        for (int i = 0; i < candies.length; i++) {
            totalCandies += candies[i];
        }

        return totalCandies;
    }
    //------------------------------------------------------------------------------


    //---------------------------区间问题---------------------------------------------

    /**
     * @Description: 贪心策略---Non-overlapping Intervals
     * @param: a
     * @return: int
     * @Author: XuWei
     * @Date 2022/10/14 17:36
     */
    public static int nonOverlappingIntervals(int[][] a){
        //记录移走箱子的个数
        int boxCounts = 0;

        //冒泡排序实现按照箱子区间结尾的大小降序排
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j][1] < a[j + 1][1]){
                    int[] temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        //若前一个箱子区间结尾大于后一个箱子区间的开始，则移走后一个箱子
        for (int i = a.length - 1; i >= boxCounts; i--) {
            if(i != a.length - 1){
                if(a[i][0] < a[i + 1][1]){
                    boxCounts++;
                    for (int j = i; j > 0; j--) {
                        a[j] = a[j - 1];
                    }
                }
            }
        }

        return boxCounts;
    }
}
