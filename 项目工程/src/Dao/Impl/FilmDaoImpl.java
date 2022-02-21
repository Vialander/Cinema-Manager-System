package Dao.Impl;

import Dao.BaseDao;
import Dao.FilmDao;
import Entity.Film;

import  java.sql.*;
import java.util.*;
import java.util.Date;

public class FilmDaoImpl extends BaseDao implements FilmDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int updateFilm(Film film){
        String sql = "update Film set mainactor = ?, score = ?, type = ?, timelong = ?, country = ?, film_image = ?, startTime = ?, onDate = ?, downDate = ? where film_id = ? and film_name = ?";
        Object[]param = {film.getMainActor(),film.getScore(),film.getType(),film.getTimeLong(),film.getCountry(),film.getFilmImage(),film.getStartTime(),film.getOnDate(),film.getDownDate(),film.getFilmId(),film.getFilmName()};
        return executeSql(sql,param);
    }

    public int delFilm(Film film){
        String sql = "delete from Film where film_id = ? and film_name = ?";
        Object[]param = {film.getFilmId(),film.getFilmName()};
        return executeSql(sql,param);
    }

    public int addFilm(Film film){
        String sql = "insert into Film values(?,?,?,?,?,?,?,?,?,?,?)";
        Object[]param = {film.getFilmId(),film.getFilmName(),film.getMainActor(),film.getScore(),film.getType(),film.getTimeLong(),film.getCountry(),film.getFilmImage(),film.getStartTime(),film.getOnDate(),film.getDownDate()};
        return executeSql(sql,param);
    }

    public ArrayList<Film> getAllFilm(){
        ArrayList<Film> filmyArrayList = new ArrayList<Film>();
        try{
            String sql = "select * from Film";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Film getFilm = new Film();
                getFilm.setFilmId(rs.getInt(1));
                getFilm.setFilmName(rs.getString(2));
                getFilm.setMainActor(rs.getString(3));
                getFilm.setScore(rs.getDouble(4));
                getFilm.setType(rs.getString(5));
                getFilm.setTimeLong(rs.getInt(6));
                getFilm.setCountry(rs.getString(7));
                getFilm.setFilmImage(rs.getString(8));
                getFilm.setStartTime(new Date(rs.getTimestamp(9).getTime()));
                getFilm.setOnDate(new Date(rs.getTimestamp(10).getTime()));
                getFilm.setDownDate(new Date(rs.getTimestamp(11).getTime()));
                filmyArrayList.add(getFilm);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return filmyArrayList;
    }

    public ArrayList<Film> selectFilm(String sql, Object[] param){
        ArrayList<Film> filmyArrayList = new ArrayList<Film>();
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
                Film getFilm = new Film();
                getFilm.setFilmId(rs.getInt(1));
                getFilm.setFilmName(rs.getString(2));
                getFilm.setMainActor(rs.getString(3));
                getFilm.setScore(rs.getDouble(4));
                getFilm.setType(rs.getString(5));
                getFilm.setTimeLong(rs.getInt(6));
                getFilm.setCountry(rs.getString(7));
                getFilm.setFilmImage(rs.getString(8));
                getFilm.setStartTime(new Date(rs.getTimestamp(9).getTime()));
                getFilm.setOnDate(new Date(rs.getTimestamp(10).getTime()));
                getFilm.setDownDate(new Date(rs.getTimestamp(11).getTime()));
                filmyArrayList.add(getFilm);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            close(rs);
            close(ps);
            close(conn);
        }
        return filmyArrayList;
    }

}
