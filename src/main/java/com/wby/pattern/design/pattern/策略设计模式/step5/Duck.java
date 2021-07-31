package com.wby.pattern.design.pattern.策略设计模式.step5;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/7/31 11:36
 * @Description: 关键在于，鸭子现在将飞行和呱呱叫的动作“委托”给别人处理，而不是使用定义在自己类(或子类)内的方法。
 *
 * 实现方法如下：
 *      1.鸭子中加入两个实例变量，分别是FlyBehavior 和Quackbehavior，声明为接口类型(不是具体类实现类型)，每个变量会利用多态的方法在运行时引用正确的类型
 *          将Duck类与其他所有子类的fly和quack方法移除。
 *          用performFly和performQuack取代Duck类中的fly和quack
 *      2.在Duck类中实现performQuack和performFly
 *      3.设定FlyBehavior 和Quackbehavior的实例变量。参考MallardDuck类
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
    }
}
/**
*
*/















