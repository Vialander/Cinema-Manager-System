package Entity;

import java.util.*;
public class FilmFrame {

    private int CinemaId;

    private int RoomId;

    private int startTime;

    private Film onFilm;//上映的电影

    private Date timeOn;//开始播放时间

    private Date timeOff;//结束时间

    private double price;

    private Seat[][] seatStatus = null;// 0 1 2

    private int seatRows;

    private int seatColomns;


    public FilmFrame(){}

    public FilmFrame(int CinemaId, int RoomId, Film onFilm, Date timeOn, Date timeOff, double price, int seatRows, int seatColomns, Seat[][] seatStatus) {
        this.CinemaId = CinemaId;
        this.RoomId = RoomId;
        this.onFilm = onFilm;
        this.timeOn = timeOn;
        this.timeOff = timeOff;
        this.price = price;
        this.seatRows = seatRows;
        this.seatColomns = seatColomns;
        this.seatStatus = seatStatus;
    }
    public void setCinemaId(int cinemaId) { CinemaId = cinemaId; }

    public void setSeatRows(int seatRows) { this.seatRows = seatRows; }

    public void setRoomId(int roomId) { RoomId = roomId; }

    public void setSeatColomns(int seatColomns) { this.seatColomns = seatColomns; }

    public int getCinemaId() { return CinemaId; }

    public int getRoomId() { return RoomId; }

    public int getSeatRows() { return seatRows; }

    public int getSeatColomns() { return seatColomns; }

    public void setTimeOn(Date timeOn) {
        this.timeOn = timeOn;
    }

    public void setTimeOff(Date timeOff) {
        this.timeOff = timeOff;
    }

    public void setOnFilm(Film onFilm) {
        this.onFilm = onFilm;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeatStatus(Seat[][] seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Date getTimeOff() {
        return timeOff;
    }

    public double getPrice() {
        return price;
    }

    public Seat[][] getSeatStatus() {
        return seatStatus;
    }

    public Date getTimeOn() {
        return timeOn;
    }

    public Film getOnFilm() {
        return onFilm;
    }

    public String toString(){
        return "RoomID:"+getRoomId()+" CinemaID:"+getCinemaId()+" Film:"+getOnFilm().toString()+" 票价:"+getPrice();
    }

}
