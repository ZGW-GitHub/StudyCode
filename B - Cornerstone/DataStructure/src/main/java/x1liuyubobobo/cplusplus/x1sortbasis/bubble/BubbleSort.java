/*
      Date:  2019-09-05 22:02
                                 */
package x1liuyubobobo.cplusplus.x1sortbasis.bubble;

import x1liuyubobobo.cplusplus.MySortHelper;

/**
 * @author 愆凡
 * 冒泡排序
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    MySortHelper.swap(arr, j, j + 1);
                }
            }
        }
    }

}
