package com.wby.pattern.design.pattern.设计箱内的工具;

/**
 * OO基础:
 *      1.抽象
 *      2.封装
 *      3.多态
 *      4.继承
 * OO原则:
 *      1.封装变化
 *          1.1:观察者模式中,变化的是主题的状态,以及观察者的数量和类型.用这个模式,你可以依赖于主体状态的对象,却不比改变主题.
 *      2.多用组合,少用继承
 *          2.1:观察者模式利用组合将许多观察者组合进入主题中.对象之间这种关系不是通过继承产生的,而是运行时利用组合的方式产生的.
 *      3.针对接口编程,不针对实现编程
 *          3.1:观察者模式中,主题与观察者都使用了接口,观察者利用主题的借口向主题注册,而主题利用观察者接口通知观察者,这样既可以让两者运作正常,又可以具有松耦合的特点
 *      4.为交互对象之间松耦合设计而努力
 *      5.对扩展开放,对修改关闭:现在有了开放-关闭原则引导,我们会努力设计系统,好让关闭的部分和扩展的部分隔离
 *      6.依赖抽象,不要依赖具体类
 *      7. 只和朋友交谈:最少知识原则
 *      8. 好莱坞原则:别调用(打电话)我们,我们会调用(打电话给)你
 * OO模式:
 *      1.策略设计模式:定义算法族.分别封装起来,让他们之间可以替换,此模式让算法的变化独立于使用算法的客户之外
 *      2.观察者模式:在对象之间定义一对多的依赖,这样一来,当一个对象改变状态时,依赖他的对象会收到通知,并自动更新
 *      3.装饰者模式:动态的将责任附加到对象上,若要扩展功能,装饰者提供了比继承更有弹性的替代方案.
 *      4. 工厂方法模式:定义了一个创建对象的接口,但由子类决定要实例化的类是哪一个.工厂方法让类把实例化推迟到子类
 *      5.抽象工厂模式:提供一个接口,用于创建相关或依赖对象的家族,而不需要明确指定具体类
 *      6. 单件模式--确保一个类只有一个实例,并提供全局访问点
 *      7. 命令模式--将请求封装成对象,让你使用不同的请求,队列,或者日志请求来参数化其他对象.命令模式也支持撤销操作
 *          当需要将发出请求的对象和执行请求的对象解耦的时候,使用命令模式
 *      8. 适配器模式:将一个类的接口,转换成客户期望另一个接口.适配器让原本不兼容的类可以合作无间
 *      9. 外观模式: 提供了一个统一的接口,用来访问子系统中的一群接口.外观定义了一个高层接口,让子系统更容易使用
 *      10. 模板方法模式: 在一个方法中定义一个算法骨架,而将一些步骤延迟到子类中.模板方法使得子类可以在不改变算法结构的情况下,重新定义算法中的某些步骤
 */
public class Text {
}
