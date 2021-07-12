package code.sort.select;

import code.SortHelperUtil;

/**
 * 选择排序
 *
 * @author 愆凡
 * @date 2020/1/5 周日 14:18
 */
public class SelectionSort {

	public static void sort(int[] arr) {

		int len = arr.length;

		for (int i = 0; i < len; i++) {
			int minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[minIndex] > arr[j]) {
					minIndex = j;
				}
			}
			SortHelperUtil.swap(arr, i, minIndex);
		}
	}

}
