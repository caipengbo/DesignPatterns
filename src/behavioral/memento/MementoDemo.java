package behavioral.memento;

/**
* Title: 备忘录模式Demo（可悔棋的象棋）
* Desc: 
* Created by Myth on 12/09/2019 in VSCode
*/
// Originator: 原发器(需要保存内部状态的类)
class Chessman {
    private String label;
    private int x;
    private int y;

    public Chessman(String label,int x,int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}
 
	public void setLabel(String label) {
		this.label = label; 
	}
 
	public void setX(int x) {
		this.x = x; 
	}
 
	public void setY(int y) {
		this.y = y; 
	}
 
	public void display() {
        System.out.println(label + "(" + x + ", " + y + ")");
    }
    // 保存和恢复
    public ChessmanMemento save() {
        return new ChessmanMemento(this.label,this.x,this.y);
    }
	public void restore(ChessmanMemento memento) {
		this.label = memento.getLabel();
		this.x = memento.getX();
        this.y = memento.getY();
    }

}
// Memento: 备忘录（除了原发器和管理者，其他类不可以使用）
class ChessmanMemento {
	private String label;
	private int x;
	private int y;
 
	public ChessmanMemento(String label,int x,int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}
 
	public void setLabel(String label) {
		this.label = label; 
	}
 
	public void setX(int x) {
		this.x = x; 
	}
 
	public void setY(int y) {
		this.y = y; 
	}
 
	public String getLabel() {
		return (this.label); 
	}
 
	public int getX() {
		return (this.x); 
	}
 
	public int getY() {
		return (this.y); 
	}	
}
// Caretaker：管理者（只负责存取备忘录：实现方式，注意访问权限限制，放到同一个包内；或者使用内部类，将memento设置为原发器的内部类）
class MementoCaretaker {
    private ChessmanMemento memento;
    // 可以设置一个List保存多个备忘录
 
	public ChessmanMemento getMemento() {
		return memento;
	}
 
	public void setMemento(ChessmanMemento memento) {
		this.memento = memento;
	}
}

public class MementoDemo {
    public static void main(String[] args) {
        Chessman chessman1 = new Chessman("車", 1, 1);
        ChessmanMemento mem1 = chessman1.save();
        chessman1.display();
        chessman1.setX(10);
        chessman1.display();
        // Restore
        chessman1.restore(mem1);
        chessman1.display();
    }
}