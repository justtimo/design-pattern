package com.wby.pattern.design.pattern.命令模式6.step6;

public class CeilingFanHighCommand implements Command {
	CeilingFan ceilingFan;
	//加入局部状态以便追踪风扇之前的速度
	int prevSpeed;

	public CeilingFanHighCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}

	/**
	 * 改变风扇速度之前,先记录之前的状态,以便撤销时使用
	 */
	public void execute() {
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.high();
	}

	//将风扇的速度设置回之前的值
	public void undo() {
		if (prevSpeed == CeilingFan.HIGH) {
			ceilingFan.high();
		} else if (prevSpeed == CeilingFan.MEDIUM) {
			ceilingFan.medium();
		} else if (prevSpeed == CeilingFan.LOW) {
			ceilingFan.low();
		} else if (prevSpeed == CeilingFan.OFF) {
			ceilingFan.off();
		}
	}
}
