/*
      Date:  2019-09-05 22:02
                                 */
package cplusplus._01_sort_basis.c_BubbleSort;

import cplusplus._00.MySortHelper;

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
