package structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: 享元模式（围棋例子）
 * Desc: 细粒度对象的共享（棋子、字符...）
 * Created by Myth-Lab on 12/2/2019
 */
abstract class FlyweightGo {
    String color = "No Color";  // 内部状态
    String position = "No Position"; // 外部状态  A2F9(类似于excel)
    public void display() {
        System.out.println("Go color：" + this.color + ", Pos: " + position);
    }
    public void setPosition(String position) {
        this.position = position;
    }
}
class BlackFlyweightGo extends FlyweightGo {
    BlackFlyweightGo() {
        color = "Black";
    }
}
class WhiteFlyweightGo extends FlyweightGo {

    WhiteFlyweightGo() {
        color = "White";
    }
}
class FlyweightFactory {
    private static FlyweightFactory instance = new FlyweightFactory();
    private static Map<String, FlyweightGo> flyweightGoPool;
    private FlyweightFactory() {
        flyweightGoPool = new HashMap<>();
    }
    public static FlyweightFactory getInstance() {
        return instance;
    }
    public FlyweightGo getFlyweightGo(String key) {
        if (!flyweightGoPool.containsKey(key)) {
            if ("b".equalsIgnoreCase(key)) {
                System.out.println("Create BlackFlyweightGo");
                flyweightGoPool.put("b", new BlackFlyweightGo());
            } else if ("w".equalsIgnoreCase(key)) {
                System.out.println("Create WhiteFlyweightGo");
                flyweightGoPool.put("w", new WhiteFlyweightGo());
            }
        }
        return flyweightGoPool.getOrDefault(key, null);
    }
}
public class GoDemo {
    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getInstance();
        for (int i = 0; i < 5; i++) {
            FlyweightGo blackGo = factory.getFlyweightGo("b");
            blackGo.setPosition("A" + i + "A" + i);
            blackGo.display();
        }
        for (int i = 0; i < 5; i++) {
            FlyweightGo whiteGo = factory.getFlyweightGo("w");
            whiteGo.setPosition("B" + i + "B" + i);
            whiteGo.display();
        }
        for (int i = 0; i < 5; i++) {
            FlyweightGo blackGo = factory.getFlyweightGo("b");
            blackGo.setPosition("AA" + i + "AA" + i);
            blackGo.display();
        }
        for (int i = 0; i < 5; i++) {
            FlyweightGo whiteGo = factory.getFlyweightGo("w");
            whiteGo.setPosition("BB" + i + "BB" + i);
            whiteGo.display();
        }

    }
}
