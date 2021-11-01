package com.wby.pattern.design.pattern.代理模式11.step1;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/10/29 15:46
 * @Description: 代理做的事情: 控制和管理访问.
 * 代理方式有多种: 代理以通过Internet为他们的代理对象搬运的整个方法调用,也可以代替某些懒惰的对象做一些事情
 */

import lombok.Data;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 新需求: 糖果公司CEO向获得糖果机的库存和及其状态的报告
 *
 * 分析:
 *      我们可以取得糖果数量getCount() 和 取得状态的getState()方法, 我们可能需要为每个糖果机添加一个位置字段
 */
interface State extends Serializable {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}

/**
 * 为GunballMachine添加处理位置的支持
 */
@Data
class GunballMachine{
    State soldState;

    //1. 添加位置
    String location;

    State state = soldState;
    int count = 0;

    public GunballMachine(String location,int numberGumballs) {
        //2. 初始化位置信息
        this.location = location;

        this.soldState = new SoldState(this);
        this.count= numberGumballs;
        if (numberGumballs > 0){
            state = soldState;
        }
    }
    //3. 添加location 的 getter(0方法 ,已通过@Data注解实现

    public void insertQuarter(){
        state.insertQuarter();
    }
    public void ejectQuarter() {
        state.ejectQuarter();

    }
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }
    void releaseBall(){
        System.out.println("释放糖果");
        if (count != 0){
            count = count-1;
        }
    }
}
/**
 * 出售糖果
 */
class SoldState implements State {
    GunballMachine gunballMachine;

    public SoldState(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("稍等,我们已经准备给你糖果了");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("抱歉,不能退款,因为你已经转动了手柄");
    }

    @Override
    public void turnCrank() {
        System.out.println("抱歉,转动两次不能额外给你糖果");
    }

    @Override
    public void dispense() {
    }
}
class GumballMonitor{
    GunballMachine gunballMachine;

    //此监视器的构造器需要被传入糖果机, 他会将糖果机记录在machine实例变量中
    public GumballMonitor(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }
    //打印方法
    public void report(){
        System.out.println(" Gumball Machi: "+ gunballMachine);
        System.out.println("Current inventory : "+ gunballMachine.getCount() + "gumballs");
        System.out.println("Current state : "+ gunballMachine.getState() );
    }
}

public class Test {
    public static void main(String[] args) {
        int count = 0;
        if (args.length > 2){
            System.out.println("GunballMachine <name> <inventory>");
            System.exit(1);
        }

        count = Integer.parseInt("2");
        GunballMachine gunballMachine = new GunballMachine("args[0]", count);

        GumballMonitor gumballMonitor = new GumballMonitor(gunballMachine);
        gumballMonitor.report();


    }
}
/**
 * 需求变化: 需要的是远程监控糖果机
 *
 * Q: 远程代理 . 现在我们给GumballMonitor一个糖果机引用 ,然后他给我们一份报告. 问题是监视器和糖果机在同一个JVM上
 *      运行, CEO希望他的桌面上监控这些机器. 所以我们可以不变化GumballMonitor ,不要将糖果机交给GumballMonitor,
 *      而是把远程对象的代理交给他
 * A: 所谓代理 , 就是代表某个真实的对象 . 这个例子中, 代理就像是糖果机对象一样, 但其实幕后是他利用网络和一个远程的真正糖果机沟通
 * Q: 意思是, 不需要改我们的代码, 只要将GunballMachine的代理版本交给 GumballMonitor监视器就可以了 ; 然后这个代理假装他是真正的
 *      对象,但是其实一切的动作是他利用讴歌网络和真正的对象沟通
 * A; 我们必须确定糖果机能够通过网络接受请求并且提供服务 ; 我们也需要让监视器有办法取得代理对象的引用. 这方面java已经有一些内置工具
 *      可以帮助我们
 */

/**
 * 远程代理的角色
 * 远程代理好比"远程对象的本地代表". "远程对象"是一种对象,活在不同的java虚拟机堆中(更一般的说法是, 在不同的地址空间运行的远程对象).
 *      本地代表: 一种可以由本地方法调用的对象, 其行为会转发到远程对象中.
 *
 * RMI
 * 想要将请求转发到远程对象上, 我们需要一个辅助对象. 这些辅助对象使客户就像在调用本地对象的方法一样.
 * 提供客户辅助对象和服务辅助对象,为客户辅助对象创建 与服务对象相同的方法.
 * RMI好处在于你不必写任何网络或I\O代码. RMI也提供了所有运行时的基础设施, 包括查找服务,这个服务用来寻找和访问远程对象.
 * RMI将客户辅助对象成为stup(桩) ,服务辅助对象成为skeleton(骨架)
 *
 * 流程:
 *      客户对象: 以为在和真正的服务沟通,以为客户扶助对象就是能够真正做事情的东西
 *      客户辅助对象: 代理. 假装自己就是服务,但其实他只是"真东西"的一个代理
 *      服务辅助对象: 他从客户辅助对象出取得请求,对他解包 , 调用真正服务上的方法
 *      服务对象: 真正提供服务的地方, 他的方法真正在做事情
 *
 * 制作远程服务步骤:
 *      1. 制作远程接口: 定义让客户远程调用的方法.客户用它作为服务的类类型. Stub和实际的服务都实现此接口
 *      2. 制作远程的实现: 实际工作的类. 为远程接口中定义的远程方法提供了真正的实现. 这就是客户真正想要调用方法的对象(GunballMachine)
 *      3. 利用rmic产生的stub和skeleton: 客户和服务辅助类. 无需创建,当你运行rmic工具时,都会自动处理.
 *      4. 启动RMI registry: 像电话簿, 客户可以从中查到代理的位置(也就是客户的stub helper对象)
 *      5. 开始远程服务: 让服务对象开始运行. 服务实现类会去实例化一个服务的实例,并将这个服务注册到RMI registry.
 *              注册后,服务就可以供客户调用了
 */


