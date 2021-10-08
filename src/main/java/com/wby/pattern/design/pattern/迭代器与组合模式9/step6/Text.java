package com.wby.pattern.design.pattern.迭代器与组合模式9.step6;

/**
 * 定义组合模式
 *
 * 我们没有放弃迭代器，他仍然是解决方案的一部分，但是菜单的管理问题已经到了一个迭代器无法解决的新维度。
 *
 * 定义： 允许你将对象组合成树形结构来表现“整体/部分”层次结构。组合能够让客户以一致的方式处理个别对象以及对象组合。
 *
 * 这个模式能够创建一个树形结构，在同一个结构中处理嵌套菜单和菜单项组。通过将菜单和项放在相同的结构中，我们创建了一个“整体/部分”层次结构，由菜单和菜单项组。
 * 一旦有了大菜单，我们就可以使用这个模式来“统一处理个别对象和组合对象”。意味着，我们如果有了树形结构的菜单、子菜单和可能还带有菜单项的子菜单，那么任何一个菜单都是一个组合。
 *
 * 组合模式使我们用树形方式创建对象结构，树包含了组合（可以包含其他菜单，也可以包含菜单项，可以理解为整个树） 以及个别的对象（并未持有其他对象），
 * 使用组合结构，能够把相同的操作应用在组合和个别对象上，即，大多数情况下，我们可以忽略对象组合和个体之间的差别
 */
public class Text {
}
/**
 * 组合模式类图
 */

/**
 * Component为组合中的所有对象定义一个接口，不管是组合还是叶节点。
 * Component为add(),remove(),getChild()和他的操作实现一些默认的行为
 */
interface Component{
    void operation();
    void add(Component component);
    void remove(Component component);
    void getChild(int index);
}

/**
 * 叶节点没有孩子。
 * 叶节点通过实现Composite支持的操作，定义了组合内元素的行为
 */
class Leaf implements Component {
    @Override
    public void operation() { }

    @Override
    public void add(Component component) { }

    @Override
    public void remove(Component component) { }

    @Override
    public void getChild(int index) { }
}
/**
 * Composite定义组件的行为，而这样的组件具有子节点。同时实现了叶节点相关的操作。
 * 注意，其中一些操作可能对Composite意义不大，因此这个情况下可能产生异常。
 */
class Composite implements Component {

    @Override
    public void operation() { }

    @Override
    public void add(Component component) { }

    @Override
    public void remove(Component component) { }

    @Override
    public void getChild(int index) { }
}

/**
 * 客户使用Component接口操作组合中的对象
 */
class Client{
    public static void main(String[] args) {
        Component component;
    }
}

/**
 * QA环节
 * Q:组件，组合，树？？我搞混了
 * A:组合包含组件。组件有两种：组合 和 叶节点元素。 听起来像递归是不是？组合持有一群孩子，这些孩子可以是别的组合或者叶节点元素。当使用此方式组织数据，最终会得到树形结构（由上而下的树形结构）。
 *      根部是一个组合，而组合的分支逐渐向下延伸，直到叶节点为止。
 * Q: 这和迭代器有什么关系/
 * A: 组合模式与迭代器合作无间，很快就能看到在组合的实现中使用迭代器，而且不止一种
 */
