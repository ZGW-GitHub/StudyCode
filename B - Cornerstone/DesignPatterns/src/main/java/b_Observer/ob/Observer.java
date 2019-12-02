package com.snow.sjms.b_Observer.ob;

/**
 * 观察者接口
 */
public interface Observer {
    // 更新数据方法
    public void updata(float temp, float humidity, float pressure);
}
