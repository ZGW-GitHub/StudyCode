package O3_Interrupt;

public class Demo2_wait {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (MONITOR) {
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println(isInterrupted());
                        }
                    }
                }
            }
        };

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());

//        t.stop();

    }
}
