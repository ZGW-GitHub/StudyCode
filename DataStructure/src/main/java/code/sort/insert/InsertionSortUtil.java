package code.sort.insert;

import code.SortHelperUtil;

/**
 * 插入排序
 *
 * @author 愆凡
 * @date 2020/1/5 周日 14:57
 */
public class InsertionSortUtil {

    public static void sort(int[] arr) {

        int len = arr.length;

        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    SortHelperUtil.swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }

    }

	public static void sortPlus(int[] arr) {
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

	}

}
