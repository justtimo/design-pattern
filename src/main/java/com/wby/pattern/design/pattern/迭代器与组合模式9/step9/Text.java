package com.wby.pattern.design.pattern.迭代器与组合模式9.step9;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * 我们已经在print（）方法内部实现了使用迭代器,除此之外，如果服务员需要我们也能让他使用迭代器遍历整个组合。
 * 想实现一个组合迭代器，需要为每个组件添加createIterator()方法。
 */
abstract class MenuComponent{
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    public MenuComponent getChild(int index){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        throw new UnsupportedOperationException();
    }
    public String getDesc(){
        throw new UnsupportedOperationException();
    }
    public double getprice(){
        throw new UnsupportedOperationException();
    }
    public boolean isVegetarian(){
        throw new UnsupportedOperationException();
    }

    public void print(){
        throw new UnsupportedOperationException();
    }
    //新增方法
    public abstract Iterator<MenuComponent> createIterator();
}

/**
 *
 */
class Menu extends MenuComponent {
    Iterator<MenuComponent> iterator = null;
    ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //CompositeIterator迭代器知道如何遍历任何组合。我们将目前组合的迭代器传入他的构造器
    public Iterator<MenuComponent> createIterator() {
        if (iterator == null) {
            iterator = new CompositeIterator(menuComponents.iterator());
        }
        return iterator;
    }


    public void print() {
        System.out.print("\n" + getName());
        System.out.println(", " + getDescription());
        System.out.println("---------------------");

        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();
        }
    }
}

/**
 *
 */
class MenuItem extends MenuComponent {

    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name,
                    String description,
                    boolean vegetarian,
                    double price)
    {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    //
    public Iterator<MenuComponent> createIterator() {
        return new NullIterator();
    }

    public void print() {
        System.out.print("  " + getName());
        if (isVegetarian()) {
            System.out.print("(v)");
        }
        System.out.println(", " + getPrice());
        System.out.println("     -- " + getDescription());
    }

}

/**
 * 遍历组件内的菜单项，而且确保所有的子菜单（以及子子菜单。。）都被包括进来
 * 实现java。util的Iterator接口
 */
class CompositeIterator implements Iterator/*<MenuComponent> */{
    Stack<Iterator<MenuComponent>> stack = new Stack<Iterator<MenuComponent>>();

    //将我们要遍历的顶层组合的迭代器传入。我们把他抛进一个堆栈数据结构中。
    public CompositeIterator(Iterator<MenuComponent> iterator) {
        stack.push(iterator);
    }

    public Object/*MenuComponent*/ next() {
        //调用hasNext()来确定是否还有下一个
        if (hasNext()) {
            Iterator/*<MenuComponent>*/ iterator = (Iterator)stack.peek();
            //有下一个元素，我们就从堆栈中取出目前的迭代器，然后取得他的下一个元素
            MenuComponent component = (MenuComponent)iterator.next();
            //如果元素是一个菜单，我们有了另一个需要被包含进遍历中的组合，所以我们将他丢进堆栈中。不管是不是菜单，我们都返回组件。
            if (component instanceof Menu){
                stack.push(component.createIterator());
            }
            /*stack.push(component.createIterator());*/
            return component;
        } else {
            return null;
        }
    }

    public boolean hasNext() {
        //想知道是否还有下一个元素，我们检查堆栈是否清空；如果已经空了，就表示没有下一个元素。
        if (stack.empty()) {
            return false;
        } else {
            //否则，我们就从堆栈的顶层中取出迭代器，看看是否还有下一个元素。如果他没有元素，我们将它弹出堆栈，然后递归调用hasNext()
            Iterator/*<MenuComponent>*/ iterator = (Iterator)stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                //否则表示还有下一个元素，返回true。
                return true;
            }
        }
    }
}

/**
 * 空迭代器。
 *
 * 选择一： 返回null
 * 选择二： 返回一个迭代器，而这个迭代器的hasNext（）永远返回false。
 */
class NullIterator implements Iterator<MenuComponent> {

    public MenuComponent next() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }
}

/**
 * 为什么遍历组合好像比以前为MenuComponent类的print()写过的遍历代码复杂。
 *
 * 在我们写MenuComponent类的print()方法的时候，我们利用了一个迭代器来遍历组件内的每个项。如果遇到的是菜单(而不是菜单项)，我们就会递归地调用print()方法处理它。
 * 即，MenuComponent是在“内部”自行处理遍历。
 *
 * 上页代码中，我们实现的是一个“外部”迭代器，所以需要追踪的事情。外部迭代器必须维护它在遍历中的位置，以便外部客户可以通过调用hasNext()和next()来驱动遍历。
 * 例子中，我们代码必须维护组合递归结构的位置。这也就是为什么当我们在组合层次结构中上上下下时，使用堆栈来维护我们的位置。
 */

/**
 * 服务员
 */
class Waitress {
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        allMenus.print();
    }

    //此方法取得allMenus的组合并得到它的迭代器作为我们的CompositeIterator。
    //我们在菜单上实现isVegetarian()方法，让它永远抛出异常。如果异常真的发生，我们就是捕捉异常，然后遍历。
    public void printVegetarianMenu() {
        Iterator<MenuComponent> iterator = allMenus.createIterator();

        System.out.println("\nVEGETARIAN MENU\n----");
        while (iterator.hasNext()) {
            //遍历组合内的每个元素
            MenuComponent menuComponent = iterator.next();
            //如果不使用try/catch，可以在调用isVegetarian()方法之前，用instanceof来检查菜单组件的运行时类型，已确定他是菜单项。
            // 但这么做，就因为无法统一处理菜单和菜单项而失去透明性。 另一种做法，也可以该写Menu的isVegetarian()方法，让他返回false，这提供了简单地方案，同时也保持了透明性。
            try {
                //调用每个元素的isVegetarian()，如果未true，就调用他的print（）方法
                //如果是Menu会抛出一个异常，因为他们不支持这个操作
                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            } catch (UnsupportedOperationException e)
            //只有菜单项的print（）方法可以被调用，绝对不能调用菜单（组合）的print（）方法。你能说出原因吗？
            //如果菜单组件不支持这个操作，那我们就对这个异常置之不理。
            { }
        }
    }
}

