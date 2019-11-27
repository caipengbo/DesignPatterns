package creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 原型模式
 * Desc: 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 * Created by Myth-Lab on 11/27/2019
 */
// 登记式的原型模式(原型、具体角色、原型管理器[维护一个注册表])
// 1. 原型
abstract class Shape implements Cloneable {  // 实现cloneable接口

    private String id;
    protected String type;

    abstract void draw();

    public String getType(){
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();  // 进行浅拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
// 2. 具体角色
class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
// 3. 原型管理者
class PrototypeManager {
    private static Map<Integer, Shape> shapeRegedit = new HashMap<>();  // 原型注册表
    public static void registerShape() {  // 注册原型
        Shape shape1 = new Rectangle();
        Shape shape2 = new Circle();
        shapeRegedit.put(1, shape1);
        shapeRegedit.put(2, shape2);
    }
    public static Shape getShape(Integer id) {  // 获取原型
        if (!shapeRegedit.containsKey(id)) {
            System.out.println("This Prototype not register!");
            return null;
        }
        return (Shape) shapeRegedit.get(id).clone();
    }
}
public class PrototypeDemo {
    public static void main(String[] args) {
        PrototypeManager.registerShape();
        Shape shape1 = PrototypeManager.getShape(1);
        Shape shape11 = PrototypeManager.getShape(1);
        Shape shape2 = PrototypeManager.getShape(2);
        Shape shape22 = PrototypeManager.getShape(2);
        System.out.println(shape1);
        System.out.println(shape11);
        System.out.println(shape2);
        System.out.println(shape22);
        shape1.draw();
        shape2.draw();
    }
}
