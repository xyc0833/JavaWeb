package com.jdbc;

import java.lang.reflect.Constructor;
import java.sql.*;

public class JdbcObject {
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_hr","root","xuyong612");
            Statement statement = connection.createStatement()
        ){
            ResultSet set = statement.executeQuery("select * from student");
            while(set.next()){
                //Student student = new Student(set.getInt(1),set.getString(2),set.getString(3));
                //student.say();
                Student student = convert(set,Student.class);
                if(student != null){
                    student.say();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //我们也可以利用反射机制来将查询结果映射为对象，使用反射的好处是，无论什么类型都可以通过我们的方法来进行实体类型映射：
    private static <T> T convert(ResultSet set, Class<T> clazz){
        try {
            //默认获取第一个构造方法
            //获取传入进来的类的构造方法
            Constructor<T> constructor = clazz.getConstructor(clazz.getConstructors()[0].getParameterTypes());   //默认获取第一个构造方法
            Class<?>[] param = constructor.getParameterTypes();  //获取参数列表
            Object[] object = new Object[param.length];  //存放参数
            for (int i = 0; i < param.length; i++) {   //数据库里返回的数据是从1开始的
                object[i] = set.getObject(i+1);
                //如果说返回数据库结果集的数据类型和 构造方法中要求的参数类型不一行 报错
                if(object[i].getClass() != param[i])
                    throw new SQLException("错误的类型转换："+object[i].getClass()+" -> "+param[i]);
            }
            //创建一个新的实例
            return constructor.newInstance(object);
        } catch (ReflectiveOperationException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //实际上，在后面我们会学习Mybatis框架，它对JDBC进行了深层次的封装，
    // 而它就进行类似上面反射的操作来便于我们对数据库数据与实体类的转换。
}
