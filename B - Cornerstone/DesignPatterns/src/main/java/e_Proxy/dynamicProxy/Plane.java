/*
      Date:  2019-11-09 12:35
                                 */
package e_Proxy.dynamicProxy;

import java.util.Random;

public class Plane implements Fly {
    @Override
    public void fly() {
        System.out.println("飞机移动中...");
        try {
            // 模拟移动
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
