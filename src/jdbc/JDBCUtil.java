package jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private static String user;
    private static String password;
    private static String url;

    static {
        ClassLoader classLoader=JDBCUtil.class.getClassLoader();
        URL url1=classLoader.getResource("jdbc.properties");

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(url1.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }

    public static Connection getConnection() {
        Connection connection=null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
             connection=DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Statement statement, Connection connection) {
        if(statement!=null){
            try{
                statement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        try{
            if(connection!=null){
                connection.close();
        }
        }catch(SQLException s){
            s.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet,Statement statement, Connection connection) {
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(statement!=null){
            try{
                statement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        try{
            if(connection!=null){
                connection.close();
            }
        }catch(SQLException s){
            s.printStackTrace();
        }

    }
    public static void close(PreparedStatement preparedStatement, Connection connection) {
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        try{
            if(connection!=null){
                connection.close();
            }
        }catch(SQLException s){
            s.printStackTrace();
        }
    }

}
