package learn1_Thread.c_CloseThread;

// 强制结束线程
public class C_ForceCloseThread {

    public static void main(String[] args) {

        long start = System.currentTimeMillis(); // 开始时间

        C_ForceCloseThreadService service = new C_ForceCloseThreadService();

        service.execute(() -> {

            /*while (true) {
                // 这里模拟执行了一个非常耗时的操作
            }*/

            // 此处模拟 runner 执行了一个耗时为 millis 的工作
            try {
                Thread.sleep(15_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        service.shutdown(10_000);

        long end = System.currentTimeMillis(); // 结束时间
        System.out.println(end - start); // 计算耗时

    }

}
