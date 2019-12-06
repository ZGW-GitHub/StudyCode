package b_Observer.display;

import b_Observer.sub.WeatherData;
import com.snow.sjms.b_Observer.ob.Observer;
import com.snow.sjms.b_Observer.play.DisplayElement;
import com.snow.sjms.b_Observer.sub.Subject;

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
