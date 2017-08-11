package observer.impl;

// 天气数据显示器
public class StatisticDisplay implements Displayable, Observer {

	private float temperature = 0;
	private float humidity = 0;

	@Override
	public void update(float temperature, float humidity) {
		this.temperature = temperature;
		this.humidity = humidity;
	}

	@Override
	public void display() {

		System.out.println("Show statistic:" + temperature + " " + humidity);
	}

}
