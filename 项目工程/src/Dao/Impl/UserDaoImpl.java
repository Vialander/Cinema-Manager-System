package Dao.Impl;

import Dao.BaseDao;
import Dao.UserDao;
import Entity.Seat;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class UserDaoImpl extends BaseDao implements UserDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int updateUser(User user){
        String sql = "update User set user_password = ?, user_account = ? where user_id = ? and user_name = ?";
        Object[]param = {user.getUserPassword(),user.getUserAccount(),user.getUserId(),user.getUserName()};
        return executeSql(sql,param);
    }

    public int delUser(User user){
        String sql = "delete from User where user_id = ? and user_name = ?";
        Object[]param = {user.getUserId(),user.getUserName()};
        return executeSql(sql,param);
    }

    public int addUser(User user){
        String sql = "insert into User values(?,?,?,?)";
        Object[]param = {user.getUserId(),user.getUserName(),user.getUserPassword(),user.getUserAccount()};
        return executeSql(sql,param);
    }

    public ArrayList<User> getAllUser(){
        ArrayList<User> userArrayList = new ArrayList<User>();
        try{
            String sql = "select * from User";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserPassword(rs.getString(3));
                user.setUserAccount(rs.getDouble(4));
                userArrayList.add(user);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return userArrayList;
    }

    public ArrayList<User> selectUser(String sql, Object[] param){
        ArrayList<User> userArrayList = new ArrayList<User>();
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if(param!=null){
                for(int i = 0; i < param.length; i++){
                    ps.setObject(i+1,param[i]);
                }
            }
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserPassword(rs.getString(3));
                user.setUserAccount(rs.getDouble(4));
                userArrayList.add(user);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return userArrayList;
    }

}
