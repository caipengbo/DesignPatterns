package creational.singleton;

import headfirst.singleton.subclass.Singleton;

/**
 * Title: 单例模式
 * Desc: 保证一个类仅有一个实例，并提供一个访问它的全局访问点（访问方法）。
 *
 * Created by Myth-Lab on 11/24/2019
 */
/*
意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
主要解决：一个全局使用的类频繁地创建与销毁。
何时使用：当您想控制实例数目，节省系统资源的时候。
如何解决：判断系统是否已经有这个单例，如果有则返回，如果没有则创建。
关键代码：构造函数是私有的。
应用实例：
    1、逻辑上只能有一个实例的情况：一些设备管理器常常设计为单例模式，比如一个电脑有两台打印机，在输出的时候就要处理不能两台打印机打印同一个文件；一个班级只能有一个班主任
    2、Windows 是多进程多线程的，在操作一个文件的时候，就不可避免地出现多个进程或线程同时操作一个文件的现象，所以所有文件的处理必须通过唯一的实例来进行。
    3、一些很耗费资源的对象
优点：
    1、在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例（比如管理学院首页页面缓存）。
    2、避免对资源的多重占用（比如写文件操作）。
缺点：没有接口，不能继承，与单一职责原则冲突，一个类应该只关心内部逻辑，而不关心外面怎么样来实例化。
使用场景：
    1、要求生产唯一序列号。
    2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
    3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。
注意事项：getInstance() 方法中需要使用同步锁 synchronized (Singleton.class) 防止多线程同时进入造成 instance 被多次实例化。
 */
// 衡量标准： 懒加载、线程安全、效率高

// 经验之谈：一般情况下，不建议使用第 1 种懒汉方式，建议使用第 2种饿汉方式。
// 只有在要明确实现 lazy loading 效果时，才会使用第 4 种登记方式。
// 如果涉及到反序列化创建对象时，可以尝试使用第 5 种枚举方式。
// 如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。

// 1. 懒汉式, 线程安全（去掉synchronied就是不安全的）
class Singleton1 {
    private static Singleton1 instance;
    // 构造方法 私有化（放置其他类实例化它）
    private Singleton1(){}
    // synchronied 保证现在安全（但是效率会变低）
    public static synchronized Singleton1 getInstance() {
        if (instance == null) instance = new Singleton1();
        return instance;
    }
}
// 2. 饿汉式（类加载时就完成实例化了，避免了线程同步，线程安全）
// 缺点：没有懒加载
class Singleton2 {
    // 在静态方法中使用，所以必须是static变量
    private static final Singleton2 instance = new Singleton2();
    private Singleton2(){}
    public static Singleton2 getInstance() {
        return instance;
    }
}
// 3. 双重校验锁DCL（ >JDK1.5）避免了synchronied的低效率，同时也能懒加载，线程安全
class Singleton3 {
    private volatile static Singleton3 instance;
    // 构造方法 私有化（放置其他类实例化它）
    private Singleton3(){}
    // synchronied 保证现在安全（但是效率会变低）
    public static Singleton3 getInstance() {
        if (instance == null) {  // 仅当实例未初始化前进行同步
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
// 4. 静态内部类(懒加载，延迟初始化，又可以保证线程安全)
class Singleton4 {
    private static class Singleton4Holder {  // 该静态内部类被使用时才会创建一个 单例
        private static Singleton4 instance = new Singleton4();
    }
    private Singleton4(){}
    public static Singleton4 getInstance() {
        return Singleton4Holder.instance;
    }
}
// 5. 枚举方式
enum Singleton5 {
    INSTANCE;
    // Singleton5的其他方法
    public void Singleton5Method() {
    }
}
public class SingletonDemo {
    public static void main(String[] args) {

    }
}
