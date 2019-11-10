# MysqlStudy
mysql详细笔记整理
## 基本语法
### 语法格式
    * 语句可以多行也可以单行一分号结尾
    * 不区分大小写，关键字建议使用大写
    * 注释的3种方式
                    1.#
                    2. -- commit(必须有空格)
                    3，/* commit */
### 语法分类
    1) DDL用来定义数据库对象的语句如create,drop,alter
    2) DML用来数据操作数据的语句insert,delete,update
    3) DQL用来查询数据的语句如select,where
    4) DCL用来定义数据库权限语句如GRANT,REVOKE等
    
### DDL
    1.操作数据库
     CRUD;
        C:创建数据库
         1） create database xxxx if not exists;
         2) use xxx;
        R:查询数据库
         1）show databases xxxxx;
         2)show create databases xxxx;查看已经创建的数据库
         3) select database();
        U:修改数据库
          1） alter database xxxx character set utf8;
        D:删除数据库
         1） drop database if exists xxxx(跑路必备);
      2.操作表
       CRUD;
       C:创建表
       1） create table student(
                     id int ,
                     name varchar(32),
                     age int ,
                     score double(4,1),
                     birthday_day date,
                     insert_time timestamp
                );
        2）create table xxx like 要复制的表名
       R:查询表
       U:修改表
        1）修改表名
           alter table xxx rename to newxxx;
        2) 修改表的字符集
            alter table xxx character set utf8;
        3) 添加一列
            alter table xxx add 列名 数据类型；
        4）修改列名称
            alter table xxx change 列名 new列名 new数据类型；
            alter table xxx modify 列名 new数据类型；
        5）删除列
            alter table xxx drop 列名          
       D:删除表
       1） drop table if exists xxx;
### DML
      1.添加数据
            insert into 表名（列名，列名...）values(1，name,....) 
            insert into 表名 values（1,name,...）;所有的列都要写
      2.删除数据
            delete form 表名 where id=xxx;
            delete form 表名 删除表里所有的数据（不推荐）
            truncate table 表名 删除表并重新建立一个空表（推荐） 
      3，修改数据
            update 表名 set age=10,name=xxx where 条件
            upddate 表名 set age=10 (所有的age都将改变)
            
            
 ### DQL
      1.基础查询
            select name,age from 表名；
            select distinct age from 表名（去除重复）
            select math,english,math+ifnull(english,0) from stu(如果为null的话，就赋值为0)
            select math 数学，englist as 英语 from stu;起别名
            
      2.条件查询
            select * from student where age>=10 and age<20;
            selcet * from student where age in(18,29,10);
            select* from student where math is null;（不断为null不能使用=）
            
      3.like语句
            1） _占位符
            2) %匹配所有
            select *from student where name like "%马%"（名字中包含马）
            select* from student where name like "_马%"（名字中第二个字为马的名字）
            
       4.排序查询
            select *from student order by math asc,english desc;(先以数学升序排，再以英语降序排)
            
       5。聚合函数
            1）count计算个数（不包含null的值）
            2）sum计算和
            3）avg计算平均值
            4）min.max计算最大，最小
            select count(id) from student;
       6.分组查询
            select sex,min(math),count(id) from student order by gender;
            把分数大于80的根据性别分成两组，同时分组后人数大于2
            select sex,count(id) from student where math>80 order by gender having count(id)>2;
            1）where 在分组前作用，having在分组后作用
        7.分页查询
            (从0开始后面的3条数据)
            select * from student limit 0,3;
### 多表查询
      会产生笛卡尔积，所以需要限定
     1.内连接查询
        1）隐式：select*from t1,t2 where t1.name=t2.name;
        2)显式：select*from t1 join t2 on t1.id=t2.id;
     2.外连接
       1） select t1.*,t2.name from tmp t1 left join dept t2 on t1.id=t2.id;查询的是左表，右表同理。
     3.子查询
       1) select*from emp where emp.'salary'=(select max(salary) from emp);
       2)多行多列可以作为一个虚拟表
       
       
            
        
### 约束
    1.分类：
        1）非空约束 not null
        2）唯一约束 unique
        3) 外键约束
            constraint 外键名称 froeign key(外键列名称) reference  table(主表列名称)；
            alter table xxx drop froeign key 外键名称
            添加外键约束，设置级联更新
            alter table employee add constrains temp_key_fk forrign key(dep_id) references deparments(id) on update cascade on delete cascade;
            
        4）主键约束 primary key
             alter table student drop primary key(删除主键)
             
             
 ### 事务
    1.mysql事务提交方式
        1）自动提交
        2）手动提交
        *修改默认提交方式 select @@autocommit=1;自动提交，0手动提交
    2.事务的四大特征
        1）原子性：事务是最小单位
        2）持久性：事务提交后，数据库会持久化保存数据
        3）隔离性：多个事务互相隔离
        4）一致性：事务操作后数据保持一致。
    3.事务隔离级别
       *问题
       1）脏读：
       2）不可重复读：
       3）幻读：
        1.读以提交（1，2，3）产生的问题
        2.读已提交（2，3）
        3.可重复读 3
        4.串行化 不产生问题       
        
        
### 数据库设计范式
    1.第一范式：每一列都是不可分割的原子数据
    2.第二范式：在1nf基础上，非码属性必须完全依赖候选码
    3.第三范式在2nf基础上，任何非主属性不依赖其他非主属性
    
### 数据库的备份与还原
    备份:mysqldump -u -p > 保存的路径
    还原：使用数据库 source 文件路径
 
 ### jdbc
    quick start:
```java
        Class.forName("com.mysql.jdbc.Driver");
        //获取connetion对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
        //定义sql语句
        String sql="update student set age=1 where id=1";
        //获取执行sql的语句
        Statement statement=connection.createStatement();
        int count = statement.executeUpdate(sql);
        System.out.println(count);
```    

    1.DriverManager:
        1）注册驱动
        *com.mysql.jdbc.Driver中的静态代码块会自动执行注册函数
        在5。0之后的msyql会自动注册，可以省略Class.forname("jdbc.mysql.jdbc.Driver")
        2）获取数据库连接
        url:jdbc:mysql://localhost/3306/db1连接本地可以省略ip和port
    2.Connection:
        1)获取执行sql的对象
            *Statement  createStatement();
            *PreparedStatement preparedStatement(String sql);
         2)管理事务
            *开启事务：setAutoCommit(false);
            *提交事务:commit();
            *回滚事务 rollback();
    3.Statement:
        1)boolean execute(String sql) 不常用
        2）int executeUpdate(String sql) 执行DML,DDL语句
        3）ResultSet executeQUery(String sql) 执行DQL语句
        
     4.jdbc更新数据demo函数
```java
public static void sql(String sql) {
    Statement statement=null;
    Connection connection=null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
        statement=connection.createStatement();
        int count=statement.executeUpdate(sql);
        System.out.println(count);

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }finally {
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }
}

```   

     5.ResultSet:
     *next()向下移动一行
     *getXXX(int index)获取数据
     *getXXX(String colname);
     
```java
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
```

    5.PreparedStatement:
        1）使用模拟流程
            String sql=”select * from student where id=？“；
            PreparedStatement statement = connecion.preparedSatement(sql);
            stament.setInt(1,1);
            statement.executeQuery();
            statement.close();
     6.事务
        
         


     
     
        
     
                   





    
    
  
    
    
    
             
     
            
            
            
            
