package com.wby.pattern.design.pattern.jdk8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/9/14 09:45
 * @Description:
 */
public class Text {
    public static void main(String[] args) {
        TestInterImpl testInter = new TestInterImpl();
        testInter.repeat(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        });
        testInter.repeat2(3, new Supplier() {
            @Override
            public Object get() {
                return "123";
            }
        });
        testInter.repeat3(3, new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("consumer...."+o);
            }
        });
        People[] list = new People[4];
        list[0]=new People("甜菜");
        list[1]=new People("深田咏美");
        list[2]=new People("猪突猛进");
        list[3]=new People("黄光一闪");
        Arrays.sort(list, Comparator.comparing(People::getName).thenComparing(People::getName));
        Arrays.sort(list,Comparator.comparing(People::getName,(f,s)->Integer.compare(f.length(),s.length())));
        Arrays.sort(list,Comparator.comparing(People::getName, Comparator.comparingInt(String::length)));
        Arrays.sort(list,Comparator.comparingInt(p->p.getName().length()));

    }
}
interface TestInterface{
    void repeat(int n,Runnable runnable);
    void repeat2(int n, Supplier supplier);
    void repeat3(int n, Consumer consumer);
}
class TestInterImpl implements TestInterface {

    @Override
    public void repeat(int n, Runnable runnable) {
        for (int i = 0; i < n; i++) {
            runnable.run();
        }
    }

    @Override
    public void repeat2(int n, Supplier supplier) {
        for (int i = 0; i < n; i++) {
            supplier.get();
        }
    }

    @Override
    public void repeat3(int n, Consumer consumer) {
        for (int i = 0; i < n; i++) {
            consumer.accept(i);
        }
    }
}
class People{
    private String name;

    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 静态内部类
 */
class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++){
            d[i] = 100 * Math.random();
        }
        ArrayAlg.Pair p = ArrayAlg.minmax(d);
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

class ArrayAlg {
    public static class Pair {
        private double first;
        private double second;

        public Pair(double f, double s) {
            first = f;
            second = s;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    public static Pair minmax(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double v : values) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
