package leetcode.editor.cn;
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 愆凡
 */
@SuppressWarnings("all")
public class TwoSum {

	public static void main(String[] args) {
		Solution solution = new TwoSum().new Solution();

		int[] ints = solution.one(new int[]{3, 2, 4}, 6);

		Arrays.stream(ints).forEach(System.out::println);
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		/**
		 * 暴力
		 */
		public int[] twoSum(int[] nums, int target) {
			for (int i = 0; i < nums.length - 1; i++) {
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[i] + nums[j] == target) {
						return new int[]{i, j};
					}
				}
			}
			throw new IllegalArgumentException();
		}

		/**
		 * 两次 Hash 法
		 */
		public int[] one(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<>();

			for (int i = 0; i < nums.length; i++) {
				map.put(nums[i], i);
			}

			for (int i = 0; i < nums.length; i++) {
				int complement = target - nums[i];
				if (map.containsKey(complement) && map.get(complement) != i) {
					return new int[]{map.get(complement), i};
				}
			}

			throw new IllegalArgumentException();
		}

		/**
		 * 一次 Hash 法
		 */
		public int[] two(int[] nums, int target) {
			Map<Integer, Integer> map = new HashMap<>();

			for (int i = 0; i < nums.length; i++) {
				int complement = target - nums[i];

				if (map.containsKey(complement) && map.get(complement) != i) {
					return new int[]{map.get(complement), i};
				}

				map.put(nums[i], i);
			}

			throw new IllegalArgumentException();
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}