package com.jdbc;

import java.sql.*;

public class Transaction_xyc {
    public static void main(String[] args) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_hr","root","xuyong612");
             Statement statement = connection.createStatement()){
            //关闭自动提交，现在将变为我们手动提交
            connection.setAutoCommit(false);
            statement.executeUpdate("insert into user values ('a', 1234)");
            //设置回滚点
            Savepoint savepoint = connection.setSavepoint();
            statement.executeUpdate("insert into user values ('b', 1234)");
            //回滚，撤销前面全部操作
            connection.rollback(savepoint);
            statement.executeUpdate("insert into user values ('c', 1234)");
            //如果前面任何操作出现异常，将不会执行commit()，之前的操作也就不会生效
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
