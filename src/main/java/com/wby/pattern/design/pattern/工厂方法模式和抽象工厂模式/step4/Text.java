package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step4;

/**
 * 简单工厂其实不是一个设计模式,反而更像一种编程习惯,有些人确实把这个习惯误认为是"工厂模式"
 *
 * 不要因为简单工厂不是一个真正的模式就忽略他的用法,让我们看看披萨店的类图:
 */

/** `
 * 这是工厂的客户,现在PizzaStore通过SimplePizzaFactory取得披萨的实例
 */
class PizzaStore{
    SimplePizzaFactory factory;
    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }
    public void orderPizza(String type){
        factory.cretePizza(type);
    }
}
/** `
 * 这是创建披萨的工厂,他应该是我们应用中唯一用到具体披萨类的地方
 */
class SimplePizzaFactory{
    public void cretePizza(String type){}
}
/** `
 * 这是工厂的"产品",披萨.
 * 吧Pizza定义为抽象类,具有一些有用的实现,这些实现可以被覆盖
 */
abstract class Pizza{
    void prepare(){}
    void bake(){}
    void cut(){}
    void box(){}
}
/** `
 * 这四个是我们的"具体产品",每个产品都必须事先Pizza接口(本例子中指"扩展抽象的Pizza类")并设计成一个实体类.这样一来,就可以被工厂创建,并返回给客户
 */
class CheesePizza extends Pizza { }
class VeggiePizza extends Pizza { }
class ClamPizza extends Pizza { }
class PepperoniPizza extends Pizza { }


/** `
 * 接下来登场的是两个重量级模式,他们都是工厂.
 *
 * 注意:设计模式中,所谓"实现一个接口"并不一定表示"写一个类,并利用implements关键字来实现某个java接口"
 *      "实现一个接口"泛指"实现某个超类型(可以是类或接口)的某个方法"
 */
public class Text {
}
