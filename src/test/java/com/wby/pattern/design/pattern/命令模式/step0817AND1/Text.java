package com.wby.pattern.design.pattern.命令模式.step0817AND1;

/**
 * 手写命令模式1
 */

interface Command{
    public void execute();
}
//接受者:知道如何进行工作,以便实现请求
class Light{
    public void on(){
        System.out.println("灯开了");
    }
    public void off(){
        System.out.println("灯关了");
    }
}
//定义了动作和接受者之间的关系.调用者只需调用execute即可
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
class Door{
    public void up(){
        System.out.println("门网上");
    };
    public void down() {
        System.out.println("门往下");
    }
    public void shuadown(){
        System.out.println("门暂停运动");
    }
}
class DoorCommand implements Command {
    Door door;

    public DoorCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.up();
    }
}
//调用者: 持有命令对象,并调用命令对象的方法,将请求付诸实行
class RemoteController{
    Command command;

    //命令参数化
    public void setCommand(Command command){
        this.command = command;
    }
    public void buttonWasPushed(){
        command.execute();
    }
}
//客户:创建command,并设置其接受者
public class Text {
    public static void main(String[] args) {
        RemoteController remoteController = new RemoteController();
        Light light = new Light();
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        remoteController.setCommand(lightOffCommand);
        remoteController.buttonWasPushed();
    }
}

