package com.wby.pattern.design.pattern.迭代器与组合模式9.step4;


import java.util.Hashtable;



/**
 * 咖啡厅也加入进来了,咖啡厅没有实现Menu接口
 */
class CafeMenu{
    //菜单使用散列表存储的,不知道是否支持迭代器?
    Hashtable menuItems=new Hashtable();

    public CafeMenu() {
    }

    public void addItem(String name,String desc,boolean vegetarian,double price){
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        //key是项目名称, value是菜单项对象
        menuItems.put(menuItem.getName(),menuItem);
    }

    //去除这个方法,因为不在需要,并且还会暴露我们的实现方式
    public Hashtable getMenuItems() {
        return menuItems;
    }
    //其他方法. 依赖于数组,并不希望重写
}
class MenuItem{
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

/**
 * 重做咖啡厅代码
 */
class CafeMenuVersion2{
    //菜单使用散列表存储的,不知道是否支持迭代器?
    Hashtable menuItems=new Hashtable();

    public CafeMenuVersion2() {
    }

    public void addItem(String name,String desc,boolean vegetarian,double price){
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        //key是项目名称, value是菜单项对象
        menuItems.put(menuItem.getName(),menuItem);
    }

    //去除这个方法,因为不在需要,并且还会暴露我们的实现方式
    public Hashtable getMenuItems() {
        return menuItems;
    }
    //其他方法. 依赖于数组,并不希望重写
}
public class Text {
}
