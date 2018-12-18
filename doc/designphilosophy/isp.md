### 接口隔离原则
> 耦合度、内聚度、扩展性、冗余度、可维护性，可测试性

#### 1. 定义:
客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上。
**理解:**
1. 如果一个类要遵守单一职责原则，那他实现的接口也应该遵守单一职责原则。
2. 单一原则主要约束的是类和实现。接口隔离主要约束的是接口和抽象，让我们更加合理的构建框架。
3. 客户端不应该依赖他不需要的接口，实际上是臃肿的接口进行拆分，继承来提高灵活度，也可以是增强接口的内聚性，来减少不必要的接口，来减少接口与客户端的联系（降低耦合性，提高可扩展性）
4. 类与类的依赖应该建立在最小的接口，为依赖接口的暴露最少的方法提供服务（高内聚）

#### 3. 总结



