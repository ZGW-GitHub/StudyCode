package leetcode.x50;

/**
 * 整数反转
 *
 * @author 愆凡
 * @date 2021/5/6 10:15
 */
public class Demo07 {

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
		if (numStr.contains("-")) {
			flag = -1;
		}

		try {
			return Integer.parseInt(new StringBuilder(numStr).reverse().toString()) * flag;
		} catch (Exception e) {
			return 0;
		}
	}

}
