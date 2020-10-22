package h.zhuang.shi.qi;

/**
 * 装饰器
 *
 * @author 愆凡
 * @date 2020/10/22 3:34 下午
 */
public class CarStructureAddStear extends Structure {

	private final Structure structure;

	public CarStructureAddStear(Structure structure) {
		this.structure = structure;
	}

	@Override
	public String getMark() {
		return this.structure.getMark() + "座椅、等";
	}
}
