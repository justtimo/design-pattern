package com.wby.pattern.design.pattern.工厂方法模式和抽象工厂模式.nextStep1;

/**
 * 现在,对象村披萨店的一些加盟店,使用低价原料增加利润,所以需要采取一些手段,以免毁了对象村的品牌
 *
 * 如何确保原料一致?
 * 你打算建造一个原料工厂,但是加盟店在不同的区域,同样的红酱料在不同区域是不同的,以后如果再新增加州加盟店呢?新增西雅图加盟店呢?
 * 想要行得通,必须先明白如何处理原料家族
 */

/** `
 * 整体来说,这三个区域组成了原料家族,每个区域实现了一个完整的原料家族
 * 每个家族都包含了一种面团,一种酱料,一种芝士,一机一中海鲜佐料的类型
 */
class NY{
    class FreshClams{}
    class MarinaraSauce{}
    class ThinCrustDough{}
    class ReggianoCheese{}
}
class Chicago{
    class FrozenClams{}
    class ThickCrustDough{}
    class PlumTomatoSauce{}
    class MozzarellaCheese{}
}
class Californai{
    class Calamari{}
    class BruaschettaSauce{}
    class GoatCheese{}
    class VeryThinCrust{}
}
public class Text {

}
