/*
      Date:  2019-07-30 10:30
                                 */
package day03.b_BlockQueue;

/**
 * 注意：
 *      是无界的（大小无限）
 *      内部使用的是：PriorityQueue（优先级队列）
 *      存放的元素必须实现 Delayed 接口
 *
 * 问题：
 *      1、元素过期后才能被删除
 *      2、peek() 返回 null/element(but not remove) be quickly.（即：不会等待过期元素）
 *      3、使用迭代器取元素不会等待过期元素，iterator.next() 直接返回 null/element.
 *      4、当没有元素过期，使用 poll() 、take() 取元素会阻塞
 *      5、不允许NULL元素
 */
public class E_DelayQueue {

    public static void main(String[] args) {



    }

}
