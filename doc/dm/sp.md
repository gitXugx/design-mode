### 单例设计模式(Singleton Pattern)
> 这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访> > 问，不需要实例化该类的对象。

**类型:** 创建型

**使用目的**
+ 保证该类在内存中只有一个实例(注意多线程问题)
+ 减少重量级对象创建带来的系统损耗，减少对象的平凡销毁和创建

**要点**
+ 多线程控制
+ 构造函数私有化
+ 反射攻击防御

#### 1. 代码

单例模式有很多种实现方式，各种都有优缺点

- 懒汉模式
- 饿汉模式
- 静态内部类
- 枚举类实现

可能单例模式时最简单的一个，但是它涉及的知识也是最多的。


##### 1.1 懒汉模式 
> 懒汉模式：看到名字你可能就能知道什么意思，需要时才创建实例对象

``````java
public class IdlerSingle {
    //1. 私有化构造函数
    private IdlerSingle(){}
    //2. 声明变量
    private static IdlerSingle idlerSingle = null;
    //3. 创建获取实例方法
    public static IdlerSingle getInstance(){//这个方法并不是线程安全，可能创建多个对象
        if(idlerSingle == null){
            idlerSingle = new IdlerSingle();
        }
        return idlerSingle;
    }
}

``````

上面懒汉并不是线程安全的，简单粗暴的就是加上synchronized的

`````java
public static synchronized IdlerSingle getInstance(){
`````

但是上面因为锁的粒度太大，不论当**idlerSingle**初始化成功没，每个线程来访问都要进行获取锁,当并发很多时可能会降低性能，提高性能就是降低锁粒度.


``````java
public class IdlerSingle {
    //1. 私有化构造函数
    private IdlerSingle(){}
    //2. 声明变量
    private static IdlerSingle idlerSingle = null;
    //3. 创建获取实例方法
    public static  IdlerSingle getInstance(){//这个方法并不是线程安全，可能创建多个对象
        if(idlerSingle == null){ // 1.
            synchronized(IdlerSingle.class){ // 2.
                if (idlerSingle == null){  // 3.
                    idlerSingle = new IdlerSingle();
                }
            }
        }
        return idlerSingle;
    }
}
``````

我们看到synchronized 关键字已经加在了方法体的内部，进行了个双重校验，一旦当第一次初始化完成后，后面的线程进来并不需要获取锁而是判断直接返回，相对上个粗暴的方法这种更优一些。
但是这种方法也有一些问题:
因为 *idlerSingle = new IdlerSingle();* 他在JVM初始化的时候并不是一个原子性的操作主要分为：
1. jvm给idlerSingle分配堆空间
2. 初始化idlerSingle对应的参数
3. 把idlerSingle指针指向分配的堆空间(这部操作完毕 就不是null了)

**因为JVM在动态编译的时候可能会在不影响结果的情况下进行指令重排优化**(什么是指令重排序：是指CPU采用了允许将多条指令不按程序规定的顺序分开发送给各相应电路单元处理)，1 步骤时固定的，但是2和3有可能会出现谁前谁后的问题

那么结合上面的代码，当线程1 进来初始化顺序为132，那么第二个线程在线程1 执行到3完毕的时候，去判断肯定*if(idlerSingle == null)*不为null，那么线程2在使用实例进行操作时就有可能发生异常。

**那么处理上面的思路就有两种**
1. 不让指令进行重排
2. 让初始化线程安全


当然在java中有一个关键**volatile**，它有两层语义:
1. 可见性(当一个线程修改了这个变量的值，volatile 保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。)
2. 禁止指令重排优化

那我们可以在*private static volatile IdlerSingle idlerSingle = null;* 加上volatile就能解决上面提到因指令重排导致的bug。


##### 1.2 饿汉模式
> 上面看完了懒汉模式，相信你已经猜到了，饿汉就是你需不需要我都创建实例对象

``````java

public class HungrySingle {

    // 1.私有化构造函数
    private HungrySingle (){}
    // 2. 利用类加载的时候时线程安全的，进行初始化
    private static final HungrySingle hungrySingle = new HungrySingle();

    public static HungrySingle getInstance(){
        return hungrySingle;
    }
}

``````

这个就是我们上面解决方案的第二种，让初始化线程安全，具体在什么情况下类在加载的时候才会初始化，这边我需要查下jvn加载类时就初始化需要什么条件

##### 1.3 静态内部类模式

```java
public class StaticInnerClassSingle {
    // 1.私有构造方法
    private StaticInnerClassSingle(){}
    // 2.创建内部类
    private static class SingleHandler{
        private static final StaticInnerClassSingle INSTANCE = new StaticInnerClassSingle();
    }
    // 3.获取实例
    public static  StaticInnerClassSingle getInstance(){
        return SingleHandler.INSTANCE;
    }
}

```
静态内部类的初始化和正常的类初始化一样，在调用的时候才会初始化。所以该实现方式，既保证了对象初始化的安全，又可以做到用到的时候才初始化，保证资源不被浪费

##### 1.4 枚举类实现模式
























































