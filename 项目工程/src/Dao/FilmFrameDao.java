package Dao;

import Entity.FilmFrame;

import java.sql.Array;
import java.util.*;

public interface FilmFrameDao {
    public abstract int updateFilmFrame(FilmFrame filmFrame);
    public abstract int delFilmFrame(FilmFrame filmFrame);
    public abstract int addFilmFrame(FilmFrame filmFrame);
    public abstract ArrayList<FilmFrame> getAllFilmFrame();
    public abstract FilmFrame getFilmFrame(int cinemaId,int roomId,Date timeon);
    public abstract ArrayList<FilmFrame> selectFilmFrame(String sql, Object[] param);
}
