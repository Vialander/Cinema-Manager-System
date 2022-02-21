package Service;

import Entity.Cinema;
import Entity.CinemaSeats;
import Entity.Film;
import Entity.Room;

import java.util.ArrayList;
import java.util.Date;

public interface AdministratorService {

    public abstract ArrayList<CinemaSeats> seeNULLSeat();
    public abstract boolean JudgeCinemaExist(String CinemaName);
    public abstract ArrayList<Room> seeCinema(String CinemaName);
    public abstract void addNewCinema(Cinema newCinema);
    public abstract void deleteOldCinema(String cinemaName);
    public abstract Cinema getCinemaInfo(String cinemaName);

    public abstract ArrayList<Film> getAllOnFilm(Date date);
    public abstract ArrayList<Film> getAllSelectedFilm();
    public abstract int onFilm(String filmName);//上架
    public abstract boolean downFilm(String filmName);//下架

    public abstract void addRoom(Room room);
    public abstract boolean delRoom(int id,int idOfCinema);
    public abstract ArrayList<Room> getCertionRoom(int id,int idOfCinema);

    public abstract boolean addFilmFrame(String datestr,int id,String filmName,double price);
    public abstract int getNextRoomNum();


}
