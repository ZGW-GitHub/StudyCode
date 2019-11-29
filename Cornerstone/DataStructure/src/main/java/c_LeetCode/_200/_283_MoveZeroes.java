/*
      Date:  2019-09-09 17:48
                                 */
package leetcode._200;

import java.util.Arrays;

public class _283_MoveZeroes {

    public static void main(String[] args) {

        int[] arr = {2, 4, 0, 6, 0, 3, 1};

//        moveZeroes(arr);
//        moveZeroes2(arr);
        moveZeroes3(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }

    }

    /**
     * 1、暴力法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private static void moveZeroes(int[] nums) {

        if (nums.length <= 0)
            return;

        int[] copy = Arrays.copyOf(nums, nums.length);

        int index = 0;
        for (int value : copy) {

            if (value != 0) {
                nums[index] = value;
                index++;
            }

        }

        for (int i = index; i < copy.length; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 1、优化
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    private static void moveZeroes2(int[] nums) {

        if (nums.length <= 0)
            return;

        int k = 0;

        for (int i = 0; i < nums.length; i++) {

            // nums[i] == 0 不操作

            // nums[i] != 0
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }

        }

        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 1、优化
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    private static void moveZeroes3(int[] nums) {

        if (nums.length <= 0)
            return;

        int k = 0;

        for (int i = 0; i < nums.length; i++) {

            // nums[i] == 0 不操作

            // nums[i] != 0
            if (nums[i] != 0) {
                if (i != k) {
                    int tar = nums[i];
                    nums[i] = nums[k];
                    nums[k] = tar;
                }
                k++;
            }

        }

    }

}
