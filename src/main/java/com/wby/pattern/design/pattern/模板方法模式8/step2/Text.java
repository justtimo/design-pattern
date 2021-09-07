package com.wby.pattern.design.pattern.模板方法模式8.step2;

/**
 * 但是再继续观察可以发现,两者其实还有共同点,进一步抽象!!!!
 *
 * 两者都采用了相同的算法:
 *  1.煮水
 *  2.用热水泡茶或咖啡
 *  3.把饮料倒进杯子
 *  4.在饮料内加入调料
 *  1,4已经被抽取出来放入了基类,但是2,3其实是一样的,只是应用在不同的饮料上
 *
 * 我们可以抽象prepareRecipe()准备饮料的方法吗?
 *      分析问题:
 *          1.咖啡的brewCoffeeGrinds()和addSugarAndMilk()  与 茶的steepTeaBag()和addLemon()存在差异, 浸泡(steep) 和 冲泡(brew)差异其实不大
 *              所以我们给他们一个新的方法名brew(),不管冲泡咖啡还是泡茶都用这个方法;类似的是加牛奶 和 加柠檬 ,我们也用心的方法addCondiment(),
 *              改造后的方法如下:
 *                  void prepareRecipe() {
 *                     boilWater();
 *                     brew();
 *                     pourInCup();
 *                     addCondiment();
 *                  }
 *          2.有了新的prepareRecipe(),我们开始改造基类
 */
abstract class CoffeinBeverege{
    final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiment();
    };
    //泡:泡茶或咖啡
    abstract void brew();
    //加调料
    abstract void addCondiment();
    //烧水
    public void boilWater() {
        System.out.println("Boiling water");
    }
    //倒进杯子
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }
}

/**
 * 3.处理咖啡 和 茶,现在让他们自己处理 泡 和加调料
 */
class TeaVersion2 extends CoffeinBeverege{
    @Override
    void brew() {
        System.out.println("泡茶");
    }
    @Override
    void addCondiment() {
        System.out.println("加柠檬");
    }
}
class CoffeVersion2 extends CoffeinBeverege {
    @Override
    void brew() {
        System.out.println("泡咖啡");
    }
    @Override
    void addCondiment() {
        System.out.println("加牛奶和糖");
    }
}

/**
 * 我们改进了什么?
 * 我们知道了两种饮料的冲泡基本相同,只是一些步骤需要不同的实现,所以我们 泛化 了冲泡法,把他放在基类(煮水,冲泡,倒进杯子,加调料),一些步骤依赖子类进行(冲泡,加调料)
 * 而不同的子类(茶:煮水,泡茶也,倒进杯子,加柠檬   咖啡:煮水,泡咖啡,倒进杯子,加牛奶和糖)
 */
public class Text {
}
