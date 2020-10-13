package leetcode.editor.cn;

import java.util.Arrays;

/**
 * [27]移除元素
 *
 * @author 愆凡
 */
public class RemoveElement {

	public static void main(String[] args) {
		Solution solution = new RemoveElement().new Solution();

		int[] nums = new int[]{1, 1, 2, 2, 3, 4, 4, 5, 5, 5, 6};
		System.out.println(solution.removeElement(nums, 2));
		System.out.println(Arrays.toString(nums));

	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		public int removeElement(int[] nums, int val) {
			int index = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] != val) {
					nums[index] = nums[i];
					index++;
				}
			}
			return index;
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
