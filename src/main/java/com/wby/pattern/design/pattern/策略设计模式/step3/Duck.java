package com.wby.pattern.design.pattern.策略设计模式.step3;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/7/31 11:36
 * @Description: 如果使用接口呢？
 *  每当有新鸭子出现，就要检查是否需要覆盖fly和quack，者很糟糕。
 *
 *  提取fly方法放入接口，只有会飞的鸭子才实现此接口；同理quack方法
 */
public abstract class Duck {
    //游泳
    public void swim(){}
    //外观：因为每种鸭子的外观不同，所以定义为抽象方法
    public abstract void display();
}
interface Quackale{
    //呱呱叫
    public void quack();
}
interface Flyable{
    public void fly();
}

class DecoyDuck extends Duck {

    @Override
    public void display() {
        //诱饵鸭：不会飞也不会叫
    }
}

class RubberDUck extends Duck implements Quackale{
    @Override
    public void display() {
        //橡皮鸭:会叫，不会飞
    }

    @Override
    public void quack() {
        //重写为： 吱吱叫
    }
}

class GreenHeadDuck extends Duck implements Quackale,Flyable{

    @Override
    public void display() {
        //绿头鸭：会飞会叫
    }

    @Override
    public void quack() {
        //重写:绿头鸭叫
    }

    @Override
    public void fly() {
        //重写：绿头鸭飞
    }
}

class RedHeadDuck extends Duck implements Quackale,Flyable{
    @Override
    public void quack() {
        //重写:红头鸭叫
    }

    @Override
    public void fly() {
        //重写：红头鸭飞
    }

    @Override
    public void display() {
        //红头鸭：会飞会叫
    }
}
/**
* @Description: 但是这个方法很笨拙！！！！
 *  如果有50个鸭子，每个都要修改fly行为，该怎么办呢？
 *
 *  PS：并非所有的鸭子都能飞和呱呱叫，所以继承并不是恰当的解决方式。
 *      虽然使用接口可以解决部分问题(不再有会飞的橡皮鸭)，但是这会造成代码无法复用，于是从一个噩梦跳入了另一个噩梦
 *      甚至，在会飞的鸭子中，飞行的动作可能还有多种变化。
 *  软件开发不变的真理：
 *      CHANGE：不管软件设计的多好，一段时间后，总是需要成长和改变
 *  解决方式：采用良好的OO软件设计原则
 *
*/

















