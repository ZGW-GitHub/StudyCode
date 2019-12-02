package duck;

import com.snow.sjms.a_StrategyPattern.fly.FlyParent;
import com.snow.sjms.a_StrategyPattern.speak.SpeakParent;


// 角色
public abstract class Duck {

    // 行为抽象为接口
    FlyParent flyParent;
    SpeakParent speakParent;

    /**
     * 将行为调用交由父类
     * 将具体行为的实现交由具体行为类
     */
    public void goFly(){
        flyParent.fly();
    }

    public void goSpeak(){
        speakParent.speak();
    }


    /**
     * 实现动态改变《 具体的 Duck 》 的 《行为》
     */
    public void setFlyParent(FlyParent flyParent) {
        this.flyParent = flyParent;
    }

    public void setSpeakParent(SpeakParent speakParent) {
        this.speakParent = speakParent;
    }

}
