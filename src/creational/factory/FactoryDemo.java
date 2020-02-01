package creational.factory;

/**
 * Title: 2. 工厂模式
 * Desc: 此模式中，通过定义一个抽象的核心工厂类，并定义创建产品对象的接口，创建具体产品实例的工作延迟到其工厂子类去完成。
 * 这样做的好处是核心类只关注工厂类的接口定义，而具体的产品实例交给具体的工厂子类去创建。
 * 当系统需要新增一个产品是，无需修改现有系统代码，只需要添加一个具体产品类和其对应的工厂子类，使系统的扩展性变得很好，符合面向对象编程的开闭原则。
 *
 * 使用场景（hibernate不同的数据库，只需要切换驱动和方言）：
 *  （1）明确地计划不同条件下创建不同实例时，客户端不需要知道它所创建的对象的具体类（面向抽象编程）
 * Created by Myth-Lab on 11/24/2019
 */

// 核心类仅仅负责给出具体工厂必须实现的接口，而不负责产品类被实例化这种细节，
// 这使得工厂方法模式可以允许系统在不修改工厂角色的情况下引进新产品
interface ShapeFactory {
    Shape produceShape();
}
// 核心的工厂类不再负责所有产品的创建，而是将具体创建工作交给子类去做
class CircleFactory implements ShapeFactory {
    @Override
    public Shape produceShape() {
        Shape shape = new Circle();
        // 初始化以及其他操作
        return shape;
    }
}
class RectangleFactory implements ShapeFactory {
    @Override
    public Shape produceShape() {
        Shape shape = new Rectangle();
        // 初始化以及其他操作
        return shape;
    }
}
public class FactoryDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new CircleFactory();
        Shape shape1 = shapeFactory.produceShape();
        shapeFactory = new RectangleFactory();
        Shape shape2 = shapeFactory.produceShape();
        shape1.draw();
        shape2.draw();
    }
}
