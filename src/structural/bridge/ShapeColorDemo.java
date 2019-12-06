package structural.bridge;

/**
 * Title: 桥接模式
 * Desc: 为形状填充颜色
 * Created by Myth-Lab on 11/29/2019
 */
abstract class Shape {
    protected Color color;
    public void setColor(Color color) {
        this.color = color;
    }
    abstract void draw();
}
// 第一个维度：扩充对象
class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Draw Rectangle");
        color.fill();
    }
}
class Round extends Shape {
    @Override
    public void draw() {
        System.out.println("Draw Round");
        color.fill();
    }
}
// 第二个维度：实现接口
interface Color {
    void fill();
}

class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Fill red color");
    }
}
class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Fill blue color");
    }
}

/*
将抽象部分与它的实现部分分离，使它们都可以独立地变化。抽象部分指的是父类，对应本例中的形状类，
实现部分指的是不同子类的区别之处。将子类的区别方式 —— 也就是本例中的颜色 —— 分离成接口，
通过组合的方式桥接颜色和形状，这就是桥接模式，它主要用于 两个或多个同等级的接口。
 */
public class ShapeColorDemo {
    public static void main(String[] args) {
        Shape shape = new Rectangle();
        Color color = new Red();
        shape.setColor(color);
        shape.draw();
    }
}
