package com.wby.pattern.design.pattern.代理模式11.step3;

/**
 * 虚拟代理
 *
 * 代理模式可以以很多形式显现,但都大致符合一般代理的设计. 为什么有这么多形式? 因为代理模式可以被用在许多不同例子中
 *
 * 远程代理:
 *      远程代理作为另一个JVM上对象的本地代表. 调用代理的方法,会被代理利用网络转发到远程执行,并且结果会通过网络返回给代理,
 *      再有代理将结果转给客户.
 *      ( Client ----> request() ----> Proxy )----> RealSubject
 * 虚拟代理:
 *      虚拟代理作为创建开销大的对象的代表.虚拟代理经常知道我们真正需要一个对象的时候才创建它. 当对象在创建前和创建中时, 由
 *      虚拟代理来扮演对象的替身. 对象创建后,代理就会将请求直接委托给对象.
 *      ( Client ----> request ----> Proxy ----> RealSubject )
 */

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * 设计CD封面的虚拟代理
 *      目的是用于隐藏创建开销大的对象(因为我们需要通过网络取得图像数据),而不是隐藏在网络其他地方的对象.
 *      <Interface> Icon ; ImageIcon implement Icon ; ImageProxy implement Icon <subject ImageIcon>
 * ImageProxy 如何工作:
 *      1. ImageProxy先创建一个ImageIcon ,然后从网络加载图像
 *      2. 加载过程中, ImageProxy显示"加载中,稍等
 *      3. 加载完毕,ImageProxy把方法调用委托给真正的ImageIcon ,这些方法包括了paintIco(),getWidth() 和getHeight()
 *      4. 如果请求新图像, 我们就创建新的代理 , 重复这样的过程
 */
class  ImageProxy implements Icon{
    //此imageIcon 是我们希望在加载后显示出来的真正的图像
    ImageIcon imageIcon;
    URL imageURL ;
    Thread retievalThread;
    boolean retrieving =false;

    //我们将图像的URL传入构造器中, 这是我们希望显示的图像所在的位置
    public ImageProxy(URL imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * 这里会在屏幕上显示一个icon图像(通过委托给ImageIcon) ,
     * 然而如果我们没有被完整的创建的ImageIcon ,那就自己创建一个.
     * @param c
     * @param g
     * @param x
     * @param y
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (imageIcon != null){
            imageIcon.paintIcon(c,g,x,y);
        }else {
            g.drawString("加载中,稍等", x+300, y+190);
            if (!retrieving){
                retrieving = true;
                retievalThread = new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                imageIcon = new ImageIcon(imageURL , "CD Cover");
                                c.repaint();
                            }
                        }
                );
                retievalThread.start();
            }
        }
    }

    /**
     * 图像加载完毕前, 返回默认的宽度和高度. 图像加载完毕后, 转交给imageIcon处理
     * @return
     */
    @Override
    public int getIconWidth() {
        if (imageIcon != null){
            return imageIcon.getIconWidth();
        }else {
            return 800;
        }
    }

    @Override
    public int getIconHeight() {
        if (imageIcon != null) {
            return imageIcon.getIconHeight();
        }else {
            return 600;
        }
    }
}
class ImageComponent extends JComponent {
    private static final long serialVersionUID = 1L;
    private Icon icon;

    public ImageComponent(Icon icon) {
        this.icon = icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int x = (800 - w)/2;
        int y = (600 - h)/2;
        icon.paintIcon(this, g, x, y);
    }
}

public class Test {
    ImageComponent imageComponent;
    Hashtable<String, String> albums = new Hashtable<String, String>();
    JFrame frame = new JFrame("Album Cover Viewer");

