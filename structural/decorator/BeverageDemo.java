package structural.decorator;

import headfirst.decorator.starbuzz.Milk;

/**
 * Title: 装饰者模式（饮料中加调料例子）
 * Desc: 可以替代继承
 * Created by Myth-Lab on 12/1/2019
 */
// 抽象构件
abstract class Beverage {
    protected String description = "Unknown Beverage";
    public String getDescription() {
        return description;
    }
    public abstract double cost();
}
// 1. 装饰器 继承 抽象构件（客户端并不会觉得对象在装饰前和装饰后有什么不同）
// 2. 内部还使用 抽象构件, 为对象附加更多的指责
abstract class Decorator extends Beverage {  // 1. 继承
    private Beverage beverage;  // 2.引用（使用）
    public Decorator(Beverage beverage) {
        this.beverage = beverage;
    }
    // 使用内部引用的抽象构件
    @Override
    public double cost() {
        return this.beverage.cost();
    }
    @Override
    public String getDescription() {
        return this.beverage.getDescription();
    }
}
// 具体构件
class Coffee extends Beverage {
    Coffee() {
        this.description = "Coffee";
    }
    @Override
    public double cost() {
        return 5.0;
    }
}
class Tea extends Beverage {
    Tea() {
        this.description = "Tea";
    }
    @Override
    public double cost() {
        return 3.0;
    }
}
// 具体装饰类
class MilkDecorator extends Decorator {

    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return super.cost() + 0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " added Milk";
    }
    // 其他扩充对象的行为(扩充功能)
    public void otherMethod() {
        System.out.println("Some other operations");
    }
}
class MochaDecorator extends Decorator {

    public MochaDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return super.cost() + 0.4;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " added Mocha";
    }
}
public class BeverageDemo {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        Beverage tea = new Tea();
        Beverage beverage = new MilkDecorator(coffee);
        System.out.println(beverage.getDescription());
        beverage = new MilkDecorator(tea);
        System.out.println(beverage.getDescription());
        beverage = new MochaDecorator(beverage);
        System.out.println(beverage.getDescription());
    }
}
