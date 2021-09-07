package com.wby.pattern.design.pattern.模板方法模式8.step3;

/**
 * 认识模板方法
 *      模板方法定义了一个算法的步骤,并允许子类为一个或多个步骤提供实现
 *      模板就是一个方法,具体而言,这个方法将算法定义成一组步骤,其中任何步骤都可以是抽象的,由子类负责实现.这可以确保算法的接口保持不变,同时由子类提供部分实现
 */
abstract class CoffeinBeverege{
    /**
     * prepareRecipe()就是模板方法,因为:
     *  1.它是一个方法
     *  2.他用作一个算法的模板,这个例子中算法是用来制作咖啡因饮料的
     *  3.定义为final的,避免子类改变这个算法的顺序
     */
    final void prepareRecipe(){
        /**
         * 这个模板内,算法的每个步骤都被一个方法代表了
         * 某些方法由超类处理;某些方法由子类处理
         */
        boilWater();
        brew();
        pourInCup();
        addCondiment();
    };

    /**
     * 需要由子类实现的方法必须在超类中声明为抽象
     */
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
 * 模板方法带来的便利:
 *                      不好的咖啡和茶实现                                                           模板方法提供的咖啡因饮料
 *              咖啡和茶主导一切,他们控制了算法                                                由超类主导一切,它拥有算法,而且保护这个算法
 *              存在重复代码                                                                超类的存在,最大化的复用代码
 *              对于算法的改变,需要修改子类很多地方                                             算法只存在一个地方,容易修改
 *              由于类的组织方法不具弹性,加入新的咖啡和茶需要许多工作                              模板方法提供了框架,新的饮料只需要实现自己的方法即可
 *              算法的知识和他的实现分散在许多类中                                              超类专注算法本身,而由子类提供完整的实现
 */
public class Text {
}
