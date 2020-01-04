/*
      Date:  2019-09-05 20:23
                                 */
package x1liuyubobobo.cplusplus.x1sortbasis.insertion;

import x1liuyubobobo.cplusplus.MySortHelper;

/**
 * @author 愆凡
 * 插入排序
 */
public class InsertionSort {

    public static void sort(int[] arr) {

        int len = arr.length;

        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    MySortHelper.swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }

    }

}