/**
 * 服务端完整代码
 */
//表示此接口要用来支持远程调用
interface MyRemote extends Remote {
    //每次远程方法调用都会有网络IO风险
    //远程方法的变量和返回值必须属于原语类型 或 Serializable类型.
    public String sayHello() throws RemoteException;
}
class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    //设计一个无参构造器,并声明异常: 因为超类构造器抛出异常, 如果超类抛出异常,那么子类构造器也抛出异常
    protected MyRemoteImpl() throws RemoteException {
        MyRemote service = new MyRemoteImpl();
        try {
            //为服务命名,用来让客户在注册表中寻找. 并在RMI registry中注册此名字和服务.
            //当绑定(bind)服务对象时 ,RMI会把服务换成stub ,然后把stub放到registry中
            Naming.rebind("RemoteHello", service);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //
    @Override
    public String sayHello() throws RemoteException {
        return null;
    }
}
/**
 * 3. 产生stub和skeleton .
 *      在远程实现类上执行rmic : rmic MyRemoteImpl .这里不用pakage,实际中,需要注意package的目录结构和名称问题.
 * 4. 执行 remiregistry ,开启终端,启动rmiregistry: 确定启动目录必须可以访问你的类: 最简单的办法是从"classes"目录启动
 *      rmiregistry
 * 5. 启动服务: 开启另一个终端,启动服务.
 *      java MyRemoteImpl
 */


/**
 * 客户端代码
 */
class MyRemoteClient {
    public static void main(String[] args) {

    }
    public void go(){
        try {
            //locahost: IP地址 或主机名
            //RemoteHello: 服务被绑定/重绑定时用的名称
            MyRemote service = (MyRemote) Naming.lookup("rmi://ocalhost/RemoteHello");
            //看起来和一般的老式方法调用没有区别
            String s = service.sayHello();

            System.out.println(s);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 客户如何取得stub类
 * 客户做lookup是必须有stub类(利用rmic产生的) ,否则客户端无法被反序列化. 客户端也需要调用远程对象方法所返回的序列化对象的类.
 * 还有一个方式: 动态类下载
 *
 *
 * 注意点:
 *      1. 忘了在启动远程服务前先启动rmiregistry: 用Naming.rebind()注册服务,rmiregistry必须是运行的
 *      2. 忘记让变量和返回值成为可序列化的类型
 *      3. 忘记给客户提供stub类
 */

interface GumballMachineRemote extends Remote {
    public int getCount() ;
    public String getLocation();
    public State getState(); // 需要将State序列化
}

class SoldState1 implements State {
    //transient: 不要序列化这个字段
    transient GunballMachine gunballMachine;

    public SoldState1(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("稍等,我们已经准备给你糖果了");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("抱歉,不能退款,因为你已经转动了手柄");
    }

    @Override
    public void turnCrank() {
        System.out.println("抱歉,转动两次不能额外给你糖果");
    }

    @Override
    public void dispense() {
    }
}
@Data
class GunballMachine1 extends UnicastRemoteObject implements GumballMachineRemote{
    State soldState;

    //1. 添加位置
    String location;

    State state = soldState;
    int count = 0;

    protected GunballMachine1(String location,int numberGumballs) throws RemoteException {
    }
    //其他代码不变
}

/**
 * RMI registry中注册
 */
class GumballMachineTestDrive{
    public static void main(String[] args) {
        GumballMachineRemote gumballMachineRemote=null;

        try {
            gumballMachineRemote = new GunballMachine1("111",1);
            Naming.rebind("//xxx/gumallmachine" , gumballMachineRemote);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}

/**
 * GumballMonitor1客户端
 */
class GumballMonitor1{
    GumballMachineRemote gunballMachine;

    //此监视器的构造器需要被传入糖果机, 他会将糖果机记录在machine实例变量中
    public GumballMonitor1(GumballMachineRemote gunballMachine) {
        this.gunballMachine = gunballMachine;
    }
    //打印方法
    public void report(){
        System.out.println(" Gumball Machi: "+ gunballMachine);
        System.out.println("Current inventory : "+ gunballMachine.getCount() + "gumballs");
        System.out.println("Current state : "+ gunballMachine.getState() );
    }
}

