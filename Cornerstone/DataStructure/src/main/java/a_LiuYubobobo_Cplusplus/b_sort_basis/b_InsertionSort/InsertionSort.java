/*
      Date:  2019-09-05 20:23
                                 */
package cplusplus._01_sort_basis.b_InsertionSort;

import cplusplus._00.MySortHelper;

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
