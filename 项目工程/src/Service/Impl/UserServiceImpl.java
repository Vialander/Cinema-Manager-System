package Service.Impl;

import Dao.Impl.*;
import Dao.*;
import Entity.*;
import Service.UserService;
import java.text.DateFormat;


import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 用户服务实现类
 */
public class UserServiceImpl implements UserService {
    public  Cinema getCinemaByName(String name){
        CinemaDao cinemaDao = new CinemaDaoImpl();
        List<Cinema> cinemaList = new ArrayList<Cinema>();
        Object[] param = {name};
        cinemaList = cinemaDao.selectCinema("select * from cinema where cinema_name = ?",param);
        Cinema cinema = cinemaList.get(0);
        return cinema;
    }

    //根据名字找电影
    public Film getFilmByName(String name){
        FilmDao filmDao = new FilmDaoImpl();
        List<Film> filmList = new ArrayList<Film>();
        Object[] param = {name};
        filmList = filmDao.selectFilm("select * from selectedfilm where film_name = ?",param);
        Film film = filmList.get(0);
        return film;
    }

    /**
     * 用户服务之购买
     */
    public  void buy(User thisUser, FilmFrame thisFilmFrame,int Row,int Colunm){
        UserDao userDao = new UserDaoImpl();
        thisUser.setUserAccount(thisUser.getUserAccount() - thisFilmFrame.getPrice());
        userDao.updateUser(thisUser);
        AccountDao accountDao = new AccountDaoImpl();
        Account account = new Account(thisUser.getUserId(),thisUser.getUserName(),thisFilmFrame.getCinemaId(),thisFilmFrame.getRoomId(),thisFilmFrame.getTimeOn(),Row,Colunm);
        accountDao.addAccount(account);
    }

    /**
     * 列出所有影院
     */
    public  void showCinema(){
        List<Cinema> cinemaList = new ArrayList<Cinema>();
        CinemaDao cinemaDao = new CinemaDaoImpl();
        cinemaList = cinemaDao.getAllCinema();
        System.out.println("电影院\t地点");
        for(Cinema cinema:cinemaList){
            System.out.print(cinema.getCinemaName());
            System.out.print("\t");
            System.out.print(cinema.getLocation());
            System.out.print("\n");
        }
    }

    /**
     * 得到所有影院
     */
    public List<Cinema> getCinema(){
        List<Cinema> cinemaList = new ArrayList<Cinema>();
        CinemaDao cinemaDao = new CinemaDaoImpl();
        cinemaList = cinemaDao.getAllCinema();
        return cinemaList;
    }




    /**
     * 选择某影院里面包含的所有上架电影
     */
    public  void showSelectedFilmsInCinema(Cinema cinema){
       // List<FilmFrame> filmFrameList = new ArrayList<FilmFrame>();
       // FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
//        filmFrameList = filmFrameDao.selectFilmFrame("select * from filmframe where cinema_id = ?",param);
        List<Film> selectedFilmList = new ArrayList<Film>();
        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        Object[] param = {cinema.getCinemaId()};
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        selectedFilmList = selectedFilmDao.selectFilm("select * from selectedFilm where film_id in (select onfilm->'$.filmId' from filmframe where cinema_id =  ?)",param);
        System.out.println("电影\t主演\t评分\t类型\t时长\t/国家\t上映日期");
        for(Film film:selectedFilmList){
            System.out.print(film.getFilmName());
            System.out.print("\t");
            System.out.print(film.getMainActor());
            System.out.print("\t");
            System.out.print(film.getScore());
            System.out.print("\t");
            System.out.print(film.getType());
            System.out.print("\t");
            System.out.print(film.getTimeLong());
            System.out.print("\t");
            System.out.print(film.getCountry());
            System.out.print("\t");
            System.out.print(df.format(film.getStartTime()));
            System.out.print("\n");
        }
    }




    /**
     * 得到某影院里面包含的所有上架电影
     */
    public  List<Film> getSelectedFilmsInCinema(Cinema cinema){
        // List<FilmFrame> filmFrameList = new ArrayList<FilmFrame>();
        // FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
//        filmFrameList = filmFrameDao.selectFilmFrame("select * from filmframe where cinema_id = ?",param);
        List<Film> selectedFilmList = new ArrayList<Film>();
        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        Object[] param = {cinema.getCinemaId()};

        selectedFilmList = selectedFilmDao.selectFilm("select * from selectedFilm where film_id in (select onfilm->'$.filmId' from filmframe where cinema_id =  ?)",param);
        return selectedFilmList;
    }

