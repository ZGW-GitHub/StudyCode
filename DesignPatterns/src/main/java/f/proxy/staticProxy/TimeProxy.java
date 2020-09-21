package f.proxy.staticProxy;

/**
 * @author NotUpToYou
 */
public class TimeProxy implements Movable {

    private final Movable tank;

    public TimeProxy(Movable tank) {
        this.tank = tank;
    }

    @Override
    public void run() {

        System.out.println("移动之前：" + System.currentTimeMillis());

        tank.run();

        System.out.println("移动之后：" + System.currentTimeMillis());

    }

}
