package com.wby.pattern.design.pattern.模板方法模式8.step1;

/**
 * 冲泡茶
 */
public class Tea {
	//茶饮冲泡手册: 与冲泡咖啡很类似,但是第2,4步不同
	void prepareRecipe() {
		boilWater();
		steepTeaBag();
		pourInCup();
		addLemon();
	}
	//烧水
	public void boilWater() {
		System.out.println("Boiling water");
	}
	//放入茶叶
	public void steepTeaBag() {
		System.out.println("Steeping the tea");
	}
	//添加柠檬
	public void addLemon() {
		System.out.println("Adding Lemon");
	}
	//倒进杯子
	public void pourInCup() {
		System.out.println("Pouring into cup");
	}
}
