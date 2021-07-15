package leetcode.x100;

import code.sort.quick.QuickSort;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 合并两个有序数组
 *
 * @author 愆凡
 * @date 2021/7/15 17:52
 */
@Slf4j
public class Demo88 {

	@Test
	public void test() {
		one(new int[]{1, 5, 2}, 3, new int[]{6, 3}, 2);
	}

	public void one(int[] nums1, int m, int[] nums2, int n) {
		int[] result = new int[m + n];

		System.arraycopy(nums1, 0, result, 0, m);
		System.arraycopy(nums2, 0, result, m, n);

		QuickSort.quickSort(result);

		System.out.println(Arrays.toString(result));
	}

}
