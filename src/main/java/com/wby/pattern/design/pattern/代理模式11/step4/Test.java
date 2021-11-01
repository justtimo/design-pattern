package com.wby.pattern.design.pattern.代理模式11.step4;

import java.lang.reflect.Method;

/**
 * 保护代理
 *
 * java的reflect有自己的代理支持, 利用这个包可以在运行时动态的创建代理类 , 实现一个或多个接口, 并将方法的调用转发到你所指定
 * 的类. 实际的代理类是在运行时创建的, 我们称之为: 动态代理
 *
 * 我们要利用动态代理创建我们的代理实现(保护代理) . 类图与代理模式的传统定义有一定出入
 */
interface Subject {
    void request();
}
class RealSubject implements Subject {
    @Override
    public void request() { }
}
//由java产生, 而且实现了完整的Subject接口
class Proxy implements Subject {
    @Override
    public void request() { }
}

/**
 * 代理现在包含interface InvocationHandle  和  class InvocationHandler
 */
interface InvocationHandle{ void invoke();}
//你提供class InvocationHandler ,Proxy上的任何方法调用都会被传入此类. class InvocationHandler控制对RealSubject的访问
class InvocationHandler implements java.lang.reflect.InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}

/**
 * java已经为你创建了Proxy类, 所以你需要告诉Proxy类你要做什么. 因为Proxy不是你直接实现的, 所以不能把代码放在Proxy类中 . 应该放在
 * class InvocationHandler类中, class InvocationHandler的工作是响应代理的任何调用. 你可以把class InvocationHandler想成是代理收到方法调用后,
 * 请求做实际工作的对象
 */


public class Test {
}





























