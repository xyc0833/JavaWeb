package com.Mybatis;

//卖瓜的 “规则 / 合同” 规定必须有 “卖西瓜” 这个行为（统一标准）
public interface Shopper {

    //卖瓜行为
    void saleWatermelon(String customer);
}
