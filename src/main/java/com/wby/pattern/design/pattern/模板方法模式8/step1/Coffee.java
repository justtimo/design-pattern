package com.wby.pattern.design.pattern.模板方法模式8.step1;

/**
 * 咖啡类,用来煮咖啡
 */
public class Coffee {
	/**
	 * 咖啡冲泡步骤手册: 每个步骤都被实现在分离的方法中
	 */
	void prepareRecipe() {
		boilWater();
		brewCoffeeGrinds();
		pourInCup();
		addSugarAndMilk();
	}
	//烧水
	public void boilWater() {
		System.out.println("Boiling water");
	}
	//冲泡咖啡
	public void brewCoffeeGrinds() {
		System.out.println("Dripping Coffee through filter");
	}
	//把咖啡倒进杯子
	public void pourInCup() {
		System.out.println("Pouring into cup");
	}
	//加糖和奶
	public void addSugarAndMilk() {
		System.out.println("Adding Sugar and Milk");
	}
}
