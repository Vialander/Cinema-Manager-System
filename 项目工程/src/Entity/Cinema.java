package Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int cinemaId;

    private String cinemaName;//影院名

    private String location;//影院位置

    private ArrayList<Room> roomList = null;

    public Cinema(){}

    public Cinema(int cinemaId, String cinemaName, String location, ArrayList<Room> roomList) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.location = location;
        this.roomList = roomList;
    }

    public Cinema(String cinemaName, String location, ArrayList<Room> roomList) {
        this.cinemaName = cinemaName;
        this.location = location;
        this.roomList = roomList;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location=location;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    public String toString(){
        return "CinemaID:"+getCinemaId()+" CenimaName:"+getCinemaName()+" Location:"+getLocation();
    }

}
