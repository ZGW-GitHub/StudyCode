package p.ce.lue.fly;

/**
 * 表现2：会飞
 *
 * @author 愆凡
 */
public class CanFly implements FlyParent {

    @Override
    public void fly() {
        System.out.println("我用翅膀飞起来了！");
    }

}
