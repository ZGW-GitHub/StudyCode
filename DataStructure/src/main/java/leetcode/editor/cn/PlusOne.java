package leetcode.editor.cn;
//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组

import java.util.Arrays;

/**
 * [66]加一
 *
 * @author 愆凡
 */
public class PlusOne {

	public static void main(String[] args) {
		Solution solution = new PlusOne().new Solution();

		System.out.println(Arrays.toString(solution.plusOne(new int[]{7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6})));
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		public int[] plusOne(int[] digits) {
			return new int[]{};
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}