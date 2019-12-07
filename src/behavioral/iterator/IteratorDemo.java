package behavioral.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Title:  迭代器模式（配合工厂模式）
* Desc: 
* Created by Myth on 12/07/2019 in VSCode
*/

interface Iterator {
	public void first(); //将游标指向第一个元素
	public void next(); //将游标指向下一个元素
	public boolean hasNext(); //判断是否存在下一个元素
	public Object currentItem(); //获取游标指向的当前元素
}
interface Aggregate {
	Iterator createIterator();
}

class ConcreteIterator implements Iterator {
    // 维持一个对具体聚合对象的引用，以便于访问存储在聚合对象中的数据
    private ConcreteAggregate objects;
	private int cursor; //游标，记录当前访问位置
	public ConcreteIterator(ConcreteAggregate objects) {
        this.objects = objects;
        cursor = 0;
	}
 
	public void first() {  
        cursor = 0;
    }
		
	public void next() {
        cursor++;    
    }
 
	public boolean hasNext() {  
        return objects.integers.size() > cursor;
    }
	
	public Object currentItem() {  
        return objects.integers.get(cursor);          
    }
}

class ConcreteAggregate implements Aggregate {	
    // 聚合对象中的聚合元素
    List<Integer> integers = Arrays.asList(100, 101, 102, 103);
    // 工厂方法
    public Iterator createIterator() {
	    return new ConcreteIterator(this);
    }

}


public class IteratorDemo {
    
}