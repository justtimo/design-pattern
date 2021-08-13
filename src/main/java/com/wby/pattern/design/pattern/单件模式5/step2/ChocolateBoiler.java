package com.wby.pattern.design.pattern.单件模式5.step2;

/**
 * 巧克力锅炉控制器
 */
public class ChocolateBoiler {
    private Boolean empty;
    private Boolean boiled;

    public ChocolateBoiler(){
        empty = true;
        boiled=false;
    }

    //锅炉放入原料时,锅炉必须是空的.放入原料就他们都设置为false
    public void fill(){
        if (isEmpty()) {
            empty=false;
            boiled = false;
            //填满巧克力和牛奶
        }
    }
    //锅炉排出时,必须是满的而且还要是煮过的.排出完毕,把empty设置为true
    public void drain(){
        if (!isEmpty() && isBoiled()){
            //排出煮沸的巧克力和牛奶
            empty = true;
        }
    }
    //煮混合物时,锅炉必须是慢的,并且是没有煮过的,一旦煮沸,将boiled设置为true
    public void boil(){
        if (!isEmpty() && !isBoiled()){
            boiled = false;
        }
    }

    public boolean isEmpty(){
        return empty;
    }
    public boolean isBoiled(){
        return boiled;
    }
}
/** `
 * 但是这个设计在有多个ChocolateBoiler对象时,就会有不好的事情发生.
 *
 * 改造成单件模式
 */
class ChocolateBoilerSingleton {
    private Boolean empty;
    private Boolean boiled;
    private static ChocolateBoilerSingleton chocolateBoilerSingleton;

    private ChocolateBoilerSingleton(){
        empty = true;
        boiled=false;
    }
    public static ChocolateBoilerSingleton getInstance(){
        if (chocolateBoilerSingleton == null){
            chocolateBoilerSingleton = new ChocolateBoilerSingleton();
        }
        return chocolateBoilerSingleton;
    }

    //锅炉放入原料时,锅炉必须是空的.放入原料就他们都设置为false
    public void fill(){
        if (isEmpty()) {
            empty=false;
            boiled = false;
            //填满巧克力和牛奶
        }
    }
    //锅炉排出时,必须是满的而且还要是煮过的.排出完毕,把empty设置为true
    public void drain(){
        if (!isEmpty() && isBoiled()){
            //排出煮沸的巧克力和牛奶
            empty = true;
        }
    }
    //煮混合物时,锅炉必须是慢的,并且是没有煮过的,一旦煮沸,将boiled设置为true
    public void boil(){
        if (!isEmpty() && !isBoiled()){
            boiled = false;
        }
    }

    public boolean isEmpty(){
        return empty;
    }
    public boolean isBoiled(){
        return boiled;
    }
}
