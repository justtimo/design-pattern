package com.wby.pattern.design.pattern.适配器模式与外观模式7.step7;

public class Screen {
	String description;

	public Screen(String description) {
		this.description = description;
	}

	public void up() {
		System.out.println(description + " going up");
	}

	public void down() {
		System.out.println(description + " going down");
	}


	public String toString() {
		return description;
	}
}
