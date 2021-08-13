package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep3;

import com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep2.*;

/** `
 * 重做披萨
 */
abstract  class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies[] veggies;
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    //现在把prepare()声明成抽象的,此方法中需要收集披萨所需要的原料.其他方法不需要改变
    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cut the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
    void setName(String name){
        this.name = name;
    }

    public String toString() {
        StringBuffer display = new StringBuffer();
        display.append("---- " + name + " ----\n");
        display.append(dough + "\n");
        display.append(sauce + "\n");
        return display.toString();
    }
}

/** `
 * 我们做了些什么?
 * 1. 引入了新的工厂,也就是所谓的抽象工厂,用来创建披萨原料.抽象工厂为产品家族提供接口.
 *      什么是家族?制作披萨所需要的一切东西,例如:面团,酱料,芝士,肉和蔬菜
 * 2. 通过抽象工厂提供的接口,可以创建产品的家族,利用此接口编写代码,我们的代码将从实际工厂解耦,以便在不同上下文中实现各种工厂,制造不同的
 *      产品:不同的区域,不同的操作系统,不同的外观及操作.
 *      从抽象工厂中派生出一些具体工厂,这些工厂产生相同的产品,但是产品的实现不同
 * 3. 代码从实际的产品中解耦了,所以我们可以替换不同的工厂来获取不同的行为
 *
 * 抽象的原料工厂---->原料工厂的具体实现---->PizzaStore---->Pizza
 */


















