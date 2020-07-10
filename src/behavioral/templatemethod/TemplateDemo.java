package structural.templatemethod;

/**
* Title: 模版方法模式的基本概念
* Desc: 
* Created by Myth on 12/13/2019 in VSCode
*/
// 整体流程不怎么变（少许变换可以通过钩子方法控制），个别环节有可能实现不同
abstract class AbstractClass {
    // 模板方法(把基本方法组合在一起)
    public void templateMethod() {
        open();
        display();
        // 通过钩子方法来确定某步骤是否执行
        if (isPrint()) {
            print();
        }
    }
    
    // 以下都是基本方法(抽象方法、具体方法、钩子方法)
    public void open() {
        System.out.println("Open");
    }
    public abstract void display();
    public abstract void print();
    // 钩子方法（分为两种：1.与具体方法挂钩，例如isPrint方法；2.空方法，编译可以通过）
    public boolean isPrint(){
        return true;
    }
}
class ConcreteClass1 extends AbstractClass {

    @Override
    public void display() {
        System.out.println("Display1");
    }

    @Override
    public void print() {
        System.out.println("Print1");
    }

}
class ConcreteClass2 extends AbstractClass {

    @Override
    public void display() {
        System.out.println("Display1");
    }

    @Override
    public void print() {
        System.out.println("Print1");
    }
    // 覆盖钩子方法，控制模板方法的流程
    @Override
    public boolean isPrint() {
        return false;
    }

}


public class TemplateDemo {
    public static void main(String[] args) {
        AbstractClass class1 = new ConcreteClass1();
        class1.templateMethod();
        class1 = new ConcreteClass2();
        class1.templateMethod();
    }
}