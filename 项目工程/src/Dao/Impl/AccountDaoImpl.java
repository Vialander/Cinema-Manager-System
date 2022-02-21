package Dao.Impl;

import Dao.AccountDao;
import Dao.BaseDao;
import Entity.Account;

import java.util.*;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class AccountDaoImpl extends BaseDao implements AccountDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public  int updateAccount(Account account){
        String sql = "update account set seat_row = ?, seat_colomn = ? where user_id = ? and user_name = ? and cinema_id = ? and room_id = ? and timeon = ?";
        Object[]param = {account.getSeatRow(),account.getSeatColumn(),account.getUserId(),account.getUserName(),account.getCinemaId(),account.getRoomId(),account.getTimeOn()};
        return executeSql(sql,param);
    }

    public int delAccount(Account account){
        String sql = "delete from account where user_id = ? and user_name = ? and cinema_id = ? and room_id = ? and timeon = ?";
        Object[]param = {account.getUserId(),account.getUserName(),account.getCinemaId(),account.getRoomId(),account.getTimeOn()};
        return executeSql(sql,param);
    }

    public int addAccount(Account account){
        String sql = "insert into account values(?,?,?,?,?,?,?)";
        Object[]param = {account.getUserId(),account.getUserName(),account.getCinemaId(),account.getRoomId(),account.getTimeOn(),account.getSeatRow(),account.getSeatColumn()};
        return executeSql(sql,param);
    }

    public ArrayList<Account> getAllAccount(){
        ArrayList<Account> accountyArrayList = new ArrayList<Account>();
        try{
            String sql = "select * from account";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Account getAccount = new Account();
                getAccount.setUserId(rs.getInt(1));
                getAccount.setUserName(rs.getString(2));
                getAccount.setCinemaId(rs.getInt(3));
                getAccount.setRoomId(rs.getInt(4));

                getAccount.setTimeOn(rs.getDate(5));

                getAccount.setSeatRow(rs.getInt(6));
                getAccount.setSeatColumn(rs.getInt(7));

                accountyArrayList.add(getAccount);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return accountyArrayList;
    }

    public ArrayList<Account> selectAccount(String sql, Object[] param){
        ArrayList<Account> accountyArrayList = new ArrayList<Account>();
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
                Account getAccount = new Account();
                getAccount.setUserId(rs.getInt(1));
                getAccount.setUserName(rs.getString(2));
                getAccount.setCinemaId(rs.getInt(3));
                getAccount.setRoomId(rs.getInt(4));
                getAccount.setTimeOn(new Date(rs.getTimestamp(5).getTime()));
                getAccount.setSeatRow(rs.getInt(6));
                getAccount.setSeatColumn(rs.getInt(7));

                accountyArrayList.add(getAccount);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return accountyArrayList;
    }


}
