/*
      Date:  2019-08-08 10:33    
                                 */
package a_create;

/**
 * Java 不能销毁一个线程，可以通过一些手段结束 Thread 的生命周期
 */
public class C_ThreadToDie {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();

        new Thread(myThread::toDie).start();

    }

    static class MyThread extends Thread {

        // 注意：必须设置为 volatile ，使其它线程对其进行修改后，本线程立即察觉
        private volatile boolean isDie;

        @Override
        public void run() {
            while (!isDie) {
                // Do Something

            }
        }

        public void toDie() {
            this.isDie = true;
        }

    }

}
