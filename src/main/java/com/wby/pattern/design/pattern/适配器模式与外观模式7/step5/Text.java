package com.wby.pattern.design.pattern.适配器模式与外观模式7.step5;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 将枚举适配到迭代器
 * 目标接口                               被适配者接口
 * Iterator:                            Enumeration
 *      hasNext()                       hasMoreElements()
 *      next()                          nextElement()
 *      remove():上两个方法可以映射,这个方法在枚举中没有类似的方法,该如何映射呢?
 *
 * 设计适配器
 *      Iterator: hasNext(),next(),remove()
 *      EnumerationIteratorAdaptor:hasNext(),next(),remove()
 *      Enumration:hasMoreElements(),nextElement()
 *
 * 处理remove方法
 *      枚举不支持删除,因为枚举是"只读"接口.适配器无法实现一个由实际功能的remove()方法,最多只能跑出一个运行时异常.幸运的是,迭代器接口的设计者
 *          考虑了这种情况,将remove方法定义成会抛出UnsupportedOperationException
 */
class EnumerationIterator implements Iterator {
    Enumeration enumeration;

    //利用组合将枚举结合进适配器,所以用一个实例变量记录枚举
    public EnumerationIterator(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
public class Text {
}
