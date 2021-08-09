package com.wby.pattern.design.pattern.装饰者模式3.step6真实世界的装饰者JAVA的IO;

import java.io.*;

/**
 * 其中很多类都是装饰者.下面是一个典型的对象集合,用装饰者将功能结合起来,以读取文件数据:
 *      需要读取的文件--->
 *      FileInputStream(被装饰的组件,还有其他的:StringBufferInputStream,ByteArrayInputStream等,都提供了最基本的字节读取功能)--->
 *      BufferedInputStream:具体的装饰者,加入两种行为(利用缓冲输入改进性能;用readLine()(一次读取一行文本输入数据)增强接口)---->
 *      LineNumberInputStream(装饰者,加上了计算行数的能力)
 *
 * BufferedInputStream及LineNumberInputStream都扩展自FilterInputStream,而FilterInputStream是一个抽象的装饰类
 *
 * 装饰IO类
 *                                          InputStream:抽象组件
 *   FileInputStream            StringBufferInputStream             ByteArrayInputStream                        FilterInputStream:一个抽象装饰者
 *             这三个InputStream类是可以被装饰者包装起来的具体组件,还有其他的组件:ObjectInputStream                 PushbackInputStream  BufferedInputStream  LineNumberInputStream
 *                                                                                                                      这里有了我们所有的具体装饰者
 *
 * 当把ioAPI范围缩小,与星巴克设计相比没有多大差异."输出流"的设计方式也是一样的,会发现Reader/Writer流(基于字符数据的输入输出)和输入流\输出流的类相当类似(
 *      虽然有差异,但相当雷同)
 * 但是IO也引出装饰者模式的一个"缺点":常常会造成设计中有大量的小类,数量是在太多,可能会造成程序员的困扰.但是你已经了解了装饰者的工作原理,当使用别人大量装饰的API时,
 *      就可以轻易辨别出他们的装饰者类是如何组织的,以便用包装的方式取得想要的行为.
 */
public class Text {
}
/**
* 编写自己的IO装饰者:将所有大写转小写
*/
class LowerCaseInputStream extends FilterInputStream{

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    //针对字节
    @Override
    public int read() throws IOException {
        int c = super.read();
        return c==-1?c:Character.toLowerCase((char)c);
    }

    //针对字节数组
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        for (int i = off; i < off+result; i++) {
            b[i]= (byte) Character.toLowerCase((char)b[i]);
        }
        return result;
    }
}
class TestMyInputStream{
    public static void main(String[] args) throws IOException {
        int c;
        LowerCaseInputStream inputStream =
                new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("src/main/resources/test.txt")));
        while ((c = inputStream.read())>=0){
            System.out.print((char)c);
        }
        inputStream.close();
    }
}
/**'
 * 装饰者访谈
 * A:我有能力为设计注入弹性,但是会在设计中加入大量的小类,会导致别人不容易了解我的设计方式.例如JAVA IO,第一次接触往往无法轻易理解,但是如果能够认识到这些
 *      类都是用来包装InputStream的,就会变得简单很多
 * Q:听起来并不严重,只需要让大家知道怎么用,问题就解决了
 * A:并不止这些,我还有类型问题.如果客户代码中依赖某些特性类型,然后忽然导入装饰者,却又没有考虑到这一点,就会出状况.
 *      我的一个优点是,可以透明的插入装饰者,客户程序不需要知道他是在和装饰者打交道,但是正如刚才所说,有些代码依赖于特殊类型,一旦导入装饰者就会出问题
 * Q:相信每个人都会了解到:插入装饰者时,必须小心谨慎.
 * A:我还有一个问题,采用装饰者在实例化组件时,会增加代码的复杂度,一旦使用,不只需要实例化组件,还要把组件包装进装饰者中,天知道有几个
 * Q:下次我会访谈工厂模式和生成器模式,这回对你这个问题很有帮助
 * Q:你是一个好的模式,适合用来建立有弹性的设计,维持开放-关闭原则
 */

/** `
 * 装饰者模式要点:
 *      继承属于扩展形式之一,但不见得是达到弹性设计的最佳方式
 *      设计中,应该允许行为可以被扩展,而无需修改现在代码
 *      组合和委托可用于在运行时动态的加上新行为
 *      除了继承,装饰者模式也可以扩展我们的行为
 *      装饰者模式意味着一群装饰者类,这些类用来包装具体组件
 *      装饰者类反映出被装饰的组件类型(事实上,他们具有相同的类型,都经过接口或继承实现)
 *      装饰者可以在被装饰者的行为钱/后加上自己的行为,甚至将被装饰者的行为整个取代掉,而达到特定的目的.
 *      你可以用无数个装饰者包装一个组件
 *      装饰者一般对组件的客户是透明的,除非程序依赖于组建的具体类型
 *      装饰者会导致设计中出现很多小类,如果过度使用,会导致程序变得复杂
 */

















