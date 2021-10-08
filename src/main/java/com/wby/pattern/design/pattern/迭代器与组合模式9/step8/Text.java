package com.wby.pattern.design.pattern.迭代器与组合模式9.step8;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 新的实现。
 * 所有组件都必须实现MenuComponent接口。然而，叶节点和组合结点角色不同，所以有些方法并不适合某些节点，这种情况下，应该抛出异常，。
 * 1. 实现菜单组件
 */
abstract class MenuComponent{
    //组合的方法3个
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    public MenuComponent getChild(int index){
        throw new UnsupportedOperationException();
    }

    //菜单项的操作方法。其中一些也可以用在菜单上
    public String getName(){
        throw new UnsupportedOperationException();
    }
    public String getDesc(){
        throw new UnsupportedOperationException();
    }
    public double getprice(){
        throw new UnsupportedOperationException();
    }
    public boolean isVegetarian(){
        throw new UnsupportedOperationException();
    }
    //此方法是一个“操作”方法，同时被菜单和菜单项实现， 但我们还是提供了默认的操作。
    public void print(){
        throw new UnsupportedOperationException();
    }
}

/**
 * 2. 实现菜单项
 */
class MenuItem extends MenuComponent {
    String name;
    String desc;
    boolean isVegetarian;
    double price;

    public MenuItem(String name, String desc,  boolean isVegetarian,double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.isVegetarian = isVegetarian;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public double getprice() {
        return price;
    }

    @Override
    public boolean isVegetarian() {
        return isVegetarian;
    }

    //对菜单项来说，会打印出完整的菜单项条目。
    @Override
    public void print() {
        System.out.print("   "+getName());
        if (isVegetarian()){
            System.out.print("(V)");
        }
        System.out.println(",  "+getprice());
        System.out.println("        ----"+getDesc());
    }
}

/**
 * 3. 实现组合菜单
 */
class Menu extends MenuComponent{
    ArrayList<MenuComponent> menuComponents=new ArrayList<>();
    String name;
    String desc;

    public Menu(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int index) {
        return menuComponents.get(index);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void print() {
        System.out.print("\n"+getName());
        System.out.println(","+getDesc());
        System.out.println("---------------------------------");
        //让菜单不仅打印菜单本身的信息，也打印菜单内所有组件的内容：其他菜单和菜单项。
        //使用迭代器进行遍历
        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();
        }
    }
}
class Waitress{
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu(){
        allMenus.print();
    }
}
public class Text {
    public static void main(String[] args) {
        //创建菜单对象
        MenuComponent pancakeHouseMenu =
                new Menu("PANCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinerMenu =
                new Menu("DINER MENU", "Lunch");
        MenuComponent cafeMenu =
                new Menu("CAFE MENU", "Dinner");
        MenuComponent dessertMenu =
                new Menu("DESSERT MENU", "Dessert of course!");
        //创建最顶层菜单。
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
        //使用组合的add方法，将每个菜单都加入到顶层菜单allMenus中
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);

        //加入菜单项
        pancakeHouseMenu.add(new MenuItem(
                "K&B's Pancake Breakfast",
                "Pancakes with scrambled eggs and toast",
                true,
                2.99));
        pancakeHouseMenu.add(new MenuItem(
                "Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage",
                false,
                2.99));
        pancakeHouseMenu.add(new MenuItem(
                "Blueberry Pancakes",
                "Pancakes made with fresh blueberries and blueberry syrup",
                true,
                3.49));
        pancakeHouseMenu.add(new MenuItem(
                "Waffles",
                "Waffles with your choice of blueberries or strawberries",
                true,
                3.59));

        dinerMenu.add(new MenuItem(
                "Vegetarian BLT",
                "(Fakin') Bacon with lettuce & tomato on whole wheat",
                true,
                2.99));
        dinerMenu.add(new MenuItem(
                "BLT",
                "Bacon with lettuce & tomato on whole wheat",
                false,
                2.99));
        dinerMenu.add(new MenuItem(
                "Soup of the day",
                "A bowl of the soup of the day, with a side of potato salad",
                false,
                3.29));
        dinerMenu.add(new MenuItem(
                "Hot Dog",
                "A hot dog, with saurkraut, relish, onions, topped with cheese",
                false,
                3.05));
        dinerMenu.add(new MenuItem(
                "Steamed Veggies and Brown Rice",
                "A medly of steamed vegetables over brown rice",
                true,
                3.99));

        dinerMenu.add(new MenuItem(
                "Pasta",
                "Spaghetti with marinara sauce, and a slice of sourdough bread",
                true,
                3.89));

        dinerMenu.add(dessertMenu);

        dessertMenu.add(new MenuItem(
                "Apple Pie",
                "Apple pie with a flakey crust, topped with vanilla icecream",
                true,
                1.59));
        dessertMenu.add(new MenuItem(
                "Cheesecake",
                "Creamy New York cheesecake, with a chocolate graham crust",
                true,
                1.99));
        dessertMenu.add(new MenuItem(
                "Sorbet",
                "A scoop of raspberry and a scoop of lime",
                true,
                1.89));

        cafeMenu.add(new MenuItem(
                "Veggie Burger and Air Fries",
                "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                true,
                3.99));
        cafeMenu.add(new MenuItem(
                "Soup of the day",
                "A cup of the soup of the day, with a side salad",
                false,
                3.69));
        cafeMenu.add(new MenuItem(
                "Burrito",
                "A large burrito, with whole pinto beans, salsa, guacamole",
                true,
                4.29));

        //将菜单交给服务员
        Waitress waitress = new Waitress(allMenus);

        waitress.printMenu();
    }
}
/**
 * 组合模式牺牲单一责任设计原则 换取 透明性。
 * 透明性： 通过让组件的接口同时包含一些管理子节点和叶节点的操作，客户就可以将组合和叶节点一视同仁。即，一个元素是叶节点还是组合，对客户是透明的
 *
 * 现在，我们在MenuComponent中同时具有两种操作。因为客户有机会对一个元素做不恰当的操作（例如将“菜单”添加到“菜单项”），所以我们会失去一些安全性。
 * 这是设计上的抉择，当然也可以采用另一中方式：讲责任区分开来，放在不同的接口。这样保证了安全性，但是失去了透明性，客户代码必须使用条件语句和Instantceof操作符处理不同类型的节点。
 *
 * 有时我们会做一些违反原则的事情，这是观点的问题：让管理子节点的操作（add，remove，getCHild）出现在叶节点中很不恰当，但是换个角度，你可以认为叶节点是没有子节点的节点
 */

