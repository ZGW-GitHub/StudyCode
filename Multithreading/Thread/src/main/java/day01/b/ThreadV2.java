package O2;

/**
 * Runnbale 接口实现了线程 、业务逻辑单元 的分离
 *      类似策略模式：
 *          不同的 Runnable 相当于 Thread 的不同策略
 */
public class ThreadV2 {

    /**
     *  此处相当于 : 三个线程执行同一个"策略"
     */
    public static void main(String[] args) {

        final ShowV2 showV2 = new ShowV2();

        Thread threadDemo1 = new Thread(showV2, "1");
        threadDemo1.start();

        Thread threadDemo2 = new Thread(showV2, "2");
        threadDemo2.start();

        Thread threadDemo3 = new Thread(showV2, "3");
        threadDemo3.start();
    }
}
