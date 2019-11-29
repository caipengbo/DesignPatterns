package structural.bridge;

/**
 * Title: 桥接模式
 * Desc: 为形状填充颜色
 * Created by Myth-Lab on 11/29/2019
 */
interface Shape {
    void draw();
}
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}
class Round implements Shape {
    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }
}
// ********** 增加颜色特性
interface Color {
    String getColor();
}

class Red implements Color {
    @Override
    public String getColor() {
        return "红";
    }
}
class Blue implements Color {
    @Override
    public String getColor() {
        return "蓝";
    }
}
// 使用桥连方式扩展原来的接口
abstract class IShape {
    // 将Color桥连进去
    Color color;
    public void fill(Color color) {
        this.color = color;
    }
    abstract void draw();
}
class RectangleNew extends IShape {
    // 实现
    @Override
    public void draw() {
        System.out.println("绘制矩形，" + "填充" + color.getColor());
    }
}
class RoundNew extends IShape {
    @Override
    public void draw() {
        System.out.println("绘制圆形，" + "填充" + color.getColor());
    }
}
/*
将抽象部分与它的实现部分分离，使它们都可以独立地变化。抽象部分指的是父类，对应本例中的形状类，
实现部分指的是不同子类的区别之处。将子类的区别方式 —— 也就是本例中的颜色 —— 分离成接口，
通过组合的方式桥接颜色和形状，这就是桥接模式，它主要用于 两个或多个同等级的接口。
 */
public class ShapeColorDemo {

}
