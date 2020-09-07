package z.bserver.sub;

import z.bserver.ob.Observer;

/**
 * 主题接口
 */
public interface Subject {
    // 注册观察者
    public void registerObserver(Observer o);
    // 移除观察者
    public void removeObserver(Observer o);
    // 更新数据
    public void updateObservers();
}
