package com.wby.pattern.design.pattern.适配器模式与外观模式7.step7;

/**
 * 外观模式
 *  现在我们看一个改变接口的新模式,改变的目的是为了简化接口.他就是外观模式
 *  之所以这么称呼,是因为他将一个或多个类的复杂的一切隐藏在背后,只表现一个干净美好的外观
 */

/**
 * 看电影(困难的方式)
 *  1. 打开爆米花机
 *  2. 开始爆米花
 *  3. 灯光调暗
 *  4. 放下屏幕
 *  5. 打开投影仪
 *  6. 将投影仪的输入切换到DVD
 *  7. 投影仪设置为宽屏
 *  8. 打开工坊
 *  9. 将功放设置为DVD
 *  10. 将功放设置为立体声
 *  11. 将功放音量调为中
 *  12. 打开DVD播放器
 *
 *  这涉及到6个不同的类.并且还不止如此,看完电影以后,需要把一切关掉,难道反向再进行一次?
 *      如果挺CD或者广播也这么麻烦?如果是哼唧系统,可能需要学习一套不同的操作过程.
 *  可以发现,家庭影院变得很复杂,让我们看看外观模式如何解决这个问题.
 */
public class Text {
}
/**
 * 通过外观模式,实现一个提供更合理的接口的外观类,可以将复杂的子系统变得容易使用.
 * 如果你需要子系统的强大威力,依旧可以使用原来的复杂接口;但是如果需要的是方便使用的接口,那么久使用外观
 */

/**
 * Q: 外观封装了子系统的类,那么需要底层功能的客户如何接触这些类?
 * A: 外观没有封装子系统的类,只提供简化的接口.所以,客户依然可以直接使用子系统的类.这是外观模式的两点:提供简化接口的同时,依然将系统完整的功能暴露出来,
 *      以供需要的人使用.
 * Q: 外观会新增功能吗,或者只是将每一个请求转给子系统执行?
 * A: 外观可以附加"聪明的"功能,让使用子系统更方便.
 * Q: 每个子系统只能有一个外观吗?
 * A: 不.可以有许多个外观.
 * Q: 除了提供简单地接口之外,还有其他优点吗?
 * A: 也允许将客户实现从任何子系统中解耦.如果升级家庭影院,如果客户代码针对外观而不是子系统编写的,那么你不需要改变客户代码,只要修改外观代码
 * Q: 可不可以这么说:适配器模式与外观模式之间的差异在于:适配器包装一个类,而外观可以代表许多类?
 * A: 错!适配器是将一个或多个接口编程客户期望的一个接口.虽然大部分例子中适配器只适配一个类,但实际上可以适配许多类来提供一个接口让客户编码.
 *      类似的,一个外观也可以只针对一个拥有复杂接口的类提供简化的接口.
 *      两个模式差异,不在于"包装了"几个类,而是他们的意图.
 *          适配器意图:改变接口符合客户的期望
 *          外观模式意图:提供子系统的一个简化接口
 *      他们都可以包装许多类,但是他们的意图不同
 */
class HomeTheaterFacade{
    //使用组合,将用到的子系统组件都放在这里
    Amplifier amp;
    Tuner tuner;
    StreamingPlayer player;
    CdPlayer cd;
    Projector projector;
    TheaterLights lights;
    Screen screen;
    PopcornPopper popper;

    //将子系统中的每个组建的引用都传入到构造器中,然后赋值给相应的实例变量
    public HomeTheaterFacade(Amplifier amp, Tuner tuner, StreamingPlayer player, CdPlayer cd, Projector projector, TheaterLights lights, Screen screen, PopcornPopper popper) {
        this.amp = amp;
        this.tuner = tuner;
        this.player = player;
        this.cd = cd;
        this.projector = projector;
        this.lights = lights;
        this.screen = screen;
        this.popper = popper;
    }
    /**
     * 实现简化接口
     */
    public void watchMovie(String movie) {
        //将之前进行的没想任务依次处理.注意.任务都是未果子系统中相应组件处理的
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setStreamingPlayer(player);
        amp.setSurroundSound();
        amp.setVolume(5);
        player.on();
        player.play(movie);
    }


    public void endMovie() {
        //负责关闭一切.每项任务都是委托子系统中合适的组件处理的
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        player.stop();
        player.off();
    }
}
/**
 * 观看电影: 轻松地方式
 *
 */
class HomeTheaterTest{
    public static void main(String[] args) {
        //1. 创建组件
        Amplifier amp = new Amplifier("Amplifier");
        Tuner tuner = new Tuner("AM/FM Tuner", amp);
        StreamingPlayer player = new StreamingPlayer("Streaming Player", amp);
        CdPlayer cd = new CdPlayer("CD Player", amp);
        Projector projector = new Projector("Projector", player);
        TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
        Screen screen = new Screen("Theater Screen");
        PopcornPopper popper = new PopcornPopper("Popcorn Popper");
        //2. 根据子系统所有的组件实例化外观
        HomeTheaterFacade homeTheater =
                new HomeTheaterFacade(amp,tuner,player,cd,projector,lights,screen,popper);
        //3. 使用简化接口,开启电影,关闭电影
        homeTheater.watchMovie("Raiders of the Lost Ark");
        homeTheater.endMovie();
    }
}
















