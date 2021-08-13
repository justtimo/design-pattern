package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep3;

/** `
 * Pizza代码利用相关的工厂生产原料.所生产原料依赖所使用的工厂,Pizza类不关心这些原料,他只关心如何制作披萨.
 * 现在,Pizza和区域原料之间被解耦了,无论原料工厂是在哪里,Pizza类都可以轻易复用.
 * sauce            =       pizzaIngredientFactory.         createSauce();
 * 把Pizza实例变量设置为        原料工厂.Pizza不在乎用什么工厂,         返回这个区域所使用的酱料.
 * 此披萨使用的某种酱料           只要是原料工厂就行
 */
class ClamPizza extends Pizza {
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


















