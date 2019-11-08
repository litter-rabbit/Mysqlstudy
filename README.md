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
         2)show create databsses xxxx;查看已经创建的数据库
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
            
      
