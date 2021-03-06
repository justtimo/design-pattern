package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step11;

/**
 * 所有工厂模式都用来封装对象的创建.
 * 工厂方法模式:通过让子类决定该创建的对象是什么,来达到将对象创建的过程封装的目的.
 * 让我们看看这些类图,以便了解有哪些组成元素:
 *
 * 创建者类:
 *                                  PizzaStore:
 *                                  这是抽象创建者类,定义了一个抽象的工厂方法,让子类实现此方法制造产品
 *                                  创建者通常包含依赖于抽象产品的代码,而这些抽象产品由子类制造.创建者不需要知道在制造那种具体产品
 *          NYPizzaStore:createPizza()方法正是工厂方法,用来制造产品
 *          ChicagoPizzaStore:因为每个加盟店都有自己的PizzaStore子类,所以可以利用实现createPizza方法创建自己风味的披萨
 *          以上两种能够产生产品的类称为:具体创建者
 *
 * 产品类:
 *                                  Pizza
 *                                  工厂生产产品,对PizzaStore来说,产品就是Pizza
 *                 NYStyleCheesePizza
 *                 NYStylePepperoniPizza
 *                 NYStyleClamPizza
 *                 NYStyleVeggiePizza
 *                 ChicagoStyleCheesePizza
 *                 ChicagoStylePepperoniPizza
 *                 ChicagoStyleClamPizza
 *                 ChicagoStyleVeggiePizza
 *                 这是具体的产品,所有店里能实际制造的披萨都在这里
 */
public class Text {
}
/** `
 * 我们可以看到,将一个orderPizza()方法和一个工厂方法联合起来,就可以称为一个框架.除此之外,工厂方法将生产知识封装进各个创建者,这样的做法,也可以被看做一个框架
 *
 * 让我们看看这两个平行的类层级,并认清他们的关系:
 *      产品类                                                                                                       创建者类
 *      Pizza       请注意这两个类层级为什么是平行的,因为他们都有抽象类,而抽象类都偶有许多具体子类,每个子类都有自己特定的实现          PizzaStore
 *      NYStyleCheesePizza                                                                                          NYPizzaStore
 *      NYStylePepperoniPizza                                                                                       它封装的是如何制造纽约风味的披萨
 *      NYStyleClamPizza
 *      NYStyleVeggiePizza
 *      ChicagoStyleCheesePizza                                                                                     ChicagoPizzaStore
 *      ChicagoStylePepperoniPizza                                                                                  封装的是如何制造芝加哥风味的披萨
 *      ChicagoStyleClamPizza
 *      ChicagoStyleVeggiePizza
 *
 */

/** `
 * 定义工厂方法模式
 *      定义:定义了 一个创建对象的接口,但由子类决定要实例化的类是哪一个.工厂方法让类把实例化推迟到子类
 * 工厂方法模式能够封装具体类型的实例化.看看下面的类图,抽象的Creator提供了创建对象的方法的接口,也称为"工厂方法".
 * 在抽象的Creator中,任何其他实现的方法,都可能使用到这个工厂方法制造出来的产品,但只有子类真正实现这个工厂方法并创建产品.
 *
 * 常常会听到:工厂方法让子类决定要实例化的类是哪一个.
 * 所谓的"决定",并不是指模式允许子类本身在运行时做决定,而是指在编写创建者类时,不需要知道世纪创建的产品是哪一个.选择使用哪个子类,就决定了实际创建的产品是什么.
 *
 *          Product                                                     Creator:factoryMethod()  anOperation()
 *   所有的产品必须实现这个共同接口,这样一来,使用这些                            Creator是一个类,它实现了所有操纵产品的方法,但不实现工厂方法
 *   产品的类就可以引用这个接口,而不是具体类                                    Creator所有子类必须实现这个抽象的factoryMethod()方法
 *
 *   ConcreteProduct                                                     ConcreteCreator: 实现了factoryMethod(),以实际制造出产品
 *                     ConcreteProduct负责创建一个或多个具体产品,只有ConcreteCreator类
 *                     知道如何创建这些产品
 */

/** `
 * Q: 当只有一个ConcreteCreator的时候,工厂方法模式有什么优点?
 * A: 尽管只有一个具体创建者,工厂方法模式依然很有用,因为他帮助我们将产品的"实现"从"使用"中解耦.如果增加产品或者改变产品的实现,Creator并不会收到影响(
 *      因为Creator与任何ConcreteProduct之间都不是紧耦合)
 * Q: 如果说纽约和芝加哥的商店利用简单工厂创建的,这说法是否正确?看起来倒是很像.
 * A: 他们很类似,但是用法不同.虽然每个具体商店的实现看起来都很像是SimplePizzaFactory,但是,这里的具体商店是扩展自一个类,此类有一个抽象的方法createPizza().
 *      由每个商店自行负责createPizza()方法的行为,在简单工厂中,工厂是另一个有PizzaStore使用的对象
 * Q: 工厂方法和创建者是否总是抽象的?
 * A: 不,可以定义一个默认的工厂方法来产生某些具体的产品,这么一来,即使创建者没有任何子类,依然可以创建产品
 * Q: 每个商店基于传入的类型制造出不同种类的披萨.是否所有的具体创建者都必须如此?能不能只创建一种披萨?
 * A: 这里采用的方式称为"参数化工厂方法".他可以根据传入的参数创建不同的对象.然而,工厂经常只产生一种对象,不需要参数化.模式的这两种形式都是有效的
 * Q: 利用字符串传入参数化的类型,似乎有点危险,玩意吧Clam拼错怎么办?
 * A: 使用java 5支持的enum
 * Q: 对于简单工厂和工厂方法之间的差异,我依然感到困惑.他们看起来很类似,差别在于,工厂方法中,返回披萨的类是子类,能解释一下吗?
 * A: 子类看起来很像简单工厂.简单工厂把全部的事情,在一个地方都处理完了,然而工厂方法却是创建一个框架,让子类决定要如何实现,比如,工厂方法中,orderPizza方法
 *      提供了一般的框架,以便创建披萨,orderPizza方法依赖工厂方法创建具体类,并制造出实际的披萨.可通过继承PizzaStore类,决定实际制造出的披萨是什么.
 *      简单工厂的做法,可以将对象的创建封装起来,但是简单工厂不具备工厂方法的弹性,因为简单工厂不能改变正在创建的产品.
 */

/** `
 * Q: 工厂可以封装实例化行为,那么工厂究竟能带来什么好处?
 * A: 将创建对象的代码集中在一个对象或方法中,可以避免代码中的重复,并更方便以后的维护.这也意味着,在实例化对象时,只会依赖接口而不是具体类.
 *      这将帮助针对接口编程,而不是针对实现编程,这样代码更有弹性,可以应对未来的扩展.
 * Q: 但是,封装创建对象的代码,可以对抽象编码,将代码与真是实现解耦.然而工厂代码中,仍然必须使用具体类来实例化真正的对象,这不是欺骗自己吗?
 * A: 对象创建是现实的,如果不创建任何对象,就无法创建任何java程序.然而,可以将创建对象的代码围起来,这样可以保护这些创建对象的代码.
 */














