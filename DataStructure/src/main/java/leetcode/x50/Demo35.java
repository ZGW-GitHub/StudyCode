package leetcode.x50;

/**
 * 搜索插入位置
 *
 * @author 愆凡
 * @date 2021/5/6 10:13
 */
public class Demo35 {

	/**
	 * 普通
	 */
	public int one(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > target || nums[i] == target) {
				return i;
			}
		}

		return nums.length;
	}

	/**
	 * 二分查找法
	 */
	public int two(int[] nums, int target) {
		int left = 0;
		int right = nums.length;

		while (left < right) {
			int min = (left + right) / 2;

			if (nums[min] == target) {
				return min;
			} else if (nums[min] < target) {
				left = min + 1;
			} else {
				right = min;
			}
		}

		return left;
	}

}
