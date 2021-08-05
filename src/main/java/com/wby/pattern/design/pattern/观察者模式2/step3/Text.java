package com.wby.pattern.design.pattern.观察者模式2.step3;

/**
 *
 */
public class Text {
}
//这是主题接口,对象使用此接口注册成为观察者,或者把自己从观察者中剔除
interface Subject{
    void registerObserver();
    void removeObserver();
    void notifyObserver();
}
//具体主题总是实现主题接口,出了注册和撤销方法之外,还实现了notifyObserver方法,用于在状态改变时更新所有观察者
class ConcreteSubject implements Subject{
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void registerObserver() {

    }

    @Override
    public void removeObserver() {

    }

    @Override
    public void notifyObserver() {

    }
}
//所有观察者必须实现观察者接口,此接口只有update方法,党主体状态改变时他被调用
interface Observer {
    void update();
}
//观察者可以是实现此接口的任意类.观察者必须注册具体主题,以便接受更新
class ConreteObserver implements Observer{

    @Override
    public void update() {

    }
}
/**
 * Q1:这和一对多有什么关系
 * A1:利用观察者,主体是具有状态的对象,并且可以控制这些状态.
 *      即,有一个具有状态的主题.另一方面,观察者使用这些状态,虽然这些状态并不属于他们.
 *      观察者依赖主题来搞诉他们状态何时改变了.这就产生了一个关系:一个主题 对 多个观察者
 *
 * Q2:依赖是如何产生的
 * A2:因为主题是真正拥有数据的人,观察者是主题的依赖者,数据变化时更新,这样比起让许多对象控制同一份数据来说,能得到更干净的OO设计
 */

/**
 * 松耦合
 *
 * 当两个对象之间松耦合,他们依然可以交互,但是不太清楚彼此的细节
 * 观察者模式提供了一种对象设计,让主题和观察者之间松耦合
 *
 * 为什么呢?
 * 关于观察者,主题只知道观察者实现了某个接口(Observer),主题不需要知道观察者具体的类是什么,做了什么或其他任何细节
 * 任何时候我们都可以增加新的观察者.因为主题唯一依赖的东西是一个实现Observer接口的对象列表,所以可以随时增加观察者.
 * 实际上,我们可以在运行时用新的观察者取代现有的观察者,主题不会受到任何影响.同样的,也可以在任何时候删除某些观察者.
 *
 * 当有新的观察者出现时,主题的代码不需要修改.不需要为了兼容新类型观察者而修改主题的代码,所要做的只是在新的类里实现此
 *  观察者接口,然后注册成为观察者即可.
 * 主题不在乎别的,他只会发送通知给所有实现了观察者接口的对象.
 *
 * 我们可以独立的复用主题或观察者,而且如果在其他地方使用主题或观察者,可以轻易复用,因为他们并非紧耦合
 *
 * 改变主题或观察者,不会影响另一方,因为他们松耦合,只要他们之间的接口被遵循,就可以自由的改变他们
 *
 * 这就是设计原则4.松耦合的设计能让我们建立弹性的OO系统,能够应对变化,因为对象之间的互相依赖降到了最低
 */
























