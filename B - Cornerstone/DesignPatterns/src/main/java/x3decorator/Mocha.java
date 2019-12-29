package x3decorator;

/**
 * 摩卡配料
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " - 摩卡 - ";
    }

    @Override
    public double cost() {
        return beverage.cost() + .20;
    }
}
