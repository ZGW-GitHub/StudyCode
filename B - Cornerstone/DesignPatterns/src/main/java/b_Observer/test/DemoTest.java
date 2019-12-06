package b_Observer.test;

import b_Observer.display.DisplayDemo1;
import b_Observer.sub.WeatherData;

public class DemoTest {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        DisplayDemo1 currentDisplay = new DisplayDemo1(weatherData);
        weatherData.setMeasurements(30,43,2f);
    }

}
