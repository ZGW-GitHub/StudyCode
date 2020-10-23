package p.ce.lue.entity;

import p.ce.lue.fly.CannotFly;
import p.ce.lue.speak.CannotSpeak;

/**
 * 具体的实体
 *
 * @author 愆凡
 */
public class DemoAnimals extends Animals {

    public DemoAnimals() {
        flyParent = new CannotFly(); // 行为的默认表现
        speakParent = new CannotSpeak(); // 行为的默认表现
    }

}
