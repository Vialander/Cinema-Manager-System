package Entity;


import java.util.*;
public class Film {

    private int filmId;//电影序号

    private String filmName;//电影名

    private String mainActor;//主演演员姓名

    private Double score;//评分

    private String type;//电影类型

    private int    timeLong;//电影时长

    private String country;//出自国家

    private String filmImage;//电影图片

    private Date   startTime;//面世时间

    private Date onDate;//上映时间

    private Date downDate;//下架时间

    public Film(){}

    public Film(int filmId, String filmName, String mainActor, Double score, String type, int timeLong, String country, String filmImage, Date startTime, Date onDate, Date downDate) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.mainActor = mainActor;
        this.score = score;
        this.type = type;
        this.timeLong = timeLong;
        this.country = country;
        this.filmImage = filmImage;
        this.startTime = startTime;
        this.onDate = onDate;
        this.downDate = downDate;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    public void setFilmImage(String filmImage) {
        this.filmImage = filmImage;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setTimeLong(int timeLong) {
        this.timeLong = timeLong;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDownDate() {
        return downDate;
    }

    public Date getOnDate() {
        return onDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Double getScore() {
        return score;
    }

    public int getTimeLong() {
        return timeLong;
    }

    public String getCountry() {
        return country;
    }

    public String getFilmImage() {
        return filmImage;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getMainActor() {
        return mainActor;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return getFilmId()+" "+getFilmName()+" "+getMainActor();
    }

}
