package com.wby.pattern.design.pattern.命令模式6.step6;

class CeilingFan {
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	String location;
	int speed;

	public CeilingFan(String location) {
		this.location = location;
		speed = OFF;
	}

	/**
	 * 设置风扇速度
	 */
	public void high() {
		speed = HIGH;
		System.out.println(location + " ceiling fan is on high");
	}

	public void medium() {
		speed = MEDIUM;
		System.out.println(location + " ceiling fan is on medium");
	}

	public void low() {
		speed = LOW;
		System.out.println(location + " ceiling fan is on low");
	}

	public void off() {
		speed = OFF;
		System.out.println(location + " ceiling fan is off");
	}

	/**
	 * 获取吊扇速度
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}
}
