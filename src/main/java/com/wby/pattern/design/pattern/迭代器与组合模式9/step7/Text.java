package com.wby.pattern.design.pattern.迭代器与组合模式9.step7;

/**
 * 利用组合设计菜单
 * 1.创建一个组件接口，作为菜单和菜单项的共同接口，让我们能够用统一的做法来处理菜单和菜单项。即，可以针对菜单或菜单项调用相同的方法。
 */
interface MenuComponent{
    //老版本中，有些方法一样，也加入了print，add，remove，getChild。稍后会在实现新菜单和菜单项时描述这些方法。
    void getName();
    void getDesc();
    void getPrice();
    void isVegetarien();
    void print();
    //下面三个方法都是用来操作组件的。菜单项和菜单都是组件。
    void add(MenuComponent component);
    void remove(MenuComponent component);
    void getChild(int index);
}

/**
 * 菜单项覆盖了覆盖了对他有意义的方法,没有意义的方法（例如add），则置之不理。因为菜单项已经是叶节点，他下面不能再有任何组件。
 */
class MenuItem implements MenuComponent {

    @Override
    public void getName() {

    }

    @Override
    public void getDesc() {

    }

    @Override
    public void getPrice() {

    }

    @Override
    public void isVegetarien() {

    }

    @Override
    public void print() {

    }

    @Override
    public void add(MenuComponent component) {

    }

    @Override
    public void remove(MenuComponent component) {

    }

    @Override
    public void getChild(int index) {

    }
}

/**
 * 菜单也覆盖了对他有意义的方法，例如增加删除菜单项。除此之外，也使用setName(),setDesc()来返回菜单名称与描述
 */
class Menu implements MenuComponent {
    MenuComponent menuComponent;

    @Override
    public void getName() {

    }

    @Override
    public void getDesc() {

    }

    @Override
    public void getPrice() {

    }

    @Override
    public void isVegetarien() {

    }

    @Override
    public void print() {

    }

    @Override
    public void add(MenuComponent component) {

    }

    @Override
    public void remove(MenuComponent component) {

    }

    @Override
    public void getChild(int index) {

    }
}

public class Text {
}