    /**
     * 显示所有已经上架的电影
     */
    public void showSelectedFilms(){
        List<Film> selectedFilmList = new ArrayList<Film>();
        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        selectedFilmList = selectedFilmDao.getAllFilm();
        System.out.println("电影\t\t主演\t\t\t评分\t类型\t时长\t国家\t上映日期");
        for(Film film:selectedFilmList){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.print(film.getFilmName());
            System.out.print("\t\t");
            System.out.print(film.getMainActor());
            System.out.print("\t\t\t");
            System.out.print(film.getScore());
            System.out.print("\t");
            System.out.print(film.getType());
            System.out.print("\t");
            System.out.print(film.getTimeLong());
            System.out.print("\t");
            System.out.print(film.getCountry());
            System.out.print("\t");
            System.out.print(df.format(film.getStartTime()));
            System.out.print("\n");
        }
    }



    /**
     * 得到所有已经上架的电影
     */
    public List<Film> getSelectedFilms(){
        List<Film> selectedFilmList = new ArrayList<Film>();
        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        selectedFilmList = selectedFilmDao.getAllFilm();
        return selectedFilmList;
    }




    /**
     * 选择已经显示的已上架电影(根据已上架电影选择场次)
     */
    public void selectSelectedFilms(Film thisFilm){
        List<FilmFrame> filmFrameList = new ArrayList<FilmFrame>();
        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
//        net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(thisFilm);
//        Object[] param = {jsonObject1.toString()};
//        select * from t1 where attributes_json->'$.P2PUser'=1 AND attributes_json->'$.PONUser'=12;
        Object[] param = {thisFilm.getFilmId()};
        filmFrameList = filmFrameDao.selectFilmFrame("select * from filmframe where onfilm->'$.filmId' = ?",param);
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        System.out.println("电影\t\t主演\t\t类型\t时长\t国家\t影院\t影厅\t场次");
        for(FilmFrame filmFrame:filmFrameList){
            System.out.print(filmFrame.getOnFilm().getFilmName());
            System.out.print("\t\t");
            System.out.print(filmFrame.getOnFilm().getMainActor());
            System.out.print("\t\t");
            System.out.print(filmFrame.getOnFilm().getType());
            System.out.print("\t");
            System.out.print(filmFrame.getOnFilm().getTimeLong());
            System.out.print("\t");
            System.out.print(filmFrame.getOnFilm().getCountry());
            System.out.print("\t");
            System.out.print(filmFrame.getCinemaId());
            System.out.print("\t");
            System.out.print(filmFrame.getRoomId() + "号厅");
            System.out.print("\t");
            System.out.print(dateFormat.format(filmFrame.getTimeOn()) + "~"+dateFormat.format(filmFrame.getTimeOff()));
            System.out.print("\n");
        }

    }

//    /**
//     * 得到已上架电影
//     *
//     */
//
//    public List<FilmFrame> getSelectSelectedFilms(Film thisFilm){
//        List<FilmFrame> filmFrameList = new ArrayList<FilmFrame>();
//        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
////        net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(thisFilm);
////        Object[] param = {jsonObject1.toString()};
////        select * from t1 where attributes_json->'$.P2PUser'=1 AND attributes_json->'$.PONUser'=12;
//        Object[] param = {thisFilm.getFilmId()};
//        filmFrameList = filmFrameDao.selectFilmFrame("select * from filmframe where onfilm->'$.filmId' = ?",param);
//        return filmFrameList;
//
//    }






