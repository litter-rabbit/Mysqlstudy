package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //注册驱动
//        Class.forName("com.mysql.jdbc.Driver");
////        //获取connetion对象
////        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
////        //定义sql语句
////        String sql="update student set age=1 where id=1";
////        //获取执行sql的语句
////        Statement statement=connection.createStatement();
////        int count = statement.executeUpdate(sql);
////        System.out.println(count);
            String sql = "insert into student values(4,'meizu',16,120,null,null)";
            String sql1="update student set name='apple' where id=4";
            sql(sql1);
    }


    public static void sql(String sql) throws SQLException {
        Connection connection=JDBCUtil.getConnection();
        Statement statement=connection.createStatement();
        int count=statement.executeUpdate(sql);
        System.out.println(count);
        JDBCUtil.close(statement, connection);
    }



}
