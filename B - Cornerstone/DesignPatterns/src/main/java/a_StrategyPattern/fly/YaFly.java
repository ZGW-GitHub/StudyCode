package com.snow.sjms.a_StrategyPattern.fly;

public class YaFly implements FlyParent {

    @Override
    public void fly() {
        System.out.println("我用翅膀飞起来了！");
    }
}
