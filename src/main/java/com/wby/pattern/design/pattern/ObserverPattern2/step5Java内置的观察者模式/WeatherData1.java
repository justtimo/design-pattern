package com.wby.pattern.design.pattern.ObserverPattern2.step5Java内置的观察者模式;

import java.util.Observable;

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
        notifyAll();
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
