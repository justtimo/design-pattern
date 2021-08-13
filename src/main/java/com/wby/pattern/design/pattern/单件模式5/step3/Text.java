package com.wby.pattern.design.pattern.单件模式5.step3;

/**
 * 定义单件模式
 *      单件模式:确保一个类只有一个类别,并提供一个全局访问点
 *  深入研究:
 *      1. 我们把某个类设计成自己管理的一个单独实例,同时也避免其他类再自行产生实例.要取得单件实例,通过单件类是唯一的途径
 *      2. 提供了实例的全局访问点:当你需要时,向类查询,他会返回单个实例.前面的例子利用延迟实例化的方式创建单间,这种做法对资源敏感的对象很重要.
 *      3. static的类变量持有唯一的单件实例
 *      4. getInstance()是静态的,是一个类方法,可在代码的任何地方使用  类名.getInstance()访问它.这和访问全局变量一样简单,只是多了个有点:单件
 *         可以延迟实例化
 *      5. 单件模式的类也可以是一般的类,具有一般的数据和方法.
 */
public class Text {
}
/** `
 * 但是当加入多线程后,fill()方法竟然允许在加热过程中继续加入原料,导致原料溢出.
 * 加入多线程就会造成这种情况吗?不是只要为ChocolateBoilerSingleton设置单件,所有getInstance()调用都会获得相同的实例吗?
 *
 * 只要把getInstance()变成同步方法,多线程遭难几乎就可以轻易解决了
 */
class ChocolateBoilerSingleton {
    private Boolean empty;
    private Boolean boiled;
    private static ChocolateBoilerSingleton chocolateBoilerSingleton;

    private ChocolateBoilerSingleton(){
        empty = true;
        boiled=false;
    }
    //加入synchronized,每个线程在进入这个方法之前,斗殴必须等候别的线程离开该方法,不会有两个现场曾可以同时进入这个方法
    public static synchronized ChocolateBoilerSingleton getInstance(){
        if (chocolateBoilerSingleton == null){
            chocolateBoilerSingleton = new ChocolateBoilerSingleton();
        }
        return chocolateBoilerSingleton;
    }

    public void fill(){
        if (isEmpty()) {
            empty=false;
            boiled = false;
        }
    }
    public void drain(){
        if (!isEmpty() && isBoiled()){
            empty = true;
        }
    }
    public void boil(){
        if (!isEmpty() && !isBoiled()){
            boiled = false;
        }
    }

    public boolean isEmpty(){
        return empty;
    }
    public boolean isBoiled(){
        return boiled;
    }
}
/** `
 * Q: 同步不是会降低性能吗?这又是另外一个问题!
 * A: 是的,而且会更严重:只有第一次执行该方法,才需要同步.之后每次调用该方法,同步都是一种累赘
 * Q: 能改善多线程吗?
 * A: 很明显,我门需要确保单件模式在多线程下正常工作,但同步getInstance的做法将拖垮性能,怎么办?
 *      一般有以下的选择:
 *          1.如果getInstance()的性能对程序不关键,就什么都别做
 *              如果程序能接受getInstance的负担,就什么都不做.因为这种方式简单有效,但是必须知道,同步一个方法可能造成程序效率降低
 *              100倍.因此,如果将getInstance()使用在频繁运行的地方,就需要重新考虑了.
 *          2. 使用"急切"创建实例,而不用延迟实例化的做法
 *              如果程序总是创建并使用单件实例,或者在创建和运行时方面的负担不太繁重,可以急切创建此单件
 *              如下所示
 */
class Singleton{
    //在静态初始化器中创建单件.这段代码保证了线程安全
    private static Singleton singleton = new Singleton();
    public Singleton() {
    }
    public static Singleton getInstance() {
        //已经有实例了,直接使用它
        return singleton;
    }
}
/**
 * 利用这个做法,JVM在加载这个类时会立即创建此唯一的单件实例.JVM保证在任何线程访问singleton静态变量之前,一定先创建此实例
 *      3. 用"双重检查加锁",在getInstance()中减少使用同步
 *          利用双重检查加锁,首先检查实例是否已经创建,如果未创建,才进行同步.
 *          这样,就只有第一次才会同步.
 */
class DoubleCheckSingleton{
    //volatile关键字确保,当singleton变量被初始化成DoubleCheckSingleton时,多个线程正确的处理singleton变量
    private volatile static DoubleCheckSingleton singleton;

    public DoubleCheckSingleton() {
    }
    public static DoubleCheckSingleton getInstance() {
        //检查实例,如果不存在,就进入同步区块
        if (singleton == null){
            //注意,只有第一次才彻底执行这里的代码
            synchronized (DoubleCheckSingleton.class){
                //进入区块后,再检查一次,如果仍然是null,才创建实例
                if (singleton == null){
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
/**
 * 如果性能是重点,那么这个做法可以帮你大大减少getInstance()的时间耗费
 */



















