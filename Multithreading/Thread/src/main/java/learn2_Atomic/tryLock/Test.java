package learn2_Atomic.tryLock;

public class Test {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    doSomething();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    private static void doSomething() throws Exception {
        try {
            TryLock.tryLock();
            System.out.println("get the Lock !");
            Thread.sleep(10_000);
        } finally {
            TryLock.unLock();
        }
    }

}
