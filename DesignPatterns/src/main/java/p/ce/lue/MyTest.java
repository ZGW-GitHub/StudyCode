package p.ce.lue;

import org.junit.Test;
import p.ce.lue.entity.Animals;
import p.ce.lue.entity.DemoAnimals;
import p.ce.lue.fly.CanFly;
import p.ce.lue.speak.CanSpeak;

/**
 * @author 愆凡
 */
public class MyTest {

    @Test
    public void demoTest() {
        Animals demoAnimals = new DemoAnimals();

        demoAnimals.goFly();
        demoAnimals.goSpeak();

        // 这里实现了改变动物的行为
        System.out.println("\n--- doChange ---\n");
        demoAnimals.setFlyParent(new CanFly());
        demoAnimals.setSpeakParent(new CanSpeak());

        demoAnimals.goFly();
        demoAnimals.goSpeak();
    }

}
