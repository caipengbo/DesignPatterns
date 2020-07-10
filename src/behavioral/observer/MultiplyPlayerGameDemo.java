package structural.observer;

/**
* Title: 多人联机游戏（观察者模式Demo） 
* Desc: 同一个联盟的玩家，有一个玩家被攻击，其他玩家会受到通知
* Created by Myth on 12/10/2019 in VSCode
*/
import java.util.*;
 
// 抽象观察类
interface Observer {
	public String getName();
	public void setName(String name);
    public void beAttacked(AllyControlCenter acc); // 遭受攻击
    public void helpOthers(); // 支援盟友
}
 
//战队成员类：具体观察者类
class Player implements Observer {
	private String name;
 
	public Player(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void helpOthers() {
		System.out.println(this.name + " come to help!!!");
	}
	// Key
    // 遭受攻击方法的实现，当遭受攻击时将调用战队控制中心类的通知方法notifyObserver()来通知盟友
	public void beAttacked(AllyControlCenter acc) {
        System.out.println(this.name + " be attacked!");
        acc.notifyObserver(name);		
	}
}
 
//战队控制中心类：目标类
abstract class AllyControlCenter {
	protected String allyName; // 战队名称
	protected ArrayList<Observer> players = new ArrayList<Observer>(); // 定义一个集合用于存储战队成员
	
	public void setAllyName(String allyName) {
		this.allyName = allyName;
	}
	
	public String getAllyName() {
		return this.allyName;
	}
	
    // 注册方法
	public void join(Observer obs) {
		System.out.println(obs.getName() + " joined " + this.allyName + " ally");
		players.add(obs);
	}
	
    // 注销方法
	public void quit(Observer obs) {
		System.out.println(obs.getName() + " quitted " + this.allyName + " ally!");
		players.remove(obs);
	}
	
    //声明抽象通知方法
	public abstract void notifyObserver(String name);
}
 
//具体战队控制中心类：具体目标类
class ConcreteAllyControlCenter extends AllyControlCenter {
	public ConcreteAllyControlCenter(String allyName) {
		System.out.println(allyName + " created!!!");
		System.out.println("----------------------------");
		this.allyName = allyName;
	}
	
    // 实现通知方法
	public void notifyObserver(String name) {
		System.out.println(this.allyName + " warning player: " + name + " be attacked!!!");
        //遍历观察者集合，调用每一个盟友（自己除外）的支援方法
        for(Object obs : players) {
            if (!((Observer)obs).getName().equalsIgnoreCase(name)) {
                ((Observer)obs).helpOthers();
            }
        }		
	}
}

public class MultiplyPlayerGameDemo {
    public static void main(String[] args) {
        AllyControlCenter acc = new ConcreteAllyControlCenter("OMINI");
        Observer player1 = new Player("Lagacy");
        Observer player2 = new Player("Seatin");
        Observer player3 = new Player("COW"); 
        acc.join(player1);
        acc.join(player2);
        acc.join(player3);
        player2.beAttacked(acc);
    }
}