package structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 组合模式
 * Desc: 又叫部分整体模式（层次结构），是用于把一组相似的对象当作一个单一的对象。组合模式依据树形结构来组合对象，用来表示部分以及整体层次。
 * Created by Myth-PC on 2019-11-30
 */
abstract class Component {
    // 创建了一个包含自己对象组的类。
    ArrayList<Component> components;
    String name;
    // 提供了修改相同对象组的方式。
    public abstract void add(Component component);
    public abstract void remove(Component component);
}
class ComponentImpl extends Component {

    ComponentImpl(String name) {
        this.name = name;
        components = new ArrayList<>();
    }
    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }
}

public class CompositeDemo {

}
