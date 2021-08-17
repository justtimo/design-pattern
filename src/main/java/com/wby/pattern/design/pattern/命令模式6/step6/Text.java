package com.wby.pattern.design.pattern.命令模式6.step6;


/**
 * 主要设计目标是让遥控器代码尽可能的简单.,新的厂商类一旦出现,遥控器并不需要修改代码.
 * 因此采用命令模式,从逻辑上将遥控器和厂商解耦
 *
 * 下面的类图提供了设计的全貌:
 *  RemoteLoader:创建许多命令对象,然后将其加载到插槽中.每个命令对象都封装了某个家电自动化装置的一项请求
 *  RemoteControl:管理一组命令对象,每个按钮都有一个命令对象.按下按钮,调用对应的XXButtonPushed(),间接调用该命令的execute()
 *  Command: 所有的遥控器命令都实现这个接口,包含execute()方法,封装了某个特定厂商的一组动作,遥控器通过调用execute()方法执行这些动作
 *  Light: 厂商类被用来控制特定的家电自动化装置.这里以Light为例子
 *  LightOnCommand:利用Command接口,每个动作都实现成一个命令对象.命令对象持有一个厂商类的实例引用,并实现execute()方法.这个方法会
 *      调用厂商类实例的一个或多个方法,完成特定的行为.这个例子中,有两个类,分别打开点灯和关闭电灯
 */
public class Text {
}
/**
 * 完成撤销
 *      1. 在Command接口中加入undo()方法
 *      2. 以LightOnCommand为例:undo()需要调用Light的off()方法进行相反的操作
 *      3. 在遥控器中加入新的变量,用来记录最后被调用的命令.
 */
class Light {
    String location;

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is off");
    }
}
interface Command {
    public void execute();
    public void undo();
}

class NoCommand implements Command {
    public void execute() { }
    public void undo() { }
}

class LightOnCommand implements Command {
    Light light;
    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}

class RemoteControlWithUndo {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteControlWithUndo() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for(int i=0;i<7;i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPushed() {
        undoCommand.undo();
    }

    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName()
                    + "    " + offCommands[i].getClass().getName() + "\n");
        }
        stringBuff.append("[undo] " + undoCommand.getClass().getName() + "\n");
        return stringBuff.toString();
    }
}
/**
 * 测试代码
 */
class RemoteLoader {

    public static void main(String[] args) {
        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();

        Light livingRoomLight = new Light("Living Room");

        LightOnCommand livingRoomLightOn =
                new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff =
                new LightOffCommand(livingRoomLight);

        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(0);
        System.out.println(remoteControl);
        remoteControl.undoButtonWasPushed();

        //测试吊扇代码
        CeilingFan ceilingFan = new CeilingFan("Living Room");

        CeilingFanMediumCommand ceilingFanMedium =
                new CeilingFanMediumCommand(ceilingFan);
        CeilingFanHighCommand ceilingFanHigh =
                new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff =
                new CeilingFanOffCommand(ceilingFan);
        //0号插槽设置为中速,1号为高速
        remoteControl.setCommand(0, ceilingFanMedium, ceilingFanOff);
        remoteControl.setCommand(1, ceilingFanHigh, ceilingFanOff);
        //以中速开启风扇
        remoteControl.onButtonWasPushed(0);
        //关闭风扇
        remoteControl.offButtonWasPushed(0);
        System.out.println(remoteControl);
        //撤销:回到中速
        remoteControl.undoButtonWasPushed();

        //开启高速
        remoteControl.onButtonWasPushed(1);
        System.out.println(remoteControl);
        //撤销:回到中速
        remoteControl.undoButtonWasPushed();
    }
}
