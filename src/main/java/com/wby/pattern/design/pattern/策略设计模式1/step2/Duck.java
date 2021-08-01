package com.wby.pattern.design.pattern.策略设计模式1.step2;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/7/31 11:36
 * @Description: 现在得让鸭子能飞
 *  解决方式：
 *      1.在超类中添加fly()方法
 *          导致不会飞的橡皮鸭飞了起来。
 *          PS：当涉及“维护”是，为了“复用”目的而使用继承，结局并不完美
 *      2.继承
 *          将橡皮鸭中的fly方法覆盖，变成什么也不做。
 *          当加入新类 诱饵鸭 时，则覆盖quack和fly让他什么也不做
 */
public abstract class Duck {
    //呱呱叫
    public void quack(){}
    //游泳
    public void swim(){}
    //外观：因为每种鸭子的外观不同，所以定义为抽象方法
    public abstract void display();

    /**
    * 这会导致：有些不会飞的鸭子也具有了飞的行为，比如橡皮鸭
    */
    public void fly(){}
}

class DecoyDuck extends Duck {

    @Override
    public void display() {
        //诱饵鸭：不会飞也不会叫
    }

    @Override
    public void quack() {
        //什么也不做
    }

    @Override
    public void fly() {
        //什么也不做
    }
}

class RubberDUck extends Duck{
    @Override
    public void display() {
        //橡皮鸭
    }
    @Override
    public void quack() {
        //覆盖重写为： 吱吱叫
    }

}

class GreenHeadDuck extends Duck {

    @Override
    public void display() {
        //绿头鸭
    }
}

class RedHeadDuck extends Duck {

    @Override
    public void display() {
        //红头鸭
    }
}
