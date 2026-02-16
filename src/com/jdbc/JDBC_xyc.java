package com.jdbc;

import java.io.PrintWriter;
import java.sql.*;

public class JDBC_xyc {
    public static void main(String[] args) {
        //可以设置日志  往控制台输出
        DriverManager.setLogWriter(new PrintWriter(System.out));
        //1. 通过DriverManager来获得数据库连接
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_hr","root","xuyong612");
             //2. 创建一个用于执行SQL的Statement对象
             Statement statement = connection.createStatement()){   //注意前两步都放在try()中，因为在最后需要释放资源！
            //3. 执行SQL语句，并得到结果集
            //执行更新语句返回的结果是int 表示生效的第几行
            //插入
            //int i = statement.executeUpdate("insert into offices values ('abc','shanghai','SH')");
            //更新
            System.out.println(statement.executeUpdate("update offices set address='xyc' where office_id = 12 "));
            ResultSet set = statement.executeQuery("select * from employees");
            //System.out.println("生效的行数" + i);
            System.out.println("============");
            //4. 查看结果
            //首先要明确，select返回的数据类似于一个excel表格
            while (set.next()){
                //一个set对应一行的数据 columnindex表示第一列
                System.out.println(set.getInt(1));
                //每调用一次next()就会向下移动一行，首次调用会移动到第一行
                System.out.println(set.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //5. 释放资源，try-with-resource语法会自动帮助我们close
    }
}
