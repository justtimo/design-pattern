package com.wby.pattern.design.pattern.命令模式6.step3;

/**
 * 车库门
 */
class GarageDoor{
    public void up(){
        System.out.println("garage door up");
    }
    public void down(){
        System.out.println("garage door down");
    }
    public void stop(){
        System.out.println("garage door stop");
    }
    public void lightOn(){
        System.out.println("garage door lightOn");
    }
    public void lightOff(){
        System.out.println("garage door lightOff");
    }
}
interface Command{
    public void execute();
}
class GarageDoorCommand implements Command {
    GarageDoor garageDoor;

    public GarageDoorCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {

    }
}
public class Text {
}
