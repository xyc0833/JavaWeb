package com.jdbc;

import java.sql.*;

public class JDBC_xyc {
    public static void main(String[] args) {
        //1. 通过DriverManager来获得数据库连接
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","xuyong612");
             //2. 创建一个用于执行SQL的Statement对象
             Statement statement = connection.createStatement()){   //注意前两步都放在try()中，因为在最后需要释放资源！
            //3. 执行SQL语句，并得到结果集
            ResultSet set = statement.executeQuery("select * from user");
            //4. 查看结果
            while (set.next()){

                System.out.println(set.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //5. 释放资源，try-with-resource语法会自动帮助我们close
    }
}
