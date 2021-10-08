package com.wby.pattern.design.pattern.迭代器与组合模式9.step5;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 女招待的代码中调用了三次printMenu（），每次有新的菜单加入，就必须打开女招待的实现并加入新的代码，已经违反了“开放-关闭原则”
 *
 * 这不是女招待的错，将他从菜单的实现上解耦，提取遍历动作到迭代器，我们做的很好。 但是我们仍然需要一种能将菜单统一管理的方法---他们目前还是一个个分离而又独立的对象
 *
 * 只给服务员一个迭代器，利用这个迭代器就能遍历所有菜单
 */
interface Menu{
    public Iterator createIterator();
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
class Waitress{
    ArrayList menus;

    public Waitress(ArrayList menus) {
        this.menus = menus;
    }

    public void printMenu(){
        Iterator iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = (Menu) iterator.next();
            printMenu(menu.createIterator());
        }
    }
    public void printMenu(Iterator iterator){
        while (iterator.hasNext()){
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.println(menuItem.getName()+",");
            System.out.println(menuItem.getPrice()+",");
            System.out.println(menuItem.getDescription());
        }
    }
}

/**
 * 当我们认为解决问题时，他们希望能够加上一份餐后甜点的“子菜单” ，我们不仅要支持多个菜单，还要支持菜单中的菜单
 * 但是餐厅菜单是Array ，而甜点菜单是数组，但是类型不同，所以不能这么做。
 */

/**
 * 重构
 * 1.该写厨师的实现
 * 2. 实现某种树形结构，可以容纳菜单、子菜单和菜单项
 * 3. 能够在每个菜单的各个项之间游走，至少要像使用迭代器一样方便
 * 4. 需要更有弹性的在菜单项之间游走。遍历餐厅菜单（同时也能遍历包含在内的甜点菜单）
 */
public class Text {
}
