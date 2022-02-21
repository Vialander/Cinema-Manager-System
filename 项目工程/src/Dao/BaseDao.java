package Dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


public class BaseDao {

    public static String DRIVER; // 数据库驱动

    public static String URL ; // url

    public static String USER; // 数据库用户名

    public static String PASSWORD; // 数据库密码

    Connection conn = null;// 数据连接对象

    static{//静态代码块,在类加载的时候执行
        init();
    }

    /**
     * 初始化连接参数,从配置文件里获得
     */
    public static void init(){
        Properties params=new Properties();
        String configFile = "database.properties";//配置文件路径
        //加载配置文件到输入流中
        InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
        if(is!=null) {
            try {
                //从输入流中读取属性列表
                params.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //根据指定的获取对应的值
            DRIVER=params.getProperty("driver");
            URL=params.getProperty("url");
            USER=params.getProperty("user");
            PASSWORD=params.getProperty("password");
        }else {
            DRIVER="jdbc:mysql://localhost:3306/cinema?useSSL=false&serverTimezone=CTT";
            URL="com.mysql.cj.jdbc.Driver";
            USER="root";
            PASSWORD="123456";
        }
    }




    public Connection getConnection(){
        Connection conn = null;
        try{
            //加载驱动
            Class.forName(DRIVER);
            //建立连接
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            return conn;
        } catch (ClassNotFoundException e){

        } catch (SQLException e){

        }
        return null;
    }

    public void close(AutoCloseable object){
        if(object!=null){
            try{
                object.close();
            } catch (Exception e){

            }
        }
    }

    public int executeSql(String sql, Object[]tmp){
        int num = 0;
        Connection conn = getConnection();
        if(conn == null){
            System.out.println("conn==null");
        }
        PreparedStatement ps = null;
        //System.out.println("进入exe");
        try{
            ps = conn.prepareStatement(sql);
            if(tmp!=null){
                for(int i = 0; i < tmp.length; i++){
                    ps.setObject(i+1,tmp[i]);
                }
            }
            //System.out.println("执行exe前");
            num = ps.executeUpdate();
            //System.out.println("执行exe后");
        } catch (Exception e){
            //System.out.println("发生异常");
            e.printStackTrace();
        } finally{
            this.close(ps);
            this.close(conn);
        }
        return num;
    }

}
