package Dao.Impl;

import Dao.BaseDao;
import Dao.FilmFrameDao;
import Entity.Film;
import Entity.FilmFrame;
import Entity.Seat;
import Tool.ArrayList_to_TwoArray;
import Tool.TwoArray_to_ArrayList;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import java.text.DateFormat;
import com.alibaba.fastjson.JSON;

import java.lang.String;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class FilmFrameDaoImpl extends BaseDao implements FilmFrameDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int updateFilmFrame(FilmFrame filmFrame){
        String sql = "update FilmFrame set onFilm = ?, timeOff = ?, price = ?, seat_status = ?, seat_rows = ?, seat_colomns = ? where cinema_id = ? and room_id = ? and timeon in (?,?) ";
       // net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(filmFrame.getOnFilm());
        String json1 =  JSON.toJSONString(filmFrame.getOnFilm());
        ArrayList arrayList = TwoArray_to_ArrayList.trans(filmFrame.getSeatStatus());
        JSONArray jsonArray = JSONArray.fromObject(arrayList);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String timeonFormat = df.format(filmFrame.getTimeOn()); // Formats a Date into a date/time string.
        String timeonFormat2 = null;
        try {
            Date date = df.parse(timeonFormat);
            date.setTime(date.getTime() + 1000);
            timeonFormat2 = df.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }finally {

        }


        Object[]param = {json1,filmFrame.getTimeOff(),filmFrame.getPrice(),jsonArray.toString(),filmFrame.getSeatRows(),filmFrame.getSeatColomns(),filmFrame.getCinemaId(),filmFrame.getRoomId(),timeonFormat,timeonFormat2};
        return executeSql(sql,param);
    }

    public int delFilmFrame(FilmFrame filmFrame){
        String sql = "delete from FilmFrame where cinema_id = ? and room_id = ? and timeon in (?,?)";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String timeonFormat = df.format(filmFrame.getTimeOn()); // Formats a Date into a date/time string.
        String timeonFormat2 = null;
        try {
            Date date = df.parse(timeonFormat);
            date.setTime(date.getTime() + 1000);
            timeonFormat2 = df.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }finally {

        }
        Object[]param = {filmFrame.getCinemaId(),filmFrame.getRoomId(),timeonFormat,timeonFormat2};
        return executeSql(sql,param);
    }


    public int addFilmFrame(FilmFrame filmFrame){
        String sql = "insert into FilmFrame values(?,?,?,?,?,?,?,?,?)";
        String json1 =  JSON.toJSONString(filmFrame.getOnFilm());
       // net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(filmFrame.getOnFilm());


        ArrayList arrayList = TwoArray_to_ArrayList.trans(filmFrame.getSeatStatus());
        JSONArray jsonArray = JSONArray.fromObject(arrayList);
        Object[]param = {filmFrame.getCinemaId(),filmFrame.getRoomId(),json1,filmFrame.getTimeOn(),filmFrame.getTimeOff(),filmFrame.getPrice(),jsonArray.toString(),filmFrame.getSeatRows(),filmFrame.getSeatColomns()};
        return executeSql(sql,param);
    }
