package aa.thread.f.demolock;

import java.util.Collection;

/**
 * @author NotUpToYou
 */
public interface Lock {

    // 自定义异常类
    class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();

}
