package com.wby.pattern.design.pattern.状态模式10.step2;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/10/29 10:39
 * @Description: 现在需要增加需求, 糖果机有10% 概率出两颗糖果
 *
 * 实现1: 新增状态值,名为'赢家' ; 投币,退币,转手柄,发糖果 的四个方法中都需要新增判断'赢家'状态的代码
 */

/**
 * Q: 新需求的出现, 导致老版本不再适用 ,打打增加了程序出BUG的几率
 * A; 我们可以尝试局部化每个状态的行为 ,如果针对某个状态做出改变,就不会把其他代码搞乱
 * Q: 即, 遵守 '封装变化' 原则
 * A: 将每个状态的行为都放在各自的类中, 每个状态只要实现自己的动作即可
 * Q: 糖果机只需要委托给代表当前状态的状态对象
 * A: 这不就是 '多用组合,少用继承' 吗?我们使用了更多的原则,太棒了!
 * Q: 这是否可以使添加新状态更容易呢?
 * A: 我认为是的. 我们将改变局限在了小范围内,新增状态,只需要加入新类还有可能要改变一些转换
 */

import lombok.Data;

/**
 * 新的设计
 *
 * 我们重写以便将状态对象封装在各自的类中, 然后再动作发生时委托给当前状态.
 * 实现方式:
 *      1. 首先,定义State接口.接口内 ,糖果机每个动作都有一个对应的方法
 *      2. 为每个状态实现状态类 .这些类负责在对应的状态下进行机器的行为
 *      3. 最后,将动作委托到状态类
 */
interface State{
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
@Data
class GunballMachine{
    // 所有的状态
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;

    State state = soldOutState;
    int count = 0;

    //初始化糖果机,传入初始糖果机的糖果数量
    public GunballMachine(int numberGumballs) {
        //每种状态都创建一个实例
        this.soldOutState = new SoldOutState(this);
        this.noQuarterState =new NoQuarterState(this);
        this.hasQuarterState =new HasQuarterState(this);
        this.soldState = new SoldState(this);
        this.winnerState = new WinnerState(this);
        this.count= numberGumballs;
        //糖果数量超过0 , 状态设置为待投币
        if (numberGumballs > 0){
            state = noQuarterState;
        }
    }
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
class SoldState implements State{
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
        gunballMachine.releaseBall();
        if (gunballMachine.getCount() > 0){
            gunballMachine.setState(gunballMachine.getNoQuarterState());
        }else {
            System.out.println("抱歉,糖果已卖完");
            gunballMachine.setState(gunballMachine.getSoldOutState());
        }
    }
}
class SoldOutState implements State {
    GunballMachine gunballMachine;

    public SoldOutState(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }

    @Override
    public void insertQuarter() {

    }

    @Override
    public void ejectQuarter() {

    }

    @Override
    public void turnCrank() {

    }

    @Override
    public void dispense() {

    }
}
class NoQuarterState implements State {
    GunballMachine gunballMachine;

    public NoQuarterState(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }

    //已投入钱币,改变及其状态到 已投币
    @Override
    public void insertQuarter() {
        System.out.println("您已投入钱币");
        gunballMachine.setState(gunballMachine.getHasQuarterState());
    }

    //如果没钱,就不能要求退款
    @Override
    public void ejectQuarter() {
        System.out.println("未投币,不能退款");
    }

    @Override
    public void turnCrank() {
        System.out.println("未投币,不能发放糖果");
    }

    @Override
    public void dispense() {
        System.out.println("没有得到前,不能发放糖果");
    }
}

/**
 * 已投币
 */
class HasQuarterState implements State {
    GunballMachine gunballMachine;

    public HasQuarterState(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("不能重复投币");
    }

