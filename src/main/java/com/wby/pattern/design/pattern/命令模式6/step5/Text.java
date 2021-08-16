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
    Command[] onCommand;
    Command[] offCommand;

    //构造器中,只需实例化并初始化这两个开与关数组
    public RemoteController() {
        onCommand=new Command[7];
        offCommand = new Command[7];

        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 7;i++){
            onCommand[i]=noCommand;
            offCommand[i] = noCommand;
        }
    }

    public void setCommand(int slot,Command OnCommand,Command offCommand){

    }
}

interface Command{
    public void execute();
}

class NoCommand implements Command {
    public void execute() { }
    public void undo() { }
}

public class Text {
}
