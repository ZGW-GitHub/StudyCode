/*
      Date:  2019-11-13 22:57
                                 */
package com.snow.study.Netty.w3cschool.d_time;

import java.util.Date;

public class MyTime {

    private long time = 0;

    public MyTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public MyTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new Date((getTime() - 2208988800L) * 1000L).toString();
    }

    public long getTime() {
        return time;
    }
}
