package com.wby.pattern.design.pattern.状态模式10.step3;

import lombok.Data;

import java.util.Random;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/10/29 15:10
 * @Description: 添加十次抽中一次
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
    Random randomwinner = new Random(System.currentTimeMillis());

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

    /**
     * 这里实现 winner的触发
     */
    @Override
    public void turnCrank() {
        System.out.println("你转动了手柄");
        gunballMachine.setState(gunballMachine.getSoldState());
        int winner = randomwinner.nextInt(10);
        if (winner == 0 && gunballMachine.getCount() > 1) {
            gunballMachine.setState(gunballMachine.getWinnerState());
        }else {
            gunballMachine.setState(gunballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println(" NO gumball dispense");
    }
}

/**
 * 十次 翻倍糖果
 */
class WinnerState implements State {
    GunballMachine gunballMachine;

    public WinnerState(GunballMachine gunballMachine) {
        this.gunballMachine = gunballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("十次翻倍中,不能投币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("十次翻倍中,不能退币");
    }

    @Override
    public void turnCrank() {
        System.out.println("十次翻倍中,不能转手柄");
    }

    @Override
    public void dispense() {
        System.out.println("你是winner , 你将获得两个糖果");
        gunballMachine.releaseBall();
        if (gunballMachine.getCount() == 0){
            gunballMachine.setState(gunballMachine.getSoldOutState());
        }else {
            gunballMachine.releaseBall();
            if (gunballMachine.getCount() > 0){
                gunballMachine.setState(gunballMachine.getNoQuarterState());
            }else {
                System.out.println("没糖果了");
                gunballMachine.setState(gunballMachine.getSoldOutState());
            }
        }
    }
}
public class Test {
    public static void main(String[] args) {
        GunballMachine gunballMachine = new GunballMachine(100);

        gunballMachine.insertQuarter();
        gunballMachine.turnCrank();
        System.out.println(gunballMachine);

        for (int i = 0; i < 10; i++) {
            gunballMachine.insertQuarter();
            gunballMachine.turnCrank();
        }

        System.out.println(gunballMachine);
    }
}

/**
 * Q: 为什么需要WinnerState ? 为什么不直接在SoldState中发放两颗糖果
 * A: 当然可以将发放两颗糖果放在SoldState中 ,但是优缺点: 你将两个状态用一个状态类来表示, 牺牲了状态类的清晰易懂来减少冗余代码.
 *      而且你也应该考虑到" 一个类, 一个责任,即单一责任原则" .
 *      当活动结束或者活动的概率改变时,你又该怎么办呢?
 */
