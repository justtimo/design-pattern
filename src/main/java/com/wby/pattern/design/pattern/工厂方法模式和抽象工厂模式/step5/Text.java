package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step5;

/**
 * 披萨店现在可以加盟了!
 * 为了确保加盟店运营质量,你希望这些点都使用你那些经过时间考验的代码.
 *
 * 但是区域的差异呢?每家加盟店都想提供不同风味的披萨(纽约披萨.芝加哥披萨),者受到开店地点以及该地区披萨美食家口味的影响
 *
 * PizzaStore:加盟店都能利用你的代码,让披萨的流程能一直不变
 * NYPizzaFactory:其中一家加盟店希望工厂能制造纽约风味的披萨:薄饼,酱料和少量芝士
 * ChicagoPizzaFactory:另一家希望工厂能制造芝加哥风味披萨:厚饼,重口味的酱料,大量的芝士
 */

/** `
 * 有一种做法:
 *      利用SimplePizzaFactory,写出三种不同的工厂,分别是NYPizzaFactory,ChicagoPizzaFactory,CaliforniaPizzaFactory,那么各地加盟店都有
 *      适合的工厂可以使用.让我们看看会变成什么样子
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

class SimplePizzaFactory{
    public void cretePizza(String type){}
}
class NYPizzaFactory extends SimplePizzaFactory{
}
class ChicagoPizzaFactory extends SimplePizzaFactory {

}

public class Text {
    public static void main(String[] args) {
        //1. 创建的工厂是制造纽约风味的披萨
        NYPizzaFactory nyPizzaFactory = new NYPizzaFactory();
        //2. 然后建立披萨店,将纽约工厂的引用作为参数
        PizzaStore nyStore = new PizzaStore(nyPizzaFactory);
        //3. 制造披萨,会得到纽约风味的披萨
        nyStore.orderPizza("Veggie");

        ChicagoPizzaFactory chicagoPizzaFactory = new ChicagoPizzaFactory();
        PizzaStore chicagoStore = new PizzaStore(chicagoPizzaFactory);
        //芝加哥披萨店也类似,先建立芝加哥风味的工厂,并建立一个披萨店,然后结合两者制造出来的披萨就是芝加哥风味的披萨
        chicagoStore.orderPizza("Veggie");
    }
}
