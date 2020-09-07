package x.strategy.test;

import x.strategy.duck.Duck;
import x.strategy.duck.ReallyDuck;

public class MyTest {
    public static void main(String[] args) {
        Duck duck = new ReallyDuck();
        duck.goFly();
        duck.goSpeak();
    }
}
