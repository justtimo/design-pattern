package com.wby.pattern.design.pattern.ObserverPattern2.step1;

public class WeatherData {
    public void getTemperature() {
        System.out.println("获取温度");
    }
    public void getHumidity(){
        System.out.println("获取湿度");
    }
    public void getPressure(){
        System.out.println("获取压力");
    }

    /**
     * 一旦气象测量更新,此方法会被调用
     */
    public void mentsChanged(){
        //你的代码加在这里
    }
}
/**
 * 目前知道什么?
 * 1.WeatherData有getter方法,可以获得三个测量值:温度,湿度,气压
 */