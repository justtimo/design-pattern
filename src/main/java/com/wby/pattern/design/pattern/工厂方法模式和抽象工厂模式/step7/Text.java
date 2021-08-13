package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step7;


/** `
 * 开加盟店有他的好处,可以从PizzaStore免费取得所有的功能.区域点只需要继承PizzaStore,然后提供createPizza()方法实现自己的披萨风味即可
 */
//1. 现在PizzaStore是抽象的,下面会解释为何如此
abstract class PizzaStore{
    public Pizza orderPizza(String type){
        Pizza pizza;
        //2. 现在createPizza()从工厂对象中移入PizzaStore中
        pizza=createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    //3. 现在把工厂对象移到这个方法中:现在这个工厂方法是抽象的
    public abstract Pizza createPizza(String type);
}
abstract class Pizza{
    void prepare(){}
    void bake(){}
    void cut(){}
    void box(){}
}

//createPizza()返回一个Pizza对象,由子类全权负责实例化哪一个具体Pizza
//NYPizzaStore继承自PizzaStore,所以拥有orderPizza()方法(以及其他的方法)
class NYPizzaStore extends PizzaStore{
    //我们必须实现createPizza方法,因为他是抽象的
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")){
            pizza=new NYCheesePizza();//这是创建具体类的地方,对于每一种披萨类型,都是创建纽约风味
        }else if (type.equals("greek")){
            pizza=new NYGreekPizza();
        }else if (type.equals("pepperoni")){
            pizza=new NYPepperoniPizza();
        }else {
            return null;
        }
        return pizza;
    }

    /** `
     * 注意,超类的orderPizza()并不知道正在创建的披萨是哪一种,他只知道这个披萨可以被准备,烘烤,切片,装盒
     */
}
class ChicagoPizzaStore extends PizzaStore{
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")){
            pizza=new NYCheesePizza();//这是创建具体类的地方,对于每一种披萨类型,都是创建纽约风味
        }else if (type.equals("greek")){
            pizza=new NYGreekPizza();
        }else if (type.equals("pepperoni")){
            pizza=new NYPepperoniPizza();
        }else {
            return null;
        }
        return pizza;
    }
}
class NYCheesePizza extends Pizza {
}
class NYGreekPizza extends Pizza {
}
class NYPepperoniPizza extends Pizza {
}
class ChicagoCheesePizza extends Pizza {
}
class ChicagoGreekPizza extends Pizza {
}
class ChicagoPepperoniPizza extends Pizza {
}


public class Text {
}

