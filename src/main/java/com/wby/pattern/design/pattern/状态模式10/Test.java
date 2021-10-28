package com.wby.pattern.design.pattern.状态模式10;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/10/28 18:07
 * @Description: 基本常识: 策略模式和状态模式是双胞胎,在出生时才分开
 */
public class Test {
}


class GumballMachine {
    public static int SOLD_OUT = 0;
    public static int NO_QUARTER = 1;
    public static int HAS_QUARTER = 2;
    public static int SOLD = 3;

    int state = SOLD ;
    int count = 0;
}
