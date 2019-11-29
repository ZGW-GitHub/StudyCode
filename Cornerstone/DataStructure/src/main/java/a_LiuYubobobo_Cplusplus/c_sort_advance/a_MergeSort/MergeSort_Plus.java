/*
      Date:  2019-09-06 9:17
                                 */
package cplusplus._02_sort_advance.a_MergeSort;

import cplusplus._00.MySortHelper;
import cplusplus._01_sort_basis.b_InsertionSort.InsertionSort_Plus;

public class MergeSort_Plus {

    public static void mergeSort(int[] arr) {

        System.out.println("归并排序 Plus (User InsertionSort_Plus)：");
        double time;
        long millis = System.currentTimeMillis();

        _mergeSort(arr, 0, arr.length - 1);

        time = (System.currentTimeMillis() - millis) / 1000f;
        System.out.println(time);

    }

    // 递归使用归并排序，对 arr[l···r] 的范围进行排序
    private static void _mergeSort(int[] arr, int l, int r) {

//        if (l >= r)
//            return;
         // 优化2: 对于小规模数组, 使用插入排序
        if (r - l <= 10) {
            InsertionSort_Plus.sortExtent(arr, l, r);
            return;
        }

        // 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
        // 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
        int flag = l + (r - l) / 2;
        _mergeSort(arr, l, flag);
        _mergeSort(arr, flag + 1, r);

        if (arr[flag] > arr[flag + 1])
            MergeSort._merge(arr, l, flag, r);

    }

    public static void main(String[] args) {

        int n = 10000;

        int[] array = MySortHelper.generateRandomArray(n, 0, n);

        mergeSort(array);

        System.out.println(MySortHelper.isSort(array));

    }

}
