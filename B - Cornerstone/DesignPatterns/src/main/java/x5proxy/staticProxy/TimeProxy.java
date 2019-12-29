/*
      Date:  2019-11-09 12:16
                                 */
package x5proxy.staticProxy;

public class TimeProxy implements Movable {

    private Movable tank;

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
