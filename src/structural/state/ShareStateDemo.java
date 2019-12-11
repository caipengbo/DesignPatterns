package structural.state;

/**
* Title: 共享状态
* Desc: 多个环境对象共享同一个状态：
* Created by Myth on 12/11/2019 in VSCode
*/
// 如果某系统要求两个开关对象要么都处于开的状态，要么都处于关的状态，在使用时它们的状态必须保持一致，开关可以由开转换到关，也可以由关转换到开。
class Switch {
    //定义三个静态的状态对象
	private static State state, onState, offState; 
	private String name;
	
	public Switch(String name) {
		this.name = name;
		onState = new OnState();
		offState = new OffState();
		this.state = onState;  // 初始是打开状态
	}
 
	public void setState(State state) {
		this.state = state;
	}
 
	public static State getState(String type) {
		if (type.equalsIgnoreCase("on")) {
			return onState;
		}
		else {
			return offState;
		}
	}
		
	public void on() {
		System.out.print(name);
		state.on(this);
	}
	
	public void off() {
		System.out.print(name);
		state.off(this);
	}
}
abstract class State {
	public abstract void on(Switch s);
	public abstract void off(Switch s);
}
//打开状态
class OnState extends State {
	public void on(Switch s) {
		System.out.println("已经打开！");
	}
	
	public void off(Switch s) {
		System.out.println("关闭！");
		s.setState(Switch.getState("off"));
	}
}
 
//关闭状态
class OffState extends State {
	public void on(Switch s) {
		System.out.println("打开！");
		s.setState(Switch.getState("on"));
	}
	
	public void off(Switch s) {
		System.out.println("已经关闭！");
	}
}

public class ShareStateDemo {
    public static void main(String args[]) {
		Switch s1,s2;
		s1=new Switch("开关1");
		s2=new Switch("开关2");
		
		s1.on();
		s2.on();
		s1.off();
		s2.off();
		s2.on();
		s1.on();	
	}
}