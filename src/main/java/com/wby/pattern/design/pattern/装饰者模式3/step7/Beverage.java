package com.wby.pattern.design.pattern.装饰者模式3.step7;


/**
 * 现在在星巴克咖啡加上容量大小,顾客可以选择小贝,中北,大杯.这是任何咖啡都具备的行为,所以在Beverage类上添加getSize,setSize方法.
 * 并且咖啡根据容量收费
 */
public abstract class Beverage {
    String description="UnKnown Beverage";
    public enum Size { TALL, GRANDE, VENTI };

    Size size = Size.TALL;

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return this.size;
    }

    public String getDescription() {
        return description;
    }
    public abstract double cost();
}

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

abstract class CondimentDecorator extends Beverage{
    public abstract String getDescription();
}

class Mocha extends CondimentDecorator{
    Beverage beverage;//darkRoast

    public Mocha(Beverage beverage) {
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
        if (beverage.getSize()==Size.TALL){
            cost+=0.10;
        }else if (beverage.getSize() == Size.GRANDE) {
            cost += .15;
        } else if (beverage.getSize() == Size.VENTI) {
            cost += .20;
        }

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

