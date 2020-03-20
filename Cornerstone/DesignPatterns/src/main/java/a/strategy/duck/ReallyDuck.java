package a.strategy.duck;

import a.strategy.fly.NoFly;
import a.strategy.speak.NoSpeak;

/**
 * @author 愆凡
 */
public class ReallyDuck extends Duck {
    public ReallyDuck(){
        flyParent = new NoFly();
        speakParent = new NoSpeak();
    }
}
