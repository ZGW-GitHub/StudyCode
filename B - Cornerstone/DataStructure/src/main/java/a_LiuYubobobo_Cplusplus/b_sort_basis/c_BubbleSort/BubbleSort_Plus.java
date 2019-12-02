/*
      Date:  2019-09-05 22:02
                                 */
package cplusplus._01_sort_basis.c_BubbleSort;

import cplusplus._00.MySortHelper;

public class BubbleSort_Plus {

    public static void bubbleSort(int[] arr) {

        System.out.println("冒泡排序 My Plus ：");
        double time;
        long millis = System.currentTimeMillis();

        int n = arr.length;

        for (int i = 0; i < n; i++) {

            int j = 0;
            // 使用 copy(备份元素) 替换元素的位置对调操作
            int copy = arr[j];
            for ( ; j < n - i - 1; j++) {

                if (copy > arr[j + 1]) {
//                    MySortHelper.swap(arr, j, j+1);
                    arr[j] = arr[j + 1];
                } else if (copy < arr[j + 1]){ // 这里考虑了当两数相等时，不进行操作，以此减少数据的总操作次数，避免浪费
                    arr[j] = copy;
                    copy = arr[j + 1];
                }

            }

        }

        time = (System.currentTimeMillis() - millis) / 1000f;
        System.out.println(time);

    }

    public static void main(String[] args) {

        int n = 10000;

        int[] array = MySortHelper.generateRandomArray(n, 0, n);

        bubbleSort(array);

        System.out.println(MySortHelper.isSort(array));

    }

}
