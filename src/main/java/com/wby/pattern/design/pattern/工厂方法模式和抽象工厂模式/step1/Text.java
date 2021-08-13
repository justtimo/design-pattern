package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step1;

/**
 * 每次使用new时,不正是针对实现编程吗?
 * 当要实例化一堆鸭子时,使用new关键字必须等到运行时才知道实例化哪一个.
 * 而且当有变化或者扩展,就必须修改代码,这会造成更难维护和更新.
 *
 * 但是总要创建对象,而JAva只提供一个new关键字创建对象,还能有些什么?
 * 技术上,使用new没有错.有问题的是"改变",以及他是如何影响new使用的
 * 针对接口编程,可以隔离以后系统可能发生的一大堆改变.因为可以通过多态,与任何新类实现该接口.
 */
public class Text {
}
/** `
 * 认识变化的方面
 */
class Pizza{

    public void prepare(){
        System.out.println("准备披萨");
    }
    public void bake(){
        System.out.println("加调料");
    }
    public void cut(){
        System.out.println("切片");
    }
    public void box(){
        System.out.println("装盒");
    }
}
class Test{
    public static void main(String[] args) {
        //为了让系统有弹性,我们希望这是一个抽象类或接口,但是如果这样,这些类或接口无法直接实例化
        Pizza pizza = new Pizza();

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
/** `
 * 但是我需要更多的披萨类型:
 *  所以必须增加一些代码
 */
class Pizza1{

    public void prepare(){
        System.out.println("准备披萨");
    }
    public void bake(){
        System.out.println("加调料");
    }
    public void cut(){
        System.out.println("切片");
    }
    public void box(){
        System.out.println("装盒");
    }

    Pizza1 orderPizza(String tyoe){
        Pizza1 pizza1;
        if (tyoe.equals("cheese")){
            return new CheesePizza1();
        }else if (tyoe.equals("greek")){
            return new GreekPizza1();
        }else if (tyoe.equals("pepperoni")){
            return new PepperoniPizza1();
        }else {
            return null;
        }
    }
}
class CheesePizza1 extends Pizza1{}
class GreekPizza1 extends Pizza1 { }
class PepperoniPizza1 extends Pizza1 { }
/** `
 * 但是如果增加了更多的类型呢?
 * 就需要添加很多if语句进行判断,而且随着菜单的变更,if语句块需要不断的变更,这就是变化的部分.
 * 而披萨的准备\调料\切片\装盒等方法,是不变的部分
 *
 * 很明显,如果实例化"某些"具体类,那么orderPizza()会出问题,而且也无法让orderPizza()对修改关闭;
 * 但是现在我们已经知道了哪些会改变,哪些不会改变,所以到了该使用封装的时候了
 */








