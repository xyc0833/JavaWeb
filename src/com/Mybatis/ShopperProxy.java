package com.Mybatis;

//静态代理

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

////代理商 / 中介  先和顾客讨价还价（前置操作），再喊老板卖瓜
//public class ShopperProxy implements Shopper{
//
//    // 持有老板的引用 → 知道自己要代理哪个老板
//    private final Shopper impl;
//    // 构造器：把老板传给代理商 → 绑定代理关系
//    public ShopperProxy(Shopper impl) {
//        this.impl = impl;
//    }
//    //代理卖瓜的行为
//    @Override
//    public void saleWatermelon(String customer) {
//        //首先进行 代理商讨价还价行为
//        //代理商的前置操作：和顾客讨价还价（额外逻辑）
//        System.out.println(customer + "：哥们，这瓜多少钱一斤啊？");
//        System.out.println("老板：两块钱一斤。");
//        System.out.println(customer + "：你这瓜皮子是金子做的，还是瓜粒子是金子做的？");
//        System.out.println("老板：你瞅瞅现在哪有瓜啊，这都是大棚的瓜，你嫌贵我还嫌贵呢。");
//        System.out.println(customer + "：给我挑一个。");
//
//        // 调用老板的核心方法 → 让老板实际卖瓜
//        impl.saleWatermelon(customer);   //讨价还价成功，进行我们告诉代理商的卖瓜行为
//    }
//}


public class ShopperProxy implements InvocationHandler {

    Object target;
    public ShopperProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String customer = (String) args[0];
        System.out.println(customer + "：哥们，这瓜多少钱一斤啊？");
        System.out.println("老板：两块钱一斤。");
        System.out.println(customer + "：你这瓜皮子是金子做的，还是瓜粒子是金子做的？");
        System.out.println("老板：你瞅瞅现在哪有瓜啊，这都是大棚的瓜，你嫌贵我还嫌贵呢。");
        System.out.println(customer + "：行，给我挑一个。");
        return method.invoke(target, args);
    }
}
