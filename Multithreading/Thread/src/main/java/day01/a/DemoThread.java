package O1;

public class DemoThread {
    public static void main(String[] args) {

        /**
         * 自定义线程：方式一
         */
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 30L);
                    for (int i = 0; i <= 1000; i++) {
                        System.out.println("匿名 -> " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        /**
         * 自定义线程：方式一
         */
        Thread DemoThread02 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 30L);
                    for (int i = 0; i <= 1000; i++) {
                        System.out.println("Demo2 -> " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        DemoThread02.start();

        /**
         * 线程 main
         */
        try {
            Thread.sleep(1000 * 30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= 1000; i++) {
            System.out.println("main -> " + i);
        }
    }

    /**
     * 自定义线程：方式二
     */
    public class Demo2Thread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 30L);
                for (int i = 0; i <= 1000; i++) {
                    System.out.println("main -> " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
