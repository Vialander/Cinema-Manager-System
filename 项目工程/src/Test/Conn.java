package Test;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

import Dao.*;
import Dao.Impl.*;
import Entity.*;
import Manager.CinemaManager;
import Service.Impl.UserServiceImpl;
import Service.UserService;
import Tool.ArrayList_to_TwoArray;
import Tool.TwoArray_to_ArrayList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
//import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import Dao.Impl.*;
import Dao.*;
import Entity.*;


public class Conn {
    public static void main(String[] args) {
//        //dao层测试
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        try{
////            //1 prepare for film
////            int id = 3;
////            String name = "Help me 好";
////            String mainactor = "Jack";
////            double score = 5.0;
////            String type = "love";
////            int timelong = 125;
////            String country = "China";
////            String pictureURL = "http:\\www.baidu.com";
////            Date startdate = sdf.parse("2018-06-06 12:10:00");
////            Date ondate = sdf.parse("2018-06-07 12:10:00");
////            Date downdate = sdf.parse("2018-07-07 12:10:00");
////            Film film1 = new Film(id,name,mainactor,score,type,timelong,country,pictureURL,startdate,ondate,downdate);
////
////            Seat seat1 = new Seat(1,"A",2,"wanda",2,1,1);
////            Seat seat2 = new Seat(2,"B",2,"wanda",2,1,2);
////            Seat seat3 = new Seat(3,"C",2,"wanda",2,2,1);
////            Seat seat4 = new Seat(4,"D",2,"wanda",2,2,2);
////            Seat[][] seats = {{seat1,seat2},{seat3,seat4}};
////            //Seat[][]seats = new Seat[2][2];
////            int row = 2;
////            int colomn = 2;
////            Date time1 = sdf.parse("2018-12-6 12:09:29");
////            Date time2 = sdf.parse("2018-12-6 14:15:20");
////
////            FilmFrame filmFrame = new FilmFrame(1,1,film1,time1,time2,20,2,2,seats);
////            ArrayList<FilmFrame> filmFrameArrayList = new ArrayList<>();
////            filmFrameArrayList.add(filmFrame);
////
////            Room room = new Room(1,"wanda",2,seats,filmFrameArrayList,6,2,2);
////
////            RoomDao roomDao = new RoomDaoImpl();
////            roomDao.updateRoom(room);
////
////            ArrayList<Room> getRoom = roomDao.getAllRoom();
////            System.out.println(getRoom.get(0).getCinemaId());
////
////            CinemaDao cinemaDao = new CinemaDaoImpl();
//////            Cinema cinema = new Cinema(1,"wanda","beijing",getRoom);
//////            cinema.setLocation("shanghai");
//////            cinemaDao.updateCinema(cinema);
////
////            ArrayList<Cinema> arrayList = cinemaDao.getAllCinema();
////            System.out.println(arrayList.get(0).getLocation());
////
////
////        } catch (Exception e){
////            e.printStackTrace();
////        }
//
//
////        UserService Service = new UserServiceImpl();
////        Scanner input = new Scanner(System.in);
////        int num;
////        User thisuser = null;
////        System.out.println("请输入您要选择的操作：");
////        System.out.println("1.登录");
////        System.out.println("2.注册");
////        System.out.println("3.退出");
////        num = input.nextInt();
////        switch (num){
////            case 1:
////                thisuser = Service.login();
////                thisuser.setUserAccount(8000);
////                break;
////            case 2:
////                Service.login();
////                break;
////            case 3:
////                System.exit(0);
////                break;
////        }
//        User user = new User(1,"cxk","1234",8000);
//        UserDao userDao = new UserDaoImpl();
//        userDao.updateUser(user);
//        Date date1 = new Date();
//        Date date2 = new Date();
//        Date date3 = new Date();
//        Seat[][] seats = new Seat[2][2];
//        seats[0][0] = new Seat(1, "范神影院", 1, 1);
//        seats[0][1] = new Seat(1, "范神影院", 1, 2);
//        seats[1][0] = new Seat(1, "范神影院", 2, 1);
//        seats[1][1] = new Seat(1, "范神影院", 2, 2);
//
//        Film film = new Film(1, "电影名字", "主演1/主演2", 9.27, "惊悚", 118, "美国", "http：//aaaa", date1, date2, date3);
//        FilmFrame filmFrame = new FilmFrame(1, 1, film, date1, date2, 1000, 2, 2, seats);
//        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
//        filmFrameDao.addFilmFrame(filmFrame);
//        //Service.selectSelectedFilms(film);
//        FilmDao filmdao = new FilmDaoImpl();
//        filmdao.addFilm(film);
//        ArrayList<Room> roomList = new ArrayList<Room>();
//        ArrayList<FilmFrame> filmFrameList = new ArrayList<FilmFrame>();
//        filmFrameList.add(filmFrame);
//        Room room1 = new Room(1, "范神影院", 1, seats, filmFrameList, 3, 2, 2);
//        Room room2 = new Room(2, "范神影院", 1, seats, filmFrameList, 3, 2, 2);
//        Room room3 = new Room(3, "范神影院", 1, seats, filmFrameList, 3, 2, 2);
//        roomList.add(room1);
//        roomList.add(room2);
//        roomList.add(room3);
//        Cinema cinema = new Cinema(1, "范神影院", "人民大街", roomList);
//        CinemaDao cinemaDao = new CinemaDaoImpl();
//        cinemaDao.addCinema(cinema);
//        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
//        selectedFilmDao.addFilm(film);
        CinemaManager cinemaManager = new CinemaManager();
        cinemaManager.showInfo();
        cinemaManager.startMenu();

//1
//
//        Service.getAutoChoose(thisuser,filmFrame);
//
//
//        Service.showCinema();
//       // Service.showSelectedFilmsInCinema(cinema);
//        Service.showSelectedFilms();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////                String pubtime = df.format(date1);
//       Map<Account,FilmFrame> accountFilmFrameMap = Service.makeUserAccount(thisuser);
//        for (Map.Entry<Account,FilmFrame> entry : accountFilmFrameMap.entrySet()) {
//            System.out.println("顾客:"+entry.getKey().getUserName());
//            System.out.println("影院:"+cinemaDao.getCinema(entry.getKey().getCinemaId()).getCinemaName());
//            System.out.println("影厅:"+entry.getValue().getRoomId()+"号");
//            System.out.println("第:"+entry.getKey().getSeatRow()+"排"+"第"+entry.getKey().getSeatColumn()+"列");
//            System.out.println("电影名称："+entry.getValue().getOnFilm().getFilmName());
//            System.out.println("票价："+entry.getValue().getPrice());
//            System.out.println("开始时间："+df.format(entry.getValue().getTimeOn()));
//            System.out.println("结束时间："+df.format(entry.getValue().getTimeOff()));
//        }
//    }
    }
}
