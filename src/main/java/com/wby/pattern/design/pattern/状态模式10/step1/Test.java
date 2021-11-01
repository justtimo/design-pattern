package com.wby.pattern.design.pattern.状态模式10.step1;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/10/28 18:07
 * @Description: 基本常识: 策略模式和状态模式是双胞胎,在出生时才分开
 */
class GumballMachine {
    public static int SOLD_OUT = 0;
    public static int NO_QUARTER = 1;
    public static int HAS_QUARTER = 2;
    public static int SOLD = 3;

    int state = SOLD_OUT ;
    /**
     * 库存
     */
    int count = 0;

    //构造方法
    public GumballMachine(int count) {
        this.count = count;
        // 库存不为0 ,进入 ' 没有25分钱' 的状态 . 库存为0进入 售罄 状态
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    // 投币
    public void insertQuarter(){
        if (state == HAS_QUARTER) {
            System.out.println("不要重复投币");
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("成功投币");
        } else if (state == SOLD_OUT) {
            System.out.println("已售罄 ,禁止投币");
        } else if (state == SOLD) {
            System.out.println("请稍等 , 状态转换中");
        }
    }

    //退钱
    public void ejectQuarter(){
        if (state == HAS_QUARTER) {
            System.out.println("退钱");
            state = NO_QUARTER ;
        } else if (state == NO_QUARTER) {
            System.out.println("未投币 ,不退钱");
        }else if (state == SOLD) {
            System.out.println("已转动手柄 拿到糖果, 不能退钱");
        } else if (state == SOLD_OUT) {
            System.out.println("已售罄,不接受投币, 无需退钱");
        }
    }

    //转动手柄
    public void  turnCrank(){
        if (state == SOLD){
            System.out.println("不要重复转动手柄");
        } else if (state == NO_QUARTER) {
            System.out.println("转手柄 : 先投币");
        } else if (state == SOLD_OUT) {
            System.out.println("转手柄 : 已售罄 ,不在出糖果");
        } else if (state == HAS_QUARTER) {
            System.out.println("转手柄 : 准备出糖果啦");
            state = SOLD ;
            dispense();
        }
    }

    //发放糖果
    public  void  dispense() {
        if (state == SOLD) {
            System.out.println("给你糖果");
        } else if (state == NO_QUARTER) {
            System.out.println("先付钱");
        } else if (state == SOLD_OUT) {
            System.out.println("每趟过了");
        } else if (state == HAS_QUARTER) {
            System.out.println("没糖果了");
        }
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state +
                ", count=" + count +
                '}';
    }
}

public class Test {
    public static void main(String[] args) {
        GumballMachine g = new GumballMachine(5);
        System.out.println(g);

        g.insertQuarter();
        g.turnCrank();
        System.out.println(g);

        g.insertQuarter();
        g.ejectQuarter();
        g.turnCrank();
    }
}
