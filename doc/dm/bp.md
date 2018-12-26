# 建造者模式((Builder Pattern)
> 它可以将复杂对象的建造过程抽象出来（抽象类别），使这个抽象过程的不同实现方法可以构造出不同表现（属性）的对象。

**类型:** 创建型

**使用目的**
+ 构造函数参数过多，或者构造函数很多时
+ 用来构建复杂对象生成不同状态的实例

**要点**
+ 创建实例
+ 管理创建的实例

## 1. 代码

+ v.1 建造者模式
+ v.2 建造者模式

### v.1 建造者模式

以营销场景为例，当客户端创建营销时根据具体的建造者来创建不同的营销方式。

1. 创建营销对象
2. 创建build抽象对象
3. 创建build实现
4. 创建build管理者

大致分为这4个步骤:

创建p一个简单的营销对象。

``````java
public class Markting {
    private String loadPerson;
    private String filterPerson;
    private String send;
}
``````

创建抽象:

``````java

public abstract class Builder {
    protected Markting markting =  new Markting();

    abstract void buildLoadPerson();

    abstract void buildSend();

    abstract void buildFilter();

    protected Markting createMarkting(){
        buildLoadPerson();
        buildSend();
        buildFilter();
        return markting;
    }
}

``````
具体的实现在对应的builder中，分为短信和邮件:

``````java
public class EmailBuilder extends Builder{

    @Override
    void buildLoadPerson() {
        super.markting.setLoadPerson("加载需要邮件营销人");
    }

    @Override
    void buildSend() {
        super.markting.setSend("开始发送邮件");
    }

    @Override
    void buildFilter() {
        super.markting.setFilterPerson("过滤邮件名单");
    }

}

public class SMSBuilder extends Builder{

    @Override
    void buildLoadPerson() {
        super.markting.setLoadPerson("加载需要短信营销人");
    }

    @Override
    void buildSend() {
        super.markting.setSend("开始发生短信营销");
    }

    @Override
    void buildFilter() {
        super.markting.setFilterPerson("过滤短信黑名单");
    }

}
``````

提供一个builder管理者来创建对应的对象:

``````java
public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }
    public Markting construct(){
        return builder.createMarkting();
    }
}

``````

client端的实现:

``````java
public class Client {

    public  static void main(String[] args){
        Director director = new Director(new SMSBuilder());
        Markting construct = director.construct();
        System.out.println(construct.toString());
    }

}
``````






























