/*
      Date:  2019-09-06 12:36
                                 */
package cplusplus._02_sort_advance.a_MergeSort;

/**
 * 自底向上的归并排序
 */
public class MergeSort_2 {

    public static void mergeSort(int[] arr) {

        System.out.println("归并排序(自底向上)：");
        double time;
        long millis = System.currentTimeMillis();




        for (int sz = 1; sz < arr.length; sz += sz)
            for (int i = 0; i + sz < arr.length; i += (2 * sz))
                // 对 arr[i, i+sz-1] 和 arr[i+sz, i+sz+sz-1] 进行归并
                _merge(arr, i, i + sz - 1, Math.min(i + 2 * sz - 1, arr.length - 1));




        time = (System.currentTimeMillis() - millis) / 1000f;
        System.out.println(time);

    }

    // 将 arr[l···flag] 与 arr[flag+1 ··· r] 两部分进行合并
    @SuppressWarnings("Duplicates")
    public static void _merge(int[] arr, int l, int flag, int r) {

        int[] copy = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            copy[i - l] = arr[i];
        }

        int i = l;
        int j = flag + 1;
        for (int k = l; k <= r; k++) {

            if (i > flag) {
                arr[k] = copy[j - l];
                j++;
            } else if (j > r) {
                arr[k] = copy[i - l];
                i++;
            } else if (copy[i - l] < copy[j - l]) {
                arr[k] = copy[i - l];
                i++;
            } else {
                arr[k] = copy[j - l];
                j++;
            }

        }

    }

}
