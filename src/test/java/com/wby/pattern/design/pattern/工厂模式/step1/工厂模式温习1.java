package com.wby.pattern.design.pattern.工厂模式.step1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LangWeiXian
 * @Date: 2021/8/14 15:35
 * @Description: 煎饼果子
 */
class JBGZ{
    String name;
    Double price;
    List<zuoliao> zuoliaos=new ArrayList<>();
    List<jiangliao> jiangliaos=new ArrayList<>();

    void prepare(){
        System.out.println("做一个煎饼果子, 用 "+zuoliaos.toArray().toString()+"和 "+jiangliaos.toArray().toString());
    }
    void bake(){
        System.out.println("烤2分钟");
    }
    void box(){
        System.out.println("考好装盘");
    }
}
class JBGZ加根肠 extends JBGZ{
    public JBGZ加根肠() {
        name="煎饼果子加根肠";
        price=6.6;
        zuoliaos.add(new XIANGCHANG());
        zuoliaos.add(new LIJI());
        zuoliaos.add(new ROUSONG());
    }
}
class xiaotuiche{
    JBGZ jbgz;
    JBGZ orderJBGZ(String type){
        jbgz=createJBGZ(type);

        jbgz.prepare();
        jbgz.bake();
        jbgz.box();
        return jbgz;
    }
    JBGZ createJBGZ(String type){
        if (type.equals("加根肠")){
            jbgz= new JBGZ加根肠();
        }
        return jbgz;
    }
}
class zuoliao{}
class XIANGCHANG extends zuoliao{}
class LIJI extends zuoliao{}
class ROUSONG extends zuoliao{}
class jiangliao{}

public class 工厂模式温习1 {
    public static void main(String[] args) {
        xiaotuiche xiaotuiche = new xiaotuiche();
        JBGZ 加根肠 = xiaotuiche.orderJBGZ("加根肠");
        System.out.println(加根肠);
    }
}
