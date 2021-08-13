package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step12;

public class ChicagoStyleCheesePizza extends Pizza {

	public ChicagoStyleCheesePizza() {
		name = "Chicago Style Deep Dish Cheese Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";

		toppings.add("Shredded Mozzarella Cheese");
	}

	void cut() {
		System.out.println("Cutting the pizza into square slices");
	}
}
