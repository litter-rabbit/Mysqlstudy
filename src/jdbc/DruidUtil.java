package jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtil {

    private static DataSource dataSource;

    static {
        try{
            Properties pro = new Properties();
            pro.load(DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource=DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        if (dataSource != null) {
            connection=dataSource.getConnection();
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


    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
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

}
