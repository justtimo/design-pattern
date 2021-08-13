package com.wby.pattern.design.pattern.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式:一对多
 */
interface Provider{
    void addObserver(ObserverS observer);
    void removeObserver(ObserverS observer);
    void notifyObserver();
}
//结合 单件模式+双重检查加锁
class WhereData implements Provider{
    private volatile static WhereData whereData;
    List<ObserverS> observerS;
    private Double tem;
    private Double hum;
    private Double press;

    private WhereData() {
        observerS = new ArrayList<ObserverS>();
    }
    public static WhereData getInstance(){
        if (whereData == null){
            synchronized (WhereData.class){
                if (whereData == null) {
                    whereData=new WhereData();
                }
            }
        }
        return whereData;
    }

    @Override
    public void addObserver(ObserverS observer) {
        observerS.add(observer);
    }

    @Override
    public void removeObserver(ObserverS observer) {
        observerS.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (ObserverS observer : observerS) {
            observer.update(tem,hum,press);
        }
    }
    public void dataChange(){
        notifyObserver();
    }
    public void setDataFunction(Double tem, Double hum, Double press){
        this.tem = tem;
        this.press = press;
        this.hum=hum;
        dataChange();
    }
}
interface ObserverS{
    void update(Double tem, Double hum, Double press);
}
class CurrentDisPlayWWWW implements ObserverS{
    Provider whereData;

    public CurrentDisPlayWWWW(Provider whereData) {
        this.whereData = whereData;
        whereData.addObserver(this);
    }

    @Override
    public void update(Double tem, Double hum, Double press) {
        System.out.println("当前温度是:{"+tem+"},湿度是:{"+hum+"},压力是:{"+press+"}");
    }
}
class WBYDisPlay implements ObserverS{
    Provider whereData;

    public WBYDisPlay(Provider whereData) {
        this.whereData = whereData;
        whereData.addObserver(this);
    }

    @Override
    public void update(Double tem, Double hum, Double press) {
        System.out.println("第二个观察者接受数据:"+"温度是:{"+tem+"},湿度是:{"+hum+"},压力是:{"+press+"}");
    }
}

public class 观察者问题20210813 {
    public static void main(String[] args) {
        WhereData whereData =  WhereData.getInstance();

        ObserverS currentDisPlay = new CurrentDisPlayWWWW(whereData);
        ObserverS wbyDisPlay = new WBYDisPlay(whereData);

        whereData.setDataFunction(1d,2d,3d);
    }
}
