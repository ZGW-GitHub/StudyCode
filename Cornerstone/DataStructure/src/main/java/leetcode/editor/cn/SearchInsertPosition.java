package leetcode.editor.cn;
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 你可以假设数组中无重复元素。 
//
// 示例 1: 
//
// 输入: [1,3,5,6], 5
//输出: 2
// 
//
// 示例 2: 
//
// 输入: [1,3,5,6], 2
//输出: 1
// 
//
// 示例 3: 
//
// 输入: [1,3,5,6], 7
//输出: 4
// 
//
// 示例 4: 
//
// 输入: [1,3,5,6], 0
//输出: 0
// 
// Related Topics 数组 二分查找

import java.util.Arrays;

/**
 * [35]搜索插入位置
 *
 * @author 愆凡
 */
public class SearchInsertPosition {

	public static void main(String[] args) {
		Solution solution = new SearchInsertPosition().new Solution();

		int[] nums = {1, 3, 5, 6};
		System.out.println(solution.searchInsert(nums, 5));
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		/**
		 * 普通
		 */
		public int searchInsert(int[] nums, int target) {
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
		public int one(int[] nums, int target) {
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
	//leetcode submit region end(Prohibit modification and deletion)

}