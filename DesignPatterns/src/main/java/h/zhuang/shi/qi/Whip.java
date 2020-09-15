package h.zhuang.shi.qi;

/**
 * 奶泡配料
 */
public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " - 奶泡 - ";
    }

    @Override
    public double cost() {
        return beverage.cost() + .10;
    }
}