package com.wby.pattern.design.pattern.迭代器与组合模式9.step4;


import java.util.Hashtable;
import java.util.Iterator;


/**
 * 咖啡厅也加入进来了,咖啡厅没有实现Menu接口
 */
class CafeMenu{
    //菜单使用散列表存储的,不知道是否支持迭代器?
    Hashtable menuItems=new Hashtable();

    public CafeMenu() {
        addItem("Veggie Burger and Air Fries",
                "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                true, 3.99);
        addItem("Soup of the day",
                "A cup of the soup of the day, with a side salad",
                false, 3.69);
        addItem("Burrito",
                "A large burrito, with whole pinto beans, salsa, guacamole",
                true, 4.29);
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
interface Menu{
    public Iterator createIterator();
}
class CafeMenuVersion2 implements Menu{
    Hashtable menuItems=new Hashtable();

    public CafeMenuVersion2() {
        addItem("Veggie Burger and Air Fries",
                "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                true, 3.99);
        addItem("Soup of the day",
                "A cup of the soup of the day, with a side salad",
                false, 3.69);
        addItem("Burrito",
                "A large burrito, with whole pinto beans, salsa, guacamole",
                true, 4.29);
    }

    public void addItem(String name,String desc,boolean vegetarian,double price){
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        //key是项目名称, value是菜单项对象
        menuItems.put(menuItem.getName(),menuItem);
    }

    @Override
    public Iterator createIterator() {
        //这里只获取值部分的迭代器
        return menuItems.values().iterator();
    }
    //其他方法. 依赖于数组,并不希望重写
}

class Waitress{
    Menu cafeMenu;

    public Waitress(Menu cafeMenu) {
        this.cafeMenu = cafeMenu;
    }

    public void printMenu(){
        Iterator cafeIterator = cafeMenu.createIterator();
        System.out.println("咖啡菜单");
        printMenu(cafeIterator);

    }

    private void printMenu(Iterator iterator) {
        while (iterator.hasNext()){
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.println(menuItem.getName()+" "+menuItem.getDescription()+" "+ menuItem.getPrice());
        }
    }
}
class MeneTestMain{
    public static void main(String[] args) {
        CafeMenuVersion2 cafeMenu = new CafeMenuVersion2();
        Waitress waitress = new Waitress(cafeMenu);
        waitress.printMenu();
    }
}

/**
 * 我们都做了什么
 * 1。原菜单项（PancakeHouseMenu、DinerMenu、CafeMenu）分别有三个不同的实现和三个不同的遍历接口。
 *      我们希望女招待能有简单地方式遍历菜单 ；并且不希望他知道菜单项是如何实现的
 * 2.将女招待和遍历的实现解耦。 女招待---》Iterator----》遍历的实现
 * 3.让女招待更具有扩展性。因为我们提供了迭代器，所以女招待知道如何处理CafeMenu，为HashTable的值制作一个迭代器也很简单
 * 4.其他的Collection类都具有让我们获得迭代器的方法，如果没有，你也知道如何自己实现一个了
 */
public class Text {
}


















