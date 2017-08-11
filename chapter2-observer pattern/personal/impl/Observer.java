package observer.impl;

// 观察者 接口
public interface Observer {
	/**
	 * 观察者更新数据
	 * @param temperature
	 * @param humidity
	 */
	void update(float temperature, float humidity);

}
