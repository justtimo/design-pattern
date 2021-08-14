package com.wby.pattern.design.pattern.工厂模式;

import java.util.ArrayList;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/13 19:53
 * @Description:
 */
class Pizza{
    String name;
    String miantuan;
    String jiangliao;
    ArrayList zuoliao=new ArrayList();
    void prepare(){
        System.out.println("准备披萨中:"+name+","+miantuan+","+jiangliao+","+zuoliao);
    }
    void bake(){
        System.out.println("烘烤中");
    }
    void cut(){
        System.out.println("切片中");
    }
    void box(){
        System.out.println("装盒中");
    }

}
class NYCheesePizza extends Pizza{
    public NYCheesePizza() {
        name="纽约芝士披萨";
        miantuan="薄面团";
        jiangliao="番茄酱";
        zuoliao.add("加香肠");
    }
}
class NYWhipPizza extends Pizza{}
class NYMeetPizza extends Pizza{}
class ChicagoCheesePizza extends Pizza{
    public ChicagoCheesePizza() {
        name="芝加哥芝士披萨";
        miantuan="后面团";
        jiangliao="甜辣酱";
        zuoliao.add("加里脊");
    }
}
class ChicagoWhipPizza extends Pizza{}
class ChicagoMeetPizza extends Pizza{}

abstract class PizzaStore{
    SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
        this.simplePizzaFactory = simplePizzaFactory;
    }

    Pizza orderPizza(String type){
        Pizza pizza;
        pizza= simplePizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
    abstract Pizza createPizza(String string);
}

class NYPizzaStore extends PizzaStore{
    public NYPizzaStore(SimplePizzaFactory simplePizzaFactory) {
        super(simplePizzaFactory);
    }

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")){
            pizza=new NYCheesePizza();
        }else if (type.equals("whip")){
            pizza=new NYWhipPizza();
        }else if (type.equals("meet")){
            pizza=new NYMeetPizza();
        }
        return pizza;
    }
}

class ChicagoPizzaStore extends PizzaStore{
    public ChicagoPizzaStore(SimplePizzaFactory simplePizzaFactory) {
        super(simplePizzaFactory);
    }

    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")){
            pizza=new ChicagoCheesePizza();
        }else if (type.equals("whip")){
            pizza=new ChicagoWhipPizza();
        }else if (type.equals("meet")){
            pizza=new ChicagoMeetPizza();
        }
        return pizza;
    }
}


class SimplePizzaFactory{
    public Pizza createPizza(String type){
        Pizza pizza = null;

        return pizza;
    }
}
class NYYPizzaFactory extends SimplePizzaFactory{
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")){
            pizza=new NYCheesePizza();
        }else if (type.equals("whip")){
            pizza=new NYWhipPizza();
        }else if (type.equals("meet")){
            pizza=new NYMeetPizza();
        }
        return pizza;
    }
}
//step3.现在要新增蛤蜊披萨,素食披萨,并去除希腊披萨
//明显,orderPizza无法对修改关闭
//step4:现在又加盟店了,每个地区加盟店的口味都不一样
//step5,想要做一些质量控制:加盟店和创建披萨捆绑在一起
public class 工厂模式温习 {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new NYPizzaStore(new NYYPizzaFactory());
        Pizza cheese = pizzaStore.orderPizza("cheese");
        System.out.println(cheese);
    }
}
/**
 * 工厂方法模式: 主要有创建者类  和  产品类 两个角色
 * 分别是上例中的:
 *      创建者: PizzaStore  NYPizzaStore  ChicagoPizzaStore
 *      产品类: Pizza NYPizzaStore  ChicagoPizzaStore
 * 另一个观点是,平行的类层级,Pizza和PizzaStore平级
 */
//Step6确保加盟店使用的原料一致:产品家族,面团,酱料,佐料根据区域不同而不同
interface PizzaIngredientFactory{
    public 面团 createMiantuan();
    public 香肠 createXC();
    public 里脊 createLJ();
    public 肉松 createRS();
    public 酱料 createJL();
    public 佐料 createZL();
}
class 面团{}
class 薄面团 extends 面团{}
class 香肠{}
class 热狗肠 extends 香肠{}
class 里脊{}
class 猪里脊 extends 里脊{}
class 肉松{}
class 猪肉松 extends 肉松{}
class 酱料{}
class 番茄酱 extends 酱料{}
class 佐料{}
class 辣椒粉 extends 佐料{}

class NYPizzaIngredientFactory implements PizzaIngredientFactory{

    @Override
    public 面团 createMiantuan() {
        return new 薄面团();
    }

    @Override
    public 香肠 createXC() {
        return new 热狗肠();
    }

    @Override
    public 里脊 createLJ() {
        return new 猪里脊();
    }

    @Override
    public 肉松 createRS() {
        return new 猪肉松();
    }

    @Override
    public 酱料 createJL() {
        return new 番茄酱();
    }

    @Override
    public 佐料 createZL() {
        return new 辣椒粉();
    }
}

abstract class PizzaSuper{
    String name;
    面团 miantuan;
    香肠 xiangchang;
    里脊 liji;
    肉松 rousong;
    酱料 jiangliao;
    佐料 zuoliao;

    abstract void prepare();

    void bake(){}
    void cut(){}
    void box(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
class CheesePizza extends PizzaSuper{
    PizzaIngredientFactory pizzaIngredientFactory;

    public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    void prepare() {
        miantuan=pizzaIngredientFactory.createMiantuan();
        xiangchang=pizzaIngredientFactory.createXC();
        liji=pizzaIngredientFactory.createLJ();
        rousong=pizzaIngredientFactory.createRS();
        jiangliao=pizzaIngredientFactory.createJL();
        zuoliao=pizzaIngredientFactory.createZL();
        System.out.println("芝士披萨,"+miantuan+","+xiangchang+","+liji+","+rousong+","+jiangliao+","+zuoliao);
    }
}

abstract class PizzaSuperStore{

    PizzaSuper orderPizza(String type){
        PizzaSuper pizza;
        pizza= createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
    abstract PizzaSuper createPizza(String string);
}

class NYYYPizzaStore extends PizzaSuperStore{

    @Override
    PizzaSuper createPizza(String string) {
        PizzaSuper pizzaSuper = null;
        PizzaIngredientFactory nyPizzaIngredientFactory = new NYPizzaIngredientFactory();

        if (string.equals("cheese")){
            return new  CheesePizza(nyPizzaIngredientFactory);
        }
        return pizzaSuper;
    }
}
class TestPizzaSuper{
    public static void main(String[] args) {
        NYYYPizzaStore nyyyPizzaStore = new NYYYPizzaStore();
        PizzaSuper cheese = nyyyPizzaStore.orderPizza("cheese");
        System.out.println(cheese);
    }
}





