/*
      Date:  2019-09-05 20:23
                                 */
package cplusplus._01_sort_basis.b_InsertionSort;

public class InsertionSort_Plus {

    public static void sort(int[] arr) {

        int len = arr.length;

        for (int i = 1; i < len; i++) {
            int copy = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (copy < arr[j]) {
                    arr[j + 1] = arr[j];
                    if (j == 0) {
                        arr[0] = copy;
                    }
                } else {
                    arr[j + 1] = copy;
                    break;
                }
            }
        }

    }

    public static void sortExtent(int[] arr, int l, int r) {

        for (int i = l + 1; i <= r; i++) {
            int copy = arr[i];
            for (int j = i - 1; j >= l; j--) {
                if (copy < arr[j]) {
                    arr[j + 1] = arr[j];
                    if (j == l) {
                        arr[l] = copy;
                    }
                } else {
                    arr[j + 1] = copy;
                    break;
                }
            }
        }

    }

}
