/*
      Date:  2019-11-09 12:18
                                 */
package x5proxy.staticProxy;

public class Test {

    public static void main(String[] args) {

        Movable proxy;

        // 先记录日志，再记录时间
        /**
         * 移动之前：1573273220750
         * 移动之前的日志：要移动了
         * 坦克移动中...
         * 移动之后的日志：移动结束
         * 移动之后：1573273224464
         */
//        proxy = new TimeProxy(new LogProxy(new TanK()));

        // 先记录时间，再记录日志
        /**
         * 移动之前的日志：要移动了
         * 移动之前：1573273355400
         * 坦克移动中...
         * 移动之后：1573273359255
         * 移动之后的日志：移动结束
         */
        proxy = new LogProxy(new TimeProxy(new TanK()));


        proxy.run();

    }

}
