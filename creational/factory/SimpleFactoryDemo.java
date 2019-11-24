package creational.factory;

/**
 * Title: 1. 简单的工厂模式(严格来说不算23种设计模式中的一个)
 * Desc: 简单工厂模式最大的优点在于实现对象的创建和对象的使用分离，将对象的创建交给专门的工厂类负责，
 * 但是其最大的缺点在于工厂类不够灵活，增加新的具体产品需要修改工厂类的判断逻辑代码，而且产品较多时，工厂方法代码将会非常复杂。
 *
 * Created by Myth-Lab on 11/24/2019
 */
// 画形状的接口
interface Shape {
    void draw();
}
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("A Circle");
    }
}
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("A Rectange");
    }
}
class ShapeSimpleFactory {
    // CIRCLE  RECTANGLE
    public static Shape getShape(String shapeType) {
        Shape shape = null;
        if (shapeType.equals("CIRCLE")) {
            shape = new Circle();
            // 一些初始化工作
        } else if (shapeType.equals("RECTANGLE")) {
            shape = new Rectangle();
            // 一些初始化工作
        }
        /* else if (shapeType.equals("OTHER")) {
            // 扩展时：再添加新的对象
        } */
        return shape;
    }
}
public class SimpleFactoryDemo {
    // CIRCLE  RECTANGLE
    public static void main(String[] args) {
        Shape shape = ShapeSimpleFactory.getShape("CIRCLE");
        shape.draw();
        shape = ShapeSimpleFactory.getShape("RECTANGLE");
        shape.draw();
    }
}
