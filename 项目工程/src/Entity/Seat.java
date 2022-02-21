package Entity;

public class Seat {

    private int userId;//座位主人Id

    private String userName;//座位主人名

    int fromWhichRoom;//所属厅号

    String fromWhichCinema;//所属影院

    int status;//座位状态 0禁用 1无人选中 2有人选中

    int colume;//列号

    int row;//行号

    public Seat(){}
    public Seat(int fromWhichRoom, String fromWhichCinema, int row, int colume) {
        this.userId = 0;
        this.userName = null;
        this.fromWhichRoom = fromWhichRoom;
        this.fromWhichCinema = fromWhichCinema;
        this.status = 1;
        this.colume = colume;
        this.row = row;
    }

    public Seat(int userId, String userName, int fromWhichRoom, String fromWhichCinema, int status, int row, int colume) {
        this.userId = userId;
        this.userName = userName;
        this.fromWhichRoom = fromWhichRoom;
        this.fromWhichCinema = fromWhichCinema;
        this.status = status;
        this.colume = colume;
        this.row = row;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setColume(int colume) {
        this.colume = colume;
    }

    public void setFromWhichCinema(String fromWhichCinema) {
        this.fromWhichCinema = fromWhichCinema;
    }

    public void setFromWhichRoom(int fromWhichRoom) {
        this.fromWhichRoom = fromWhichRoom;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public int getColume() {
        return colume;
    }

    public int getFromWhichRoom() {
        return fromWhichRoom;
    }

    public int getRow() {
        return row;
    }

    public int getStatus() {
        return status;
    }

    public String getFromWhichCinema() {
        return fromWhichCinema;
    }

    public String toString(){
        return "座位状态:"+getStatus()+" 使用者姓名:"+getUserName()+" 使用者ID:"+getUserId()+" 影厅ID:"+getFromWhichRoom();
    }

}
