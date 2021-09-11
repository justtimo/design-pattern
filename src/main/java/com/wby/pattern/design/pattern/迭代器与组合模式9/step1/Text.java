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
/**
 * 除了getMenuItems()方法的返回类型不同以外,这两个菜单很相似
 * 封装变化: 这个例子中变化的部分: 由不同的集合类型所造成的遍历.
 * 创建一个对象: 迭代器
 *      利用它来封装"遍历集合内的每个对象的过程"
 *
 * 迭代器模式:
 *      第一件事,就是他依赖于一个成为迭代器的接口
 */
interface Iterator<T>{
    public boolean hasNext();
    public T next();
}
/**
 * 现在在餐厅菜单中加入迭代器接口
 */
class DinerMenuInterator implements Iterator<MenuItem>{
    MenuItem[] menuItems;
    int postion=0;

    public DinerMenuInterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        //数组,我们不但要检查是否超出了数组长度,还要检查下一项是否为null,如果是null,就表示没有下一项了
        if (postion>=menuItems.length || menuItems[postion]==null) {
            return false;
        }else {
            return true;
        }

    }

    @Override
    public MenuItem next() {
        MenuItem menuItem=menuItems[postion];
        postion+=1;
        return menuItem;
    }
    //其他方法. 依赖于数组,并不希望重写
}
/**
 * 使用迭代器改造餐厅菜单
 */
class DinerMenuWithInterator{
    static final int MAX_ITEMS=6;
    int numberOfItems=0;
    MenuItem[] menuItems;

    public DinerMenuWithInterator() {
        menuItems=new MenuItem[MAX_ITEMS];

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

    //去除这个方法,因为不在需要,并且还会暴露我们的实现方式
    /*public MenuItem[] getMenuItems() {
        return menuItems;
    }*/

    public Iterator create(){
        return new DinerMenuInterator(menuItems);
    }
    //其他方法. 依赖于数组,并不希望重写
}
class PancakeHouseMenuIterator implements Iterator{
    ArrayList menuItems;
    int position=0;
    public PancakeHouseMenuIterator(ArrayList menuItems) {
        this.menuItems = menuItems;

    }
    @Override
    public boolean hasNext() {
        if (position>=menuItems.size()-1 || menuItems.get(position)==null) {
            return false;
        }else {
            return true;
        }
    }
    @Override
    public Object next() {
        MenuItem menuItem=(MenuItem) menuItems.get(position);
        position+=1;
        return menuItem;
    }
}
class PancakeHouseMenuWithIterator {
    ArrayList menuItems;

    public PancakeHouseMenuWithIterator() {
        menuItems=new ArrayList();

    }

    public void addItem(String name,String desc,boolean vegetarian,double price){
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        menuItems.add(menuItem);
    }
    //其他菜单代码: 这些代码都依赖于这个List,所以并不希望重写这些方法
    public Iterator create(){
        return new PancakeHouseMenuIterator(menuItems);
    }
}

class WorkerUserIterator{
    PancakeHouseMenuWithIterator pancakeHouseMenuWithIterator;
    DinerMenuWithInterator dinerMenuWithInterator;

    public WorkerUserIterator(PancakeHouseMenuWithIterator pancakeHouseMenuIterator, DinerMenuWithInterator dinerMenuWithInterator) {
        this.pancakeHouseMenuWithIterator = pancakeHouseMenuIterator;
        this.dinerMenuWithInterator = dinerMenuWithInterator;
    }

    public void printMenu(){
        Iterator iterator = pancakeHouseMenuWithIterator.create();

    }
}
