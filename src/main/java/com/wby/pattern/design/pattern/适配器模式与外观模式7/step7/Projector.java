package com.wby.pattern.design.pattern.适配器模式与外观模式7.step7;

public class Projector {
	String description;
	StreamingPlayer player;

	public Projector(String description, StreamingPlayer player) {
		this.description = description;
		this.player = player;
	}

	public void on() {
		System.out.println(description + " on");
	}

	public void off() {
		System.out.println(description + " off");
	}

	public void wideScreenMode() {
		System.out.println(description + " in widescreen mode (16x9 aspect ratio)");
	}

	public void tvMode() {
		System.out.println(description + " in tv mode (4x3 aspect ratio)");
	}

        public String toString() {
                return description;
        }
}
