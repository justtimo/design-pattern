package com.wby.pattern.design.pattern.策略设计模式;

/**
 * 定义Dog超类,定义jiao,跑方法.子类(真实的狗:跑叫,玩具狗(跑叫飞),模型狗(什么都不会))继承.
 * 现在能让真实的狗飞起来,但是因为继承导致模型狗也飞了起来,可以覆盖方法,可以将方法放到接口中分别实现(复用降低)
 * 策略设计:抽取方法--->实现接口(叫:汪汪叫,电子音叫等等)--->将方法的执行委托给行为类(Bark接口,Fly接口)
 */
class Dog{
    Bark bark;
    Fly fly;
    void performBark(){
        bark.bark();
    }
    void performFly() {
        fly.fly();
    }
    void run() {
        System.out.println("所有的狗都会跑");
    }

    public void setFly(Fly fly) {
        this.fly = fly;
    }
}
class RedDog extends Dog {

    public RedDog() {
        bark=new RealBark();
    }
}
class ModelDog extends Dog {
    public ModelDog() {
        bark=new FakeBark();
        fly=new FlyWithWings();
    }
}
interface Bark{
    void bark();
}
interface Fly{
    void fly();
}
class RealBark implements Bark {

    @Override
    public void bark() {
        System.out.println("汪汪叫");
    }
}
class FakeBark implements Bark {

    @Override
    public void bark() {
        System.out.println("电子音叫声");
    }
}
class FlyWithWings implements Fly{

    @Override
    public void fly() {
        System.out.println("给狗加上翅膀飞起来");
    }
}
class FlyWithHand implements Fly{

    @Override
    public void fly() {
        System.out.println("我用手把他扔飞");
    }
}


public class 策略设计模式温习20210813 {
    public static void main(String[] args) {
        RedDog redDog = new RedDog();
        redDog.performBark();
        redDog.setFly(new FlyWithWings());
        redDog.performFly();
    }
}

