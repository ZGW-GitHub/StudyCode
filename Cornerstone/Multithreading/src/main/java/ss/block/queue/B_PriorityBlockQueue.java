/* ******************************
 *   Time:  2019-07-26 16:37    *
 ****************************** */
package ss.block.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 注意：
 *      大小无限（ 直到撑爆 JVM ）
 *
 * 与 ArrayBlockingQueue 相比：
 *      增加了排序
 *      put() 、offer()、add() 与 ArrayBlockingQueue 一致
 *
 */
public class B_PriorityBlockQueue {

    private static final PriorityBlockingQueue queue = new PriorityBlockingQueue();

    public static void main(String[] args) {




    }

}
