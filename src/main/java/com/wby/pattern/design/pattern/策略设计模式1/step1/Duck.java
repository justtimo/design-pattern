package com.wby.pattern.design.pattern.策略设计模式1.step1;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/7/31 11:36
 * @Description: init
 */
public abstract class Duck {
    //呱呱叫
    public void quack(){}
    //游泳
    public void swim(){}
    //外观：因为每种鸭子的外观不同，所以定义为抽象方法
    public abstract void display();
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
