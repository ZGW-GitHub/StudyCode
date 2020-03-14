/*
      Date:  2019-11-09 12:11
                                 */
package x5proxy.staticProxy;

public class LogProxy implements Movable {

    private Movable tanK;

    public LogProxy(Movable tanK) {
        this.tanK = tanK;
    }

    // 在调用被代理类的方法之前/之后，添加逻辑
    @Override
    public void run() {

        System.out.println("移动之前的日志：要移动了");

        tanK.run();

        System.out.println("移动之后的日志：移动结束");

    }

}
