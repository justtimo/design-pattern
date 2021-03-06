package com.wby.pattern.design.pattern.策略设计模式1.step7;

/**
 * 来看看整体布局
 *
 * 鸭子继承DUck,飞行行为实现FlyBehavior,呱呱叫行为实现Quackbehavior接口.
 * 注意,我们不在把鸭子的行为说成"一组行为",我们开始把行为想成是"一组算法".
 * 想想看,在SimUDuck的设计中,算法代表鸭子能做的事(不同的叫法和飞行),这样的做法也能用于用一群类计算不同州的销售税金
 *
 * 请注意类之间的关系.
 *  客户:DUck,MallardDuck,GreenHeadDuck,RedHeadDuck.DecoyDuck
 *  封装飞行行为: FlyBehavior接口,FlyWithWings\FlyNoWay类
 *  封装呱呱叫行为:Quackbehavior接口,Quack,Squeak,MuteQuack:这三个算法是可以互换的
 *  封装飞行行为  和  封装呱呱叫行为  :每套行为 想象成一个算法族
 *
 */
public class step7 {
}
/**
 * 有一个 可能比 是一个 更好
 * 如同本例子,酱两个类结合起来使用,就是组合.
 * 这种做法和继承不同的地方在于,鸭子的行为不是继承而来的,而是和适当的行为对象组合而来
 *
 * 这就是设计原则3.
 * 使用组合建立的系统有很大的弹性,不仅可以将算法族封装成类,还可以"在运行时动态的改变行为",只要组合的行为对象,符合接口标准即可
 *
 * 组合用在许多设计模式中,后续会慢慢见到
 */
/**
 * 策略模式:定义了算法族,分别封装起来,让他们之间可以互相替换,此模式让算法的变化独立于使用算法的客户
 */