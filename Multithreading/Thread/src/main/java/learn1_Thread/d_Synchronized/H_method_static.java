package learn1_Thread.d_Synchronized;

/**
 * 此时锁是 G_method_static 类的 Class 对象（ G_method_static.class ）
 *
 * 即：对于静态方法，锁是当前类的 Class 对象
 */
public class H_method_static {

    public static void main(String[] args) {

        System.out.println("开始了！");

        new Thread(() -> G_method_static.m1(), "T1").start();

        new Thread(() -> G_method_static.m2(), "T2").start();

        new Thread(() -> G_method_static.m3(), "T3").start();

    }

}
