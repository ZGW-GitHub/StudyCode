package a.thread.d.synchronize;

/**
 * 对于普通方法，锁是当前实例对象
 *
 * @author NotUpToYou
 */
public class MethodThisC {

    public static void main(String[] args) {

        System.out.println("开始了！");

        LockDemo lockDemo = new LockDemo();

        new Thread(() -> lockDemo.m1(), "T1").start();

        new Thread(() -> lockDemo.m2(), "T2").start();

    }

    public static class LockDemo {

        public synchronized void m1() {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -> m1 执行了");
        }

        public synchronized void m2() {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -> m2 执行了");
        }

    }

}
