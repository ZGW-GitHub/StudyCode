/*
      Date:  2019-09-06 13:04
                                 */
package x1liuyubobobo.cplusplus.x2sortadvance.quick;

import x1liuyubobobo.cplusplus.MySortHelper;

/**
 * @author 愆凡
 * 快排
 */
public class QuickSort {

    public static void quickSort(int[] arr) {

        System.out.println("快速排序：");
        double time;
        long millis = System.currentTimeMillis();


        _quickSort(arr, 0, arr.length - 1);


        time = (System.currentTimeMillis() - millis) / 1000f;
        System.out.println(time);

    }

    // 对 arr[l,r] 进行快速排序
    private static void _quickSort(int[] arr, int l, int r) {

        if (l >= r)
            return;

        int p = _partition(arr, l, r);

        _quickSort(arr, l, p - 1);
        _quickSort(arr, p + 1, r);

    }

    // 对 arr[l,r] 部分进行 partition 操作
    // 返回p，使得 arr[l, p-1] < arr[p] ；arr[p+1, r] > arr[p]
    private static int _partition(int[] arr, int l, int r) {

        int value = arr[l];
        int j = l;

        // arr[l+1, j] < value      arr[j+1, i) > v
        for (int index = l+1; index <= r; index++) {
            if (arr[index] < value) {
                MySortHelper.swap(arr, j+1, index);
                j++;
            }
        }

        MySortHelper.swap(arr, l, j);

        return j;

    }

}
