package creationalpatterns.factorypattern;

/**
 * Title:
 * Desc:
 * Created by Myth-PC on 2019-11-23
 */
public class Main {
    // CIRCLE  RECTANGLE
    public static void main(String[] args) {
        Shape shape = ShapeFactory.getShape("CIRCLE");
        shape.draw();
        shape = ShapeFactory.getShape("RECTANGLE");
        shape.draw();
    }
}
