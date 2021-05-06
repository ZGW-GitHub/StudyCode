package leetcode.x50;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author 愆凡
 * @date 2021/5/5 18:39
 */
public class Demo01 {

	/**
	 * 暴力
	 */
	public int[] one(int[] nums, int target) {
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
	public int[] two(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>(0);

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
	public int[] three(int[] nums, int target) {
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
