package x1strategy.test;

import x1strategy.duck.Duck;
import x1strategy.duck.ReallyDuck;
import x1strategy.fly.YaFly;

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
