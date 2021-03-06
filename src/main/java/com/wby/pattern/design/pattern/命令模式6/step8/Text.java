package com.wby.pattern.design.pattern.命令模式6.step8;

/**
 * 命令模式的更多用途: 队列请求
 *      命令可以将运算块打包(一个接受者和一组动作),然后把他传来传去,向一般的对象一样.
 *      现在,即使命令对象创建很久,运算依然可以被调用.
 *      实际上,他甚至可以在不同的县城中被调用.利用此特性,可以实现:日程安排,线程池,工作队列等功能.
 *
 *      注意,工作队列类和进行计算的对象之间完全是解耦的.此刻线程可能在进行财务运算,下一刻却在读取网络数据.工作队列对象不在乎到底做些什么.
 *      只知道取出命令对象,然后调用其execute()方法.同样,他们只要是实现命令模式的对象,就可以放入队列,当线程可用时,就调用此对象的execute()方法
 *
 * 命令模式的更多用途: 日志请求
 *      通过新增两个方法(store(),load()),命令模式能够支持这一点.Java中,可以利用对象的序列化实现这些方法,但一般认为序列化最好还是用在对象持久化上
 *      怎么做呢?执行命令时,将历史记录存储在磁盘上,一旦死机,可以将命令对象重新加在,并成批次的依次调用这些对象的execute()方法.
 *      这种日志的方式对遥控器没有意义,然而,许多调用大型数据结构的动作的应用无法在每次改变发生时被快速地存储.通过使用记录日志,可以将上次检查点之后的
 *          所有操作记下来,系统发生意外就从检查点开始应用这些操作.
 *      例如:表格应用,可能想要实现的错误恢复方式是将表格的操作记录在日志中,而不是每次表格变化就记录整个表格.
 *          对于更高级的应用而言,这些技巧可以被扩展应用到事务处理中:一群操作必须全部完成,或者没有进行任何操作.
 */
public class Text {
}
