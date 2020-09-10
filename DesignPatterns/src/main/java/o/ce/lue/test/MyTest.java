package o.ce.lue.test;

import o.ce.lue.duck.Duck;
import o.ce.lue.duck.ReallyDuck;

public class MyTest {
    public static void main(String[] args) {
        Duck duck = new ReallyDuck();
        duck.goFly();
        duck.goSpeak();
    }
}
