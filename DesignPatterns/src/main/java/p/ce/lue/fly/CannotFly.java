package p.ce.lue.fly;

/**
 * @author 愆凡
 */
public class CannotFly implements FlyParent {

    @Override
    public void fly() {
        System.out.println("我不会飞！");
    }

}
