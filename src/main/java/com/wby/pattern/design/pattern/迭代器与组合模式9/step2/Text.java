package com.wby.pattern.design.pattern.迭代器与组合模式9.step2;

import java.util.ArrayList;


public class Text {
}

/**
 * 两个餐厅共同使用的菜单类
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
 * 实现一个具体的迭代器,为餐厅菜单服务.
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

    //去除这个方法,因为不在需要,并且还会暴露我们的实现方式
    /*public MenuItem[] getMenuItems() {
        return menuItems;
    }*/

    public Iterator create(){
        return new DinerMenuInterator(menuItems);
    }
    //其他方法. 依赖于数组,并不希望重写
}

/**
 * 煎饼屋菜单迭代器实现类
 */
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
class PancakeHouseMenu {
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
    //其他菜单代码: 这些代码都依赖于这个List,所以并不希望重写这些方法
    public Iterator create(){
        return new PancakeHouseMenuIterator(menuItems);
    }
}

class Waitress{
    PancakeHouseMenu pancakeHouseMenu;
    DinerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu(){
        Iterator pancakeIterator = pancakeHouseMenu.create();
        Iterator dinnerIterator = dinerMenu.create();
        System.out.println("早餐菜单");
        printMenu(pancakeIterator);
        System.out.println("晚餐菜单");
        printMenu(dinnerIterator);

    }

    private void printMenu(Iterator iterator) {
        while (iterator.hasNext()){
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.println(menuItem.getName()+" "+menuItem.getDescription()+" "+ menuItem.getPrice());
        }
    }
}
class MenuTestDriver{
    public static void main(String[] args) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();
        Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
        waitress.printMenu();
    }
}
/**
 * 目前,煎饼屋和晚餐厅都无需更改自己的实现方式(List,数组),只要我们给他们两个迭代器,他们各自在自己的菜单中加入一个create()方法创建各自的迭代器就好了,而且服务员的实现也变得更友好
 *          难以维护的服务员实现                                                      由迭代器支持的新服务员实现
 *     菜单封装的不好:餐厅使用的是ArrayList,而煎饼屋使用的数组                    菜单实现被封装起来了.服务员不知道菜单如何存储集合的
 *     两个循环来遍历菜单项                                                   只要实现迭代器,我们使用一个循环,就可以多态地处理任何项的集合
 *     女招待捆绑于具体类(MenuItem[]和ArrayList)                              服务员使用一个接口(迭代器)
 *     女招待捆绑于两个不同的具体菜单类,尽管他们大致相同                            菜单接口完全一样.但依然捆绑于两个具体的菜单类
 *
 * 接下来,对"依然捆绑于两个具体的菜单类"进行修改
 */

