package com.snow.sjms.a_StrategyPattern.test;


import com.snow.sjms.a_StrategyPattern.duck.Duck;
import com.snow.sjms.a_StrategyPattern.duck.ReallyDuck;

public class MyTest {
    public static void main(String[] args) {
        Duck duck = new ReallyDuck();
        duck.goFly();
        duck.goSpeak();
    }
}
