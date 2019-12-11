package structural.state;

/**
* Title:  屏幕Demo（使用环境类控制状态的切换）
* Desc: 
* Created by Myth on 12/11/2019 in VSCode
*/
// 屏幕类
class Screen {
    //枚举所有的状态，currentState表示当前状态
	private State currentState, normalState, largerState, largestState;
 
	public Screen() {
    	this.normalState = new NormalState(); // 创建正常状态对象
    	this.largerState = new LargerState(); // 创建二倍放大状态对象
    	this.largestState = new LargestState(); // 创建四倍放大状态对象
    	this.currentState = normalState; // 设置初始状态
    	this.currentState.display();
	}
	
	public void setState(State state) {
		this.currentState = state;
	}
	
    // 单击事件处理方法，封转了对状态类中业务方法的调用和状态的转换
    // 这种方式如果增加或者减少状态的时候，可能需要修改代码了，不过可以抽象出一层Context，使用具体的Context控制不太的状态转换
	public void onClick() {
    	if (this.currentState == normalState) {
    		this.setState(largerState);
    		this.currentState.display();
    	}
    	else if (this.currentState == largerState) {
    		this.setState(largestState);
    		this.currentState.display();
    	}
    	else if (this.currentState == largestState) {
    		this.setState(normalState);
    		this.currentState.display();
    	}
	}
}
 
//抽象状态类
abstract class State {
	public abstract void display();
}
 
//正常状态类
class NormalState extends State{
	public void display() {
		System.out.println("正常大小！");
	}
}
 
//二倍状态类
class LargerState extends State{
	public void display() {
		System.out.println("二倍大小！");
	}
}
 
//四倍状态类
class LargestState extends State{
	public void display() {
		System.out.println("四倍大小！");
	}
}

public class ScreeDemo {
    
}