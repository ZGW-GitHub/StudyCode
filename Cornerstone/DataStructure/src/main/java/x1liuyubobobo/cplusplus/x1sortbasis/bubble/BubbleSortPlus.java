/*
      Date:  2019-09-05 22:02
                                 */
package x1liuyubobobo.cplusplus.x1sortbasis.bubble;

import x1liuyubobobo.cplusplus.MySortHelper;

/**
 * @author 愆凡
 * 冒泡排序
 */
public class BubbleSortPlus {

    public static void bubbleSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n; i++) {

            int j = 0;
            // 使用 copy(备份元素) 替换元素的位置对调操作
            int copy = arr[j];
            for ( ; j < n - i - 1; j++) {
                // 这里考虑了当两数相等时，不进行操作，以此减少数据的总操作次数，避免浪费
                if (copy > arr[j + 1]) {
                    arr[j] = arr[j + 1];
                } else if (copy < arr[j + 1]){
                    arr[j] = copy;
                    copy = arr[j + 1];
                }

            }

        }

    }

}
