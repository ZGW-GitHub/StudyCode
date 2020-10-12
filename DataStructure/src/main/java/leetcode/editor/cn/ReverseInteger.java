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

        System.out.println(solution.one(-1999999999));
    }

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {
		public int one(int x) {
		    boolean less = false;
            StringBuilder numStr = new StringBuilder(String.valueOf(x));

            if (numStr.toString().contains(CODE)) {
                less = true;
                numStr = new StringBuilder(numStr.substring(1, numStr.length()));
            }

            char[] numChars = numStr.toString().toCharArray();

            for (int i = 0; i < numChars.length / 2; i++) {
                char perNum = numChars[i];
                numChars[i] = numChars[numChars.length - 1 - i];
                numChars[numChars.length - 1 - i] = perNum;
            }

            numStr = new StringBuilder();
            for (char numChar : numChars) {
                if (numStr.length() == 0 && numChar == 0) {
                    continue;
                }
                numStr.append(numChar);
            }

            try {
                int re;
                if (less) {
                    re = Integer.parseInt("-" + numStr.toString());
                } else {
                    re = Integer.parseInt(numStr.toString());
                }
                return re;
            } catch (Exception e) {
                log.error("转化出错：", e);
            }

            return 0;
        }

        public int two(int x) {
            String numStr = String.valueOf(x);

            return 0;
        }
	}
    //leetcode submit region end(Prohibit modification and deletion)

}
