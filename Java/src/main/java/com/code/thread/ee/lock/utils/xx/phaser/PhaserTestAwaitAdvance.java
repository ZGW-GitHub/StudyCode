package com.code.thread.ee.lock.utils.xx.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

// awaitAdvance() ：等待该相位器的相位从给定相位值前进，如果当前相位不等于给定相位值或该相位器终止则继续向下执行。
public class PhaserTestAwaitAdvance {

    private static final Phaser phaser = new Phaser(4);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 4; i++) {
            ThreadDemo thread = new ThreadDemo(String.valueOf(i));
            thread.start();
        }

        while (true) {
            phaser.awaitAdvance(phaser.getPhase());
            TimeUnit.SECONDS.sleep(1);
            System.out.println("结束。。。");
        }

    }

    private static class ThreadDemo extends Thread {

        ThreadDemo(String name) {
            super("第 " + name + " 个线程：");
        }

        @Override
        public void run() {

            try {
                System.out.println(phaser.getPhase());
                TimeUnit.SECONDS.sleep(2);
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + phaser.getPhase() + " 结束。。。 ");
                TimeUnit.SECONDS.sleep(1);

                System.out.println(phaser.getPhase());
                TimeUnit.SECONDS.sleep(2);
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + phaser.getPhase() + " 结束。。。 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
