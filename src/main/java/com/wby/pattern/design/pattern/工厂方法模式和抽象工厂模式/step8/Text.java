package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step8;


/**
 * 声明一个工厂方法
 *      原本由一个对象负责所有具体类的实例化,现在通过PizzaStore编程由一群子类来负责实例化
 *      让我们看的更仔细一些:
 */
abstract class PizzaStore{
    //1. PizzaStore的子类在createPizza()方法中处理对象的实例化
    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza=createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    //2. 现在,实例化披萨的责任被转移到一个"方法"中,此方法就如同一个工厂
    //更为详细的说:工厂方法用来处理对象的创建,并将这样的行为封装在子类中.客户程序中关于超类的代码就和子类对象创建代码解耦了.
    //工厂方法模板:   abstract Product factoryMethod(String type)
    //abstract:工厂方法是抽象的,所以依赖子类来处理对象的创建
    //Product:工厂方法必须返回一个产品.超类中定义的方法,通常使用到工厂方法的返回值
    //factoryMethod:工厂方法将客户(也就是超类中的代码,例如orderPizza())和实际创建具体产品的代码分隔开来
    //String type:工厂方法可能需要参数(也可能不需要)来制定所需的产品
    public abstract Pizza createPizza(String type);
}
class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")){
            pizza=new NYCheesePizza();
        }else {
            return null;
        }
        return pizza;
    }
}

abstract class Pizza{
    void prepare(){}
    void bake(){}
    void cut(){}
    void box(){}
}

class NYCheesePizza extends Pizza {}

public class Text {
}
