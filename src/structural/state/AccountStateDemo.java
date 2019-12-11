package structural.state;

/**
* Title: 账号状态转换Demo(使用状态来控制状态的切换)
* Desc: 账号状态：余额大于0；0 —— -2000，  < -2000 
* Created by Myth on 12/11/2019 in VSCode
*/
// 银行账户：COntext类
class Account {
	private AccountState state; // 维持一个对抽象状态对象的引用（存在着状态）
	private String owner; // 开户名
	private double balance = 0; // 账户余额
	
	public Account(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;
		this.state = new NormalState(this); //设置初始状态
		System.out.println(this.owner + " Create a bank account with ￥ " + balance);	
		System.out.println("---------------------------------------------");	
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setState(AccountState state) {
		this.state = state;
	}
	// 存
	public void deposit(double amount) {
		System.out.println(this.owner + " Deposit: " + amount);
		state.deposit(amount);  // 调用状态对象的 deposit() 方法
		System.out.println("Now balance: "+ this.balance);
		System.out.println("State is: "+ this.state.getClass().getName());
		System.out.println("---------------------------------------------");			
	}
	
	public void withdraw(double amount) {
		System.out.println(this.owner + "Withdraw: " + amount);
        state.withdraw(amount);  // 调用状态对象的 withdraw() 方法
		System.out.println("Now balance: "+ this.balance);
		System.out.println("State is: "+ this. state.getClass().getName());		
		System.out.println("---------------------------------------------");
	}
	
	public void computeInterest()
	{
		state.computeInterest();  // 调用状态对象的computeInterest()方法
	}
}
 
// 抽象状态类
abstract class AccountState {
	protected Account acc;  // Context 和 State存在关联关系
    // State 去控制 Account 的存取款
    public abstract void deposit(double amount);
	public abstract void withdraw(double amount);
	public abstract void computeInterest();
	public abstract void stateCheck();
}
 
// 正常状态：具体状态类
class NormalState extends AccountState {
	public NormalState(Account acc) {
		this.acc = acc;
	}
 
public NormalState(AccountState state) {
		this.acc = state.acc;
	}
		
	public void deposit(double amount) {
		acc.setBalance(acc.getBalance() + amount);
		stateCheck();
	}
	
	public void withdraw(double amount) {
		acc.setBalance(acc.getBalance() - amount);
		stateCheck();
	}
	
	public void computeInterest()
	{
		System.out.println("Normal State, No interest :)");
	}
	
    // 状态转换
	public void stateCheck() {
		if (acc.getBalance() > -2000 && acc.getBalance() <= 0) {
			acc.setState(new OverdraftState(this));
		}
		else if (acc.getBalance() == -2000) {
			acc.setState(new RestrictedState(this));
		}
		else if (acc.getBalance() < -2000) {
			System.out.println("操作受限！");
		}	
	}   
}  
 
// 透支状态：具体状态类
class OverdraftState extends AccountState
{
	public OverdraftState(AccountState state) {
		this.acc = state.acc;
	}
	
	public void deposit(double amount) {
		acc.setBalance(acc.getBalance() + amount);
		stateCheck();
	}
	
	public void withdraw(double amount) {
		acc.setBalance(acc.getBalance() - amount);
		stateCheck();
	}
	
	public void computeInterest() {
		System.out.println("Calculate interest!!!");
	}
	
    // 状态转换
	public void stateCheck() {
		if (acc.getBalance() > 0) {
			acc.setState(new NormalState(this));
		}
		else if (acc.getBalance() == -2000) {
			acc.setState(new RestrictedState(this));
		}
		else if (acc.getBalance() < -2000) {
			System.out.println("操作受限!!!");
		}
	}
}
 
// 受限状态：具体状态类
class RestrictedState extends AccountState {
	public RestrictedState(AccountState state) {
		this.acc = state.acc;
	}
	
	public void deposit(double amount) {
		acc.setBalance(acc.getBalance() + amount);
		stateCheck();
	}
	
	public void withdraw(double amount) {
		System.out.println("Withdraw error, Account restricted!!!");
	}
	
	public void computeInterest() {
		System.out.println("Calculate interest!!!");
	}
	
    //状态转换
	public void stateCheck() {
		if(acc.getBalance() > 0) {
			acc.setState(new NormalState(this));
		}
		else if(acc.getBalance() > -2000) {
			acc.setState(new OverdraftState(this));
		}
	}
}
public class AccountStateDemo {
    public static void main(String[] args) {
        Account acc = new Account("Myth",0.0);
		acc.deposit(1000);
		acc.withdraw(2000);
		acc.deposit(3000);
		acc.withdraw(4000);
		acc.withdraw(1000);
		acc.computeInterest();
    }
}