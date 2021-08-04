package com.wby.pattern.design.pattern.策略设计模式.step6手写;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/3 09:37
 * @Description: 1.模型呀不会飞,解决办法:
 *  1.覆盖超类的fly方法,但是每个都覆盖很麻烦
 *  2.将fly抽取为接口,但是具有不同飞行行为的鸭子要分别实现接口,具有相同飞行行为的鸭子也要实现相同的代码,
 *      导致代码重复
 *  3.将不同的飞行行为分别实现为具体类,然后在不同的鸭子中复用
 */
public abstract class Duck {
    Flyable flyable;
     void swim(){
         System.out.println("我能游泳");
     }
     void quack(){
         System.out.println("我能呱呱叫");
     }
     void fly(){
         System.out.println("我能飞");
     }
    abstract void display();

     void performFly(){
         flyable.fly();
     }
     void setFlyable(Flyable flyable){
         this.flyable = flyable;
     }
}
interface Flyable {
    void fly();
}
class FlyWithSwing implements Flyable{

    @Override
    public void fly() {
        System.out.println("用翅膀飞");
    }
}
class FlyWithRocket implements Flyable {

    @Override
    public void fly() {
        System.out.println("用火箭飞");
    }
}
class RedHeadDuck extends Duck implements Flyable{
    @Override
    void display() {
        System.out.println("我是红头鸭");
    }

    @Override
    public void fly() {

    }
}
class GreenHeadDuck extends Duck{
    public GreenHeadDuck() {
        flyable=new FlyWithSwing();
    }

    @Override
    void display() {
        System.out.println("我是绿头鸭");
    }
}
class ModelDuck extends Duck implements Flyable{

    @Override
    void display() {
        System.out.println("我是模型鸭子");
    }

    @Override
    public void fly() {
        flyable=new FlyWithRocket();
    }
}
class Test{
    public static void main(String[] args) {
        GreenHeadDuck greenHeadDuck = new GreenHeadDuck();
        greenHeadDuck.performFly();
        greenHeadDuck.setFlyable(new FlyWithRocket());
        greenHeadDuck.performFly();
    }
}
