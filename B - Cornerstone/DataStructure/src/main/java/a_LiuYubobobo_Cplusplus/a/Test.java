/*
      Date:  2019-11-08 15:16
                                 */
package a_LiuYubobobo_Cplusplus.a;

import cplusplus._01_sort_basis.c_BubbleSort.BubbleSort;

public class Test {

    public static void main(String[] args) {

        int[] array = MySortHelper.generateRandomArray(1000, 0, 10000);
        MySortHelper.printArray(array);


//        SelectionSort.sort(array);
//        InsertionSort.sort(array);
//        InsertionSort_Plus.sort(array);
//        InsertionSort_Plus.sortExtent(array, 1, 8);
        BubbleSort.sort(array);


        MySortHelper.printArray(array);
        System.out.println(MySortHelper.isSort(array));

    }

}
