package behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
* Title: Java中迭代器的使用
* Desc: 提供一种方法对一个容器对象中的各个元素进行访问，而又不暴露该对象容器的内部细节
* Created by Myth-MBP on 14/05/2020 in VSCode
*/

// 迭代器
//java.util.Iterator:
// public interface Iterator<E> {
//     boolean hasNext();//判断是否存在下一个对象元素
//     E next();//获取下一个元素
//     void remove();//移除元素
// }

// Java中还提供了一个Iterable接口，Iterable接口实现后的功能是‘返回’一个迭代器，
// 我们常用的实现了该接口的子接口有:Collection<E>、List<E>、Set<E>等。该接口的iterator()方法返回一个标准的Iterator实现。
// 实现Iterable接口允许对象成为Foreach语句的目标。就可以通过foreach语句来遍历你的底层序列。
// Iterable接口包含一个能产生Iterator对象的方法，并且Iterable被foreach用来在序列中移动。因此如果创建了实现Iterable接口的类，都可以将它用于foreach中。
// java.lang:
// public interface Iterable<T> {
//     Iterator<T> iterator();
// }

// 一般的用法，聚合对象实现Iterable接口，聚合对象内部类Itr实现Iterator作为自己的迭代器
class MyList implements Iterable<Integer> {

    int MAX_SIZE = 100;

    Integer[] data = new Integer[MAX_SIZE];

    private int size = 0;

    public void add(Integer ele) {
        if (size < MAX_SIZE) data[size++] = ele;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<Integer> {

        private int cursor;
        public Itr() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Integer next() {
            if (cursor >= size) return null;
            return data[cursor++];
        }
    }
}
public class JavaIteratorDemo {
    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        for (Integer ele : myList) {
            System.out.println(ele);
        }
    }
}