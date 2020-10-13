package leetcode.editor.cn;

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
