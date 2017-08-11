package observer.buildin;

import java.util.Observable;
import java.util.Observer;

/**
 * Title:
 * Created by Myth on 2017/8/11.
 */
// 实现Observer接口
public class CurrentWeatherDisplay implements Observer, Displayable {
    Observable observable;  // 在观察者内部 设置 被观察者 父类； 多用组合

    private float temperature = 0;
    private float humidity = 0;

    public CurrentWeatherDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this); //登记成观察者
    }

    @Override
    public void display() {
        System.out.println("Temperature: " + temperature + " Humidity: " + humidity);
    }

    // 被观察者的notifyObserver()方法，会自动调用观察者的update方法
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)o;
            // 主动从被观察者 pull数据
            temperature = weatherData.getTemperature();
            humidity = weatherData.getHumidity();
            display();
        }
    }
}
