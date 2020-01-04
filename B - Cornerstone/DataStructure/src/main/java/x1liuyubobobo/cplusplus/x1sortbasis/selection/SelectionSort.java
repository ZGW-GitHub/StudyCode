/*
      Date:  2019-09-05 17:43
                                 */
package x1liuyubobobo.cplusplus.x1sortbasis.selection;

import x1liuyubobobo.cplusplus.MySortHelper;

/**
 * @author 愆凡
 * 选择排序
 */
public class SelectionSort {

    public static void sort(int[] arr) {

        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            MySortHelper.swap(arr, i, minIndex);
        }

    }

}
