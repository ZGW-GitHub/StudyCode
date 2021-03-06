package code.sort.bubble;

import code.SortHelperUtil;

/**
 * 冒泡排序
 *
 * @author 愆凡
 */
public class BubbleSortUtil {

	public static void sort(int[] arr) {
		int len = arr.length;

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					SortHelperUtil.swap(arr, j, j + 1);
				}
			}
		}
	}

	/**
	 * 优化
	 *
	 * @param arr 数组
	 */
	public static void bubbleSort(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n; i++) {
			int j = 0;
			// 使用 copy(备份元素) 替换元素的位置对调操作
			int copy = arr[j];
			for (; j < n - i - 1; j++) {
				// 这里考虑了当两数相等时，不进行操作，以此减少数据的总操作次数，避免浪费
				if (copy > arr[j + 1]) {
					arr[j] = arr[j + 1];
				} else if (copy < arr[j + 1]) {
					arr[j] = copy;
					copy = arr[j + 1];
				}
			}
		}
	}

}
