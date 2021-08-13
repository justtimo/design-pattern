package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step2;

/**
 * 现在将创建对象移到orderPizza()外面,把创建披萨的代码移到另外一个对象中,由这个对象专门创建披萨
 *
 * 我们称这个新对象为"工厂":
 *      工厂处理创建对象的细节.
 *      一旦有了SimplePizzaFactory,orderPizza()就变成此对象的客户.当需要披萨时,就让披萨工厂做一个.
 *      那些需要orderPizza()方法知道是希腊披萨还是蛤蜊披萨的日子一去不复返.现在orderPizza只关心从工厂得到了一个披萨,并且这个披萨实现了
 *          pizaa接口,所以他可以调用准备\烘烤\切片\装盘等方法
 *       还有一些细节待补充,比如,原本在orderPizza方法中创建的代码,现在怎么写?现在就来实现一个简单地披萨工厂,来研究这个问题.
 */
public class Pizza { }
class SimplePizzaFactory{
    //所有客户使用这个方法来实例化新对象
    public Pizza createPizza(String type){
        Pizza pizza;
        if (type.equals("cheese")){
            return new CheesePizza();
        }else if (type.equals("greek")){
            return new GreekPizza();
        }else if (type.equals("pepperoni")){
            return new PepperoniPizza();
        }else {
            return null;
        }
    }
}
class CheesePizza extends Pizza{}
class GreekPizza extends Pizza { }
class PepperoniPizza extends Pizza { }
/** `
 * Q: 这么做有什么好处?似乎只是吧问题搬到了另一个对象罢了,问题依旧存在
 * A: SimplePizzaFactory可以由很多客户.虽然目前只看到orderPizza方法是他的客户,然而,可能还有PizzaShopMenu(披萨店菜单)类,会利用讴歌这个工厂
 *      取得披萨的价钱和描述;可能会有HomeDelivery(宅急送)类,会以与PizzaShop类不同的方式来处理披萨.总而言之,SimplePizzaFactory可以有许多客户
 *      所以,把创建披萨的代码包装进一个类,当以后实现改变时,只需修改这个类即可.
 *      我们也正要把具体实例化的过程,从客户的代码中删除
 * Q: 我曾看到过把工厂定义为一个静态的方法这种设计方式,这有何差别?
 * A: 利用静态方法定义一个简单工厂是常见的技巧,常被成为静态工厂.
 *      为何使用静态方法?因为不需要使用创建对象的方法来实例化对象.但是,这也有缺点,不能通过继承来改变创建方法的行为.
 */



















