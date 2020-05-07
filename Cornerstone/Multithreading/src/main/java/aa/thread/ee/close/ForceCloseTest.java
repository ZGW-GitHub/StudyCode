package aa.thread.ee.close;

/**
 * 强制结束线程
 *
 * @author NotUpToYou
 */
public class ForceCloseTest {

    public static void main(String[] args) {

		// 开始时间
        long start = System.currentTimeMillis();

        ForceCloseService service = new ForceCloseService();

        service.execute(() -> {
            // 此处模拟 runner 执行了一个耗时为 millis 的工作
            try {
                Thread.sleep(15_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        service.shutdown(10_000);

		// 结束时间
        long end = System.currentTimeMillis();
		// 计算耗时
        System.out.println(end - start);

    }

}
