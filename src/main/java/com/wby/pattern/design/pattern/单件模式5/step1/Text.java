package com.wby.pattern.design.pattern.单件模式5.step1;

/**
 * 单件模式(单例模式):用来创建独一无二的,只能有一个实例的对象的入场券
 */

/** `
 * Q: 什么用处
 * A: 有些对象我们只需要一个,比如:线程池,缓存,对话框....这些对象只能有一个实例,如果有多个,会导致许多问题
 * Q:难道不能靠程序员之间的约定或者利用全局变量做到吗?比如,利用java静态变量
 * A: 单件模式是经得起时间考验的办法,可以确保只有一个实例会被创建.也给了我们一个全局的访问点,和全局变量一样方便,有没有全局变量的缺点
 * Q: 什么缺点?
 * A: 举个例子: 如果将对象赋值给一个全局变量,那么你必须在程序一开始就创建好他(某些实现是用到的时候才创建对象).
 *      如果这个对象很耗费资源,而程序这次执行过程中又没有用到它,就形成了浪费.
 *      利用单件模式,可以在需要时才创建对象
 * Q: 可我还是觉得没什么困难的
 * A: 利用静态变量,静态方法和适当的访问修饰符,确实可以做到这一点.
 *      单件模式听起来简单,但是如何保证一个对象只能被实例化一次?
 */

/** `
 * class MyClass{
 *     private MyClass() {}
 * }
 * A: 虽然没想过,但这是一个合法的定义,有一定的道理:含有私有的构造起的类不能被实例化,除了他自己.
 *      但这又不太合理,因为必须有MyClass类的实例才能调用MyClass的构造器,但是因为么有其他类能够实例化MyClass,所以得不到这样的实例.
 *      就形成了"鸡生蛋,还是蛋生鸡"的问题
 * Q: 如果这样呢?
 *      class MyClass{
 *          public static MyClass getInstance() {}
 *      }
 * A: 可以这样调用: MyClass.getInstance()
 * Q: 为什么使用类名而不是对象名
 * A: 因为他是静态方法,是一个类方法,引用一个静态方法只需要使用类名即可
 * Q: 如果把他们结合起来呢?是否就可以初始化一个MyClass?
 *      class MyClass{
 *          private MyClass() {}
 *          public static MyClass getInstance() {return new MyClass();}
 *      }
 * A: 当然可以.
 * Q: 除了使用new关键字,你还有第二种实例化对象的方式吗?
 * A: MyClass.getInstance();
 * Q: 可以使MyClass只有一个实例被产生吗?
 * A: 见下面的代码
 */
class MySingleton{
    //使用静态变量记录类的唯一实例
    private static MySingleton mySingleton;
    //吧构造器声明为私有的,只有自Singleton类内才可以调用构造器
    private MySingleton() {}
    //getInstance()实例化对象,并返回这个实例
    public static MySingleton getInstance() {
        //mySingleton拥有一个实例,如果他是空的则表示还没创建实例
        if (mySingleton == null) {
            //不存在就利用私有的构造器产生一个实例,并赋值给mySingleton静态变量.注意,如果不需要这个实例,则永远不会产生,这就是"延迟实例化"
            mySingleton = new MySingleton();
        }
        //这里表示我们已经有了实例,并将mySingleton当成返回值
        return mySingleton;
    }
    //其他方法
}

/** `
 * A: 我是独一无二的,这个模式保证任何时刻只有一个对象
 * Q: 不会浪费吗?毕竟花了时间写了代码,竟然只产生一个对象?
 * A: "一个"的威力也很大,比如:注册表设置的对象,你并不会希望这样的对象有多个拷贝,那样会把设置高的一团糟
 *      我常常被用来管理共享的资源,例如数据库连接池或者线程池
 */
public class Text {
}





























