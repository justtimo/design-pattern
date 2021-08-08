package com.wby.pattern.design.pattern.装饰者模式3.step1;

/**
 * 本章可以称为:给爱用继承的人一个全新的设计眼界.
 * 本章将再度讨论继承滥用的问题,并学习如何通过组合做到运行时装饰类.
 * 一旦学会次技巧,你将能够在不修改任何底层代码的情况下,给对象赋予新职责
 */
public class Text {
}
/**
* 星巴克咖啡.
 * Beverage饮料是一个抽象类,店内所有的饮料都必须继承自此类
*/
abstract class Beverage{

    /**
     * 描述:由每个子类设置,用来描述饮料
     */
    private String description;

    public String getDescription() {
        return description;
    }

    /**
     * 每个饮料实现此方法,返回饮料的价格
     */
    abstract int cost();
}
class HourseBlend extends Beverage{

    @Override
    int cost() {
        return 1;
    }
}
class DarkRoast extends Beverage {

    @Override
    int cost() {
        return 2;
    }
}
class Decaf extends Beverage {

    @Override
    int cost() {
        return 3;
    }
}
class Espost extends Beverage {

    @Override
    int cost() {
        return 4;
    }
}
/**
* 购买咖啡时,在其中加入各种调料:蒸奶,豆浆,摩卡,奶泡等等
 * 所以订单系统必须考虑到这些调料的部分
 * 每个cost方法都必须计算订单上各种调料的价格.
 *
 * 违反了两个设计原则:松耦合 和 XX,如果牛奶涨价或者新增一种焦糖调料时,怎么办?
 *
*/
