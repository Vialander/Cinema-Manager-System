package Manager;
import java.security.Provider;
import java.util.*;

import Dao.FilmFrameDao;
import Dao.Impl.FilmFrameDaoImpl;
import Entity.*;
import Service.UserService;
import Service.Impl.UserServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class UserManager {
    private User thisUser = new User();
    private UserService service = new UserServiceImpl();

    public void setThisUser(User thisUser) {
        this.thisUser = thisUser;
    }

    public User getThisUser() {
        return thisUser;
    }

    public void setService(UserService service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }

    public void userStartMenu(){
        System.out.println("请输入操作：");
        System.out.println("1.查看新上架电影");
        System.out.println("2.查看影院");
        System.out.println("3.查看订单");
        System.out.println("4.退出登录");
        System.out.print("请选择：");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        try{
            choice = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("请正确输入！");
            userStartMenu();
        }
        switch (choice){
            case 1:
                selectedFilmMenu();
                userStartMenu();
                break;
            case 2:
                cinemaMenu();
                userStartMenu();
                break;
            case 3:
                accountMenu();
                userStartMenu();
                break;
            case 4:
                System.out.println("退出登录成功！\n");
                CinemaManager cinemaManager = new CinemaManager();
                cinemaManager.startMenu();
                break;
            default:
                System.out.println("服务选项错误！");
                userStartMenu();
        }
    }

    public void selectedFilmMenu() {
        service.showSelectedFilms();
        System.out.println("请选择您要观看的电影");
        Scanner input = new Scanner(System.in);
        String str = input.next();
        service.selectSelectedFilms(service.getFilmByName(str));
        System.out.println("请选择您要观看的影院,影厅，和场次(开始时间)(yyyy-MM-dd HH:mm:ss)");
        int c_id = input.nextInt();
        int r_id = input.nextInt();
        String tmp = input.next();
        String timeon = input.nextLine();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {

            date = df.parse(tmp+" "+timeon);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入：1.手动选座 2.自动选座");
        int choice = 0;
        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("请正确输入！");
            userStartMenu();
        }
        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
        switch (choice) {
            case 1:
                service.getIntoChoose(thisUser, filmFrameDao.getFilmFrame(c_id, r_id, date));
                break;
            case 2:
                service.getAutoChoose(thisUser, filmFrameDao.getFilmFrame(c_id, r_id, date));
                break;
            default:
                System.out.println("服务选项错误！");

        }
    }

        public void cinemaMenu() {
            service.showCinema();
            System.out.println("请选择您要去往的影院：");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            System.out.println("该影院正热映：");
            service.showSelectedFilmsInCinema(service.getCinemaByName(str));
            System.out.println("请选择您想看的电影：");
            str = input.next();
            service.selectSelectedFilms(service.getFilmByName(str));
            System.out.println("请选择您要观看的影院,影厅，和场次(开始时间)(yyyy-MM-dd HH:mm:ss)");
            int c_id = input.nextInt();
            int r_id = input.nextInt();
            String tmp = input.next();
            String timeon = input.nextLine();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            try {

                date = df.parse(tmp+" "+timeon);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("请输入：1.手动选座 2.自动选座");
            int choice = 0;
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("请正确输入！");
                userStartMenu();
            }
            FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
            switch (choice) {
                case 1:
                    service.getIntoChoose(thisUser, filmFrameDao.getFilmFrame(c_id, r_id, date));
                    break;
                case 2:
                    service.getAutoChoose(thisUser, filmFrameDao.getFilmFrame(c_id, r_id, date));
                    break;
                default:
                    System.out.println("服务选项错误！");
            }
        }

        public void accountMenu() {
            System.out.println("用户"+thisUser.getUserName()+"的订单信息如下：");
            service.showUserAccount(thisUser);
        }
}