    //退钱,并将状态改变成未投币状态
    @Override
    public void ejectQuarter() {
        System.out.println("已退回货币");
        gunballMachine.setState(gunballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("你转动了手柄");
        gunballMachine.setState(gunballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println(" NO gumball dispense");
    }
}
class WinnerState implements State {
    GunballMachine gunballMachine;

    public WinnerState(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }

    @Override
    public void insertQuarter() {

    }

    @Override
    public void ejectQuarter() {

    }

    @Override
    public void turnCrank() {

    }

    @Override
    public void dispense() {

    }
}

/**
 * 状态模式: 允许对象在内部状态改变时改变他的行为,对象看起来好像修改了他的类
 *  第一句话: 此模式将状态封装成为独立的类,并将动作委托到代表当前状态的对象, 行为会随着内部状态而改变.
 *      例如,在未投币和已投币状态下, 投币后,就会得到不同的行为(接受投币 或 拒绝投币)
 *  第二句话: 从客户角度看,若使用的对象能够完全改变他的行为,那会觉得,这个对象是从别的类实例化来的.然而实际上,我们是使用组合通过引用
 *      不同的状态对象来造成类改变的假象.
 *
 *  见下例类图
 */

/**
 * state接口定义了所有具体状态的具体接口,任何状态都实现这个相同的接口,这样,状态之间可以互相替换
 */
interface State1{}

/**
 * 具体状态 来处理Context的请求,每个具体状态都提供了他自己对于请求的实现. 当Context改变状态时行为也跟着改变
 */
class ConcreteStateA implements State1 { }
class ConcreteStateB implements State1 { }

/**
 * 就像 糖果机 .Context可以拥有一些内部状态
 * 无论何时,只要调用COntext的request(0方法 ,他就会被委托到状态来处理
 */
class Context{
    State1 state1;
    void  request(){
        //state1.handle();
    }
}

/**
 * Q: 稍等! 在我看来,策略模式和这张类图根本是一模一样
 * A: 是的,类图是一样的, 但是两个模式的差别在于他们的 "意图"
 *      状态模式: 将一群行为封装在状态对象中,context的行为委托到状态对象中的一个 .随时间不同,状态在状态对象集合中游走改变,
 *      以此反应出context内部的状态. 因此,context的行为也会跟着改变. 但是context的客户对状态对象可以说浑然不觉
 *
 *      策略模式: 客户通常主动指定context 所需要的组合的策略对象是哪个. 策略模式虽然让我们具有弹性, 能够在运行时改变策略,
 *      但对于某个context对象来说,通常只有一个最适当的策略对象. 例如,第一张, 有些鸭子(绿头鸭)被这是为利用典型的飞行行为进行
 *      飞翔 , 而有些鸭子(橡皮鸭和诱饵鸭)使用的飞翔行为只能让他们紧贴地面
 *
 *      一般来说, 我们吧策略模式 想成是除了继承之外的一种弹性替代方案. 如果使用继承定义了一个类的行为,你将被这个行为困住,甚至
 *      修改它都很难. 而策略模式,让你通过组合不同的对象来改变行为.
 *      另外来说, 我们把状态模式想成是不用在context中放置许多条件判断的替代方案. 通过将行为包装进状态对象中,你可以通过在context
 *      内简单的改变状态对象来改变context的行为.
 * Q: Context中状态决定下一个状态应该是什么 . ConcreteState总是决定接下来的状态是什么吗?
 * A: 并非总是如此. Context也可以决定状态转换的流向.
 *      一般, 状态转换是固定的时候,适合放在Context中 ; 然而, 当状态转换是动态的,通常应该放在状态类中(例如.,糖果机由运行时糖果的
 *          数目决定转换到NoQuarter 还是 SoldQuarter).
 *      将状态转换放在状态类中的缺点是: 状态类之间产生了依赖 . 在糖果机实现中, 我们使用getter方法把依赖减少到最下, 而不是显式硬编码
 *          具体状态类.
 *      注意, 做这个决定时,也等于在为另一件事做决定: 当系统进化时, 究竟哪个类是对修改封闭(Context 还是 状态类)的.
 * Q: 客户会直接和状态交互吗?
 * A: 不会.装填使用在COntext中代表他们内部状态以及行为的 ,所以只有Context才会对状态提出请求 .客户不会直接改变Context的状态. 了解状态
 *      是Context的工作, 客户根本不了解,所以不会直接和状态联系
 * Q; 如果Context有很多实例,这些实例之间可以共享状态对象吗?
 * A: 完全可以. 实际上,这是很常见的做法. 但是前提是, 状态对象不能持有他们自己的内部状态,否则就不能共享
 *      想要共享状态 , 需要把每个状态都指定到静态的实例变量中. 如果你的状态需要利用Context中的方法或者实例变量, 你必须在每个handle()方法
 *      内传入一个context的引用.
 * Q: 使用状态模式似乎总是增加我们设计中类的数目
 * A: 没错.在个别状态类中封装状态行为,结果总是增加这个设计中的类的数目.这就是弹性的代价. 其实重要的是暴露给客户的类数目 ,而我们有办法将
 *      这些额外的状态类全部隐藏起来.
 *      看看另一种做法: 如果一个应用有很多状态,但是不将这些状态不分装在不同的对象中,那么得到的会是巨大,整块的条件语句. 这使得维护和理解困难.
 *          而使用许多对象, 可以让状态变得很干净,理解和维护他们也可以更方便
 * Q: 类图显示State是一个抽象类, 而不是一个接口
 * A: 如果没有共同的功能可以放进抽象类中, 就会使用接口. 实现状态模式时 ,很可能想使用抽象类,这样当你需要在抽象类中加入新的方法时就很容易,不需要
 *      打破具体状态的实现.
 */
public class Test {
}
