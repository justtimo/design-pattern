package com.wby.pattern.design.pattern.命令模式6.step5;

/**
 * 绑定命令到插槽
 *      遥控器每个插槽对应一个命令,让遥控器编程调用者.按下按钮,调用exectute方法,接受者动作执行
 */


/**
 * 实现遥控器
 */
class RemoteController{
    //遥控器处理7个开与关的命令,使用相应数组记录这些命令
    Command[] onCommands;
    Command[] offCommands;

    //构造器中,只需实例化并初始化这两个开与关数组
    public RemoteController() {
        onCommands=new Command[7];
        offCommands = new Command[7];

        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 7;i++){
            onCommands[i]=noCommand;
            offCommands[i] = noCommand;
        }
    }

    /**
     * 此方法有三个参数,分别是插槽位置,开的命令,关的命令.这些命令对应开关数组中对应的插槽位置,以供后续使用
     * @param slot
     * @param onCommand
     * @param offCommand
     */
    public void setCommand(int slot,Command onCommand,Command offCommand){
        onCommands[slot]=onCommand;
        offCommands[slot]=offCommand;
    }

    /**
     * 按下开或关的按钮,硬件就会负责调用对应的方法,也就是onButtonWasPushed,offButtonWasPushed
     * @param slot
     */
    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
    }
    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
    }

    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName()
                    + "    " + offCommands[i].getClass().getName() + "\n");
        }
        /*stringBuff.append("[undo] " + undoCommand.getClass().getName() + "\n");*/
        return stringBuff.toString();
    }
}

interface Command{
    public void execute();
}

class NoCommand implements Command {
    public void execute() { }
    public void undo() { }
}

/**
 * 我们已经为SimpleRemoteController实现过 LightOnCommand,现在实现LightOffCommand
 */
class Light{
    String location = "";

    public Light(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " light is on");
    }

    public void off() {
        System.out.println(location + " light is off");
    }
}
class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}

/**
 * 为音响编写开关命令.关很容易,开就有些复杂了.假设我们要写一个StereoOnWithCDCommand
 */
class StereoOnWithCDCommand implements Command {
    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }

    public void undo() {
        stereo.off();
    }
}
class Stereo{
    String location;

    public Stereo(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " stereo is on");
    }

    public void off() {
        System.out.println(location + " stereo is off");
    }

    public void setCD() {
        System.out.println(location + " stereo is set for CD input");
    }

    public void setDVD() {
        System.out.println(location + " stereo is set for DVD input");
    }

    public void setRadio() {
        System.out.println(location + " stereo is set for Radio");
    }

    public void setVolume(int volume) {
        // code to set the volume
        // valid range: 1-11 (after all 11 is better than 10, right?)
        System.out.println(location + " Stereo volume set to " + volume);
    }
}
class StereoOnCommand implements Command {
    Stereo stereo;

    public StereoOnCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
    }

    public void undo() {
        stereo.off();
    }
}
class StereoOffCommand implements Command {
    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.off();
    }

    public void undo() {
        stereo.on();
    }
}
class CeilingFan {
    String location = "";
    int level;
    public static final int HIGH = 2;
    public static final int MEDIUM = 1;
    public static final int LOW = 0;

    public CeilingFan(String location) {
        this.location = location;
    }

    public void high() {
        // turns the ceiling fan on to high
        level = HIGH;
        System.out.println(location + " ceiling fan is on high");

    }

    public void medium() {
        // turns the ceiling fan on to medium
        level = MEDIUM;
        System.out.println(location + " ceiling fan is on medium");
    }

    public void low() {
        // turns the ceiling fan on to low
        level = LOW;
        System.out.println(location + " ceiling fan is on low");
    }

    public void off() {
        // turns the ceiling fan off
        level = 0;
        System.out.println(location + " ceiling fan is off");
    }

    public int getSpeed() {
        return level;
    }
}
class CeilingFanOnCommand implements Command {
    CeilingFan ceilingFan;

    public CeilingFanOnCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }
    public void execute() {
        ceilingFan.high();
    }
}
class CeilingFanOffCommand implements Command {
    CeilingFan ceilingFan;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }
    public void execute() {
        ceilingFan.off();
    }
}
class GarageDoor {
    String location;

    public GarageDoor(String location) {
        this.location = location;
    }

    public void up() {
        System.out.println(location + " garage Door is Up");
    }

    public void down() {
        System.out.println(location + " garage Door is Down");
    }

    public void stop() {
        System.out.println(location + " garage Door is Stopped");
    }

    public void lightOn() {
        System.out.println(location + " garage light is on");
    }

    public void lightOff() {
        System.out.println(location + " garage light is off");
    }
}
class GarageDoorUpCommand implements Command {
    GarageDoor garageDoor;

    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.up();
    }
}
class GarageDoorDownCommand implements Command {
    GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.up();
    }
}

public class Text {
    public static void main(String[] args) {
        RemoteController remoteControl = new RemoteController();

        //将所有装置创建在合适的位置
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        CeilingFan ceilingFan= new CeilingFan("Living Room");
        GarageDoor garageDoor = new GarageDoor("Garage");
        Stereo stereo = new Stereo("Living Room");

        //创建所有电灯命令对象
        LightOnCommand livingRoomLightOn =
                new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff =
                new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn =
                new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff =
                new LightOffCommand(kitchenLight);

        //创建风扇的开关命令
        CeilingFanOnCommand ceilingFanOn =
                new CeilingFanOnCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff =
                new CeilingFanOffCommand(ceilingFan);

        //创建车库门的上下命令
        GarageDoorUpCommand garageDoorUp =
                new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown =
                new GarageDoorDownCommand(garageDoor);

        //创建印象的开关命令
        StereoOnWithCDCommand stereoOnWithCD =
                new StereoOnWithCDCommand(stereo);
        StereoOffCommand  stereoOff =
                new StereoOffCommand(stereo);

        //加载到遥控器中
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff);
        remoteControl.setCommand(3, stereoOnWithCD, stereoOff);

        System.out.println(remoteControl);

        //按下每个插槽的开关按钮
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
    }
}
/**
 * Q: 插槽4到6是" NoCommand",这是怎么回事
 * A: 我们不想每次都检查插槽是否加载了命令.使用的方式是,每个插槽都先指定成NoCommand对象,以便确定每个插槽永远都有命令对象
 *      所以在测试的输出中,没有被明确指定命令的插槽,其命令讲师默认的NoCommand对象.
 *    NoCommand是一个空对象的例子.举例来说,遥控器不能一出厂就设置有意义的命令对象,所以提供了NoCommand作为替代品,当调用他的execute
 *      时,这种对象什么事情都不做.
 *    许多设计模式中,都会看到空对象,甚至,空对象本身也被视作为一种设计模式
 */
