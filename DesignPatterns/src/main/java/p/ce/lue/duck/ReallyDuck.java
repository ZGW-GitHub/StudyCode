package p.ce.lue.duck;

import p.ce.lue.fly.NoFly;
import p.ce.lue.speak.NoSpeak;

/**
 * @author 愆凡
 */
public class ReallyDuck extends Duck {
    public ReallyDuck(){
        flyParent = new NoFly();
        speakParent = new NoSpeak();
    }
}
