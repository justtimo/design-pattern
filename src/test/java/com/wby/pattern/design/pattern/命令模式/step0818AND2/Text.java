package com.wby.pattern.design.pattern.命令模式.step0818AND2;

import java.util.Arrays;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/18 11:09
 * @Description:
 */
interface Command{
    public void execute();
    public void undo();
}
class Light{
    String location;

    public Light(String location) {
        this.location = location;
    }

    public void on(){
        System.out.println(location+" 的灯开了");
    }
    public void off(){
        System.out.println(location+" 的灯关了");
    }

    @Override
    public String toString() {
        return "Light{" +
                "location='" + location + '\'' +
                '}';
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

    @Override
    public void undo() {
        light.off();
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

    @Override
    public void undo() {
        light.on();
    }
}

class Stereo{
    String cdName;
    String dvdName;
    String radioName;
    Integer volumength;
    public void on() {
        System.out.println("打开音响");
    }
    public void off() {
        System.out.println("关闭音响");
    }
    public void setCD(String name){
        this.cdName = name;
    }
    public void setDVD(String name){
        this.dvdName = name;
    }
    public void setRadio(String name){
        this.radioName = name;
    }
    public void setVolume(int i){
        this.volumength=i;
    }

    @Override
    public String toString() {
        return "Stereo{" +
                "cdName='" + cdName + '\'' +
                ", dvdName='" + dvdName + '\'' +
                ", radioName='" + radioName + '\'' +
                ", volumength=" + volumength +
                '}';
    }
}
class StereoCommand implements Command {
    Stereo stereo;
    @Override
    public void execute() {
        stereo.on();
        stereo.setCD("林志炫大合集");
        stereo.setVolume(2);
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
class NoCommand implements Command{

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
class RemoteController {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;


    public RemoteController() {
        onCommands=new Command[7];
        offCommands = new Command[7];
        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i]=noCommand;
            offCommands[i]=noCommand;
        }
        undoCommand=noCommand;
    }
    public void setCommand(int index,Command onCommand,Command offCommand){
        onCommands[index]=onCommand;
        offCommands[index] = offCommand;
    }
    public void onButtonWasPushed(int index){
        onCommands[index].execute();
        undoCommand=onCommands[index];
    }
    public void offButtonWasPushed(int index){
        offCommands[index].execute();
        undoCommand=offCommands[index];
    }

    @Override
    public String toString() {
        return "RemoteController{" +
                "onCommands=" + Arrays.toString(onCommands) +
                ", offCommands=" + Arrays.toString(offCommands) +
                '}';
    }
}
public class Text {
    public static void main(String[] args) {
        RemoteController remoteController = new RemoteController();

        Light light = new Light("客厅");
        remoteController.setCommand(0,new LightOnCommand(light),new LightOffCommand(light));
        remoteController.onButtonWasPushed(0);
        remoteController.offButtonWasPushed(0);
    }
}
