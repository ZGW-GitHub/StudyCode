package learn1_Thread.d_Synchronized;

/**
 * 此时方法的执行顺序为（ m1 m2 可能调换位置 ）：
 *      static
 *      m1 , m3
 *      m2
 */
public class G_method_static {

    static {
        synchronized (G_method_static.class) {
            System.out.println(Thread.currentThread().getName() + " -> static代码块执行了");
        }
    }

    public static synchronized void m1() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " -> m1 执行了");
    }

    public static synchronized void m2() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " -> m2 执行了");
    }

    public static void m3(){
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " -> m3 执行了");
    }
}
