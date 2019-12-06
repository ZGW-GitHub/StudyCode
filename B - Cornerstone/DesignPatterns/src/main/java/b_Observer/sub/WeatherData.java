package b_Observer.sub;

import b_Observer.ob.Observer;

import java.util.ArrayList;

/**
 * 具体的主题
 */
public class WeatherData implements Subject {

    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;

    // 构造方法，初始化观察者集合
    public WeatherData() {
        observers = new ArrayList();
    }

    // 注册观察者
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    // 移除观察者
    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    // 更新数据
    @Override
    public void updateObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.updata(temperature, humidity, pressure);
        }
    }

    // 调用更新数据的方法
    public void measurementsChanged(){
        updateObservers();
    }

    /**
     * 获取新数据，并调用更新数据的方法
     */
    public void setMeasurements(float temp, float humidity, float pressure){
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
