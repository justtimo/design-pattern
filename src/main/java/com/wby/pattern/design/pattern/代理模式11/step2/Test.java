package com.wby.pattern.design.pattern.代理模式11.step2;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/10/29 18:30
 * @Description: 代理模式: 为另一个对象提供一个替身或占位符以控制对这个对象的访问.
 * 使用代理模式创建代表对象,让代表对象控制某对象的访问, 被代理的对象可以是远程对象,创建开销大的对象或需要安全控制的对象
 *
 * 代理控制访问:
 *  代理控制了对远程对象的访问,代理之所以需要控制访问,是因为客户(监视器)不知道如何和远程对象沟通.
 *  从某方面看,远程代理控制访问, 好帮我们处理网络上的细节.代理模式许多变体,而这些变体几乎都和"控制访问"的做法有关.
 *  代理控制访问的方式:
 *      1. 远程代理控制访问远程对象
 *      2. 虚拟代理控制访问创建开销大的资源
 *      3. 保护代理基于权限控制对资源的访问
 *
 * 类图:
 *  1.Subject ,他为RealSubject和Proxy提供了接口. 通过实现统一接口, Proxy在RealSubject出现的地方取代他
 *      RealSubject是真正做事的对象,他是被proxy代理和控制访问的对象.
 *  2. Proxy持有RealSubject的引用 .Proxy还会负责RealSubject对象的创建于销毁.
 */

/**
 * 允许任何客户都可以向处理RealSubject一样处理proxy对象
 */
interface Subject{}

/**
 * 持有Subject引用, 所以必要时他可以将请求转发给Subject
 */
class Proxy implements Subject{}

/**
 * RealSubject通常是真正做事的对象,proxy会控制对RealSubject的访问
 */
class RealSubject implements Subject { }


public class Test {
}






















