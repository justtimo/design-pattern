package com.wby.pattern.design.pattern.策略设计模式.step4;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/7/31 11:36
 * @Description: 现在我们知道继承有一些缺陷，因为改变鸭子的行为会影响所有种类的鸭子，这并不恰当。
 *  接口一开始不错，解决了问题(只有会飞的鸭子才继承Fly接口)，但是java的接口不具有实现代码，所以继承接口无法复用代码。
 *  这意味着：你需要修改某个行为，你必须得往下追踪并修改每一个定义此行为的类。
 *
 *  根据设计原则1，如果每次新的需求都会变化某方面的代码，那么就可以确定，这部分的代码需要被抽出来，和其他不变的代码区分开。
 *  把会变化的部分取出并“封装”起来，让其他部分不会受到影响。
 *  代码变化之后，出其不意的部分变得很少，系统变得更有弹性。
 *
 *  下面是这个原则的另一个思考方式：把会变化的部分取出并封装起来，以便以后可以轻易的扩充此部分，而不影响不需要变化的其他部分。
 *
 *  这样的概念很简单，几乎是每个设计模式背后的精神所在。所有的模式都提供了一套方法让“系统中某部分改变不会影响其他部分”
 */
/**
* @Description: 如何开始？
 * 除了fly和quack的问题之外，Duck类还算正常，所以除了某些小改变之外，我们不打算对Duck类做太多处理。
 * 现在，为了分开“变化和不变的部分”，我们准备建立两组类(完全远离Duck类)，一个是fly相关，一个是quack相关的，每组类将实现各自的动作。
 * 比如，我们可能有个类实现“呱呱叫”，另一个类实现“吱吱叫”，另一个类实现“安静”
 *
 * 我们知道DUck类内的fly和quack随着鸭子不同而改变，为了区分开，我们把他们从Duck类中取出，建立一组新类代表每个行为。
*/
/**
* @Description: 如何设计类实现飞行和呱呱叫的行为？
 * 我们希望一切都有弹性。
 * 我们还想能够“指定”行为到鸭子的实例，比如想要产生绿头鸭实例，并指定特定“类型”的飞行行为给他。
 * 干脆顺便让鸭子的行为可以动态的改变。换句话说，我们应该在鸭子类中包含设定行为的方法，就可以在运行时动态的改变绿头鸭的飞行行为
 *
 * 有了这些目标，也就设计原则2.
 * 利用接口代表每个行为，比如FlyBehavior和QuackBehavior，而行为的每个实现都必须实现这些接口。
 * 所以这次Duck类不会负责实现Flying和Quack接口，反而由其他类专门实现FlyBehavior和QuackBehavior，这就称为"行为"类。
 * 由行为类实现行为接口，而不是由Duck类实现行为接口
 *
 * 以往做法是：行为是继承Duck超类的具体实现而来，或是继承某个接口并由子类自行实现。
 * 这两种方法都是依赖于“实现”,我们被实现绑得死死的，没办法改变行为(除非编写更多代码)
 *
 * 新设计中，鸭子的子类将使用接口所表示的行为，所以实现的“实现”不会被绑死在鸭子的子类中
 * 换句话说，特定的实现代码位于实现FlyBehavior和QuackBehavior的特定类中
*/
/**
* 那么，为什么不把 FlyBehavior设计成抽象类，这样不就可以使用多态吗？
 * “针对接口编程”的真正意思是“针对超类型编程”
 * 这里“接口”有多个含义，接口是一个概念，也是一种interface构造。
 * 你再不涉及java interface的情况下，“针对接口编程”关键就在多态。利用多态，程序可以针对超类型编程。
 * 针对超类型编程 这句话，可以更明确的说成“变量的声明类型，应该是超类型，通常是一个抽象类或者是一个接口。这意味着，声明类时，不用理会以后执行时的真正对象类型”
 *
*/
/**
* 看看下面这个简单地多态例子：一个抽象类Animal，两个具体实现Dog和Cat，针对实现编程做法如下
*/
abstract class Animal{
    public abstract void makeSound();
}
class Dog extends Animal{

    @Override
    public void makeSound() {
        bark();
    }
    public void bark(){
        //汪汪叫
    }
}
class Cat extends Animal {

    @Override
    public void makeSound() {
        meow() ;
    }
    public void meow(){
        //喵喵叫
    }
}
class AnimalTest{
    public static void main(String[] args) {
        //老方式：变量d为Dog类型(Animal的具体实现，造成我们必须针对实现编码)
        Dog d = new Dog();
        d.bark();
        //针对接口\超类型编程 ：利用animal进行多态调用
        Animal dog = new Dog();
        dog.makeSound();
        //更棒的是，子类型实例化的动作不再需要在代码中硬编码，例如new Dog()，而是在运行时才指定具体实现的对象.
        //不需要知道实际子类型，只关心如何正确进行makeSound的动作
        //a=getAnimal();
        //a.makeSound();
    }
}
/**
 * 实现鸭子的行为
*/
interface FlyBehavior{
    public void fly();
}
class FlyWithWings implements FlyBehavior{

    @Override
    public void fly() {
        //实现鸭子飞行。所有有翅膀的鸭子飞行动作
    }
}
class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() {
        //什么都不做，不会飞。实现所有不会飞的鸭子的动作。
    }
}
interface Quackbehavior{
    public void quack();
}
class Quack implements Quackbehavior {

    @Override
    public void quack() {
        //实现鸭子呱呱叫。 真的呱呱叫
    }
}
class Squeak implements Quackbehavior {

    @Override
    public void quack() {
        //橡皮鸭子吱吱叫：名为呱呱叫，实际上是吱吱叫
    }
}
class MuteQuack implements Quackbehavior {

    @Override
    public void quack() {
        //不会叫。名为呱呱叫。，实际上不会叫
    }
}
/**
*  这样的设计，可以让飞行和呱呱叫的动作被其他对象复用，因为这些行为已经和Duck类无关了。我们新增的一些行为，不会影响到既有的行为类，也不会影响有“使用”
 *  到飞行行为的鸭子类。  这样，有了继承的“复用”好处，却没有继承所带来的包袱
*/















