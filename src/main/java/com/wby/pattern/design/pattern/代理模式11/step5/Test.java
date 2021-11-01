package com.wby.pattern.design.pattern.代理模式11.step5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 实现对象村的约会服务, Hot表示喜欢, NOT表示不喜欢,
 */
//允许设置或得到一个人的信息:
interface Person {

    String getName();
    String getGender();
    String getInterests();
    int getGeekRating();

    void setName(String name);
    void setGender(String gender);
    void setInterests(String interests);
    void setGeekRating(int rating);

}
//
class PersonImpl implements Person {
    //实例变量
    String name;
    String gender;
    String interests;
    int rating;
    int ratingCount = 0;

    //getter()各自返回相应的实例变量
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getInterests() {
        return interests;
    }
    //计算rating的平均值
    public int getGeekRating() {
        if (ratingCount == 0) return 0;
        return (rating/ratingCount);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setGeekRating(int rating) {
        this.rating += rating;
        ratingCount++;
    }
}

/**
 * 根据我们定义的Person, 任何客户都可以调用任何方法 , 但是系统不应该允许用户修改别人的数据 .
 * 这是使用"保护代理"的绝佳例子 .保护代理: 根据访问权限决定客户可否访问对象的代理. 比如: 有个雇员,保护代理允许雇员调用对象上的某些方法,
 *      经理可以额外调用其他的方法, 人力则可以调用对象上的所有方法.
 *
 * 在这个约会服务中, 顾客可以修改自己的信息, 同时又防止他人更改这些信息. 评分则相反, 自己不能改,别人可以改.
 */

/**
 * 为Person创建动态打理
 *  修正问题: 顾客不能修改自己的评分, 也不能改变其他客人的个人信息.
 *  解决问题: 创建两个代理. 一个访问自己的Person对象, 另一个访问另一个顾客的Person对象. 这样代理就可以控制每种情况下允许哪种请求.
 *  步骤:
 *      1. 创建两个InvocationHandler
 *          InvocationHandle实现了代理的行为, java负责创建真实代理类和对象. 我们只需要提供方法调用发生时知道做什么的handler.
 *      2. 写代码创建动态代理
 *          编写代码产生代理类, 并实例化他们
 *      3. 利用适当的代理包装任何Person对象
 *          使用Person对象时, 如果不是顾客自己(拥有者),就是另一个顾客正在检查的服务使用这(非拥有者). 无论哪种 , 都要为Person创建合适的代理
 */

/**
 * 步骤一:
 *      编写两个InvocationHandler(调用处理器), 一个给拥有者, 一个给非拥有者 .
 *      什么是InvocationHandler ?当代理的方法被调用时, 代理会把调用转发给InvocationHandler, 但这并不是通过调用InvocationHandler的相应
 *          方法得到的. 工作流程:
 *              1. 假设proxy的setRating()方法被调用
 *                  proxy.setRating(9)  ---> InvocationHandler.invoke()
 *              2. proxy接着调用InvocationHandler的invoke()方法
 *                  InvocationHandler.invoke(Object proxy ,Method method ,Object[] args)
 *                      Object proxy: proxy
 *                      Method method: setRating
 *                      Object[] args: 9
 *                      --->调用RealSubject方法的代码
 *              3. handler决定如何处理这个请求, 可能会转发给RealSubject. 如何决定呢? 稍后揭晓
 *                  return method.invoke(person , args)
 *                      method: 调用原始proxy被调用的方法.这个对象在调用时被传给我我们.只不过加载调用的是真正的主题(person)
 *                      person: 被调用的对象
 *                      args: 使用原始的变量
 *
 *      继续创建InvocationHandler
 *          当proxy调用invoke()时 ,通常, 先检查方法是否来自proxy, 并基于该方法的名称和变量做决定.
 */
class MyInvocationHandler implements InvocationHandler {
    Person person;

    public MyInvocationHandler(Person person) {
        this.person = person;
    }

    /**
     * 每次proxy的方法被调用,就会导致proxy调用此方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().startsWith("get")){
            //如果方法是一个getter(),就调用person内的方法
            return method.invoke(person,args);
        } else if (method.getName().equals("setGeekRating")) {
            //如果是setGeekRating()方法, 不允许调用
            throw  new IllegalAccessException();
        } else if (method.getName().startsWith("set")) {
            //因为我们是拥有者,所以任何set方法都可以,我们就在主题上调用它
            return method.invoke(person, args);
        }
        //调用其他方法则不理会, 返回null
        return null;
    }
}
public class Test {
}

























