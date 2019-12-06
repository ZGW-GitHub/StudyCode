package a_StrategyPattern.duck;

import a_StrategyPattern.fly.NoFly;
import a_StrategyPattern.speak.NoSpeak;

public class ReallyDuck extends Duck {
    public ReallyDuck(){
        flyParent = new NoFly();
        speakParent = new NoSpeak();
    }
}
