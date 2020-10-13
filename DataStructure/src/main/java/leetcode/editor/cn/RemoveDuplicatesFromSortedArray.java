package leetcode.editor.cn;

import java.util.Arrays;

/**
 * [26]删除排序数组中的重复项
 *
 * @author 愆凡
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedArray().new Solution();

        int[] nums = new int[]{1, 1, 2, 2, 3, 4, 4, 5, 5, 5, 6};
        System.out.println(solution.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 双指针
         */
        public int removeDuplicates(int[] nums) {
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
    //leetcode submit region end(Prohibit modification and deletion)

}
