package leetcode.x50;

/**
 * 移除元素
 *
 * @author 愆凡
 * @date 2021/5/6 10:11
 */
public class Demo27 {

	public int one(int[] nums, int val) {
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
