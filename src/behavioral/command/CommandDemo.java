package behavioral.command;

/**
* Title: 命令模式
* Desc: 请求发送者和请求接收者解耦
* Created by Myth on 12/06/2019 in VSCode
*/
// 命令模式的关键在于引入了抽象命令类，请求发送者针对抽象命令类编程，只有实现了抽象命令类的具体命令才与请求接收者相关联。

// 请求 == 命令
interface Command {
    // 通过这些方法可以调用请求接收者的相关操作
    void execute();
}
class Invoker {
	private Command command;   // 可以创建一个CommandQueue类,就可以处理多个Command了
	
    //构造注入
	public Invoker(/*final Command command*/) {
		// this.command = command;
	}
	
    //设值注入(可以在程序运行时动态进行)
	public void setCommand(final Command command) {
		this.command = command;
	}
	
	//业务方法，用于调用命令类的execute()方法
	public void call() {
        // 命令执行
		command.execute();
	}
}
// 具体实现对请求的业务处理，在命令内部做事情（一般和具体命令配套）
class Receiver {  // 起名叫Action更符合
	public void action() {
		System.out.println("Receive ConcreteCommand, and do something!!!");
	}
}
// ******可以将 Receiver设置成抽象类或者接口，不同的Action执行不同的操作

class ConcreteCommand implements Command {
	private Receiver receiver; //维持一个对请求接收者对象的引用
 
    ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

	public void execute() {
		receiver.action(); //调用请求接收者的业务处理方法action()
	}
}

public class CommandDemo {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        invoker.setCommand(command);
        invoker.call();
    }    
}