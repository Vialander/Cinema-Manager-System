package Dao;

import Entity.Cinema;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface CinemaDao {
    public abstract int addCinema(Cinema cinema);
    public abstract int delCinema(Cinema cinema);
    public abstract int updateCinema(Cinema cinema);
    public abstract Cinema getCinema(int cinemaId);
    public abstract ArrayList<Cinema> getAllCinema();
    public abstract ArrayList<Cinema> selectCinema(String sql, Object[] param);
}
