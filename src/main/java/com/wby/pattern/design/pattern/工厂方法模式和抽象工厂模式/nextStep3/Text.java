package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep3;

/**
 * 定义抽象工厂模式
 *      新增了另一个工厂模式,这个模式可以创建产品的家族.
 *      定义: 抽象工厂模式提供一个接口,用来创建相关或依赖对象的家族,而不需要明确指定具体类.
 *
 *      抽象工厂允许客户使用抽象的接口来创建一组相关的产品,而无需知道(或关心)实际产品产出的具体产品是什么.这样,客户就从具体的产品被解耦.
 *
 *      Text.UML是相当复杂的类图,让我们从PizzaStore的角度去看看他
 *
 *      PizzaIngredientFactory是抽象的披萨原料工厂接口,定义了如何生产一个相关产品的家族.这个家族包含了所有制作披萨的原料
 *      NYPizzaIngredientFactory,ChicagoPizzaIngredientFactory:具体的披萨原料工厂负责生产披萨原料,每个工厂都知道如何生产符合自己区域口味的对象
 *      NYPizzaStore,ChicagoPizzaStore:披萨店的具体实例,是抽象工厂的客户
 *
 */
public class Text {
}
/** `
 * Q: 抽象工厂的每个方法实际上看起来都像是工厂方法(比如:createDough,createSauce等),每个方法都被声明为抽象,而子类的方法覆盖这些方法来创建某些对象.
 *      这不就是工厂方法吗?工厂方法是不是隐藏在抽象工厂里面??
 * A:是的,抽象工厂的方法经常以工厂方法的方式实现.抽象工厂的任务时定义一个负责创建一组产品的接口.这个接口内的每个方法负责创建一个具体的产品,同时我们利用
 *      实现抽象工厂的子类来提供这些具体的做法.所以,抽象工厂中利用工厂方法实现生产方法是很自然的做法.
 *
 */
/** `
 * A: 工厂方法:虽然我们都是工厂模式,但不代表他们就该合在一起访问
 * Q: 怎么区分抽象工厂和工厂方法?
 * A: 抽象工厂:我们两个把应用从特定实现中解耦都很有一套,只是做法不同而已
 *      工厂方法:毕竟我使用的是类,而你使用的是对象,根本就不是一回事.
 *          虽然都是负责创建对象,但是我使用的是继承,而抽象工厂是通过对象的组合.
 *          这意味着,利用工厂方法创建对象,需要扩展一个类,并覆盖他的工厂方法.
 * Q: 那这个工厂方法是做什么的呢?
 * A: 工厂方法:当然是创建对象的.其实整个工厂方法模式,只不过就是通过子类来创建对象.这样,客户只需要知道他们所使用的抽象类型就可以,而由
 *          子类决定具体类型.所以,我只负责将客户从具体类型中解耦
 *    抽象工厂: 这点我也可以做到,只是做法不同.我提供一个用来创建产品家族的抽象类型,这个类型的子类定义了产品被产生的方法.要使用这个工厂,必须先实例化它.
 *          然后将它传入针对抽象类型所写的代码中.所以,我也可以吧客户从使用的实际具体产品中解耦
 * Q:我明白了,所以抽象工厂的优点是可以把一群相关的产品集合起来.
 * A; 抽象工厂:对
 * Q: 万一需要扩展这组产品(比如新增一个产品)怎么办呢?难道不需要改变接口吗?
 * A: 是的,如果加入新产品就必须改变接口,这意味着必须深入改变每个子类的接口.但是我需要一个大的接口,因为我是被用来创建整个产品家族的.而工厂方法只是创建一个
 *      产品,所以工厂方法不需要一个大接口,只需要一个方法就可以.
 * Q: 抽象工厂,听说你经常使用工厂方法来实现你的具体工厂?
 * A: 是的,我的具体工厂实现工厂方法来创建他们的产品,不过,这些具体工厂只是用来创建产品罢了
 *      工厂方法: 对我而言,抽象创建者(creator)中所实现的代码通常会用到子类所创建的具体类型.
 * Q: 你们都能将对象的创建封装起来,使应用程序解耦,并降低其对特定实现的依赖.
 * A: 抽象工厂:当你需要创建产品家族和想让制造的相关产品集合起来时,你可以使用我
 *      工厂方法:我可以吧你的客户代码从需要实例化的具体类中解耦.或者如果你目前还不知道以后需要实例化哪些具体类时,也可以用我.
 *          使用我只需要把我继承成为子类,并实现我的工厂方法就可以了
 */
