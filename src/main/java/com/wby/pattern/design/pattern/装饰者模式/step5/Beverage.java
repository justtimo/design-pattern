package com.wby.pattern.design.pattern.装饰者模式.step5;


/**
 * 饮料抽象类:装饰者模式中的 顶级超类
 *
 * @author LangWeiXian
 * @date 2021/08/05
 */
public abstract class Beverage {
    String description="UnKnown Beverage";

    public String getDescription() {
        return description;
    }
    public abstract double cost();
}

/**
 * 浓缩咖啡
 *
 * @author LangWeiXian
 * @date 2021/08/05
 */
class Espresso extends Beverage {
    public Espresso() {
        //为了设置描述,我们写了一个构造器,description来自父类
        description = "Espresso";
    }

    @Override
    public double cost() {
        //不用管调料的价格,只需要返回浓缩咖啡的价格即可
        return 1.99;
    }
}
class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
    }
    @Override
    public double cost() {
        return 0.89;
    }
}
class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "HouseBlend";
    }
    @Override
    public double cost() {
        return 0.89;
    }
}
class Decaf extends Beverage {
    public Decaf() {
        description = "HouseBlend";
    }
    @Override
    public double cost() {
        return 0.89;
    }
}

/**
 * 调味品装饰: 调料抽象类,也就是装饰者类,所有调料的父类
 *
 * @author LangWeiXian
 * @date 2021/08/05
 */
abstract class CondimentDecorator extends Beverage{
    public abstract String getDescription();
}

/**
 * 摩卡:是一个装饰者,所以继承自CondimentDecorator
 * 要让Mocha能够引用一个Beverage,实现方式如下:
 *      1.用一个实例变量记录饮料,也就是被装饰者
 *      2.想办法让被装饰者(饮料)被记录到实例变量中:这里的做法是,把饮料当做构造器参数,再由构造器将饮料记录在实例变量中
 *
 * @author LangWeiXian
 * @date 2021/08/05
 */
class Mocha extends CondimentDecorator{
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * 成本:计算带Mocha饮料的价格.
     * 首先将调用委托给被装饰对象,以计算价钱,然后再加上Mocha的价钱,得到最后结果
     * @return double
     */
    @Override
    public double cost() {
        return 0.20+ beverage.cost();
    }

    /**
     * 得到描述:我们希望不只是描述饮料,而是完整的连同调料也描述出来.
     * 所以首先利用委托的做法,得到一个叙述,然后再加上附加的叙述(Mocha)
     * @return {@link String}
     */
    @Override
    public String getDescription() {
        return beverage.getDescription()+",Mocha";
    }
}
class Soy extends CondimentDecorator{
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 0.11+ beverage.cost();
    }
    @Override
    public String getDescription() {
        return beverage.getDescription()+",Soy";
    }
}
class Whipe extends CondimentDecorator{
    Beverage beverage;

    public Whipe(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 0.20+ beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Whipe";
    }
}

