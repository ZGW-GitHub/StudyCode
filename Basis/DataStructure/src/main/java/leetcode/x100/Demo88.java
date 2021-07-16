package leetcode.x100;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
		int index1 = m - 1;
		int index2 = n - 1;
		int lastIndex = m + n - 1;

		while (index1 >= 0 && index2 >= 0) {
			if (nums1[index1] > nums2[index2]) {
				nums1[lastIndex] = nums1[index1];
				index1--;
			} else {
				nums1[lastIndex] = nums2[index2];
				index2--;
			}
			lastIndex--;
		}

		if (index2 >= 0) {
			System.arraycopy(nums2, 0, nums1, 0, index2 + 1);
		}
	}

}
