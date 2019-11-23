package creationalpatterns.factorypattern;

/**
 * Title:
 * Desc:
 * Created by Myth-PC on 2019-11-23
 */
public class ShapeFactory {
    // CIRCLE  RECTANGLE
    public static Shape getShape(String shapeType) {
        Shape shape = null;
        if (shapeType.equals("CIRCLE")) {
            shape = new Circle();
            // Some INIT Operation
        } else if (shapeType.equals("RECTANGLE")) {
            shape = new Rectangle();
            // some init Operation
        }
        return shape;
    }
}
