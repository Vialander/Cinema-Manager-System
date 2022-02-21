package Dao;

import Entity.Film;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface FilmDao {
    public abstract int addFilm(Film film);
    public abstract int delFilm(Film film);
    public abstract int updateFilm(Film film);
    public abstract ArrayList<Film> getAllFilm();
    public abstract ArrayList<Film> selectFilm(String sql, Object[] param);
}
