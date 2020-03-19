package a.thread.d.synchronize;

/**
 * 此时锁是 MethodStaticA 类的 Class 对象（ MethodStaticA.class ）
 *
 * 即：对于静态方法，锁是当前类的 Class 对象
 */
public class MethodStaticB {

    public static void main(String[] args) {

        System.out.println("开始了！");

        new Thread(() -> MethodStaticA.m1(), "T1").start();

        new Thread(() -> MethodStaticA.m2(), "T2").start();

        new Thread(() -> MethodStaticA.m3(), "T3").start();

    }

}