/**
 * 模式访谈。。
 * Q: 请介绍一下自己
 * A: 当你有数个对象的集合，他们彼此之间有“整体/部分”的关系，并且你想用一致的方式对待这些对象时，你就需要我。
 * Q: 整体/部分 关系指的什么？
 * A: 以图形用户来说，经常看到一个顶层组件包含着其他组件所以你的GUI包含了若干部分，但是当你需要显示她的时候，你认为他是一个整体。你告诉顶层组件显示，由顶层组件负责显示所有相关的部分。
 *      我们称这种包含其他组件的组件为组合对象，而称没有包含其他组件的组件成为叶节点对象。
 * Q: "一致的方式对待"所有的对象，是什么意思？是指组合和叶节点之间具有共同的方法可以调用？
 * A: 没错。我可以叫组合对象显示或叫叶节点对象显示，他们会各自做正确的事情。组合对象会叫他所有的组件显示
 * Q: 这意味着每个对象都有相同的接口。玩意组合中有些对象的行为不太一样怎么办？
 * A: 为了保持透明性，组合内所有的对象都必须实现相同的接口，否则客户就必须操心哪个对象是用哪个接口，这就失去了组合模式的意义。
 *      这也意味着，有些对象具备一些没有意义的方法调用。
 * Q; 那怎么办？
 * A: 有些方式可以处理这一点。有时候你可以让这样的方法不做事，或者返回null或false。至于用哪种方式，就看哪种在应用中更合乎逻辑。
 *      有时，你可能想采用更激烈的手法，直接抛出异常。当然，客户就要多做一些事情，以确定方法调用不会做意料之外的事情。
 * Q: 如果客户不知道他所处理的对象是哪一种，在不检查类型的情况下，他们如何知道应该调用什么呢？
 * A: 如果有创意，可以将你的方法架构起来，好让默认实现能够做一些有意义的事情。比如，客户端调用了getChild()，对组合来说，这个方法是有意义的。如果你把叶节点想象成没有孩子的对象，这个方法
 *      对叶节点来说也是有意义的。
 * Q: 但是，我听说一些客户很担心这个问题，所以他们对不同的对象用不同的接口，这样，就不会产生没有意义的方法调用。这还算是组合模式吗？
 * A: 是的，这是更安全版本的组合模式，但是这需要客户检查每个对象的类型，然后才进行方法的调用。
 * Q; 说说更多的关于组合和叶节点对象的结构的事。
 * A: 通常是用树形结构。根是顶层的组合，往下是他的孩子，最末端是叶节点。
 * Q: 孩子会不会反向指向他的父亲？
 * A: 是的。组件可以有一个指向父亲的指针，以便在游走时更容易。而且，如果引用某个孩子，你想从树形结构中删除这个孩子，你会需要父亲去删除他。一旦孩子有了指向父亲的引用，这做起来就很容易。
 * Q: 在你的实现上，还真的有很多事情需要考虑。在实现组合模式的时候，还有其他的问题吗？
 * A: 有。 其中之一就是孩子的次序。万一你有一个需要保持特定孩子次序的组合对象，就需要使用更复杂的管理方案来进行孩子的增加和删除，而且当你在这个层次结构内游走时，应该更加小心
 * Q: 很好的观点。
 * A: 你想到过缓存吗？
 * Q: 缓存？
 * A: 有时候，这个组合结构很复杂，或者遍历的代价太高，那么实现组合结点的缓存就很有帮助。比如，你要不断地遍历一个组合，而且他的每个子节点都需要进行某些计算，那么就应该使用缓存来临时保存结果。
 * Q: 组合模式的最大强项是什么？
 * A: 让客户更简单。客户不再需要操心面对的是组合对象还是叶节点对象了，所以就不需要写一堆if语句来保证他们对正确的对象调用了正确的方法。通常只需要对整个结构调用一个方法并执行操作就可以。
 */
public class Text {
}
/**
 * 总结
 *
 * 1. 迭代器允许访问聚合的元素，而不需要暴露他的内部结构。
 * 2. 迭代器将遍历聚合的工作封装进一个对象中。
 * 3. 当使用迭代器的时候，我们依赖聚合提供遍历
 * 4. 迭代器提供了一个通用的接口，让我们遍历聚合的项，当我们编码使用聚合的项时，就可以使用多态机制。
 * 5. 我们应该努力让一个类只分配一个责任
 * 6. 组合模式提供了一个结构，可同时包容个别对象和组合对象
 * 7. 组合模式允许客户对个别对象以及组合对象一视同仁。
 * 8. 组合结构内的任意对象称为组件，组件可以是组合，也可以是叶节点
 * 9. 实现组合模式时，有许多设计上的折中。你要根据需要平衡透明性和安全性
 *
 * 原则： 类应该只有一个改变的理由
 * 迭代器模式： 提供一种方法顺序访问一个聚合对象中的各个元素，而又暴露其内部的表示。
 * 组合模式： 允许你将对象组成树形结构来表现“整体/部分”的层次结构。组合能让客户以一致的方式处理个别对象和对象组合。
 */
