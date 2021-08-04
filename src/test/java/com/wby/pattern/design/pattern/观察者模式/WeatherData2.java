package com.wby.pattern.design.pattern.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式类图
 * 主体接口:注册为观察者,移除观察者,通知观察者
 * 主体接口实现类:
 *
 * 观察者接口:更新数据
 * 观察者接口实现类:
 */
interface Subject1{
    public void addObserver(Observer1 observer);
    public void removeObserver(Observer1 observer);
    public void notifyObservers();
}

public class WeatherData2 implements Subject1{
    List<Observer1> observers;

    public WeatherData2() {
        observers = new ArrayList<Observer1>();
    }

    @Override
    public void addObserver(Observer1 observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer1 observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer1 observer : observers) {
            observer.update();
        }
    }

    public void dataChanged(){
        notifyObservers();
    }
}

interface Observer1 {
    public void update();
    public void display();
}
class CurrentDisplay1 implements Observer1 {
    private Subject1 subject1;
    public CurrentDisplay1(Subject1 weatherData2) {
        subject1=weatherData2;
        weatherData2.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("显示当前数值的布告板 更新数据");
    }

    @Override
    public void display() {
        System.out.println("显示当前数值的布告板");
    }
}
class ForseDisplay1 implements Observer1 {
    private Subject1 subject1;
    public ForseDisplay1(Subject1 weatherData2) {
        subject1=weatherData2;
        weatherData2.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("显示平均值的布告板 更新数据");
    }

    @Override
    public void display() {
        System.out.println("显示平均值的布告板");
    }
}
class StatisticDisplay1 implements Observer1 {
    private Subject1 subject1;
    public StatisticDisplay1(Subject1 weatherData2) {
        subject1=weatherData2;
        weatherData2.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("显示统计的布告板 更新数据");
    }

    @Override
    public void display() {
        System.out.println("显示统计的布告板");
    }
}
class Test{
    public static void main(String[] args) {
        WeatherData2 weatherData2 = new WeatherData2();
        CurrentDisplay1 currentDisplay1 = new CurrentDisplay1(weatherData2);
        ForseDisplay1 forseDisplay1 = new ForseDisplay1(weatherData2);
        StatisticDisplay1 statisticDisplay1 = new StatisticDisplay1(weatherData2);
        weatherData2.dataChanged();
    }
}