//    public int addFilmFrame(FilmFrame filmFrame){
//        String sql = "insert into FilmFrame values(?,?,?,?,?,?,?,?,?)";
//        //net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(filmFrame.getOnFilm());
//        String json =  JSON.toJSONString(filmFrame.getOnFilm());
//        ArrayList arrayList = TwoArray_to_ArrayList.trans(filmFrame.getSeatStatus());
//        JSONArray jsonArray = JSONArray.fromObject(arrayList);
//        Object[]param = {filmFrame.getCinemaId(),filmFrame.getRoomId(),json.toString(),filmFrame.getTimeOn(),filmFrame.getTimeOff(),filmFrame.getPrice(),jsonArray.toString(),filmFrame.getSeatRows(),filmFrame.getSeatColomns()};
//        return executeSql(sql,param);
//    }

    public ArrayList<FilmFrame> getAllFilmFrame(){
        ArrayList<FilmFrame> filmFrameArrayList = new ArrayList<FilmFrame>();
        try{
            String sql = "select * from FilmFrame";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                FilmFrame filmFrame = new FilmFrame();
                filmFrame.setCinemaId(rs.getInt(1));
                filmFrame.setRoomId(rs.getInt(2));
                //Film对象
                String str1 = rs.getString(3);
            //    net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str1);
            //    Film tmpFilm1 = (Film) net.sf.json.JSONObject.toBean(jsonObject,Film.class);
                Film tmpFilm1 = JSON.parseObject(str1, Film.class);
                filmFrame.setOnFilm(tmpFilm1);
                filmFrame.setTimeOn(new Date(rs.getTimestamp(4).getTime()));
                filmFrame.setTimeOff(new Date(rs.getTimestamp(5).getTime()));
                filmFrame.setPrice(rs.getDouble(6));
                //Seat数组
                int row = rs.getInt(8);
                int colomn = rs.getInt(9);
                String str2 = rs.getString(7);
                List<Seat> list2 = JSONObject.parseArray(str2,Seat.class);
                ArrayList<Seat> arrayList2 = (ArrayList<Seat>)list2;
                filmFrame.setSeatStatus(ArrayList_to_TwoArray.trans(arrayList2,row,colomn));
                filmFrame.setSeatRows(row);
                filmFrame.setCinemaId(colomn);
                filmFrameArrayList.add(filmFrame);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return filmFrameArrayList;
    }

    public ArrayList<FilmFrame> selectFilmFrame(String sql, Object[] param){
        ArrayList<FilmFrame> filmFrameArrayList = new ArrayList<FilmFrame>();
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
                FilmFrame filmFrame = new FilmFrame();
                filmFrame.setCinemaId(rs.getInt(1));
                filmFrame.setRoomId(rs.getInt(2));
                //Film对象
                String str1 = rs.getString(3);
               //// JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"MM/dd/yyyy HH:mm:ss"}) );
               // net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str1);
               // Film tmpFilm1 = (Film) net.sf.json.JSONObject.toBean(jsonObject,Film.class);
                Film tmpFilm1 = JSON.parseObject(str1, Film.class);
                filmFrame.setOnFilm(tmpFilm1);
                filmFrame.setTimeOn(new Date(rs.getTimestamp(4).getTime()));
                filmFrame.setTimeOff(new Date(rs.getTimestamp(5).getTime()));
                filmFrame.setPrice(rs.getDouble(6));
                //Seat数组
                int row = rs.getInt(8);
                int colomn = rs.getInt(9);
                String str2 = rs.getString(7);
                List<Seat> list2 = JSONObject.parseArray(str2,Seat.class);
                ArrayList<Seat> arrayList2 = (ArrayList<Seat>)list2;
                filmFrame.setSeatStatus(ArrayList_to_TwoArray.trans(arrayList2,row,colomn));
                filmFrame.setSeatRows(row);
                filmFrame.setSeatColomns(colomn);
                filmFrameArrayList.add(filmFrame);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return filmFrameArrayList;
    }

    public  FilmFrame getFilmFrame(int cinemaId,int roomId,Date timeon){
        FilmFrame filmFrame = new FilmFrame();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String timeonFormat = df.format(timeon); // Formats a Date into a date/time string.
        String timeonFormat2 = null;
        try {

            Date date = df.parse(timeonFormat);
            date.setTime(date.getTime() + 1000);
            timeonFormat2 = df.format(date);

        }catch (ParseException e){
            e.printStackTrace();
        }

        Object[] tmp = {cinemaId,roomId,timeonFormat,timeonFormat2};
        try{
            String sql = "select * from FilmFrame where cinema_id = ? and room_id  = ? and timeon in (?,?)";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if(tmp!=null){
                for(int i = 0; i < tmp.length; i++){
                    ps.setObject(i+1,tmp[i]);
                }
            }
            rs = ps.executeQuery();
            while(rs.next()){
                filmFrame.setCinemaId(rs.getInt(1));
                filmFrame.setRoomId(rs.getInt(2));
                //Film对象
                String str1 = rs.getString(3);
               // net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(str1);
               // Film tmpFilm1 = (Film) net.sf.json.JSONObject.toBean(jsonObject,Film.class);
                Film tmpFilm1 = JSON.parseObject(str1, Film.class);
                filmFrame.setOnFilm(tmpFilm1);
                filmFrame.setTimeOn(new Date(rs.getTimestamp(4).getTime()));
                filmFrame.setTimeOff(new Date(rs.getTimestamp(5).getTime()));
                filmFrame.setPrice(rs.getDouble(6));
                //Seat数组
                int row = rs.getInt(8);
                int colomn = rs.getInt(9);
                String str2 = rs.getString(7);
                List<Seat> list2 = JSONObject.parseArray(str2,Seat.class);
                ArrayList<Seat> arrayList2 = (ArrayList<Seat>)list2;
                filmFrame.setSeatStatus(ArrayList_to_TwoArray.trans(arrayList2,row,colomn));
                filmFrame.setSeatRows(row);
                filmFrame.setSeatColomns(colomn);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return filmFrame;
    }

}
