package Entity;

import java.lang.reflect.Array;
import java.util.*;
public class Room {

    private int roomId;//厅号

    private String cinemaName;

    private int cinemaId;

    private Seat[][] seat = null;//0 1

    private ArrayList<FilmFrame> scheduleTime = null;

    private int midColomn;//中线

    private int seatRows;//纵向座位个数

    private int seatColomns;//横向座位个数

    public Room(){}

    public Room(int roomId, String cinemaName, int cinemaId, Seat[][] seat, ArrayList<FilmFrame> scheduleTime, int midColomn, int seatRows, int seatColomns) {
        this.roomId = roomId;
        this.cinemaName = cinemaName;
        this.cinemaId = cinemaId;
        this.seat = seat;
        this.scheduleTime = scheduleTime;
        this.midColomn = midColomn;
        this.seatRows = seatRows;
        this.seatColomns = seatColomns;
    }

    public Room(String nameOfCinema, int idOfCinema, Seat[][] seat, ArrayList<FilmFrame> scheduleTime, int lengthRoom, int wideRoom) {
        this.cinemaName = nameOfCinema;
        this.cinemaId = idOfCinema;
        this.seat = seat;
        this.scheduleTime = scheduleTime;
        this.midColomn = (lengthRoom + wideRoom)/2;
        this.seatRows = lengthRoom;
        this.seatColomns = wideRoom;
    }

    public void setSeat(Seat[][] seat) {
        this.seat = seat;
    }

    public void setSeatRows(int seatRows) { this.seatRows = seatRows; }

    public void setMidColomn(int midColomn) {
        this.midColomn = midColomn;
    }

    public void setSeatColomns(int seatColomns) { this.seatColomns = seatColomns; }

    public int getSeatRows() { return seatRows; }

    public int getMidColomn() {
        return midColomn;
    }

    public int getSeatColomns() { return seatColomns; }

    public Seat[][] getSeat() {
        return seat;
    }

    public void setScheduleTime(ArrayList<FilmFrame> scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public ArrayList<FilmFrame> getScheduleTime() {
        return scheduleTime;
    }

    public void setCinemaId(int cinemaId) { this.cinemaId = cinemaId; }

    public int getCinemaId() { return cinemaId; }

    public void setCinemaName(String cinemaName) { this.cinemaName = cinemaName; }

    public String getCinemaName() { return cinemaName; }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId=roomId;
    }

    public String toString(){
        return "RoomId:"+getRoomId()+" CinemaId:"+getCinemaId()+" CinemaName:"+getCinemaName();
    }

}
