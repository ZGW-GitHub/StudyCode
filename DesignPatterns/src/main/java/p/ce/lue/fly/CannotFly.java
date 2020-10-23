package p.ce.lue.fly;

/**
 * 表现1：不会飞
 *
 * @author 愆凡
 */
public class CannotFly implements FlyParent {

    @Override
    public void fly() {
        System.out.println("我不会飞！");
    }

}
