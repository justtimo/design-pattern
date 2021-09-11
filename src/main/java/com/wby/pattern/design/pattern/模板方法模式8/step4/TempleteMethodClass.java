package com.wby.pattern.design.pattern.模板方法模式8.step4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 更深入的了解模板方法
 *
 * 这是我们的抽象类,他被声明为抽象的,被用作基类,其子类必须实现特定的方法
 */
public abstract class TempleteMethodClass {
    /**
     * 这是模板方法,被声明为final,避免子类修改方法的顺序
     */
    final void templateMethod(){
        //一连串的步骤,每个步骤由一个方法代表
        operation1();
        operation2();
        operation3();
        //添加了新的方法调用,改变了templateMethod()
        hock();
    }
    //子类必须各自实现的两个方法
    abstract void operation1();
    abstract void operation2();
    //抽象类具体的操作
    /*void operation3(){
        System.out.println("这里是具体实现");
    }*/
    //此时这个方法被声明为final的,子类无法覆盖它.他可以被模板方法直接使用,或者被子类使用
    final void operation3(){
        System.out.println("这里是具体实现");
    }

    /**
     * 这是一个具体的方法,但是他不做任何操作
     * 我们称这种"默认不做任何事"的方法为"hock(钩子)".子类可以视情况覆盖它
     */
    void hock(){}
}
/**
 * 钩子
 * 钩子是被生命在抽象类中的方法,但是只有空的或者默认的实现.
 * 他的存在,可以让子类有能力对算法的不同点进行挂钩,是否需要挂钩由子类决定
 *
 * 钩子有几种用途,我们先看一个:
 */
abstract class CaffeineBeveregeWithHock{
    void prepareRecipe(){
        boildWater();
        brew();
        pullCup();
        /**
         * 加入了条件语句,条件是否成立是由具体方法customerWantsCondiments()决定的,如果顾客想要调料,只有这时我们才调用addCondiments()
         */
        if (customerWantsCondiments()){
            addCondiments();
        }
    }
    abstract void brew();
    abstract void addCondiments();
    void boildWater(){
        System.out.println("煮水");
    }
    void pullCup(){
        System.out.println("倒入杯子");
    }

    /**
     * 定义一个方法,(通常)是空的缺省实现:只会返回true,不做别的事情.
     * 这就是一个钩子.子类可以覆盖该方法
     * @return
     */
    boolean customerWantsCondiments(){
        return true;
    }
}
/**
 * 使用钩子
 *
 * 为了使用钩子,我们在子类中覆盖它.这里,钩子控制力饮料是否需要执行某部分算法:即饮料是否添加调料
 */
class CoffeeWithHock extends CaffeineBeveregeWithHock{

    @Override
    void brew() {
        System.out.println("放咖啡");
    }

    @Override
    void addCondiments() {
        System.out.println("添加糖和牛奶");
    }

    @Override
    boolean customerWantsCondiments() {
        String answer=getUserInput();
        return answer.toLowerCase().startsWith("y")?true : false;
    }

    public String getUserInput(){
        String answer=null;
        System.out.println("想要添加调料吗?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answer==null){
            return "no";
        }
        return answer;
    }
}
class BeveregeWithHockTest{
    public static void main(String[] args) {
        CoffeeWithHock coffeeWithHock = new CoffeeWithHock();
        coffeeWithHock.prepareRecipe();
    }
}
/**
 * 作用1: 钩子作为条件控制,影响抽象类中的算法流程
 */























