package h.zhuang.shi.qi;

/**
 * 浓缩咖啡
 *
 * @author 愆凡
 */
public class Espresso extends Beverage {

    public Espresso(){
        description = "浓缩咖啡！";
    }

    @Override
    public double cost() {
        return 1.99;
    }

}
