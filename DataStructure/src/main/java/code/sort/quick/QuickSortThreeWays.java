package code.sort.quick;

import code.SortHelperUtil;
import code.sort.insert.InsertionSortUtil;

/**
 * 三路快排
 *
 * @author 愆凡
 */
public class QuickSortThreeWays {

	public static void quickSort3Ways(int[] arr) {

		System.out.println("三路快速排序 (优化：插入排序,随机比较) ：");
		double time;
		long millis = System.currentTimeMillis();


		_quickSort3Ways(arr, 0, arr.length - 1);


		time = (System.currentTimeMillis() - millis) / 1000f;
		System.out.println(time);

	}

	// 对 arr[l, r] 进行三路快速排序
	// 将 arr[l, r] 分为 >v 、<v 、=v 三部分
	// 之后递归对 >v 、<v 两部分继续进行三路快排
	private static void _quickSort3Ways(int[] arr, int l, int r) {

		if (r - l < 10) {
			InsertionSortUtil.sortExtent(arr, l, r);
			return;
		}


		// partition :

		// 随机选择 value
		SortHelperUtil.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

		int value = arr[l];
		int indexl = l; // arr[l+1, indexl] < v
		int index = l + 1; // arr[indexl + 1, index] == v
		int indexr = r + 1; // arr[index, r] > v

		while (index < indexr) {
			if (arr[index] > value) {
				SortHelperUtil.swap(arr, index, indexr - 1);
				indexr--;
			} else if (arr[index] < value) {
				SortHelperUtil.swap(arr, index, indexl + 1);
				index++;
				indexl++;
			} else { // arr[index] == value
				index++;
			}

		}

		SortHelperUtil.swap(arr, l, indexl);

		_quickSort3Ways(arr, l, indexl - 1);
		_quickSort3Ways(arr, indexr, r);

	}

}
