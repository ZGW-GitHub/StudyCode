package learn1_Thread.f_DemoLock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements Lock {

    // 当 initValue 为 true 时：表示 锁 被获取了 。
    // 当 initValue 为 false 时：表示 锁 是空闲的 。
    private boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    // 指代获取了锁的线程
    private Thread currentThread;

    BooleanLock() {
        // 在初始化类时将锁置为 空闲
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {

        while (initValue) {
            // 锁已被抢占
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();

        }

        // 锁空闲
        blockedThreadCollection.remove(Thread.currentThread());
        currentThread = Thread.currentThread();
        // 将锁置为已被获取
        this.initValue = true;

    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeOutException {

    }

    @Override
    public synchronized void unlock() {

        // 判断执行 unlock 的是否是获取了锁的线程而不是 mian/其它 线程
        if (Thread.currentThread() == currentThread) {
            // 将锁置为空闲
            this.initValue = false;
            System.out.println(Thread.currentThread().getName() + " 已经释放锁！");
            // 唤醒线程
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        // 以不可修改的方式返回 blockedThreadCollection 实例
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }

}
