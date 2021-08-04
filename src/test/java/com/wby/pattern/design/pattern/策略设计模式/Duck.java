package com.wby.pattern.design.pattern.策略设计模式;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/4 16:43
 * @Description:
 */
public abstract class Duck {
    public abstract void display();
    protected Flyable flyable;
    protected Quackable quackable;

    public void performFly(){
        if (flyable == null) {
            return;
        }else {
            flyable.fly();
        }
    }
    public void perforQuack(){
        quackable.quack();
    }

    public void setFlyable(Flyable flyable) {
        this.flyable = flyable;
    }

    public void setQuackable(Quackable quackable) {
        this.quackable = quackable;
    }
}
interface Flyable{
    public void fly();
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
        System.out.println("用火箭起飞");
    }
}
interface Quackable{
    public void quack();
}
class guaguaQuack implements Quackable{

    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
class zhizhiQuack implements Quackable {

    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}

class ReadHeadDUck extends Duck{
    public ReadHeadDUck() {
        flyable=new FlyWithSwing();
        quackable=new guaguaQuack();
    }

    @Override
    public void display() {
        System.out.println("红头鸭");
    }
}
class ModelDuck extends Duck {
    public ModelDuck() {
        quackable = new zhizhiQuack();
    }

    @Override
    public void display() {
        System.out.println("模型鸭子");
    }
}

class Test{
    public static void main(String[] args) {
        ModelDuck modelDuck = new ModelDuck();
        modelDuck.display();
        modelDuck.perforQuack();
        modelDuck.performFly();
    }
}
