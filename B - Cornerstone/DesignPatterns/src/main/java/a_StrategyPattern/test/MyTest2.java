package a_StrategyPattern.test;

import a_StrategyPattern.duck.Duck;
import a_StrategyPattern.duck.ReallyDuck;
import a_StrategyPattern.fly.YaFly;

public class MyTest2 {
    public static void main(String[] args) {
        /* 此时为 NoFly，NoSpeak */
        Duck duck = new ReallyDuck();
        /* 在这里实现了 “运行时改变Duck的行为” */
        duck.setFlyParent(new YaFly());
        duck.goFly();
        duck.goSpeak();
    }
}
