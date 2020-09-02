package code.sort.insert;

import code.MySortHelper;

/**
 * 插入排序
 *
 * @author 愆凡
 * @date 2020/1/5 周日 14:57
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
