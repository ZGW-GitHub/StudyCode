/*
      Date:  2019-09-06 13:04
                                 */
package cplusplus._02_sort_advance.b_QuickSort;

import cplusplus._00.MySortHelper;
import cplusplus._01_sort_basis.b_InsertionSort.InsertionSort_Plus;

/**
 * 优化：
 * 1、使用 插入排序 优化
 * 2、随机选择要比较的元素 k 使得 k 左侧都比 k 小，k 右侧都比 k 大
 * <p>
 * 3、将 等于 要比较的元素分散开
 * <p>
 * 优化了对 近乎有序 的数据的排序
 * 优化了对 大量重复 的数据的排序
 */
@SuppressWarnings("Duplicates")
public class QuickSort_Plus2 {

    public static void quickSort(int[] arr) {

        System.out.println("快速排序 (优化：插入排序,随机比较,重复元素分散) ：");
        double time;
        long millis = System.currentTimeMillis();


        _quickSort(arr, 0, arr.length - 1);


        time = (System.currentTimeMillis() - millis) / 1000f;
        System.out.println(time);

    }

    // 对 arr[l,r] 进行快速排序
    private static void _quickSort(int[] arr, int l, int r) {

//        if (l >= r)
//            return;
        if (r - l <= 10) {
            // 使用 插入排序 优化
            InsertionSort_Plus.sortExtent(arr, l, r);
            return;
        }


        int p = _partition(arr, l, r);

        _quickSort(arr, l, p - 1);
        _quickSort(arr, p + 1, r);

    }

    // 对 arr[l,r] 部分进行 partition 操作
    // 返回p，使得 arr[l, p-1] < arr[p] ；arr[p+1, r] > arr[p]
    private static int _partition(int[] arr, int l, int r) {

        // 随机选择 value
        MySortHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        int value = arr[l];
        // arr[l+1, indexl) <= value ; arr(indexr, r] >= value
        int indexl = l + 1;
        int indexr = r;
        while (true) {
            while (arr[indexl] < value && indexl <= r) indexl++;
            while (arr[indexr] > value && indexr >= l + 1) indexr--;
            if (indexl > indexr) break;
            MySortHelper.swap(arr, indexl, indexr);
            indexl++;
            indexr--;
        }
        MySortHelper.swap(arr, l, indexr);

        return indexr;
    }

}
