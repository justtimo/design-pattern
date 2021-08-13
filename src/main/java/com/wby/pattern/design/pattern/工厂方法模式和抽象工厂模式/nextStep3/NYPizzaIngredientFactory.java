package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep3;

import com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep2.*;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/12 18:06
 * @Description:
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return null;
    }

    @Override
    public Sauce createSauce() {
        return null;
    }

    @Override
    public Cheese createCheese() {
        return null;
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[0];
    }

    @Override
    public Pepperoni createPepperoni() {
        return null;
    }

    @Override
    public Clams createClams() {
        return null;
    }
}
