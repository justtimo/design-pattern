package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step6;

/**
 * 但是想要多一些质量控制: 我发现加盟店虽然采用我的工厂创建披萨.但是却采用他们自创的流程:不要切片,使用其他厂商的盒子
 *
 * 如此一来,我希望能够建立一个框架,吧加盟店和创建披萨捆绑在一起的同时又保持一定的弹性.
 * 稍早的SimplePizzaFactory之前,只做pizza的代码绑在了PizzStore中,但这么做却没有弹性.
 * 怎么做才能鱼和熊掌兼得呢?
 *
 * 给披萨店使用的框架::
 *      把createPizza()放回到PizzaStore中,不过把他设置为"抽象方法",然后为每个区域风味创建一个PIzzaStore的子类
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

/** `
 * 现在已经有了一个PizzaStore作为超类:每个域类型(NYPizzaStore,ChicagoPizzaStore,CaliforniaPizzaStore)都继承这个PizzaStore,
 *      每个子类格子决定如何制造披萨,让我们看看如何进行
 * 别忘了,PizzaStore已经有了不错的订单系统,由orderPizza()负责处理订单,而我希望所有加盟店对于订单的处理都能够一致
 *
 * 各个地区披萨店的差异在于他们披萨的风味,现在要让createPizza()能够应对这些变化来负责创建正确种类的披萨.做法是让子类自己定义自己的createPizza()
 *      所以我们会得到一些PizzaStore的具体子类,每个子类都有自己的披萨变体,而仍然适合PIzzaStore框架,并使用调试好的orderPizza()
 */
class NYStylePizzaStore extends PizzaStore{

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")){
            pizza=new NYCheesePizza();
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

class ChicagoStylePizzaStore extends PizzaStore{

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")){
            pizza=new ChicagoCheesePizza();
        }else if (type.equals("greek")){
            pizza=new ChicagoGreekPizza();
        }else if (type.equals("pepperoni")){
            pizza=new ChicagoPepperoniPizza();
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

/** `
 * Q: 我不明白,毕竟PizzaStore的子类终究是子类,如何能做决定?在NYStylePizzaStore类中,并没有看到任何做决定的代码.
 * A: 关于这点,要从PizzaStore的orderPizza()方法观点来看,此方法在抽象的PizzaStore内定义,但是只在子类中实现具体类型.
 *      orderPizza()在抽象的PizzaStore内而不是在子类中定义,所以此方法并不知道哪个子类将实际上制作披萨
 *
 *      现在,更进一步,orderPizza()方法对Pizza对象做了很多事情(准备,烘烤,切片,装盒),但是由于Pizza对象时抽象的,orderPizza并不知道那些实际的具体
 *      类参与进来了.换句话说,这就是在 解耦.
 *
 *      orderPizza()调用createPizza()取得披萨对象,但究竟会得到哪一种披萨饼,这不是orderPizza能决定的,那么是由谁决定的呢?
 *      当调用createPizza()时,某个披萨店将负责创建披萨.
 *
 *      那么子类是实时做出这样的决定吗?并不是,从orderPizza()的角度看,如果选择在NYStylePizzaStore订购披萨,那么就是由这个子类(NYStylePizzaStore)
 *      决定.严格来说,并非有这个子类实际做"决定",而是由"顾客"决定到哪一家风味的披萨店才决定了披萨的风味.
 */
public class Text {
}


























