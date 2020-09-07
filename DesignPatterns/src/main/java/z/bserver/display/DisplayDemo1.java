package z.bserver.display;

import z.bserver.ob.Observer;
import z.bserver.play.DisplayElement;
import z.bserver.sub.Subject;
import z.bserver.sub.WeatherData;

/**
 * 具体的观察者
 */
public class DisplayDemo1 implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private Subject weatherData;

    public DisplayDemo1(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void updata(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    // 打印方法
    @Override
    public void display() {
        System.out.println(temperature+"----------"+humidity);
    }

}
