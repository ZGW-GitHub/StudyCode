package com.a_snow.c_executor.a_basic;

/********************************
 *   Time:  2019-07-23 21:04    *
 ********************************/

// shutDown()
// shutDownNow()
public class B_shutDown_shutDownNow {

    /*
     * shutDown()
     *      20 Threads
     *          10 is working
     *          10 is idle（空闲）
     *
     *      shutDown
     *
     *      10 waiting to finished the work
     *      10 Threads is interruped（中断）
     *      20 will exit
     *
     *
     * shutDownNow()
     *      20 Threads
     *          10 is working
     *          10 in queue
     *
     *      shutDown
     *
     *      10 return list<Runnable> 10 Thread`s runnable
     *      20 Threads is interruped（中断）
     *
     */

}
