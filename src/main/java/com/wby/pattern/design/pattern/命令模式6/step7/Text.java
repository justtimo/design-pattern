package com.wby.pattern.design.pattern.命令模式6.step7;



/**
 * 遥控器的"Party"模式
 *      按下一个按钮,可以关闭等,打开音响和电视,设置DVD,并加热热水器
 */
interface Command {
    public void execute();
    public void undo();
}
class MacroCommand implements Command {
    Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }

    /**
     * NOTE:  these commands have to be done backwards to ensure
     * proper undo functionality
     */
    public void undo() {
        for (int i = commands.length -1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}
/**
 * 使用宏命令
 *      1. 创建想要进入宏的命令集合:
 *          //创建所有的装置
 *          Light light = new Light("Living Room");
 * 		    TV tv = new TV("Living Room");
 * 		    Stereo stereo = new Stereo("Living Room");
 * 		    Hottub hottub = new Hottub();
 * 		    //创建所有的on命令控制他们
 * 		    LightOnCommand lightOn = new LightOnCommand(light);
 * 		    StereoOnCommand stereoOn = new StereoOnCommand(stereo);
 * 		    TVOnCommand tvOn = new TVOnCommand(tv);
 * 		    HottubOnCommand hottubOn = new HottubOnCommand(hottub);
 * 		2.创建数组,一个记录开启命令,一个记录关闭命令,并在数组内放入对应的命令
 * 	        Command[] partyOn = { lightOn, stereoOn, tvOn, hottubOn};
 * 		    Command[] partyOff = { lightOff, stereoOff, tvOff, hottubOff};
 *
 * 		    MacroCommand partyOnMacro = new MacroCommand(partyOn);
 * 		    MacroCommand partyOffMacro = new MacroCommand(partyOff);
 * 	    3. 将宏命令指定给按钮
 *          remoteControl.setCommand(0, partyOnMacro, partyOffMacro);
 *      4. 按下按钮,测试是否正常工作
 *          System.out.println(remoteControl);
 * 		    System.out.println("--- Pushing Macro On---");
 * 		    remoteControl.onButtonWasPushed(0);
 * 		    System.out.println("--- Pushing Macro Off---");
 * 		    remoteControl.offButtonWasPushed(0);
 *
 */
public class Text {
}
/**
 * Q: 接收者一定要存在吗?为什么命令对象不直接实现execute()方法的细节
 * A: 一般来说,尽量设计"傻瓜"命令对象,只懂得调用一个接收者的一个行为.然而,很多命令对象会实现许多逻辑,直接完成一个请求.你可以这么设计,只是
 *      这样一来,调用者与接收者之间的解耦程度比不上"傻瓜"命令对象,而且,也不能把接收者当做参数传递命令.
 * Q: 如何能够实现多层次撤销操作?即,按下撤销按钮多次,撤销到很早以前的状态
 * A: 使用堆栈记录每一个命令,然后从堆栈中取出最上层的命令,调用undo()方法
 * Q: 创建PartyCommand,在他的execute()中调用其他命令,利用这种方式可以实现Party模式吗
 * A: 可以这么做,然而会导致把Party模式硬编码到PartyCommand中.利用宏命令,可以动态的决定PartYCommand由哪些命令组成,所以使用宏命令
 *      更加灵活,更优雅,需要更少的代码
 */
