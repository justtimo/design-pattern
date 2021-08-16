package com.wby.pattern.design.pattern.命令模式6.step2;


class Light{
    public void on(){
        System.out.println("light is on");
    }
    public void off(){}
}
/**
 * 1. 实现命令接口
 *      让所有的命令对象实现相同的包含一个方法的接口.
 *      餐厅的例子中,我们称此方法为orderUp().现在改为管用的execute()
 */
interface Command{
    public void execute();
}

/**
 * 2. 实现打开点灯的命令
 *      根据厂商提供的类,Light有两个方法:on(),off()
 */
class LightCommand implements Command {
    Light light;

    /**
     * 构造器传入某个电灯(比如客厅电灯),以便让这个命令控制,然后记录在实例变量中,一旦调用execute()就由这个电灯对象成为接受者,负责接受请求
     */
    public LightCommand(Light light) {
        this.light = light;
    }

    /**
     * 调用接收对象(我们正在控制的电灯)的on()方法
     */
    @Override
    public void execute() {
        light.on();
    }
}

/**
 * 3. 使用命令对象
 *      假设有个遥控器,他只有一个按钮和对应的插槽,可以控制一个装置
 */
class SimpleRemoteController{
    //有一个插槽持有命令,控制着一个装置
    Command slot;

    //用来设置插槽控制的命令.如果客户想要改变遥控器按钮的行为,可以多次调用这个方法
    public void setCommand(Command cmd){
        slot = cmd;
    }
    //按下按钮,使得命令链接插槽,并调用他的execute()方法
    public void buttonWasPressed(){
        slot.execute();
    }
}

/**
 * 4. 遥控器的简单测试
 *      Text是命令模式的客户
 */
public class Text {
    public static void main(String[] args) {
        //遥控器就是调用者,传入一个命令对象,可以发出请求
        SimpleRemoteController simpleRemoteController = new SimpleRemoteController();
        //电灯对象,此对象是命令的接受者
        Light light = new Light();
        //创建命令,然后将接受者传递给他
        LightCommand lightCommand = new LightCommand(light);
        //把命令传给调用者
        simpleRemoteController.setCommand(lightCommand);
        //模拟按下按钮
        simpleRemoteController.buttonWasPressed();
    }
}

























