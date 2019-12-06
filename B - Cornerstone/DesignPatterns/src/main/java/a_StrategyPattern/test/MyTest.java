package a_StrategyPattern.test;

import a_StrategyPattern.duck.Duck;
import a_StrategyPattern.duck.ReallyDuck;

public class MyTest {
    public static void main(String[] args) {
        Duck duck = new ReallyDuck();
        duck.goFly();
        duck.goSpeak();
    }
}
