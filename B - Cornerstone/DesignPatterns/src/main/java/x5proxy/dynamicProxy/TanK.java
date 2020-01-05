/*
      Date:  2019-11-09 12:08
                                 */
package x5proxy.dynamicProxy;

import java.util.Random;

public class TanK implements Movable {
    @Override
    public void run() {
        System.out.println("坦克移动中...");
        try {
            // 模拟移动
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}