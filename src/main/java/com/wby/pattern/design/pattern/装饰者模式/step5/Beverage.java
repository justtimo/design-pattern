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
        description = "DarkRoast";
    }
    @Override
    public double cost() {
        return 0.89;
    }
}
class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf";
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
    Beverage beverage;//darkRoast

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
        double cost=0.20+ beverage.cost();
        System.out.println(beverage.toString());
        return cost;
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
        double cost=0.11+ beverage.cost();
        System.out.println(beverage.toString());
        return cost;
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
        double cost=0.20+ beverage.cost();
        System.out.println(beverage.toString());
        return cost;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Whipe";
    }
}

class Test{
    public static void main(String[] args) {
        //一杯Espresso,不需要任何调料,打印他的价格
        /*Espresso espresso = new Espresso();
        System.out.println(espresso.getDescription()+"$ "+espresso.cost());*/

        //一杯DarkRoast,两份Mocha,一份Whip
        Beverage darkRoast = new DarkRoast();
        darkRoast=new Mocha(darkRoast);
        darkRoast=new Soy(darkRoast);
        darkRoast=new Whipe(darkRoast);
        System.out.println(darkRoast.getDescription() + "$ "+darkRoast.cost());

        //
        /*Beverage houseBlend = new HouseBlend();
        houseBlend=new Soy(houseBlend);
        houseBlend=new Mocha(houseBlend);
        houseBlend = new Whipe(houseBlend);
        System.out.println(houseBlend.getDescription() + "$ "+houseBlend.cost());*/
    }
}
/**
* Q:如果针对特定种类的具体组件(例如HouseBlend),做一些特殊的事情(例如:打折),我担心这样的设计是否恰当.因为一旦用装饰者包装HouseBlend,就会造成类型改变.
 * A:如果你把代码写成依赖于具体的组件类型,那么装饰者就会导致程序出问题.只有针对抽象组件类型编程时,才不会因为装饰者而受到影响.但是,如果的确针对特定的具体组件编程,就应该
 *      重新思考你的应用架构,以及装饰者是否合适.
 * Q:对于使用饮料的某些客户来说,会不会容易不使用最外圈的装饰者?比如,有深谙咖啡,以摩卡,豆浆,奶泡来装饰,引用到豆浆而不是奶泡.,代码会好些一些,意味着订单里没有奶泡了.
 * A:你可以狡辩说:装饰者模式必须管理更多的对象,所以犯下错误的机会增多.但是,装饰者通常是用其他类似于工厂或者生成器这样的模式创建的.一旦我们讲到这个模式,你就会
 *      明白具体的组件以及装饰者的创建过程,他们会"封装的很好",所以不会有这样的问题.
*/
