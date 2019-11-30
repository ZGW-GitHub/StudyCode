package day01.b_Methods;

public class D_join {

    public static void main(String[] args) {

        Thread main = Thread.currentThread();

        Thread t = new Thread(() -> {
            while (true){

            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            main.interrupt(); // 打断成功
//            t.interrupt(); // 打断失败
        });

        t.start();
        t2.start();

        try {
            System.out.println("即将 join");
            t.join(); // 原因：此处 join 了 main 线程，而不是 t 线程，谈何打断 t 的 join 。
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main 执行到了这里！");
        }

        System.out.println("main 执行完了！ ");

    }

}
