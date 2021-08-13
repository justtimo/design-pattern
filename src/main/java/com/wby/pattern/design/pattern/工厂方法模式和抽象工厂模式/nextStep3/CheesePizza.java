package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep3;

/** `
 * 现在有了抽象披萨,开始创建纽约和芝加哥风味的披萨.从此以后,加盟店必须从原料工厂取得原料.
 * 我们之前写过工厂方法的代码,NYCheesePizza和ChicagoCheesePizza类,比较他们,唯一差别在于使用区域性原料.
 * 所以,不需要设计两个不同的类来处理不同风味的披萨,让原料工厂处理这种区域差异就可以了.
 * 下面是CheesePizza
 */
class CheesePizza extends Pizza {
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


















