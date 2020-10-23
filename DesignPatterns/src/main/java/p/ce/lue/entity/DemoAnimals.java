package p.ce.lue.entity;

import p.ce.lue.fly.CannotFly;
import p.ce.lue.speak.CannotSpeak;

/**
 * 具体的动物
 *
 * @author 愆凡
 */
public class DemoAnimals extends Animals {

    public DemoAnimals() {
        flyParent = new CannotFly();
        speakParent = new CannotSpeak();
    }

}
