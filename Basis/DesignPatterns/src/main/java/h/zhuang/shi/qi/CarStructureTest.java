package h.zhuang.shi.qi;

import org.junit.Test;

/**
 * @author 愆凡
 * @date 2020/10/22 3:20 下午
 */
public class CarStructureTest {

	@Test
	public void doTest() {
		Structure carStructure = new CarStructure();

		CarStructureAddTires carStructureAddTires = new CarStructureAddTires(carStructure);
		CarStructureAddStear carStructureAddStear = new CarStructureAddStear(carStructureAddTires);

		System.out.println(carStructureAddStear.getMark());
	}

}
