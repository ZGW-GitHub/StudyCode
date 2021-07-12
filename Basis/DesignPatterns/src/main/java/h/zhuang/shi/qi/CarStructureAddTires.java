package h.zhuang.shi.qi;

/**
 * 装饰器
 *
 * @author 愆凡
 * @date 2020/10/22 3:15 下午
 */
public class CarStructureAddTires extends Structure {

	private final Structure structure;

	public CarStructureAddTires(Structure structure) {
		this.structure = structure;
	}

	@Override
	public String getMark() {
		return this.structure.getMark() + "轮胎、";
	}
}
