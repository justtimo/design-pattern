package com.wby.pattern.design.pattern.ObserverPattern2.step4;

import java.util.ArrayList;

public class 设计气象站 {
}
//主题接口
interface Subject{
    /**
     * 前两个个方法都需要观察者作为变量,该观察者用来被注册或删除
     */
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);

    /**
     * 当主题状态改变时,该方法会被调用,以便通知所有观察者.
     */
    public void notifyObserver();
}

//WeatherData实现Subject接口
class WeatherData implements Subject{
    private ArrayList observers;
    float temp;
    float humidity;
    float pressure;

    /**
     * 我们加上一个ArrayList来记录观察者,此ArrayList是在构造器中建立的
     */
    public WeatherData() {
        observers=new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        System.out.println("WeatherData 注册成为观察者");
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i>=0){
            observers.remove(i);
        }
        System.out.println("WeatherData 从观察者中移除");
    }

    /**
     * 有趣的地方来了,这里我们把状态告诉每一个观察者,因为都实现了update(),所以我们知道如何通知他们
     */
    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(temp,humidity,pressure);
        }
        System.out.println("WeatherData 通知所有观察者");
    }

    /**
     * 一旦气象测量更新,此方法会被调用,通知观察者
     */
    public void mentsChanged(){
        //你的代码加在这里
        notifyObserver();
    }
    //使用此方法测试布告板
    public void setMeasurementsChanged(float temp,float humidity,float pressure){
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        mentsChanged();

    }

    public void getTemperature() {
        System.out.println("WeatherData 获取温度");
    }
    public void getHumidity(){
        System.out.println("WeatherData 获取湿度");
    }
    public void getPressure(){
        System.out.println("WeatherData 获取压力");
    }
}

//观察者接口.所有气象组件(所有观察者)都实现此观察者接口.这样主题通知观察者时,有了一个共同的接口
interface Observer {
    /**
     * 当气象站的数值改变时,主题会吧这些状态值当做参数传递给观察者
     */
    public void update(float temp,float humidity,float pressure);
}
//为布告板建立一个共同的接口.布告板只需要实现display方法
interface DisplayElement{
    void display();
}
//此布告板根据WeatherData对象显示当前观测值.
// 实现了Observer接口,所以可以从WeatherData对象中获得改变.
// 实现了DisplayElement,因为API规定所有的布告板必须实现此接口
class CurrrentConditionDisplay implements Observer,DisplayElement{
    private float temp;
    private float humidity;
    private Subject weatherData;

    //构造器需要weatherData对象(主题)作为注册之用
    public CurrrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp,float humidity,float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
        System.out.println("CurrrentConditionDisplay update");
    }
    //display()方法把最近的温度湿度显示出来
    @Override
    public void display() {
        System.out.println("CurrrentConditionDisplay:"+temp+"F degeress and "+humidity+"% humidity");
        System.out.println("CurrrentConditionDisplay 显示当前观测值");
    }
}
//此布告板跟踪最小,平均,最大的观测值,并显示它们
class StatisticDisplay implements Observer,DisplayElement{

    public StatisticDisplay(WeatherData weatherData) {

    }

    @Override
    public void update(float temp,float humidity,float pressure) {
        System.out.println("StatisticDisplay update()");
    }

    @Override
    public void display() {
        System.out.println("StatisticDisplay 显示最小,平均和最大的观测值");
    }
}
//此布告板根据气压计显示天气预报
class ForecastDisplay implements Observer,DisplayElement{

    public ForecastDisplay(WeatherData weatherData) {

    }

    @Override
    public void update(float temp,float humidity,float pressure) {
        System.out.println("ForecastDisplay update()");
    }

    @Override
    public void display() {
        System.out.println("ForecastDisplay 显示天气预报");
    }
}
//开发人员可以通过实现观察者和显示接口来创建自己的自定义布告板
class ThirdPartyDisplay implements Observer,DisplayElement{

    @Override
    public void update(float temp,float humidity,float pressure) {
        System.out.println("ThirdPartyDisplay update()");
    }

    @Override
    public void display() {
        System.out.println("ThirdPartyDisplay 通过实现观察者和显示接口来创建自己的布告板");
    }
}
/**
 * StatisticDisplay,ForecastDisplay,ThirdPartyDisplay都应该有一个被命名为"subject"的指针来指向WeatherData对象
 * .但是这里没有实现,以免看起来太乱
 *
 * Q1:update()方法是最适合调用display()的地方吗?
 * A1:这个例子中,当数值变化的时候调用display()是很合理的.
 *      然而,有更好的方法设计显示数据的方式,MVC模式时会再次说明
 * Q2:为什么保存对Subject的引用,构造完之后似乎用不到了?
 * A2:确实如此,但是如果以后想要取消注册,如果已经有了对Subject的引用会方便很多
 */
class WeatherStation{
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrrentConditionDisplay currentDisplay = new CurrrentConditionDisplay(weatherData);
        /*StatisticDisplay statisticDisplay = new StatisticDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);*/

        //模拟新的气象测量
        weatherData.setMeasurementsChanged(80,65,30.4f);
    }
}







