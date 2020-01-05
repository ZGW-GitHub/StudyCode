/*
      Date:  2019-09-05 20:23
                                 */
package x1liuyubobobo.cplusplus.x1sortbasis.insertion;

/**
 * @author 愆凡
 * 插入排序
 */
public class InsertionSortPlus {

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

}
