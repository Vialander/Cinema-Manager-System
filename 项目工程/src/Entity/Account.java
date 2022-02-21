package Entity;
import java.util.*;

public class Account {

    private int userId;
    private String userName;
    private int cinemaId;
    private int roomId;
    private Date timeOn;
    private int seatRow;
    private int seatColumn;
    public Account(){

    }

    public Account(int userId, String userName, int cinemaId, int roomId, Date timeOn, int seatRow, int seatColumn) {
        this.userId = userId;
        this.userName = userName;
        this.cinemaId = cinemaId;
        this.roomId = roomId;
        this.timeOn = timeOn;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public Date getTimeOn() {
        return timeOn;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public void setTimeOn(Date timeOn) {
        this.timeOn = timeOn;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", cinemaId=" + cinemaId +
                ", roomId=" + roomId +
                ", timeOn=" + timeOn +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                '}';
    }

}
