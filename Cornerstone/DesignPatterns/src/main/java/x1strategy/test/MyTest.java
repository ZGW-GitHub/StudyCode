package x1strategy.test;

import x1strategy.duck.Duck;
import x1strategy.duck.ReallyDuck;

public class MyTest {
    public static void main(String[] args) {
        Duck duck = new ReallyDuck();
        duck.goFly();
        duck.goSpeak();
    }
}
