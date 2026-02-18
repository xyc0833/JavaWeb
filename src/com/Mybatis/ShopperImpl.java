package com.Mybatis;

//真实的瓜老板 执行 “实际卖瓜” 的核心操作（不搞虚的，只干活）
public class ShopperImpl implements Shopper {
    @Override
    public void saleWatermelon(String customer) {
        System.out.println("成功出售西瓜给" + customer);
    }

}
