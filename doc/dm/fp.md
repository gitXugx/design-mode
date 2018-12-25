### 工厂模式
> 定义了一个创建产品对象的工厂接口，将实际创建工作推迟到子类工厂当中。

**类型:** 创建型

**使用目的**
+ 使其更有扩展性，降低客户端耦合

**要点**
+ 定义一个创建的接口给客户端
+ 使创建延迟到子类

#### 1. 代码

+ 简单工厂
+ 工厂模式
+ 抽象工厂

每个模式都解决了不同维度的问题

##### 1.1 简单工厂

假设有一家汽车厂家，客户过来订车

1. 创建汽车抽象
2. 实现汽车细节
3. 创建工厂
4. 创建client

首先创建产品抽象,我们的产品是汽车,这边没有写汽车的行为

``````java
public interface Car {
}
`````

下面实现细节 小型轿车

``````java
public class CompactCar implements  Car{
    private String name;
    private String price;
}
``````
不同的产品:
``````java
public class SUVCar  implements  Car{
    private String name;
    private String price;
}
``````
生产汽车的工厂类

``````java
public class   SimpleFactory {

    public static Car createCar(String type){
        switch (type){

            case "A":
                return new CompactCar("小型轿车" ,"10W - 15W");
            case "B":
                return new SUVCar("SUV轿车" ,"20W - 30W");
            default: throw new RuntimeException("没有你要的机型");
        }
    }

}
``````

具体的客户端:

``````java

public class Client {
    public  static void main(String[] args){
        Car a = SimpleFactory.createCar("A");
        System.out.println(a);
        Car b = SimpleFactory.createCar("B");
        System.out.println(b);
    }
}
``````

可以看到好像创建的时候传入字符串并不优雅，当增加新的产品时我们还需要去修改工厂，我们可以通过反射来修整一下:具体修改的 SimpleFactory类

``````java
public class   SimpleFactory {

    public static Car createCar(Class clazz){
        try {
            return (Car)Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace(); //这种错误打印很不好
            return null;
        }
    }
}

public class Client {
    public  static void main(String[] args){
        Car a = SimpleFactory.createCar(CompactCar.class); //传参就变成了class对象
        System.out.println(a);
        Car b = SimpleFactory.createCar(SUVCar.class);
        System.out.println(b);
    }
}

``````
这样我们就可以复合开闭原则.简单工厂名字，就和它的名字差不都，设计很简单
通过这样一修改，就符合前面我们设计原则中的开闭原则，迪米特原则;

#### 1.2 工厂模式


























