package com.wby.pattern.design.pattern.迭代器与组合模式9.step3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 接下来我们不自己实现迭代器了,而是使用java提供的Iterator接口
 * 不止java.util有迭代器接口,连ArrayList也有一个返回一个迭代器的Iterator()方法.但是我们仍然需要为数组实现一个迭代器(数据不支持iterator()方法或其他创建数组迭代器的方法)
 */
public class Text {
}
/**
 * Q: 多线程情况下,可能会有多个迭代器引用同一个对象集合.remove()会造成什么影响
 * A: 后果并没有指明,所以很难预料
 */

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
class PancakeHouseMenu implements Menu{
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
    public Iterator createIterator(){
        return menuItems.iterator();
    }
}

/**
 * 实现一个具体的迭代器,为餐厅菜单服务.
 */
class DinerMenuInterator implements Iterator<MenuItem> {
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

    /**
     * 因为使用的是固定长度的数组,所以remove()方法调用时,我们将后面的所有元素往前移动一格
     */
    @Override
    public void remove() {
        if (postion<=0) {
            System.out.println("没有元素了");
        }
        if (menuItems[postion-1] !=null) {
            for (int i = postion-1; i < menuItems.length; i++) {
                    menuItems[i]=menuItems[i+1];
            }
            menuItems[menuItems.length-1]=null;
        }
    }
    //其他方法. 依赖于数组,并不希望重写
}

/**
 *
 */
class DinerMenu implements Menu{
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

    public Iterator createIterator(){
        return new DinerMenuInterator(menuItems);
    }
    //其他方法. 依赖于数组,并不希望重写
}
/**
 * 现在给菜单添加一个共同的接口,然后再稍微修改下服务员代码
 *
 */

interface Menu{
    public Iterator createIterator();
}
class Waitress{
    Menu pancakeHouseMenu;
    Menu dinerMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu(){
        Iterator pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator dinnerIterator = dinerMenu.createIterator();
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
/**
 * 这样修改有什么好处?
 * 煎饼屋和餐厅都实现了Menu接口,服务员可以利用接口(而不是具体类)引用每一个菜单对象."针对接口编程,而不是针对实现编程",我们减少了服务员和具体类之间的依赖
 * 新的菜单接口有一个createIterator()方法,每个菜单类(餐厅和煎饼屋菜单)都必须负责提供合适的迭代器实现
 */

/**
 * 定义迭代器模式
 *      迭代器模式:提供一种方法顺序访问一个聚合对象中的各个元素,而又不暴露其内部的表示
 *
 *      此模式提供了一种方法,可以顺序的访问一个聚集对象中的元素,而又不用知道内部是如何表示的
 *      设计中使用迭代器的影响是明显的:如果有一个统一的方法访问聚合中的每一个对象,就可以编写多态的代码和这些聚合搭配使用-----就像printMenu()方法一样,
 *          只要有了迭代器这个方法就无需关心菜单项究竟是数组还是List来保存的
 *      迭代器模式把元素之间游走的责任交给了迭代器,而不是聚合对象.这不仅让聚合的接口和实现变得简洁,也可以让聚合更专注在他应该关注的事情上(管理对象集合),而不必理会遍历的事情
 */

/**
 * Q: 内部迭代器和外部迭代器  是什么?我们前面的例子是哪种?
 * A: 我们实现的是外部迭代器,即客户通过调用next()取得下一个元素. 而内部迭代器则是由迭代器自己控制. 这种情况下,因为迭代器自行在元素之间游走,所有你需要告诉迭代器在游走过程中
 *      要做哪些事情,即,必须将操作传入迭代器.因为客户无法控制遍历的过程,所以内部迭代器更没有弹性.
 *      然而,一些人认为内部迭代器比较容易使用,因为只需要将操作告诉他,他就会完成所有的事情.
 * Q: 迭代器可以被实现成向后移动吗?
 * A: 可以.加两个方法:一个方法取得前一个元素,一个方法判断是否到了集合顶端. Java的Collection提供另一种迭代器接口,称为ListIterator.它在标准的迭代器接口上多加了个previous()和
 *      一些其他方法.任何实现了List接口的集合,都支持这样的做法
 * Q: 对于散列表这样的集合,元素之间没有明显的次序关系,该怎么办?
 * A: 迭代器意味着没有次序,只是取出所有的元素,并不代表取出的先后就表示元素的大小次序. 对于迭代器来说,数据结构可以是有次序的,无次序的,甚至数据可以是重复的. 除非某个集合的文件有特别说明,
 *      否则不可以对迭代器所取出的元素大小顺序做出假设
 * Q: 迭代器写出"多态的代码",详细的解释是什么?有进一步的说明吗?
 * A: 当我们写了一个迭代器当做参数的方法时,其实就是在使用多态的迭代. 即,我们写出的代码可以在不同的集合中游走,只要这个集合支持迭代器即可. 我们不在乎这个集合是如何被实现的,但依然可以编程
 *      在它内部的元素之间游走
 * Q: 如果我使用JAVA,我不见得总是想利用util.Iterator,可能想用自己的迭代器实现. 这和已经使用JAVA标准迭代器的类做整合,可以做到吗?
 * A: 或许可以. 如果有一个通用的迭代器接口,那么让自己的集合和JAVA集合混合使用就会比较容易.但是,当你需要在迭代器接口为你的集合新增功能,你可以随时扩展迭代器接口.
 * Q: JAVA有一个Enumeration(枚举)接口,它实现了迭代器模式吗?
 * A: 我们在适配器那节提到过他.他是一个有次序的迭代器实现,两个方法分别类似于hasNext()和next() .然而你会比较想用迭代器,而不是枚举. 如果想把两者互相转换,请复习适配器那张,哪里实现了枚举和迭代器的适配器
 */

/**
 * 单一责任
 *
 * Q: 如果我们允许聚合实现他们内部的集合,以及相关的操作和遍历的方法,会怎么样?这回增加聚合内的方法个数,这么做哪里不好?
 * A: 当我们允许一个类不仅要完成自己的事情(管理某种聚合),还同时要担负更多的责任(例如遍历)时,我们就给了这个类两个变化的原因.
 *      两个变化的原因是: 1. 集合改变的话,类也必须改变  2. 遍历的方式改变的话,类也必须跟着改变
 *    将一个责任只指派给一个类
 *
 *    内聚: 用来度量一个类或模块紧密的达到单一目的或责任.
 *      当一个模块或一个类被设计成只支持一组相关的功能时,我们说它具有高内聚; 反之,当被设计成支持一组不相关的功能时,我们说它具有低内聚
 *      内聚是比单一责任原则更普遍的概念,但两者关系密切.遵守这个原则的类容易具有很高的凝聚力,而且比背负许多责任的低内聚类更容易维护.
 *
 */
