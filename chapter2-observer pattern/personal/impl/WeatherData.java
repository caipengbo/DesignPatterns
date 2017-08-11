package observer.impl;

import java.util.ArrayList;
import java.util.List;

//连接天气API的接口
public class WeatherData implements Subject {
	//Subject 与 Observer 是一对多的关系
	private List<Observer> observers;

	private float temperature;
	private float humidity;


	public WeatherData() {
		this.observers = new ArrayList<>();
	}

	@Override
	public void registObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		if (observers.size() > 0) {
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObervers() {
		for (Observer observer : observers) {
			observer.update(temperature, humidity);
		}
	}
	// 检测 气候 变化
	public void measureChanged() {
		notifyObervers(); // 通知所有观察者
	}
	// 获取天气
	public void setWeather(float temperature, float humidity) {
		this.temperature = temperature;
		this.humidity = humidity;
		measureChanged();
	}
}
