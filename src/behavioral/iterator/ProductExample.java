package behavioral.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 商品管理示例 Desc: Created by Myth on 12/07/2019 in VSCode
 */

//抽象聚合类
abstract class AbstractObjectList {
	protected List<Object> objects = new ArrayList<Object>();
 
	public AbstractObjectList(List objects) {
		this.objects = objects;
	}
	
	public void addObject(Object obj) {
		this.objects.add(obj);
	}
	
	public void removeObject(Object obj) {
		this.objects.remove(obj);
	}
	
	public List<Object> getObjects() {
		return this.objects;
	}
	
    // 迭代器对象的抽象工厂方法
	public abstract AbstractIterator createIterator();
}
//抽象迭代器
interface AbstractIterator {
	public void next(); //移至下一个元素
	public boolean isLast(); //判断是否为最后一个元素
	public void first(); //判断是否为第一个元素
	public Object getCurItem(); //获取下一个元素
}

//商品数据类：具体聚合类
class ProductList extends AbstractObjectList {
	public ProductList(List products) {
        super(products);
	}
	
	public AbstractIterator createIterator() {
        // 在此处更改Iterator，原有的Iterator不用改变
		return new ProductIterator(this);
	}
} 

//商品迭代器：具体迭代器
class ProductIterator implements AbstractIterator {
	private List<Object> products;  // 具体的迭代器中包含着要访问的具体类的数据
	private int cursor;
	
	public ProductIterator(ProductList list) {
		this.products = list.getObjects();
		cursor = 0;
	}
	
	public void next() {
		if(cursor < products.size()) {
			cursor++;
		}
	}
	
	public boolean isLast() {
		return (cursor == products.size());
	}
	
	
	public void first() {
		cursor = 0;
	}
	
	public Object getCurItem() {
		return products.get(cursor);
	} 	
}
// 再增加一个新的 UserList, 实现相应的Iterator就行
// 另一种实现方法 —— 内部类
class UserList extends AbstractObjectList {
    
    private class UserIterator implements AbstractIterator {
        private int cursor;
        public UserIterator() {
            cursor = 0;
        }
        
        public void next() {
            if(cursor < objects.size()) {
                cursor++;
            }
        }
        
        public boolean isLast() {
            return (cursor == objects.size());
        }
        
        public void first() {
            cursor = 0;
        }
        
        public Object getCurItem() {
            return objects.get(cursor);
        } 	
    }

    public UserList(List objects) {
		super(objects);
	}
	
	public AbstractIterator createIterator() {
        // 在此处更改Iterator，原有的Iterator不用改变
		return new UserIterator();
	}
} 

public class ProductExample {
    public static void main(String[] args) {
        List<String> products = Arrays.asList("product0", "product1", "product2", "product3");
        List<String> users = Arrays.asList("user0", "user1", "user2", "user3");
        ProductList productList = new ProductList(products);
        UserList userList = new UserList(users);
        AbstractIterator productIterator = productList.createIterator();
        while (!productIterator.isLast()) {
            System.out.println(productIterator.getCurItem());;
            productIterator.next();
        }
        
        AbstractIterator userIterator = userList.createIterator();
        while (!userIterator.isLast()) {
            System.out.println((String)userIterator.getCurItem());;
            userIterator.next();
        }
    }
}