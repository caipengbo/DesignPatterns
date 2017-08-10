package observer;

// 当前天气 显示器
public class CurrentWeatherDisplay implements Displayable, Observer {

	private float temperature = 0;
	private float humidity = 0;
	
	@Override
	public void display() {
		System.out.println("Temperature: " + temperature + " Humidity: " + humidity);
	}

	@Override
	public void update(float temperature, float humidity) {
		this.temperature = temperature;
		this.humidity = humidity;
	}

}
