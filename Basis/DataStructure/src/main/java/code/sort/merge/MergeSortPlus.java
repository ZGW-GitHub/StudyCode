package code.sort.merge;

import code.SortHelperUtil;
import code.sort.insert.InsertionSortUtil;

/**
 * @author 愆凡
 */
@SuppressWarnings("all")
public class MergeSortPlus {

	public static void mergeSort(int[] arr) {

		System.out.println("归并排序 Plus (User insertionSortPlus)：");
		double time;
		long millis = System.currentTimeMillis();

		_mergeSort(arr, 0, arr.length - 1);

		time = (System.currentTimeMillis() - millis) / 1000f;
		System.out.println(time);

	}

	// 递归使用归并排序，对 arr[l···r] 的范围进行排序
	private static void _mergeSort(int[] arr, int l, int r) {

		if (l >= r)
			return;
		// 优化1: 对于小规模数组, 使用插入排序
		if (r - l <= 10) {
			InsertionSortUtil.sortExtent(arr, l, r);
			return;
		}

		// 优化2: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
		// 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
		int flag = l + (r - l) / 2;
		_mergeSort(arr, l, flag);
		_mergeSort(arr, flag + 1, r);

		if (arr[flag] > arr[flag + 1])
			MergeSort._merge(arr, l, flag, r);

	}

	public static void main(String[] args) {

		int n = 10000;

		int[] array = SortHelperUtil.generateRandomArray(n, 0, n);

		mergeSort(array);

		SortHelperUtil.isSort(array);

	}

}
