package structural.observer;

import java.util.ArrayList;

/**
 * Title: 观察者模式 
 * Desc:  发布-订阅模式
 * Created by Myth on 12/10/2019 in VSCode
 */

abstract class Subject {
    protected ArrayList<Observer> observers = new ArrayList<>();
 
    // 注册方法 增加观察者
	public void attach(Observer observer) {
        observers.add(observer);
    }
 
    // 注销方法，在观察者集合中删除一个观察者
	public void detach(Observer observer) {
        observers.remove(observer);
    }
 
    // 抽象通知方法
	public abstract void notifyObserver();
}

interface Observer {
    // 响应方法
    public void update();
}
// 如果具体的Subject和具体的Observer存在着依赖或关联关系，就需要在具体的Observer内引用具体的Subject
class ConcreteSubject extends Subject {
    // 实现通知方法
    @Override
	public void notifyObserver() {
        // 发送通知
		for(Object obs : observers) {
			((Observer)obs).update();
		}
	}	
}
class ConcreteObserver implements Observer {
    // 响应
    @Override
	public void update() {
		System.out.println("Update data");
	}
}


public class ObserverDemo {
    
}