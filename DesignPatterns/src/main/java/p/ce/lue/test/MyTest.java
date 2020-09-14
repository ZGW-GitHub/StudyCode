package p.ce.lue.test;

import p.ce.lue.duck.Duck;
import p.ce.lue.duck.ReallyDuck;

public class MyTest {
    public static void main(String[] args) {
        Duck duck = new ReallyDuck();
        duck.goFly();
        duck.goSpeak();
    }
}
