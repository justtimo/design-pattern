package com.wby.pattern.design.pattern.策略设计模式1.step6;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/7/31 11:36
 * @Description: 动态设定行为
 *  我们想在鸭子子类通过set()方法设定鸭子的行为,而不是在鸭子的构造器内实例化
*/
public abstract class Duck {
    FlyBehavior flyBehavior;
    Quackbehavior quackbehavior;

    public Duck() {
    }
    //外观：因为每种鸭子的外观不同，所以定义为抽象方法
    public abstract void display();

    //委托给行为类
    public void performFly(){
        flyBehavior.fly();
    }
    public void perforQuack(){
        quackbehavior.quack();
    }

    //游泳
    public void swim(){
        System.out.println("all duck float,even decoys");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackbehavior(Quackbehavior quackbehavior) {
        this.quackbehavior = quackbehavior;
    }
}

interface FlyBehavior{
    public void fly();
}

class FlyWithWings implements FlyBehavior{

    @Override
    public void fly() {
        //实现鸭子飞行。所有有翅膀的鸭子飞行动作
        System.out.println("all can fly!");
    }
}

class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() {
        //什么都不做，不会飞。实现所有不会飞的鸭子的动作。
        System.out.println("I can`t fly");
    }
}

class FlyRocket implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("I`m flying with rocket");
    }
}

interface Quackbehavior{
    public void quack();
}

class Quack implements Quackbehavior {

    @Override
    public void quack() {
        //实现鸭子呱呱叫。 真的呱呱叫
        System.out.println("Quack");
    }
}

class Squeak implements Quackbehavior {

    @Override
    public void quack() {
        //橡皮鸭子吱吱叫：名为呱呱叫，实际上是吱吱叫
        System.out.println("Squeak");
    }
}

class MuteQuack implements Quackbehavior {

    @Override
    public void quack() {
        //不会叫。名为呱呱叫。，实际上不会叫
        System.out.println("MuteQuack");
    }
}

/**
*  MallardDuck继承自Duck，具有flyBehavior和quackbehavior实例变量
 *  野鸭：能呱呱叫，并且有翅膀能飞。
 *  怎么做到的呢？当MallardDuck实例化时，会把继承来的quackbehavior初始化为Quack、flyBehavior初始化为FlyWithWings
*/
class MallardDuck extends Duck{
    public MallardDuck() {
        quackbehavior = new Quack();
        flyBehavior=new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I`m real mallard duck");
    }
}

class MOdelDuck extends Duck{
    public MOdelDuck() {
        //一开始并不会飞,后面给他安装上火箭动力的飞行行为
        flyBehavior=new FlyNoWay();
        quackbehavior=new Quack();
    }

    @Override
    public void display() {
        //模型鸭子:不会飞
    }
}

/**
* 虽然把行为设定成具体的类：通过实例化类似Quack或FlyWithWings，并指定到行为引用变量中，但是还是可以在运行时改变该行为
 * 所以目前该做法很有弹性。
 * 但是，因为quackbehavior实例变量是一个接口类型，能够在运行时通过多态动态的指定不同的quackbehavior实现类给他
 *
 * 想想，如何实现鸭子，呢能够让他在运行时做出改变
*/
class MiniDuckSimulator{
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        //调用继承来的方法，进而委托给该对象的Quackbehavior来处理(即调用quackbehavior的quack()方法)
        mallardDuck.perforQuack();
        mallardDuck.performFly();

        /**
         * 结果:
         *      I can`t fly
         *      I`m flying with rocket
         * 说明模型鸭子动态的改变了行为.如果吧行为的实现绑死在鸭子类中,就无法做到这样.
         * 第一次调用performFly()会被委托给flyBehavior对象(也就是FlyNoWay对象.该对象是模型鸭子构造器设置的)
         * 然后调用set方法,把火箭动力飞行的行为设定到模型鸭子中
         */
        Duck mOdelDuck = new MOdelDuck();
        mOdelDuck.performFly();
        mOdelDuck.setFlyBehavior(new FlyRocket());
        mOdelDuck.performFly();
    }
}
/**
*
*/















