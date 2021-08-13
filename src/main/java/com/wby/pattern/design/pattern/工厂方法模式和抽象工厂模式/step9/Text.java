package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step9;

/**
 *  如何订购一个披萨:两个客户,一个想要纽约风味的披萨,一个想要芝加哥风味的披萨
 *      1. 取得披萨店实例,分别实例化 ChicagoPizzaStore 和 NYPizzaStore
 *      2. 调用orderPizza方法,传入披萨类型(芝士,素食,蛤蜊...)
 *      3. orderPizza()调用createPizza创建披萨.其中,ChicagoPizzaStore实例化芝加哥风味披萨,NYPizzaStore实例化纽约风味披萨,createPizza()
 *          会将创建好的披萨当做返回值返回
 *      4. orderPizza()并不知道真正创建的是哪一种披萨,只知道这是一个披萨,能够被准备\烘烤\切片\装盒,然后提供给两个客户
 */
public class Text {
}
