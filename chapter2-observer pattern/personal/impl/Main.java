package observer.impl;

/**
 *  自己实现 观察者模式
 */
public class Main {
	
	public static void main(String[] args) {
		CurrentWeatherDisplay currentWeatherDisplay = new CurrentWeatherDisplay();
		StatisticDisplay statisticDisplay = new StatisticDisplay();
		ForecastDisplay forecastDisplay = new ForecastDisplay();
		WeatherData weatherData = new WeatherData();
		weatherData.registObserver(currentWeatherDisplay);
		weatherData.registObserver(statisticDisplay);
		weatherData.registObserver(forecastDisplay);
		weatherData.setWeather(22f, 96f); 
		currentWeatherDisplay.display();
		statisticDisplay.display();
		forecastDisplay.display();
		weatherData.setWeather(30f, 90f);
		currentWeatherDisplay.display();
		statisticDisplay.display();
		forecastDisplay.display();
	}

}
