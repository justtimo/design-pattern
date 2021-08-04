package com.wby.pattern.design.pattern.观察者模式;

import java.util.List;

/**
 * 观察者模式类图
 * 主体接口:注册为观察者,移除观察者,通知观察者
 * 主体接口实现类:
 *
 * 观察者接口:更新数据
 * 观察者接口实现类:
 */
public class WeatherData1{

}
interface Subject{
    public void registerObeserber(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObeserber();
}
//一个具体的主体
class ConcreteSubject implements Subject {
    List<Observer> observers;

    public void getStat(){}
    public void setStat(){}

    @Override
    public void registerObeserber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.size() <1) {
            return;
        }
        observers.remove(observer);
    }

    @Override
    public void notifyObeserber() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
interface Observer{
    public void update();
}
class ConcreteObserver implements Observer {

    @Override
    public void update() {

    }
}
