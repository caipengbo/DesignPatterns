package structural.strategy;

/**
* Title: 电影买票打折（策略模式）
* Desc: 打折策略多样，未来还有可能增加或者减少策略
* Created by Myth on 12/12/2019 in VSCode
*/
// 电影票类：环境类
class MovieTicket {
	private double price;
	private Discount discount;  // 维持一个对抽象折扣类的引用
 
	public void setPrice(double price) {
		this.price = price;
	}
 
    //注入一个折扣类对象
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
 
	public double getPrice() {
        // 调用折扣类的折扣价计算方法（策略）
		return discount.calculate(this.price);
	}
}
 
// 折扣类：抽象策略类
interface Discount {
	public double calculate(double price);
}
 
// 学生票折扣类：具体策略类
class StudentDiscount implements Discount {
	public double calculate(double price) {
		System.out.println("学生票：");
		return price * 0.8;
	}
} 
 
// 儿童票折扣类：具体策略类
class ChildrenDiscount implements Discount {
	public double calculate(double price) {
		System.out.println("儿童票：");
		return price - 10;
	}
} 
 
// VIP会员票折扣类：具体策略类
class VIPDiscount implements Discount {
	public double calculate(double price) {
		System.out.println("VIP票：");
		System.out.println("增加积分！");
		return price * 0.5;
	}
}
public class MoviesTicketDemo {
    
}