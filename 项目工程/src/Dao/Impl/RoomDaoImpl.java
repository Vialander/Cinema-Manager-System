package Dao.Impl;

import Dao.BaseDao;
import Dao.RoomDao;
import Entity.Film;
import Entity.FilmFrame;
import Entity.Room;
import Entity.Seat;
import Tool.ArrayList_to_TwoArray;
import Tool.TwoArray_to_ArrayList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;

import java.awt.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl extends BaseDao implements RoomDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int updateRoom(Room room){
        String sql = "update Room set cinema_name = ?, seat = ?,schedule_time = ?, midColomn = ?, seat_rows = ?, seat_colomns = ? where room_id = ? and cinema_id = ?";
        //com.alibaba.fastjson.JSONArray jsonArray1 = com.alibaba.fastjson.JSONArray.parseArray(JSON.toJSONString(room.getScheduleTime()));

        List<FilmFrame> filmFrames = room.getScheduleTime();
        String jsonArray1 = JSON.toJSONString(filmFrames);

        ArrayList arrayList = TwoArray_to_ArrayList.trans(room.getSeat());
        JSONArray jsonArray2 = JSONArray.fromObject(arrayList);
        Object[]param = {room.getCinemaName(),jsonArray2.toString(),jsonArray1,room.getMidColomn(),room.getSeatRows(),room.getSeatColomns(),room.getRoomId(),room.getCinemaId()};
        return executeSql(sql,param);
    }

    public int delRoom(Room room){
        String sql = "delete from Room where room_id = ? and cinema_id = ?";
        Object[]param = {room.getRoomId(),room.getCinemaId()};
        return executeSql(sql,param);
    }

    public int addRoom(Room room){
        String sql = "insert into Room values(?,?,?,?,?,?,?,?)";
        //com.alibaba.fastjson.JSONArray jsonArray1 = com.alibaba.fastjson.JSONArray.parseArray(JSON.toJSONString(room.getScheduleTime()));

        List<FilmFrame> filmFrames = room.getScheduleTime();
        String jsonArray1 = JSON.toJSONString(filmFrames);

        ArrayList arrayList = TwoArray_to_ArrayList.trans(room.getSeat());
        JSONArray jsonArray2 = JSONArray.fromObject(arrayList);
        Object[]param = {room.getRoomId(),room.getCinemaName(),room.getCinemaId(),jsonArray2.toString(),jsonArray1,room.getMidColomn(),room.getSeatRows(),room.getSeatColomns()};
        return executeSql(sql,param);
    }

    public ArrayList<Room> getAllRoom(){
        ArrayList<Room> roomList = new ArrayList<>();
        try{
            String sql = "select * from Room";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Room room = new Room();
                room.setRoomId(rs.getInt(1));
                room.setCinemaName(rs.getString(2));
                room.setCinemaId(rs.getInt(3));
                int row = rs.getInt(7);
                int colomn = rs.getInt(8);
                //Seat数组
                //String str2 = rs.getString(7);
                //List<Seat> list2 = JSONObject.parseArray(str2,Seat.class);
                //ArrayList<Seat> arrayList2 = (ArrayList<Seat>)list2;
                String str1 = rs.getString(4);
                List<Seat> list1 = JSONObject.parseArray(str1,Seat.class);
                ArrayList<Seat> arrayList1 = (ArrayList<Seat>)list1;
                room.setSeat(ArrayList_to_TwoArray.trans(arrayList1,row,colomn));
                //日程表
                String str2 = rs.getString(5);
                //com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
                List<FilmFrame> list = JSON.parseArray(str2,FilmFrame.class);
                ArrayList<FilmFrame> arrayList = (ArrayList<FilmFrame>)list;
                room.setScheduleTime(arrayList);
                room.setMidColomn(rs.getInt(6));
                room.setSeatRows(row);
                room.setSeatColomns(colomn);
                roomList.add(room);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return roomList;
    }

    public ArrayList<Room> selectRoom(String sql, Object[] param){
        ArrayList<Room> roomList = new ArrayList<>();
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if(param!=null){
                for(int i = 0; i < param.length; i++){
                    ps.setObject(i+1,param[i]);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()){
                Room room = new Room();
                room.setRoomId(rs.getInt(1));
                room.setCinemaName(rs.getString(2));
                room.setCinemaId(rs.getInt(3));
                int row = rs.getInt(7);
                int colomn = rs.getInt(8);
                //Seat数组
                //String str2 = rs.getString(7);
                //List<Seat> list2 = JSONObject.parseArray(str2,Seat.class);
                //ArrayList<Seat> arrayList2 = (ArrayList<Seat>)list2;
                String str1 = rs.getString(4);
                List<Seat> list1 = JSONObject.parseArray(str1,Seat.class);
                ArrayList<Seat> arrayList1 = (ArrayList<Seat>)list1;
                room.setSeat(ArrayList_to_TwoArray.trans(arrayList1,row,colomn));
                //日程表
                String str2 = rs.getString(5);
                //com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
                List<FilmFrame> list = JSON.parseArray(str2,FilmFrame.class);
                ArrayList<FilmFrame> arrayList = (ArrayList<FilmFrame>)list;
                room.setScheduleTime(arrayList);
                room.setMidColomn(rs.getInt(6));
                room.setSeatRows(row);
                room.setSeatColomns(colomn);
                roomList.add(room);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return roomList;
    }

}
