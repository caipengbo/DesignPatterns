package behavioral.mediator;

/**
* Title: 中介者模式（窗口程序Demo） 
* Desc: 网状结构 引入 一个中介者 变成 星型结构
* Created by Myth on 12/08/2019 in VSCode
*/
// 开发一个窗口程序：界面组件之间存在较为复杂的交互关系：
// 如果删除一个客户，要在客户列表(List)中删掉对应的项，
// 客户选择组合框(ComboBox)中客户名称也将减少一个；
// 如果增加一个客户信息，客户列表中需增加一个客户，且组合框中也将增加一项。

//抽象中介者
abstract class Mediator {
    public abstract void register(Component component);
	public abstract void componentChanged(Component c);
}
 
//具体中介者（去协调内部的各个同事类）
class ConcreteMediator extends Mediator {
	//维持对各个同事对象的引用
	public Button addButton;
	public List list;
	public TextBox userNameTextBox;
	public ComboBox cb;
 
    @Override
    public void register(Component component) {
        if (component instanceof Button) {
            addButton = (Button) component;
        } else if (component instanceof List) {
            list = (List) component;
        } else if (component instanceof TextBox) {
            userNameTextBox = (TextBox) component;
        } else if (component instanceof ComboBox) {
            cb = (ComboBox) component;
        }
    }
    //封装同事对象之间的交互
    @Override
	public void componentChanged(Component c) {
		//单击按钮
        if(c == addButton) {
			list.update();
			cb.update();
			userNameTextBox.update();
		}
        //从列表框选择客户
		else if(c == list) {
			cb.select();
			userNameTextBox.setText();
		}
        //从组合框选择客户
		else if(c == cb) {
			cb.select();
			userNameTextBox.setText();
		}
	}

}
 
//抽象组件类：抽象同事类
abstract class Component {
	protected Mediator mediator;
	
	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
 
	//转发调用（所有组件类共有的方法）涉及到与其他组件的交互，交给中介者去处理
	public void changed() {
		mediator.componentChanged(this);
	}
    public abstract void update();
    // 每个具体的组件类都有自己的方法	
}
 
//按钮类：具体同事类
class Button extends Component {
	public void update() {
        // 无更新方法
	}
}
 
//列表框类：具体同事类
class List extends Component {
	public void update() {
		System.out.println("List added a user!");
	}
	
	public void select() {
		System.out.println("Select user from list");
	}
}
 
//组合框类：具体同事类
class ComboBox extends Component {
	public void update() {
		System.out.println("Combobox added a user!");
	}
	
	public void select() {
		System.out.println("Select user from combobox");
	}
}
 
//文本框类：具体同事类
class TextBox extends Component {
	public void update() {
		System.out.println("Added user succeed, clear textbox");
	}
	
	public void setText() {
		System.out.println("Textbox show username");
	}
}
public class UIDemo {
    public static void main(String[] args) {
        ConcreteMediator mediator;
		mediator = new ConcreteMediator();
		
        //定义同事对象
		Button addBT = new Button();
		List list = new List();
	    ComboBox cb = new ComboBox();
	    TextBox userNameTB = new TextBox();
 
		addBT.setMediator(mediator);
		list.setMediator(mediator);
		cb.setMediator(mediator);
		userNameTB.setMediator(mediator);
 
        mediator.register(addBT);
        mediator.register(list);
        mediator.register(cb);
        mediator.register(userNameTB);
		
		addBT.changed();
		System.out.println("-----------------------------");
		list.changed();
    }
}

