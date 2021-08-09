package com.wby.pattern.design.pattern.装饰者模式3.step4;

/**
 * 用装饰者构造饮料订单:
 *  1.建立DarkRoadst,有cost()方法
 *  2.建立Mocha,包着DarkRoast,并且也有cost()
 *  3.建立Whip,包着Mocha,并且有cost()
 *  4.算钱,先调用whip的cost方法,whip调用Mocha的cost方法,Mocha调用DarkRoast的cost方法,然后再从里到外返回结果.
 *
 *  这就是我们目前知道的一切:
 *      1.装饰者,被装饰者具有相同的超类型.
 *      2.可以用一个或多个装饰者包装一个对象(Whip,Mocha包装DarkRoast)
 *      3.既然拥有相同的超类型,那么任何需要原始对象(被包装的)的场合,可以用装饰对象代替他
 *      4.*** 装饰者可以在所委托被装饰者的行为之前\之后,加上自己的行为,以达到特定的目的!!!(!!!!!!非常重要)
 *      5.对象可以在任何时候被装饰,所以可以在运行时动态的,不限量的使用装饰者来装饰对象
 *
 *  装饰者模式:动态的将责任附加到对象上,若要扩展功能,装饰者提供了比继承更有弹性的替代方案.
 *
 */
public class Text {
}

/**
 * 饮料:所有的饮料和调料的共同接口
 *
 * @author LangWeiXian
 * @date 2021/08/05
 */
class Beverage{
    private String description;

    public String getDescription() {
        return description;
    }

    public void cost(){}
}
//四个具体组件,每个都代表一种咖啡类型
class HouseBlend extends Beverage{}     class DarkRoast extends Beverage { }    class Decaf extends Beverage{}    class Espresso extends Beverage{}

/**
 * 调味品装饰,所有调料的共同接口
 *
 * @author LangWeiXian
 * @date 2021/08/05
 */
class CondimentDecorator extends Beverage{
    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
class Milk extends CondimentDecorator{
    Beverage beverage;

    @Override
    public void cost() {
        super.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
class Mocha extends CondimentDecorator {
    @Override
    public void cost() {
        super.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
class Soy extends CondimentDecorator{
    @Override
    public void cost() {
        super.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
class Whip extends CondimentDecorator {
    @Override
    public void cost() {
        super.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
/**
* 关于此例中继承和组合的观念混淆
 * Q: 原以为此模式中不会使用继承,而是会利用组合取代继承,类图上看CondimentDecorator继承自Beverage
 * A:确实如此,但是这么做的重点在于,装饰者和被装饰者必须是一样的类型,也就是具有共同的超类.这里,我们利用继承达到"类型匹配",而不是利用继承获得"行为"
 * Q:我知道装饰和和被装饰者必须具有相同的接口,因为装饰者必须能取代被装饰者,但是行为又是从哪来的?
 * A:当我们将装饰者与组件组合时,就是在加入新的行为.所得到的新行为,并不是继承自超类,而是由组合对象而来.
 * Q:继承Beverage抽象类是为了有正确的类型,而不是继承他的行为.行为来自装饰者和基础组件,.或者与其他装饰着之间的组合关系.
 * Q:我明白了,而且因为使用对象组合,可以吧所有饮料和调料更有弹性的加以混合与匹配,这会显得更方便.
 * A:是的,如果依赖继承,那么类的行为只能在编译期静态决定.即,行为不是来自超类,就是子类覆盖后的版本.反之,使用组合,可以吧装饰者混合使用,而且是在"运行时"
 * Q:而且,可以在任何时候,实现新的装饰者增加新的行为.如果使用继承,每当需要新行为时,还得修改现有的代码.
 * Q:还有个问题,我们需要继承的是comment类型,为什么不把Beverage设计成接口而是抽象类呢?
 * A:当我们从星巴克拿到Beverage时,他已经是一个抽象类了.
 *      通常,装饰者模式是采用抽象类,但是在Java中可以使用接口.
 *      尽管如此,通常我们都避免修改现有代码,所以既然他运行的很好,我们还是不要修改它
*/
