package o.ce.lue.duck;

import o.ce.lue.fly.NoFly;
import o.ce.lue.speak.NoSpeak;

/**
 * @author 愆凡
 */
public class ReallyDuck extends Duck {
    public ReallyDuck(){
        flyParent = new NoFly();
        speakParent = new NoSpeak();
    }
}
