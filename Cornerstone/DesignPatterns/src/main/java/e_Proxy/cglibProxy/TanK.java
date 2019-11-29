/*
      Date:  2019-11-09 12:08
                                 */
package e_Proxy.cglibProxy;

import java.util.Random;

public class TanK {
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
