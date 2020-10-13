package leetcode.editor.cn;
//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1:
//
// 输入: 123
//输出: 321
//
//
// 示例 2:
//
// 输入: -123
//输出: -321
//
//
// 示例 3:
//
// 输入: 120
//输出: 21
//
//
// 注意:
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
// Related Topics 数学
// 👍 2251 👎 0

import lombok.extern.slf4j.Slf4j;

/**
 * [7]整数反转
 *
 * @author 愆凡
 */
@Slf4j
public class ReverseInteger {

	private static final String CODE = "-";

	public static void main(String[] args) {
		Solution solution = new ReverseInteger().new Solution();

//		System.out.println(solution.one(-1999999999));
        System.out.println(solution.two(342));
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		public int one(int x) {
			long n = 0;
			while (x != 0) {
				n = n * 10 + x % 10;
				x = x / 10;
			}
			return (int) n == n ? (int) n : 0;
		}

        public int two(int x) {
            String numStr = String.valueOf(x);

            int flag = 1;
            if (numStr.contains(CODE)) {
                flag = -1;
            }

            try {
                return Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString()) * flag;
            } catch (Exception e) {
                return 0;
            }
        }
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
