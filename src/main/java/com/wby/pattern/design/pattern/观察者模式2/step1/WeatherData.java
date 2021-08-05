package com.wby.pattern.design.pattern.观察者模式2.step1;

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
 * 2.新数据备妥时,mentsChanged()就会被调用
 * 3.实现三个使用天气数据的布告板:目前状况,气相统计,天气预报
 *      一旦WeatherData有新数据,就必须马上更新
 * 4.可扩展:其他人员建立定制的布告板,用户可以添加或删除布告板.
 *      出示布告板有三个:目前状况,气象统计,天气预报
 */
/**
 * 看一个错误的示范
 */
class WeatherData1{
    public float getTemperature() {
        System.out.println("获取温度");
        return 0;
    }
    public float getHumidity(){
        System.out.println("获取湿度");
        return 0;
    }
    public float getPressure(){
        System.out.println("获取压力");
        return 0;
    }
    /**
     * 一旦气象测量更新,此方法会被调用
     */
    public void mentsChanged(){
        //获取最新的测量值
        float temp=getTemperature();
        float humidity=getHumidity();
        float pressure=getPressure();
        //更新布告板:
        //  针对具体实现编程,导致后续更新或删除布告板时必须修改代码
        //  从参数来看,方法看起来像是一个统一的接口,名称都是update,参数都是温度湿度压强
        //  因此,这里是会改变的地方,需要封装起来
        /*currentCoditionsDisplay.update(temp, humidity, pressure);
        statisticsDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);*/
    }
    //其他方法
}
