package code;

import code.sort.insert.InsertionSortPlus;

/**
 * @author 愆凡
 * @date 2020/1/5 周日 14:45
 */
public class SortArrayTest {
    public static void main(String[] args) {

        int[] array = MySortHelper.generateRandomArray(1000, 0, 99999);
        assert array != null;

        // 打印原数组
        MySortHelper.printArray(array);

        // 排序
        // 1. 选择排序
//        SelectionSort.sort(array);
        // 2. 插入排序
//        InsertionSort.sort(array);
        InsertionSortPlus.sort(array);


        // 打印排序后的数组
        MySortHelper.printArray(array);
        // 打印是否有序
        MySortHelper.isSort(array);

    }
}
