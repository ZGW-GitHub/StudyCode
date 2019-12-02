package com.snow.sjms.a_StrategyPattern.duck;

import com.snow.sjms.a_StrategyPattern.fly.NoFly;
import com.snow.sjms.a_StrategyPattern.speak.NoSpeak;

public class ReallyDuck extends Duck {
    public ReallyDuck(){
        flyParent = new NoFly();
        speakParent = new NoSpeak();
    }
}
