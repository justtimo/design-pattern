package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step3;

/** `
 * 重做Pizza类
 */
public class Pizza {
    //1. 加入工厂的引用
    SimplePizzaFactory factory;
    //2. 构造器需要一个工厂作为参数
    public Pizza(SimplePizzaFactory factory){
        this.factory = factory;
    }
    Pizza orderPizza(String type){
        Pizza pizza;
        //3. 将new操作替换成工厂对象的创建方法,这里不再使用具体实例化
        pizza= factory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
    public void prepare(){
        System.out.println("准备披萨");
    }
    public void bake(){
        System.out.println("加调料");
    }
    public void cut(){
        System.out.println("切片");
    }
    public void box(){
        System.out.println("装盒");
    }
}

class SimplePizzaFactory{
    //所有客户使用这个方法来实例化新对象
    public Pizza createPizza(String type){
        Pizza pizza;
        if (type.equals("cheese")){
            return new CheesePizza(new SimplePizzaFactory());
        }else if (type.equals("greek")){
            return new GreekPizza(new SimplePizzaFactory());
        }else if (type.equals("pepperoni")){
            return new PepperoniPizza(new SimplePizzaFactory());
        }else {
            return null;
        }
    }
}
class CheesePizza extends Pizza {
    public CheesePizza(SimplePizzaFactory factory) {
        super(factory);
    }
}
class GreekPizza extends Pizza {
    public GreekPizza(SimplePizzaFactory factory) {
        super(factory);
    }
}
class PepperoniPizza extends Pizza {
    public PepperoniPizza(SimplePizzaFactory factory) {
        super(factory);
    }
}
/** `
 * 我们知道对象组合可以在运行时动态改变行为,因为我们可以更换不同的实现.
 * 在上例中如何做到这一点呢?有哪些工厂的实现能够被我们自由更换?
 */

























