package b.bserver.test;

import b.bserver.display.DisplayDemo1;
import b.bserver.sub.WeatherData;

public class DemoTest {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        DisplayDemo1 currentDisplay = new DisplayDemo1(weatherData);
        weatherData.setMeasurements(30,43,2f);
    }

}
