/*
      Date:  2019-09-05 17:43
                                 */
package a_LiuYubobobo_Cplusplus.b_sort_basis.a_SelectionSort;

import a_LiuYubobobo_Cplusplus.a.MySortHelper;

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