    /**
     * 手动选座
     */
    public void getIntoChoose(User thisUser,FilmFrame thisFilmFrame) {
        if (thisUser.getUserAccount() < thisFilmFrame.getPrice()) {
            System.out.print("余额不足，请您先充值");
        } else {
            System.out.println("该场次情况：");
            Seat[][] seats = thisFilmFrame.getSeatStatus();
            int row = 0;
            int column = 0;
            for (int i = thisFilmFrame.getSeatRows() - 1; i >= 0 ; i--) {
                for (int j = 0 ; j < thisFilmFrame.getSeatColomns(); j++) {
                    if (seats[i][j].getStatus() == 0) {
                        System.out.print("✗");
                    } else if (seats[i][j].getStatus() == 1) {
                        System.out.print("☆");
                    } else {
                        System.out.print("★");
                    }

                }
                System.out.print("\n");
            }
            Scanner input = new Scanner(System.in);
            System.out.println("请选择第几排：");
            row = input.nextInt();
            System.out.println("请选择第几列：");
            column = input.nextInt();
            while (true) {
                if (row > 0 && row <= thisFilmFrame.getSeatRows() && column > 0 && column <= thisFilmFrame.getSeatColomns() && seats[row - 1][column - 1].getStatus() == 1) {
                    break;
                } else {
                    System.out.println("该座位非法！请重新选择第几排：");
                    row = input.nextInt();
                    System.out.println("请选择第几列：");
                    column = input.nextInt();
                }
            }
            seats[row - 1][column - 1].setStatus(2);
            seats[row - 1][column - 1].setUserId(thisUser.getUserId());
            seats[row - 1][column - 1].setUserName(thisUser.getUserName());
            thisFilmFrame.setSeatStatus(seats);
            FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
            filmFrameDao.updateFilmFrame(thisFilmFrame);
            this.buy(thisUser,thisFilmFrame,row,column);
            System.out.println("选座成功");
        }
    }

