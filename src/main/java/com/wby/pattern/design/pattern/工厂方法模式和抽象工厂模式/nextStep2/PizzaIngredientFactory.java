package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep2;

/**
 * 建造一个工厂,将负责创建原料家族中的每一种原料.
 */
public interface PizzaIngredientFactory {
    //接口中,每种原料都有对应的方法创建该原料
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClams();
}

/** `
 * 如果每个工厂实例内都有某一种通用的"机制"需要实现,那么可以吧这个例子改成抽象类
 */
class Dough{}
class Sauce{}
class Cheese{}
class Veggies{}
class Pepperoni{}
class Clams{}
/** `
 * 要做的事情是:
 *      1. 为每个区域建造一个工厂.需要创建一个继承自PizzaIngredientFactory的子类实现每个创建方法
 *      2. 实现一组原料类供工厂使用,例如ReggianoCHeese,RedPeppers,ThickCrustDough.这些类可以在合适的区域共享.
 *      3. 将这一切组织起来,将新的原料工厂整合进旧的PizzaStore中
 */
class NYPizzaIngredientFactory implements PizzaIngredientFactory{

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    /** `
     * 对于蔬菜,这里使用了简单地做法
     */
    @Override
    public Veggies[] createVeggies() {
        Veggies veggies[]={new Garlic(),new Onion(),new Mushroom(),new RedPepper()};
        return new Veggies[0];
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicePepperoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
class ThinCrustDough extends Dough{}
class MarinaraSauce extends Sauce{}
class ReggianoCheese extends Cheese{}
class Garlic extends Veggies{}
class Onion extends Veggies{}
class Mushroom extends Veggies{}
class RedPepper extends Veggies{}
class SlicePepperoni extends Pepperoni{}
class FreshClams extends Clams{}

/** `
 * 重做披萨
 */
abstract  class Pizza{
    String name;
    Dough dough;
    Sauce sauce;
    Veggies[] veggies;
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    //现在把prepare()声明成抽象的,此方法中需要收集披萨所需要的原料.其他方法不需要改变
    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cut the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
    void setName(String name){
        this.name = name;
    }

    public String toString() {
        StringBuffer display = new StringBuffer();
        display.append("---- " + name + " ----\n");
        display.append(dough + "\n");
        display.append(sauce + "\n");
        return display.toString();
    }
}
/** `
 * 现在有了抽象披萨,开始创建纽约和芝加哥风味的披萨.从此以后,加盟店必须从原料工厂取得原料.
 * 我们之前写过工厂方法的代码,NYCheesePizza和ChicagoCheesePizza类,比较他们,唯一差别在于使用区域性原料.
 * 所以,不需要设计两个不同的类来处理不同风味的披萨,让原料工厂处理这种区域差异就可以了.
 * 下面是CheesePizza
 */
class CheesePizza extends Pizza{
    PizzaIngredientFactory pizzaIngredientFactory;

    public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("Prepareing "+ name);
        //神奇的地方在这里:一步步创建芝士披萨,需要原料就向工厂要
        dough=pizzaIngredientFactory.createDough();
        sauce= pizzaIngredientFactory.createSauce();
        cheese= pizzaIngredientFactory.createCheese();
    }
}
/** `
 * Pizza代码利用相关的工厂生产原料.所生产原料依赖所使用的工厂,Pizza类不关心这些原料,他只关心如何制作披萨.
 * 现在,Pizza和区域原料之间被解耦了,无论原料工厂是在哪里,Pizza类都可以轻易复用.
 * sauce            =       pizzaIngredientFactory.         createSauce();
 * 把Pizza实例变量设置为        原料工厂.Pizza不在乎用什么工厂,         返回这个区域所使用的酱料.
 * 此披萨使用的某种酱料           只要是原料工厂就行
 */
class ClamPizza extends Pizza{
    PizzaIngredientFactory pizzaIngredientFactory;

    public ClamPizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("Prepareing "+ name);
        //神奇的地方在这里:一步步创建芝士披萨,需要原料就向工厂要
        dough=pizzaIngredientFactory.createDough();
        sauce= pizzaIngredientFactory.createSauce();
        cheese= pizzaIngredientFactory.createCheese();
        clams=pizzaIngredientFactory.createClams();
    }
}

/** `
 * 回到披萨店,让他们使用正确的披萨,并且和本地的原料工厂搭上线
 */
class NYPizzaStore extends PizzaStore{

    @Override
    Pizza createPizza(String type) {
        Pizza pizza=null;
        NYPizzaIngredientFactory nyPizzaIngredientFactory = new NYPizzaIngredientFactory();
        if (type.equals("cheese")){
            pizza=new CheesePizza(nyPizzaIngredientFactory);
            pizza.setName("NY style cheese pizza");
        }else if (type.equals("veggie")){
            pizza=new ClamPizza(nyPizzaIngredientFactory);
            pizza.setName("NY style clam pizza");
        }
        return pizza;
    }
}
abstract class PizzaStore{
    public Pizza orderPizza(String type){
        Pizza pizza;

        pizza=createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    abstract Pizza createPizza(String type);
}
/** `
 * 我们做了些什么?
 * 1. 引入了新的工厂,也就是所谓的抽象工厂,用来创建披萨原料.抽象工厂为产品家族提供接口.
 *      什么是家族?制作披萨所需要的一切东西,例如:面团,酱料,芝士,肉和蔬菜
 * 2. 通过抽象工厂提供的接口,可以创建产品的家族,利用此接口编写代码,我们的代码将从实际工厂解耦,以便在不同上下文中实现各种工厂,制造不同的
 *      产品:不同的区域,不同的操作系统,不同的外观及操作.
 *      从抽象工厂中派生出一些具体工厂,这些工厂产生相同的产品,但是产品的实现不同
 * 3. 代码从实际的产品中解耦了,所以我们可以替换不同的工厂来获取不同的行为
 *
 * 抽象的原料工厂---->原料工厂的具体实现---->PizzaStore---->Pizza
 */


















