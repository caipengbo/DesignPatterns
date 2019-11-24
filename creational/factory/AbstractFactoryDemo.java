package creational.factory;

/**
 * Title: 3. 抽象工厂
 * Desc: 工厂类不单单可以创建一个对象，而是可以创建一组对象（对象的组合，固定搭配）。这是和工厂方法最大的不同点。
 * 适用场景：
 * （1）需要一组对象共同完成某种功能时（对象组合）。并且可能存在多组对象完成不同功能的情况。
 * （2）和工厂方法一样客户端不需要知道它所创建的对象的类。
 * （3）系统结构稳定，不会频繁的增加对象。（因为一旦增加就需要修改原有代码，不符合开闭原则）
 * Created by Myth-Lab on 11/24/2019
 */
// 不光需要绘图，还需要染色
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
abstract class AbstractFactory {
    // 组合
    abstract Shape produceShape();
    abstract Color produceColor();
}
// 红色的圆（太阳）   蓝色的长方形（天空）
// 每次客户端只使用一个组合（比如：衣服，商务装，运动装； 不同的操作系统的程序组合）
class SunFactory extends AbstractFactory {

    @Override
    Shape produceShape() {
        return new Circle();
    }

    @Override
    Color produceColor() {
        return new Red();
    }
}
class SkyFactory extends AbstractFactory {

    @Override
    Shape produceShape() {
        return new Rectangle();
    }

    @Override
    Color produceColor() {
        return new Blue();
    }
}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // 客户端使用
        AbstractFactory factory;
        Color color;
        Shape shape;
        factory = new SunFactory();
        // factory = new SkyFactory();
        shape = factory.produceShape();
        color = factory.produceColor();
        shape.draw();
        color.fill();


    }
}
