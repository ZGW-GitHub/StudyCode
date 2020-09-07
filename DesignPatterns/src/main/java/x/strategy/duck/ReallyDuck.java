package x.strategy.duck;

import x.strategy.fly.NoFly;
import x.strategy.speak.NoSpeak;

/**
 * @author 愆凡
 */
public class ReallyDuck extends Duck {
    public ReallyDuck(){
        flyParent = new NoFly();
        speakParent = new NoSpeak();
    }
}
