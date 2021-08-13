package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step10;

import lombok.Data;

import java.util.ArrayList;

/**
 * 刚刚忽略了一件事:披萨本身
 * 如果没有可售卖的披萨,那么披萨店开的再多也不行.接下来就实现披萨.
 */

/** `
 * 次抽象类提供了默认的基本做法,用来进行烘烤,切片,装盒
 */
@Data
public abstract class Pizza {
    /**
     * 名字
     */
    String name;
    /**
     * 面团类型
     */
    String dough;
    /**
     * 酱料类型
     */
    String sauce;
    /**
     * 一套配料
     */
    ArrayList toppings=new ArrayList<>();
    //准备工作以特定的顺序进行,有一连串的步骤
    void prepare(){
        System.out.println("Prepareing "+name);
        System.out.println("Tossing dough...");
        System.out.println("adding sauce...");
        System.out.println("adding toppings: ");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println("   "+toppings.get(i));
        }
    }
    void bake(){
        System.out.println("bake for 25 minutes at 350`");
    }
    void cut(){
        System.out.println("Cutting the pizza into 8 slices");
    }
    void box(){
        System.out.println("Place pizza in offical box");
    }
}
/** `
 * 定义纽约和芝加哥风味的芝士披萨
 */
class NYStyleCheesePizza extends Pizza{
    //纽约披萨有自己的番茄酱和薄饼
    public NYStyleCheesePizza() {
        name="NY style Sauce and cheese Pizza";
        dough="Thin Crust Dough";
        sauce="Marinara Sauce";
        //上面覆盖的事意大利高级干酪
        toppings.add("Grated Teggiano Cheese");
    }
}
class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name="Chicago Style Deep Dish Cheese Pizza";
        dough = "小番茄酱料";
        sauce = "厚饼";
        toppings.add("很多意大利白干酪");
    }
    void cut(){
        System.out.println("覆盖父类方法,芝加哥披萨将披萨切成正方形");
    }
}

abstract class PizzaStore{
    public Pizza orderPizza(String type){
        Pizza pizza;

        pizza=createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    abstract Pizza createPizza(String type);
}
class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")){
            pizza=new NYStyleCheesePizza();
        }else {
            return null;
        }
        return pizza;
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        if (type.equals("cheese")){
            pizza=new ChicagoStyleCheesePizza();
        }else {
            return null;
        }
        return pizza;
    }
}

class Test{
    public static void main(String[] args) {
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();

        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("老汉订购了一个"+pizza.getName());

        Pizza pizza1 = chicagoPizzaStore.createPizza("cheese");
        System.out.println("我订购了一个"+pizza1.getName());
    }
}


























