package O3_Interrupt;

public class Demo4_join {

    public static void main(String[] args) {

        Thread main = Thread.currentThread();

        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){

                }
            }
        };

        t.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                main.interrupt();

            }
        };

        t2.start();

        try {
            System.out.println("即将 join");
            t.join();// 打断失败，原因：此处 join 了 main 线程，而不是 t 线程，谈何打断 t 的 join 。
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("t 执行到了这里！");
        }

        System.out.println("main 执行完了！ ");

    }

}