    /**
     * 自动选择算法
     * @param thisUser
     * @param thisFilmFrame
     * @return
     */
    public  FilmFrame  autoChoose(User thisUser,FilmFrame thisFilmFrame){
        Seat seats[][] = thisFilmFrame.getSeatStatus();
        int row = thisFilmFrame.getSeatRows();
        int column = thisFilmFrame.getSeatColomns();

        int[][] value = new int[row][column];
        List<Integer> nums = new ArrayList<Integer>();
        int num;
        int flag = 1;
        for(int i = 0;i < row ;i++){
            for(int j = 0;j < column ;j++) {
                if (seats[i][j].getStatus() == 1) {
                    value[i][j] = Math.abs(row - 1 - 2 * i) + Math.abs(column - 1 - 2 * j);
                    nums.add(value[i][j]);
                }else{
                    value[i][j] = 99999;
                    nums.add(value[i][j]);
                }
            }
        }
        num = Collections.min(nums);
        if(num == 99999){
            System.out.println("座位已满");
            return thisFilmFrame;
        }
        else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (value[i][j] == num) {
                        seats[i][j].setStatus(2);
                        seats[i][j].setUserId(thisUser.getUserId());
                        this.buy(thisUser,thisFilmFrame,i+1,j+1);
                        System.out.println("选择" + (i + 1) + "排" + (j + 1) + "列");
                        flag = 2;
                        break;
                    }
                }
                if (flag == 2) {
                    break;
                }
            }
            thisFilmFrame.setSeatStatus(seats);
            return thisFilmFrame;
        }
    }


    /**
     * 自动选座
     */
    public void getAutoChoose(User thisUser,FilmFrame thisFilmFrame) {
        if (thisUser.getUserAccount() < thisFilmFrame.getPrice()) {
            System.out.print("余额不足，请您先充值");
        } else {
            System.out.println("该场次情况：");
            Seat[][] seats = thisFilmFrame.getSeatStatus();
            int row = 0;
            int column = 0;
            for (int i = thisFilmFrame.getSeatRows() - 1; i >= 0 ; i--) {
                for (int j = 0 ; j < thisFilmFrame.getSeatColomns(); j++) {
                    if (seats[i][j].getStatus() == 0) {
                        System.out.print("✗");
                    } else if (seats[i][j].getStatus() == 1) {
                        System.out.print("☆");
                    } else {
                        System.out.print("★");
                    }

                }
                System.out.print("\n");
            }
            thisFilmFrame = autoChoose(thisUser,thisFilmFrame);
            FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
            filmFrameDao.updateFilmFrame(thisFilmFrame);
            for (int i = thisFilmFrame.getSeatRows() - 1; i >= 0 ; i--) {
                for (int j = 0 ; j < thisFilmFrame.getSeatColomns(); j++) {
                    if (seats[i][j].getStatus() == 0) {
                        System.out.print("✗");
                    } else if (seats[i][j].getStatus() == 1) {
                        System.out.print("☆");
                    } else {
                        System.out.print("★");
                    }

                }
                System.out.print("\n");
            }
            System.out.println("选座成功");
        }
    }

    /**
     * 登陆
     */
    public boolean login(String userName,String password){
        UserDao userDao = new UserDaoImpl();
        String sql = "select * from user where user_name = ? and user_password = ?";
        Object[]param = {userName,password};
        if(userDao.selectUser(sql,param).size() == 0){
            return false;
        }
        return true;
    }


    /**
     * 登陆
     */
    public User loginUser(String userName,String password){
        List<User> userList = new ArrayList<User>();
        User thisUser = new User();
        UserDao userDao = new UserDaoImpl();
        String sql = "select * from user where user_name = ? and user_password = ?";
        Object[]param = {userName,password};
        userList = userDao.selectUser(sql,param);
        if(userList.size() == 0){
            return null;
        }
        thisUser = userList.get(0);
        return thisUser;
    }

    //获取重名用户
    public boolean getSameUsername(String userName){
        UserDao userDao = new UserDaoImpl();
        String sql = "select * from user where user_name = ?";
        Object[]param = {userName};
        if(userDao.selectUser(sql,param).size() == 0){
            return false;
        }
        return true;
    }

    //注册
    public void regist(User user){
        UserDao userDao = new UserDaoImpl();
        userDao.addUser(user);
    }


    /**
     * 查看该用户订单信息
     */
    public  List<Account>getUserAccount(User thisUser){
        List<Account> accountsList = new ArrayList<Account>();
        AccountDao accountDao = new AccountDaoImpl();
        Object[] param = { thisUser.getUserId(),thisUser.getUserName()};
        accountsList = accountDao.selectAccount("select * from account where user_id = ? , user_name = ?",param);
        return accountsList;
    }


    /**
     * 根据用户获得订单信息
     */
    public  Map<Account,FilmFrame> makeUserAccount(User thisUser){
        Map<Account,FilmFrame> accountFilmFrameMap = new HashMap<>();
        List<Account> accountsList = new ArrayList<Account>();
        FilmFrame filmFrame = new FilmFrame();
        AccountDao accountDao = new AccountDaoImpl();
        Object[] param = { thisUser.getUserId(),thisUser.getUserName()};
        accountsList = accountDao.selectAccount("select * from account where user_id = ? and user_name = ?",param);
        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
        for(Account account : accountsList){
            filmFrame = filmFrameDao.getFilmFrame(account.getCinemaId(),account.getRoomId(),account.getTimeOn());
            accountFilmFrameMap.put(account,filmFrame);
        }
        return accountFilmFrameMap;
    }

    /**
     * 根据订单信息列出当前人员的订单
     */
    public void showUserAccount(User thisUser){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CinemaDao cinemaDao = new CinemaDaoImpl();
        Map<Account,FilmFrame> accountFilmFrameMap = this.makeUserAccount(thisUser);
        for (Map.Entry<Account,FilmFrame> entry : accountFilmFrameMap.entrySet()) {
            System.out.println("顾客:"+entry.getKey().getUserName());
            System.out.println("影院:"+cinemaDao.getCinema(entry.getKey().getCinemaId()).getCinemaName());
            System.out.println("影厅:"+entry.getValue().getRoomId()+"号");
            System.out.println("第:"+entry.getKey().getSeatRow()+"排"+"第"+entry.getKey().getSeatColumn()+"列");
            System.out.println("电影名称："+entry.getValue().getOnFilm().getFilmName());
            System.out.println("票价："+entry.getValue().getPrice());
            System.out.println("开始时间："+df.format(entry.getValue().getTimeOn()));
            System.out.println("结束时间："+df.format(entry.getValue().getTimeOff()));
        }
    }



    public void showSeats(FilmFrame filmFrame){
        Seat[][] seats = filmFrame.getSeatStatus();
        for (int i = filmFrame.getSeatRows() - 1; i >= 0 ; i--) {
            for (int j = 0 ; j < filmFrame.getSeatColomns(); j++) {
                if (seats[i][j].getStatus() == 0) {
                    System.out.print("✗");
                } else if (seats[i][j].getStatus() == 1) {
                    System.out.print("☆");
                } else {
                    System.out.print("★");
                }
            }
            System.out.print("\n");
        }
    }
}
