package Entity;

public class User {

    private int userId;

    private String userName;//用户姓名

    private String userPassword;//用户密码

    private double userAccount;

    public User(){}

    public User(int userId, String userName, String userPassword, double userAccount) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAccount = userAccount;
    }

    public User(String userName, String userPassword, double userAccount) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAccount = userAccount;
    }

    public void setUserAccount(double userAccount) {
        this.userAccount = userAccount;
    }

    public double getUserAccount() {
        return userAccount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String toString(){
        return "UserId:"+getUserId()+" UserName:"+getUserName()+" User余额:"+getUserAccount();
    }

}
