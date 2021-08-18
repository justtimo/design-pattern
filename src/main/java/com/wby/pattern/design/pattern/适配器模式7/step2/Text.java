package com.wby.pattern.design.pattern.适配器模式7.step2;

/**
 * 如果走路像鸭子,叫声像鸭子,那么他可能是一只包装了鸭子适配器的火鸡
 */
interface Duck{
    void quack();
    void fly();
}

/**
 * 绿头鸭
 */
class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("真鸭子.呱呱叫");
    }

    @Override
    public void fly() {
        System.out.println("真鸭子,可以飞比较远");
    }
}

/**
 * 火鸡
 */
interface Turkey{
    //咯咯叫
    void gobble();
    void fly();
}
class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("火鸡,咯咯叫");
    }

    @Override
    public void fly() {
        System.out.println("火鸡,可以短距离飞");
    }
}

/**
 * 适配器:首先需要实现想要转换成的类型接口,即客户所期望的接口
 */
class TurkeyAdpter implements Duck{
    Turkey turkey;

    //取得适配的对象引用,这里使用构造器获得这个引用
    public TurkeyAdpter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    /**
     * 虽然都有fly接口,但是火鸡要飞5次才能与鸭子的飞行距离对应
     */
    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
public class Text {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();

        WildTurkey wildTurkey = new WildTurkey();
        TurkeyAdpter turkeyAdpter = new TurkeyAdpter(wildTurkey);
        System.out.println("--------测试火鸡--------------------");
        wildTurkey.gobble();
        wildTurkey.fly();
        System.out.println("------------测试鸭子----------------");
        mallardDuck.quack();
        mallardDuck.fly();
        System.out.println("-----------测试适配器-----------------");
        turkeyAdpter.quack();
        turkeyAdpter.fly();
    }
}










