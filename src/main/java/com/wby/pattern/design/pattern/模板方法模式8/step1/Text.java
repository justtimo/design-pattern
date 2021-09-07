package com.wby.pattern.design.pattern.模板方法模式8.step1;

/**
 * 封装算法块,以便子类可以将自己挂进运算里
 * 茶与咖啡
 *
 * 可以在两个类里发现重复的方法,接下来将公共部分抽取出来
 */
abstract class CoffeAndTeaBeverege{
    abstract void prepareRecipe();
    //烧水
    public void boilWater() {
        System.out.println("Boiling water");
    }
    //倒进杯子
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }
}
class CoffeVersion1 extends CoffeAndTeaBeverege{

    @Override
    void prepareRecipe() {
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();
    }
    //冲泡咖啡
    public void brewCoffeeGrinds() {
        System.out.println("Dripping Coffee through filter");
    }
    //加糖和奶
    public void addSugarAndMilk() {
        System.out.println("Adding Sugar and Milk");
    }
}
class TeaVersion1 extends CoffeAndTeaBeverege {

    @Override
    void prepareRecipe() {
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }
    //放入茶叶
    public void steepTeaBag() {
        System.out.println("Steeping the tea");
    }
    //添加柠檬
    public void addLemon() {
        System.out.println("Adding Lemon");
    }
}

public class Text {
}
