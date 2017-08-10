package observer;

// 主题，  被观察者 接口
public interface Subject {

	/**
	 * 注册Observer
	 * @param observer
	 */
	void registObserver(Observer observer);
	/**
	 * 移除Observer
	 * @param observer
	 */
	void removeObserver(Observer observer);
	/**
	 *  通知所有观察者
	 */
	void notifyObervers();

}