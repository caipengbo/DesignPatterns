package observer.impl;

// 天气预报显示器
public class ForecastDisplay implements Displayable, Observer{

	private float temperature = 0;
	private float humidity = 0;

	@Override
	public void update(float temperature, float humidity) {
		this.temperature = temperature;
		this.humidity = humidity;
	}

	@Override
	public void display() {
		System.out.println("Forecast:" + temperature + " " + humidity);
	}

}
