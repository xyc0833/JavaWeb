package com.jdbc;

import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor  // 核心修复：添加无参构造器
@ToString
@EqualsAndHashCode
@Builder
@Data
@Accessors(chain = true)
//有了lombok 就不需要手动写构造方法
public class Student {
    //可以针对特定的属性值修改类型
    //@Getter(AccessLevel.PROTECTED)
    private Integer sid;
    private String name;
    private String sex;


//    public Student(Integer sid, String name, String sex) {
//        this.sid = sid;
//        this.name = name;
//        this.sex = sex;
//    }

    public void say(){
        System.out.println("我叫："+name+"，学号为："+sid+"，我的性别是："+sex);
    }
}