package com.wby.pattern.design.pattern.观察者模式;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/4 17:16
 * @Description:
 */
public class WeatherData {
    private float press;
    private float tem;
    private float hum;

    private CurrentDisplay currentDisplay;
    private StatisticDisplay statisticDisplay;
    private ForseDisplay forseDisplay;

    public float getPress(){
        return 1.1f;
    }
    public float getTem(){
        return 4.1f;
    }
    public float getHum(){
        return 9.1f;
    }
    /**
    * 这种方式,直接使用实现类会导致增删布告板时,需要更改代码
     * 而且三个布告板的update()参数完全相同
    */
    public void memsChanged(){
        press=getPress();
        tem=getTem();
        hum=getHum();

        currentDisplay.update(press,tem,hum);
        statisticDisplay.update(press,tem,hum);
        forseDisplay.update(press,tem,hum);
    }
}
class CurrentDisplay{
    public void update(float press,float tem,float hum) {

    }
}
class StatisticDisplay{
    public void update(float press,float tem,float hum) {

    }
}
class ForseDisplay{
    public void update(float press,float tem,float hum) {

    }
}
