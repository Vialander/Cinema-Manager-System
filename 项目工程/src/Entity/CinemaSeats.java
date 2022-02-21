package Entity;

public class CinemaSeats {

    private String cinemaName;
    private String location;
    private int nullSeatNum;
    private int allSeatNum;

    public CinemaSeats(){}
    public CinemaSeats(String cinemaName,int nullSeatNum,int allSeatNum){
        this.cinemaName = cinemaName;
        this.nullSeatNum = nullSeatNum;
        this.allSeatNum = allSeatNum;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setAllSeatNum(int allSeatNum) {
        this.allSeatNum = allSeatNum;
    }

    public void setNullSeatNum(int nullSeatNum) {
        this.nullSeatNum = nullSeatNum;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public int getAllSeatNum() {
        return allSeatNum;
    }

    public int getNullSeatNum() {
        return nullSeatNum;
    }

    public String getLocation() {
        return location;
    }
}

