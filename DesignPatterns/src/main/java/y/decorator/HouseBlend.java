package y.decorator;

/**
 * 综合咖啡
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "综合咖啡！";
    }

    @Override
    public double cost() {
        return 0.89;
    }

}
