package com.wby.pattern.design.pattern.观察者模式2.step5Java内置的观察者模式;

import java.util.Observable;
import java.util.Observer;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/4 19:25
 * @Description: 使用java内置的Observable
 */
public class WeatherData1 extends Observable {
    private float tem;
    private float hum;
    private float press;

    public WeatherData1() {
    }

    public void dataChanged(){
        setChanged();
        notifyObservers();
    }
    public void setValues(float tem,float hum, float press){
        this.tem = tem;
        this.hum = hum;
        this.press = press;
        dataChanged();
    }

    public float getTem() {
        return tem;
    }

    public float getHum() {
        return hum;
    }

    public float getPress() {
        return press;
    }
}
interface Display1{
    void display();
}
class CurrentDisplay1 implements Observer ,Display1{
    private Observable observable;
    private float tem;
    private float hum;

    public CurrentDisplay1(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("CurrentDisplay1展示当前温度{"+tem+"}湿度{"+hum+"}");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData1){
            WeatherData1 weatherData1=(WeatherData1)o;
            tem=weatherData1.getTem();
            hum= weatherData1.getHum();
            display();
        }
    }
}
class FOreDisplay2 implements Observer ,Display1{
    private Observable observable;
    private float tem;
    private float hum;

    public FOreDisplay2(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("FOreDisplay2 展示当前温度{"+tem+"}湿度{"+hum+"}");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData1){
            WeatherData1 weatherData1=(WeatherData1)o;
            tem=weatherData1.getTem();
            hum= weatherData1.getHum();
            display();
        }
    }
}
class CurrentDisplay3 implements Observer ,Display1{
    private Observable observable;
    private float tem;
    private float hum;

    public CurrentDisplay3(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("CurrentDisplay3展示当前温度{"+tem+"}湿度{"+hum+"}");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData1){
            WeatherData1 weatherData1=(WeatherData1)o;
            tem=weatherData1.getTem();
            hum= weatherData1.getHum();
            display();
        }
    }
}
class Test1{
    public static void main(String[] args) {
        WeatherData1 weatherData1 = new WeatherData1();
        CurrentDisplay1 currentDisplay1 = new CurrentDisplay1(weatherData1);
        FOreDisplay2 currentDisplay2 = new FOreDisplay2(weatherData1);
        weatherData1.dataChanged();
    }
}
