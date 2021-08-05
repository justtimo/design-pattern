package com.wby.pattern.design.pattern.观察者模式2.step5Java内置的观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/4 19:08
 * @Description: 自己手写实现java内置的Observable
 */
class Observable{
    List<Observer > observers;
    Boolean flag;

    public void setChange(){
        flag=true;
    }
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    public void notifyObserver(Object args){
        if (flag){
            for (Observer observer : observers) {
                observer.update(this,args);
            }
            flag=false;
        }
    }
    public void notifyObserver(){
        notifyObserver(null);
    }
}
public class WeatherData extends Observable{
    public WeatherData() {
        observers=new ArrayList<>();
    }

    float getTem(){
return 2.0f;
    }
    float getHum(){
        return 1.0f;
    }
    void getPress(){

    }
    void dataChanged(){
        setChange();
        notifyObserver();
    }
}
interface Observer {
    void update(Observable o,Object args);
    void update();
}
interface Display{
    void display();
}
class CurrentDisplay implements Observer,Display {
    private Observable observable;

    public CurrentDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o,Object args) {
        System.out.println("CurrentDisplay update: Observable:" + o + "   args:"+args);
    }
    public void update(Observable o,Object... args) {
        for (Object arg : args) {
            System.out.println("CurrentDisplay update: Observable:" + o + "   args:"+arg);
        }

    }
    @Override
    public void update() {
        update(observable,null);
    }

    @Override
    public void display() {

    }
}
class ForseDisplay implements Observer,Display {
    private Observable observable;

    public ForseDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }
    @Override
    public void update(Observable o,Object args) {
        System.out.println("ForseDisplay update");
    }
    @Override
    public void update() {
        update(observable,null);
    }

    @Override
    public void display() {

    }
}
class StatisticDisplay implements Observer,Display {
    private Observable observable;

    public StatisticDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }
    @Override
    public void update(Observable o,Object args) {
        System.out.println("StatisticDisplay update");
    }
    @Override
    public void update() {
        update(observable,null);
    }

    @Override
    public void display() {

    }
}
class Test{
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentDisplay currentDisplay = new CurrentDisplay(weatherData);
        ForseDisplay forseDisplay = new ForseDisplay(weatherData);
        StatisticDisplay statisticDisplay = new StatisticDisplay(weatherData);
        weatherData.dataChanged();
        currentDisplay.update(weatherData,weatherData.getHum(),weatherData.getTem());
    }
}
