package x1strategy.duck;

import x1strategy.fly.NoFly;
import x1strategy.speak.NoSpeak;

/**
 * @author 愆凡
 */
public class ReallyDuck extends Duck {
    public ReallyDuck(){
        flyParent = new NoFly();
        speakParent = new NoSpeak();
    }
}
