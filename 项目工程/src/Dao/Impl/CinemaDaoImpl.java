package Dao.Impl;

import Dao.BaseDao;
import Dao.CinemaDao;
import Entity.Cinema;
import Entity.Room;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import  java.sql.*;
import java.util.*;

public class CinemaDaoImpl extends BaseDao implements CinemaDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int updateCinema(Cinema cinema){
        String sql = "update Cinema set location = ?, room_list = ? where cinema_id = ? and cinema_name = ?";
        //com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(JSON.toJSONString(cinema.getRoomList()));

        List<Room> roomList = cinema.getRoomList();
        String jsonStr = JSON.toJSONString(roomList);

        Object[] param = {cinema.getLocation(),jsonStr,cinema.getCinemaId(),cinema.getCinemaName()};
        return executeSql(sql,param);
    }

    public int delCinema(Cinema cinema){
        String sql = "delete from Cinema where cinema_id = ? and cinema_name = ?";
        Object[] param = {cinema.getCinemaId(),cinema.getCinemaName()};
        return executeSql(sql,param);
    }

    public int addCinema(Cinema cinema){
        String sql = "insert into Cinema values(?,?,?,?)";
        //com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(JSON.toJSONString(cinema.getRoomList()));

        List<Room> roomList = cinema.getRoomList();
        String jsonStr = JSON.toJSONString(roomList);

        Object[] param = {cinema.getCinemaId(),cinema.getCinemaName(),cinema.getLocation(),jsonStr};
        return executeSql(sql,param);
    }


    public ArrayList<Cinema> getAllCinema(){
        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
        try{
            String sql = "select * from Cinema";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cinema getCinema = new Cinema();
                getCinema.setCinemaId(rs.getInt(1));
                getCinema.setCinemaName(rs.getString(2));
                getCinema.setLocation(rs.getString(3));
                String str = rs.getString(4);
                List<Room> list = JSONObject.parseArray(str,Room.class);
                ArrayList<Room> arrayList = (ArrayList<Room>)list;
                getCinema.setRoomList(arrayList);

                cinemaList.add(getCinema);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return cinemaList;
    }

    public ArrayList<Cinema> selectCinema(String sql, Object[] param){
        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if(param!=null){
                for(int i = 0; i < param.length; i++){
                    ps.setObject(i+1, param[i]);
                }
            }
            rs = ps.executeQuery();
            while(rs.next()){
                Cinema getCinema = new Cinema();
                getCinema.setCinemaId(rs.getInt(1));
                getCinema.setCinemaName(rs.getString(2));
                getCinema.setLocation(rs.getString(3));
                String str = rs.getString(4);
                List<Room> list = JSONObject.parseArray(str,Room.class);
                ArrayList<Room> arrayList = (ArrayList<Room>)list;
                getCinema.setRoomList(arrayList);
                cinemaList.add(getCinema);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return cinemaList;
    }

    public Cinema getCinema(int cinemaId){
        Cinema getCinema = new Cinema();
        try{
            String sql = "select * from Cinema where cinema_id = " + cinemaId;
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                getCinema.setCinemaId(rs.getInt(1));
                getCinema.setCinemaName(rs.getString(2));
                getCinema.setLocation(rs.getString(3));
                String str = rs.getString(4);
                List<Room> list = JSONObject.parseArray(str,Room.class);
                ArrayList<Room> arrayList = (ArrayList<Room>)list;

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return getCinema;
    }

}
