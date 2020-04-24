/* ******************************
 *   Time:  2019-07-26 16:38    *
 ****************************** */
package ss.block.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 注意：
 *      无参构造：则队列最大为 Integer.MAX_VALUE
 *      有参构造：则队列最大为传入的参数
 *      有参（参数为容器对象）构造：此构造会先调用有参构造：this(Integer.MAX_VALUE);
 */
public class C_LinkedBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

        queue.put(null);

    }

}
