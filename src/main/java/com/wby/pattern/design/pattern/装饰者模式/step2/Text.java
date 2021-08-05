package com.wby.pattern.design.pattern.装饰者模式.step2;

/**
 * 利用实例变量和继承,来追踪这些调料
 */
public class Text {
}
abstract class Beverage{
    private String description;
    private Boolean hasMilk;
    private Boolean hasSoy;
    private Boolean hasWhip;

    public String getDescription() {
        return description;
    }

    public Boolean getHasMilk() {
        return hasMilk;
    }

    public void setHasMilk(Boolean hasMilk) {
        this.hasMilk = hasMilk;
    }

    public Boolean getHasSoy() {
        return hasSoy;
    }

    public void setHasSoy(Boolean hasSoy) {
        this.hasSoy = hasSoy;
    }

    int cost(){
        int i=0;
        //cost不再是抽象方法,提供了cost的实现,让他计算要加入各种饮料的价格,子类仍将覆盖cost,但是会调用超类的cost方法,计算出基本的饮料价格加上调料的价格.
        if (hasMilk){
            i=i+1;
        }
        if (hasSoy){
            i=i+3;
        }
        return i;
    }
}
class HourseBlend extends Beverage {

    @Override
    int cost() {
        return 1+super.cost();
    }
}
class DarkRoast extends Beverage {

    @Override
    int cost() {
        return 2 +super.cost() ;
    }
}
class Decaf extends Beverage {

    @Override
    int cost() {
        return 3+super.cost();
    }
}
class Espost extends Beverage {

    @Override
    int cost() {
        return 4+super.cost();
    }
}
/**
* 但是这种设计也会带来问题;
 *  调料价格改变会使我们更改现有的代码
 *  一旦增加新的调料,我们就需要加上新的方法,并改变超类中的cost()
 *  对于新饮料,比如红茶,某些调料并不适合,但是此设计方式将继承那些不合适的方法,比如奶泡
 *  如果顾客想要双倍摩卡调料怎么办呢?
*/
