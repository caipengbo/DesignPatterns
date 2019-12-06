package behavioral.chainofresponsibility;

/**
 * Title: 职责链模式
 * Desc:
 * Created by Myth-Lab on 12/5/2019
 */
// 资金审批例子
//采购单：请求类
class PurchaseRequest {
    private double amount;  //采购金额
    private int number;  //采购单编号

    public PurchaseRequest(int number, double amount) {
        this.number = number;
        this.amount = amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}
//审批者类：抽象处理者： 1. 处理 2. 转发
abstract class Approver {
    protected Approver successor; //定义后继对象
    protected String name; //审批者姓名

    public Approver(String name) {
        this.name = name;
    }

    //设置后继者
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    //抽象请求处理方法
    public abstract void processRequest(PurchaseRequest request);
}

//主任类：具体处理者
class Director extends Approver {
    public Director(String name) {
        super(name);
    }

    //具体请求处理方法
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            System.out.println("Director: " + this.name + "approved " + request.getNumber() + " request $" + request.getAmount() + ".");  //处理请求
        }
        else {
            this.successor.processRequest(request);  //转发请求
        }
    }
}

//副董事长类：具体处理者
class VicePresident extends Approver {
    public VicePresident(String name) {
        super(name);
    }

    //具体请求处理方法
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 100000) {
            System.out.println("VicePresident: " + this.name + "approved " + request.getNumber() + " request $" + request.getAmount() + ".");
        }
        else {
            this.successor.processRequest(request);  //转发请求
        }
    }
}
//董事长类：具体处理者
class President extends Approver {
    public President(String name) {
        super(name);
    }

    //具体请求处理方法
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 500000) {
            System.out.println("President: " + this.name + "approved " + request.getNumber() + " request $" + request.getAmount() + ".");
        }
        else {
            System.out.println("Too Money");
        }
    }
}
public class ChainResposibilityDemo {
    public static void main(String[] args) {
        Approver d, v, p;
        d = new Director("D1");
        v = new VicePresident("V1");
        p = new President("P1");
        // 客户端创建责任链
        d.setSuccessor(v);
        v.setSuccessor(p);
        // 处理请求
        PurchaseRequest request1, request2, request3;
        request1 = new PurchaseRequest(1, 1000);
        request2 = new PurchaseRequest(2, 150000);
        request3 = new PurchaseRequest(3, 90000);
        d.processRequest(request1);
        d.processRequest(request2);
        d.processRequest(request3);
    }
}
