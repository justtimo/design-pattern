package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep3;


/** `
 * 回到披萨店,让他们使用正确的披萨,并且和本地的原料工厂搭上线
 */
class NYPizzaStore extends PizzaStore {

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


