    public Test() {
        albums.put("Selected Ambient Works, Vol. 2","http://images.amazon.com/images/P/B000002MNZ.01.LZZZZZZZ.jpg");
        URL initialURL = null;
        try {
            initialURL = new URL((String)albums.get("Selected Ambient Works, Vol. 2"));
            Icon icon = new ImageProxy(initialURL);
            imageComponent = new ImageComponent(icon);
            frame.getContentPane().add(imageComponent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Test testDrive = new Test();
    }
}
/**
 * 我们做了什么?
 *      1. 创建用来显示的ImageProxy ,paintIcon()方法会被调用, 而ImageProxy会产生线程取得图像, 并创建ImageIcon
 *          页面  -> paintIcon()  -> ImageProxy ->ImageIcon -> 服务器取图像
 *      2. 某个时间点,图像被返回, ImageIcon被完整的实例化
 *          服务器取图像 ->ImageICon 取回图像
 *      3. 在ImageIcon被创建后, 下次调用到paintIcon()时 , 代理就委托ImageIcon进行.
 */

/**
 * QA环节
 * Q: 远程服务器和虚拟服务器差异非常大, 他们真的是一个模式吗?
 * A: 真实世界中,代理有很多变体,但都有一个共通点: 都会将客户对主题(Subject)施加的方法调用拦截下来. 这种间接的级别让我们可以做很多事情,
 *      包括将请求分发到远程主题; 给创建开销大的对象提供代表; 或者正如你将要看到的 , 提供某些级别的保护, 这种保护能决定那些客户能调用哪些
 *      方法. 一般的代理模式还可以以许多形式使用,后面会简略的介绍几种变体.
 * Q: ImageProxy在我看来好像是Decorator(装饰者). 我的意思是, 我们基本上都是用同一个对象把另一个包起来, 然后把调用委托给ImageIcon.我这样说
 *      有什么问题吗?
 * A: 有时他们很像,但是他们的目的不同. 装饰者对对象增加行为, 而代理是控制对象的访问. 也许你会疑问:显示加载中消息难道不是增加行为吗?
 *      某方面看来,这的确算是,但ImageProxy是控制ImageIcon的访问. 如何控制呢? 试想:代理将客户从ImageIcon解耦了, 如果他们之间没有解耦,客户
 *      就必须等到每个图像都被取回,然后才能把他绘制在页面上. 代理控制ImageIcon的访问, 以便在图像完全创建之前提供屏幕上的代表. 一旦ImageIcon
 *      被创建,代理就允许访问ImageIcon .
 * Q: 如何让客户使用代理 ,而不是真正的对象?
 * A: 常用的方式是提供一个工厂 ,实例化并返回主题 . 因为这是在工厂方法内发生的, 我们可以用代理包装主题再返回, 而客户不知道也不在乎他使用的是代理还是
 *      真东西
 * Q: 在ImageProxy中, 总是创建新的ImageProxy来去的图像, 即使已经被取回来过 . 能不能把加载放在缓存中呢?
 * A: 你说的其实是"缓存代理" . 缓存代理会维护之前创建的对象, 当收到请求时 , 在可能的情况下返回缓存对象. 本章最后会介绍代理模式的几种变体
 * Q: 我知道代理和装饰者的关系了, 但是适配器呢? 代理和适配器也很类似
 * A: 代理和适配器都是挡在其他对象的前面, 并负责将请求转发给他们. 适配器会改变对象适配的接口, 而代理则实现相同的接口.
 *      有一个额外相似性牵涉到"保护代理". 保护代理可以根据客户的角色来决定是否允许客户访问特定的方法. 所以保护代理可能只提供给客户部分接口, 这就和
 *      某些适配器很像了. 再过几页,我们会看到保护代理
 */

/**
 * 代理 与 装饰者 访谈
 * Z: 事实上,你不过是乔装后的装饰者
 * D: 错! 我控制对象的访问, 你只是装饰对象 ,我的工作比你重要多了
 * Z: 我为对象增加行为, 这会改变对象的行为, 你说这不重要?
 * D: 我是代表对象, 不是装饰对象
 * Z: 你可以说是:代表:  ,但如果看着像鸭子, 走着像鸭子,,,,看看虚拟代理, 他只是加入行为的另一种方式, 在创建开销大的对象时做一些事情, 还有远程代理,
 *      就是一种和远程对象沟通的方法, 这样客户不用操心了. 你全都是关于行为,就像我说的
 * D: 你还是没搞懂. 我代表对象, 不只是在对象上加上动作. 客户使用我作为真正主题的替身, 因为我可以保护对象避免不想要的访问, 也可以避免在加载大对象的过程
 *      中GUI会挂起, 或者隐藏主题在远程运行的事实. 我的意图和你差别很大
 * Z: 我事先和所包装对象相同的接口, 你不也是一样吗?
 * D: 你说你包装了一个对象, 有时我们非正式的说: 代理包装了他的主题. 这说法并不准确.
 *      想想远程代理, 我包装了什么对象? 我所代表和控制访问的对象在另外一台机器上!
 *      拿 虚拟代理来说. 当客户第一次用我做代理时 ,主题甚至还不存在呢, 我又包装了什么?
 * Z: 我才你要说 对象其实是你创建的
 * D: 当然有时候我会创建对象, 不然你以为虚拟代理是怎么取得主题的 ! ok, 你刚刚指出了我们之间的一个大差异: 我们都知道装饰者只能装饰点缀, 你们从来不会实例化
 *      任何东西.
 * Z: 我倒想看看你有没有能耐将一个对象包装十层 !
 * D: 你很少看到代理将一个主题包装多次. 实际上, 如果真的把某些对象包装十次, 最好重新检查你的设计.
 */














