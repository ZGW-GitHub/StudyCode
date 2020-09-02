package code.sort.bubble;

import code.MySortHelper;

/**
 * 冒泡排序
 *
 * @author 愆凡
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
