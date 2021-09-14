package com.wby.pattern.design.pattern.迭代器与组合模式9.step1;

import java.util.ArrayList;

/**
 * 当客户想遍历集合时,你并不希望客户能看到.
 * 本章将展示如何能让客户遍历你的对象而又无法窥探你存储对象的方式;也将学习如何创建一些对象超集合(能跳过某些数据结构);还将学习一些关于对象职责的知识.
 */
public class Text {
}
/**
 * 餐厅和煎饼屋合并了.
 * 但是菜单的合并实现出了问题:一个用ArrayList,一个用数组
 * 餐厅菜单:
 *      素食BLT
 *      BLT
 *      例汤
 *      热狗
 *      清蒸时蔬
 * 煎饼屋菜单:
 *      薄煎饼早餐
 *      薄煎饼早餐例餐
 *      蓝莓煎饼
 *      松饼
 */
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
 * 煎饼屋菜单实现: 使用List
 */
class PancakeHouseMenu{
    ArrayList menuItems;

    public PancakeHouseMenu() {
        menuItems=new ArrayList();
        addItem("wby1","wby1",true,1.02);
        addItem("wby2","wby2",true,1.03);
        addItem("wby3","wby3",true,1.04);
        addItem("wby4","wby4",true,1.05);
        addItem("wby5","wby5",true,1.06);

    }

    public void addItem(String name,String desc,boolean vegetarian,double price){
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        menuItems.add(menuItem);
    }

    public ArrayList getMenuItems() {
        return menuItems;
    }
    //其他菜单代码: 这些代码都依赖于这个List,所以并不希望重写这些方法
}
/**
 * 餐厅菜单实现: 使用数组
 */
class DinerMenu{
    static final int MAX_ITEMS=6;
    int numberOfItems=0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems=new MenuItem[MAX_ITEMS];
        addItem("wby1","wby1",true,1.02);
        addItem("wby2","wby2",true,1.03);
        addItem("wby3","wby3",true,1.04);
        addItem("wby4","wby4",true,1.05);
        addItem("wby5","wby5",true,1.06);

    }

    public void addItem(String name,String desc,boolean vegetarian,double price){
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        if (numberOfItems>=MAX_ITEMS) {
            System.out.println("菜单满了,无法继续添加");
        }else {
            menuItems[numberOfItems]=menuItem;
            numberOfItems+=1;
        }
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }
    //其他方法. 依赖于数组,并不希望重写
}
/**
 * 客户代码: 实现一个同时使用这两个菜单的代码
 * 服务生
 */
class Worker{
    public void printMenu(){
        //调用各自的getMenuItems()
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        ArrayList menuItemList = pancakeHouseMenu.getMenuItems();
        DinerMenu dinerMenu = new DinerMenu();
        MenuItem[] menuMenuArray = dinerMenu.getMenuItems();
        //打印
        for (int i = 0; i < menuItemList.size(); i++) {
            MenuItem menuItem = (MenuItem)menuItemList.get(i);
            System.out.println(menuItem.getName()+" "+menuItem.getDescription()+" "+menuItem.getPrice());
        }
        for (int i = 0; i < menuMenuArray.length; i++) {
            MenuItem menuItem=menuMenuArray[i];
            System.out.println(menuItem.getName()+" "+menuItem.getDescription()+" "+ menuItem.getPrice());
        }
        //如果整合了第三家餐厅,那么类似的代码就要重复三遍
    }
}
class TEst{
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.printMenu();
    }
}
