package a_StrategyPattern.fly;

public class NoFly implements FlyParent {

    @Override
    public void fly() {
        System.out.println("我不会飞！");
    }
}