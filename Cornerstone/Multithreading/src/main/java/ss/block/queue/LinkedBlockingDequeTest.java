/*
      Date:  2019-07-30 12:15
                                 */
package ss.block.queue;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Deque ------> Double End Queue
 *
 * 注意：
 *      无参构造：则队列最大为 Integer.MAX_VALUE
 *      有参构造：则队列最大为传入的参数
 *      有参（参数为容器对象）构造：此构造会先调用有参构造：this(Integer.MAX_VALUE);
 *
 *      可以双向存取
 *
 *      不能有NULL元素
 *
 */
public class LinkedBlockingDequeTest {

    public static void main(String[] args) {

        LinkedBlockingDeque<Object> blockingDeque = new LinkedBlockingDeque<>();

    }

}
