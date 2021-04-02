package code;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 愆凡
 * @date 2020/1/5 周日 14:45
 */
public class SortArrayTest {

	private int[] array = null;

	@Test
	public void test() {

	}

	@Before
	public void before() {
		array = SortHelperUtil.generateRandomArray(1000, 0, 99999);

		// 打印原数组
		SortHelperUtil.printArray(array);
	}

	@After
	public void after() {
		// 打印排序后的数组
		SortHelperUtil.printArray(array);

		// 打印是否有序
		SortHelperUtil.isSort(array);
	}

}
