package leetcode.x50;

/**
 * 删除有序数组中的重复项
 *
 * @author 愆凡
 * @date 2021/5/5 18:41
 */
public class Demo26 {

	public int one(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int i = 0;
		for (int j = 1; j < nums.length; j++) {
			if (nums[i] != nums[j]) {
				i++;
				nums[i] = nums[j];
			}
		}

		return i + 1;
	}

}
