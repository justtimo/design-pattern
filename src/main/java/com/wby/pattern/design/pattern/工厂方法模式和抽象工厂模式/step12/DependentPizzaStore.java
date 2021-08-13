package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.step12;

/** `
 * 假设你从未听说过OO工厂.下面是一个不使用工厂模式的披萨店版本.如果又增加一种加州风味披萨,又会依赖几个对象呢?
 */
public class DependentPizzaStore {

	public Pizza createPizza(String style, String type) {
		Pizza pizza = null;
		if (style.equals("NY")) {
			if (type.equals("cheese")) {
				pizza = new NYStyleCheesePizza();
			} else if (type.equals("veggie")) {
				pizza = new NYStyleVeggiePizza();
			} else if (type.equals("clam")) {
				pizza = new NYStyleClamPizza();
			} else if (type.equals("pepperoni")) {
				pizza = new NYStylePepperoniPizza();
			}
		} else if (style.equals("Chicago")) {
			if (type.equals("cheese")) {
				pizza = new ChicagoStyleCheesePizza();
			} else if (type.equals("veggie")) {
				pizza = new ChicagoStyleVeggiePizza();
			} else if (type.equals("clam")) {
				pizza = new ChicagoStyleClamPizza();
			} else if (type.equals("pepperoni")) {
				pizza = new ChicagoStylePepperoniPizza();
			}
		} else {
			System.out.println("Error: invalid type of pizza");
			return null;
		}
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
/** `\
 * 当实例化一个对象时,就是依赖他的具体类.上面的例子是由披萨店创建所有的披萨对象,而不是工厂
 */
/** `
 * 依赖倒置原则
 * 		代码里减少对于具体类的依赖是件好事.一个OO原则就正式阐明了这一点:依赖倒置原则
 * 	首先,这个原则听起来像是"针对接口编程,不针对实现编程",不是吗?的确很相似,然而这里更强调"抽象".这个原则说明了:不能让高层组件依赖于底层组件,而且
 * 		不管高层或低层组件,"两者"都应该依赖于抽象.
 * 	所谓"高层"组件,是由其他低层组件定义其行为的类.例如,PizzaStore是个高层组件,因为他的行为是由披萨定义的,他创建所有不同的披萨对象,准备\烘烤\切片\装盒.
 * 	而披萨本身属于低层组件.PizzaStore依赖这些具体披萨类
 *
 * 	这个原则告诉我们,应该抽写代码以便我们依赖抽象类,而不是具体类.对于高层及低层模块都应该如此.
 */

/** `
 * 非常依赖披萨店的问题在于:他依赖每个披萨类型.因为他是在自己的orderPizza()中实例化这些具体类型的
 * 虽然我们已经创建了一个抽象,也就是Pizza,但是代码中仍然实际的创建了具体的Pizza,所以,这个抽象没什么影响力.
 *
 * 如何在orderPizza中,将实例化代码独立出来,工厂方法就能用到了.
 * 应用工厂方法之后,高层组件(PizzaStore)和低层组件(披萨的具体实现)都依赖了Pizza抽象
 * PizzaStore--->Pizza<-----NYChessePizza,NYXXXPizaa,ChicagoChessPizza,ChicagoXXXPizza
 * 想要遵循依赖倒置原则,工厂方法并不是唯一的技巧,但却是最有威力的技巧之一.
 */

/** `
 * 依赖倒置原则,究竟倒置在哪里?
 * 倒置 指的是,和一般OO设计的思考方式完全相反..
 * 最开始,低层组件依赖高层的抽象.同样,高层组件现在也依赖相同的抽象.
 * 最开始的依赖图是从上而下的,现在确倒置了,而且高层与底层现在都依赖这个抽象
 */

/** `
 * 倒置你的思考方式
 *
 * Q: 需要实现一个披萨店,第一件想到的事情是什么?
 * A: 披萨店进行准备,烘烤,装盒,所以必须能制作许多不同风味的披萨.比如:芝士披萨,素食披萨
 * Q: 没错,先从顶端开始,然后往下到具体类.但是正如你所看到的,你不想让披萨店理会这些具体类,要不然披萨店将全部依赖这些具体类.
 * 		现在,"倒置"你的想法,别从顶端开始,而是从披萨开始,然后想想能抽象化些什么
 * A: 芝士披萨,素食披萨都是Pizza,所以他们应该共享一个Pizza接口
 * Q: 好了,你要抽象画一个Pizza,现在回头重新思考如何设计披萨店
 * A: 已经有了一个披萨抽象,就开始设计披萨店而不用理会具体的披萨类了
 * Q: 但是这么做必须靠一个工厂来将这些具体类取出披萨.一旦这么做,各种不同的具体披萨类型只能依赖一个抽象,而披萨店也会依赖这个披萨抽象.
 * 		我们已经倒置了商店依赖具体类的设计,而且也倒置了你的思考方式
 */

/** `
 * 下面的指导方针,能让你避免在OO设计中违反依赖倒置原则
 * 		1. 变量不可以持有具体类的引用:使用new就会持有具体类的引用,你可以改用工厂来避开这样的做法
 * 		2. 不要让类派生自具体类:如果派生自具体类,你就会依赖具体类,请派生自一个抽象(接口或抽象类)
 * 		3. 不要覆盖基类中已实现的方法:如果这么做,那么基类就不是一个真正适合被继承的抽象,基类已实现的方法,应该由所有的子类共享.
 *
 * 	Q: 但是如果完全遵循这些指导仿真似乎不太可能吧,如果这么做,我连一个简单程序都写不出来!
 * 	A: 没错,正如我们许多原则一样,应该尽量达到这个原则,而不是随时都要遵循这个原则.我们很清楚,任何java程序都有违反这些原则的地方.
 * 		但是,如果深入体验这些方针,将他们化成思考的一部分,那么在设计时,你就会知道何时会有足够得理由违反这样的原则
 * 		比如,有一个不像是会改变的类,那么直接实例化也没什么问题.比如字符串
 * 		另一方面,如果有个类可能改变,就应该采取一些技巧(例如工厂方法)来封装改变.
 */














