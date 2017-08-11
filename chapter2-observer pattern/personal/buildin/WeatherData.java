package observer.buildin;

import java.util.Observable;

/**
 * Titile: 使用自带Observable实现 Subject
 * Created by Myth on 2017/8/11.
 */
/*
 *Observable 类（注意是个类哦！） 是java.util 中自带的，继承变可以变成被观察者
 * 重要方法：addObserver(Observer o)  deleteObserver(Observer o)
 *          setChanged()   clearChanged()
 *          notifyObservers()
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;

    // 不用再使用数据结构维护 一对多的关系
    public WeatherData() {
    }

    // 检测 气候 变化
    public void measureChanged() {
        //（被观察者主动通知观察者称为push，观察者主动提取数据称为pull）
        setChanged();  //采用  pull 的方式, 通知观察者 状态改变了
        notifyObservers();
    }
    // 获取天气
    public void setWeather(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        measureChanged();
    }

    // 供观察者提取数据
    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }
}
