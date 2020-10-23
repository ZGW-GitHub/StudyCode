package p.ce.lue.entity;

import lombok.Setter;
import p.ce.lue.fly.FlyParent;
import p.ce.lue.speak.SpeakParent;

/**
 * 实体（动物抽象类）
 *
 * @author 愆凡
 */
@Setter
public abstract class Animals {

    /**
     * 将行为抽象为接口
     */
    FlyParent flyParent; // 动物拥有的行为一
    SpeakParent speakParent; // 动物拥有的行为二

    /**
     * 将调用行为的操作交由父类
     * 并将具体行为的实现交由 set 进来的具体行为类
     */
    public void goFly(){
        flyParent.fly();
    }

    public void goSpeak(){
        speakParent.speak();
    }

}
