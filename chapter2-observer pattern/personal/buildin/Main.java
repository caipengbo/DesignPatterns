package observer.buildin;

/**
 * Title: 使用java自带的观察者模式 实现 Weather检测
 * Created by Myth on 2017/8/11.
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentWeatherDisplay currentWeatherDisplay = new CurrentWeatherDisplay(weatherData);
        weatherData.setWeather(31f, 50f);
    }
}
