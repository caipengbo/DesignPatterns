package structural.facade;

/**
 * Title: 外观模式
 * Desc: 对复杂的流程进行一次包装
 * Created by Myth-Lab on 12/2/2019
 */
interface Shape {
    void draw();
}
class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
// 外观类
class ShapeMakerFacade {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMakerFacade() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
public class FacadeDemo {
    public static void main(String[] args) {
        ShapeMakerFacade shapeMaker = new ShapeMakerFacade();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }

}
