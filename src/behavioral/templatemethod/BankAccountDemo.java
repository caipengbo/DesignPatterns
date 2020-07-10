package structural.templatemethod;

/**
* Title: 银行利息计算模块(模版方法Demo)
* Desc: 利息计算流程如下：
      (1) 系统根据账号和密码验证用户信息，如果用户信息错误，系统显示出错提示；
      (2) 如果用户信息正确，则根据用户类型的不同使用不同的利息计算公式计算利息（如活期账户和定期账户具有不同的利息计算公式）；
      (3) 系统显示利息。
* Created by Myth on 12/13/2019 in VSCode
*/
//Account.cs

abstract class Account {
    // 基本方法——具体方法
    public boolean validate(String account, String password) {
        // 模拟验证
        if (account.equals("username") && password.equals("password")) {
            return true;
        } else {
            return false;
        }
    }

    // 基本方法 —— 抽象方法
    public abstract void calculateInterest();

    // 基本方法 —— 具体方法
    public void display() 
    {
        System.out.println("Show interest");
    }

    // 模板方法
    public void Handle(String account, String password) {
        if (!validate(account, password)) 
        {
            System.out.println("账号或者密码错误");
            return;
        }
        calculateInterest();
        display();
    }
}
class CurrentAccount extends Account {

    @Override
    public void calculateInterest() {
        System.out.println("活期利息");
    }

}
class SavingAccount extends Account {

    @Override
    public void calculateInterest() {
        System.out.println("定期利息");
    }

}
public class BankAccountDemo {
    
}