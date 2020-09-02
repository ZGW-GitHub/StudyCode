package com.code.thread.ee.lock.utils.xx.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

// arrive() ：到达而不等待，并减少parties的计数
public class PhaserTestArrive {

    // 注意：因为没有注册（register），所以构造函数要给parties
    private static final Phaser phaser = new Phaser(6);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new ThreadDemo().start();
        }

        phaser.arriveAndAwaitAdvance();

        System.out.println("main ---> 它们第一阶段结束了");

    }

    private static class ThreadDemo extends Thread {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " ---> 第一阶段完成。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arrive();

            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " ---> 第二阶段完成。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

