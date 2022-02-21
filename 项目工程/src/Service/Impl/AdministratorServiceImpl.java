package Service.Impl;

import Dao.*;
import Dao.Impl.*;
import Entity.*;
import Service.AdministratorService;
import Tool.Date_trans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdministratorServiceImpl implements AdministratorService {

    //查看影院名称、位置、座位、空座信息
    public ArrayList<CinemaSeats> seeNULLSeat(){
        CinemaDao cinemaDao = new CinemaDaoImpl();
        ArrayList<Cinema> cinemaList = cinemaDao.getAllCinema();
        ArrayList<CinemaSeats> result = new ArrayList<CinemaSeats>();
        for(int i=0;i<cinemaList.size();i++){
            CinemaSeats resultTemp = new CinemaSeats();
            resultTemp.setCinemaName(cinemaList.get(i).getCinemaName());
            resultTemp.setLocation(cinemaList.get(i).getLocation());
            int tempIN = 0;
            int tempOut = 0;
            int sum = 0;
            ArrayList<Room> roomlist = cinemaList.get(i).getRoomList();
            for(int j=0;j<roomlist.size();j++){
                for(int k=0;k<roomlist.get(j).getSeatColomns();k++){
                    for(int l=0;l<roomlist.get(j).getSeatRows();l++){
                        if(roomlist.get(j).getSeat()[l][k].getStatus() == 1){
                            tempIN++;
                        } else if(roomlist.get(j).getSeat()[l][k].getStatus() == 2){
                            tempOut++;
                        }
                    }
                }
            }
            sum = tempIN + tempOut;
            resultTemp.setAllSeatNum(sum);
            resultTemp.setNullSeatNum(tempIN);
            result.add(resultTemp);
        }
        return result;
    }

    //判断影院是否存在
    public boolean JudgeCinemaExist(String CinemaName){
        CinemaDao cinemaDao = new CinemaDaoImpl();
        String sql = "select * from cinema where cinema_name = ?";
        Object[]param = {CinemaName};
        if(cinemaDao.selectCinema(sql,param).size() == 0){
            return false;
        }
        return true;
    }


    //根据选取影院名，得到对应影厅列表
    public  ArrayList<Room> seeCinema(String CinemaName){
        RoomDao roomDao = new RoomDaoImpl();
        String sql = "select * from room where cinema_name = ?";
        Object[]param = {CinemaName};
        ArrayList<Room> result = new ArrayList<Room>();
        result = roomDao.selectRoom(sql,param);
        return result;
    }

    //增加新影院
    public void addNewCinema(Cinema newCinema){
        CinemaDao cinemaDao = new CinemaDaoImpl();
        cinemaDao.addCinema(newCinema);
    }

    //删除影院                                                      //测试
    public void deleteOldCinema(String cinemaName){
        CinemaDao cinemaDao = new CinemaDaoImpl();
        String sql = "select * from cinema where cinema_name = ?";
        Object[]param = {cinemaName};
        ArrayList<Cinema> cinemaList = cinemaDao.selectCinema(sql,param);
        //得到Cinema
        Cinema delCinema = cinemaList.get(0);

        //根据CinemaId得到和Cinema有关的RoomList
        RoomDao roomDao = new RoomDaoImpl();
        String sql1 = "select * from Room where cinema_name = ?";
        Object[] param1 = {delCinema.getCinemaId()};
        ArrayList<Room> roomArrayList = roomDao.selectRoom(sql1,param1);

        //根据cinemaId得到 FilmFrameList
        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
        String sql2 = "select * from FilmFrame where cinema_name = ?";
        Object[]param2 = {delCinema.getCinemaId()};
        ArrayList<FilmFrame> filmFrameArrayList = filmFrameDao.selectFilmFrame(sql2, param2);

        //根据FilmFrameList 逐行删除
        for (int j = 0; j < filmFrameArrayList.size(); j++){
            filmFrameDao.delFilmFrame(filmFrameArrayList.get(j));
        }

        //根据roomlist对Room删除
        for(int i = 0; i < roomArrayList.size(); i++){
            roomDao.delRoom(roomArrayList.get(i));
        }

        //根据CinemaId删除Cinema表中的信息
        cinemaDao.delCinema(delCinema);

    }


    public Cinema getCinemaInfo(String cinemaName){
        CinemaDao cinemaDao = new CinemaDaoImpl();
        String sql = "select * from cinema where cinema_name = ?";
        Object[]param = {cinemaName};
        Cinema result = cinemaDao.selectCinema(sql,param).get(0);
        return result;
    }


    public ArrayList<Film> getAllOnFilm(Date strDate){
        int past = -5;
        int forward = 5;
        FilmDao filmDao = new FilmDaoImpl();
        ArrayList<Film> tmpGetFilm = filmDao.getAllFilm();
        ArrayList<Film> resultList = new ArrayList<>();
        Date date = strDate;
        Calendar calendar = Calendar.getInstance();
        //获取past期限 -5天
        calendar.setTime(date);
        calendar.add(Calendar.DATE,past);
        Date datePastMax = calendar.getTime();
        //获取forward期限 +5天
        calendar.setTime(date);
        calendar.add(Calendar.DATE,forward);
        Date dateForwardMax = calendar.getTime();

        for(int i = 0; i < tmpGetFilm.size()-1; i++){
            if((tmpGetFilm.get(i).getOnDate().compareTo(dateForwardMax) < 0 ) && (tmpGetFilm.get(i).getDownDate().compareTo(datePastMax) > 0)){
                resultList.add(tmpGetFilm.get(i));
            }
        }
        return resultList;
    }

    public ArrayList<Film> getAllSelectedFilm(){
       FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        ArrayList<Film> resultFilmList = selectedFilmDao.getAllFilm();
        return resultFilmList;
    }


    //上架
    public int onFilm(String filmName){
        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        String sql = "select * from selectedfilm where film_name = ?";
        Object[]param = {filmName};
        if(selectedFilmDao.selectFilm(sql,param).size() != 0){
            //已上架，返回值为1
            return 1;
        }
        sql = "select * from film where film_name = ?";
        FilmDao filmDao = new FilmDaoImpl();
        ArrayList<Film> temp = filmDao.selectFilm(sql,param);
        if(temp.size() == 0){
            //错误ID，返回值为2
            return 2;
        }
        selectedFilmDao.addFilm(temp.get(0));
        //上架成功，返回值为0
        return 0;
    }

    //下架
    public boolean downFilm(String filmName){
        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        String sql = "select * from selectedfilm where film_name = ?";
        Object[]param = {filmName};
        ArrayList<Film> temp = selectedFilmDao.selectFilm(sql,param);
        if(temp.size() == 0){
            //该项不存在，返回值为false
            return false;
        }
        selectedFilmDao.delFilm(temp.get(0));
        //删除成功，返回值为true
        return true;
    }

    public void addRoom(Room room){
        RoomDao roomDao = new RoomDaoImpl();
        room.setRoomId(getNextRoomNum());
        roomDao.addRoom(room);

        //得到影院
        CinemaDao cinemaDao = new CinemaDaoImpl();
        String sql = "select * from Cinema where cinema_id = ?";
        Object[]param = {room.getCinemaId()};
        ArrayList<Cinema> cinemaArrayList = cinemaDao.selectCinema(sql,param);
        Cinema resultCinema = cinemaArrayList.get(0);
        //增加影厅后，修改影院的RoomList
        ArrayList<Room> roomArrayList = resultCinema.getRoomList();
        roomArrayList.add(room);
        resultCinema.setRoomList(roomArrayList);
        cinemaDao.updateCinema(resultCinema);

    }

    public ArrayList<Room> getCertionRoom(int id,int idOfCinema){
        RoomDao roomDao = new RoomDaoImpl();
        String sql = "select * from room where room_id = ? and cinema_id = ?";
        Object[]param = {id,idOfCinema};
        ArrayList<Room> temp = roomDao.selectRoom(sql,param);
        return temp;
    }

    public boolean delRoom(int id,int idOfCinema){
        RoomDao roomDao = new RoomDaoImpl();
        String sql = "select * from room where room_id = ? and cinema_id = ?";
        Object[]param = {id,idOfCinema};
        ArrayList<Room> temp = roomDao.selectRoom(sql,param);
        Room delRoom = temp.get(0);
        if(temp.size() == 0){
            //该项不存在，返回值为false
            return false;
        }
        roomDao.delRoom(temp.get(0));

        //删除Room后修改FilmFrame 和 Cinema

        //首先修改Cinema
        CinemaDao cinemaDao = new CinemaDaoImpl();
        String sql1 = "select * from Cinema where cinema_id = ?";
        Object[]param1 = {delRoom.getCinemaId()};
        ArrayList<Cinema> cinemaArrayList1 = cinemaDao.selectCinema(sql1,param1);
        Cinema resultCinema = cinemaArrayList1.get(0);

        ArrayList<Room> roomArrayList = resultCinema.getRoomList();
        roomArrayList.remove(delRoom);
        resultCinema.setRoomList(roomArrayList);
        cinemaDao.updateCinema(resultCinema);

        //现在修改FilmFrame
        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
        String sql2 = "select * from FilmFrame where room_id = ?";
        Object[]param2 = {delRoom.getRoomId()};
        ArrayList<FilmFrame> filmFrameArrayList = filmFrameDao.selectFilmFrame(sql2,param2);

        for(int i = 0; i < filmFrameArrayList.size();i++){
            filmFrameDao.delFilmFrame(filmFrameArrayList.get(i));
        }

        //删除成功，返回值为true
        return true;
    }

    //开始时间，RoomId，电影名称，票价
    //开始时间，RoomId，电影名称，票价
    public boolean addFilmFrame(String datestr,int id,String filmName,double price){
        Date_trans date_trans = new Date_trans();
        FilmFrameDao filmFrameDao = new FilmFrameDaoImpl();
        String sql = "select * from FilmFrame where room_id = ?";
        Object[]param = {id};
        //Id影厅的所有信息
        ArrayList<FilmFrame> filmFrameArrayList = filmFrameDao.selectFilmFrame(sql,param);

        Date onTime = date_trans.trans(datestr);//开始时间
        FilmDao selectedFilmDao = new SelectedFilmDaoImpl();
        String sql1 = "select * from SelectedFilm where film_name = ?";
        Object[] param1 = {filmName};
        ArrayList<Film> filmArrayList = selectedFilmDao.selectFilm(sql1,param1);
        if(filmArrayList.size() == 0){
            return false;
        }
        Film getFilm = filmArrayList.get(0);//得到上架电影信息

        //电影开始时间是否在上映时间内
        if(onTime.compareTo(getFilm.getOnDate())<0 || onTime.compareTo(getFilm.getDownDate())>0){
            //输入信息不在上映范围内
            System.out.println("不在范围内");
            return false;
        }

        int timelong = getFilm.getTimeLong();
        //预排的电影开始时间
        Date preOn = date_trans.trans(datestr);

        //预排的电影结束时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(preOn);
        calendar.add(Calendar.MINUTE,timelong);
        Date preOff = calendar.getTime();

        //判断原理：如果有排片的场次的ON<preOff且OFF>preOn的话就算是排片冲突

        //TODO 检验是否冲突
        if (filmArrayList.size() != 0){
            for(int i = 0; i < filmFrameArrayList.size(); i++){
                if((filmArrayList.get(i).getOnDate().compareTo(preOn)<0 && filmArrayList.get(i).getDownDate().compareTo(preOn)>0)||(filmArrayList.get(i).getOnDate().compareTo(preOff)<0 && filmArrayList.get(i).getDownDate().compareTo(preOff)>0)){
                    System.out.println("排片冲突！");
                    return false;
                }
            }
        }

        calendar = Calendar.getInstance();
        calendar.setTime(onTime);
        calendar.add(Calendar.MINUTE,getFilm.getTimeLong());
        Date offTime = calendar.getTime(); //结束时间

        //找到idRoom
        RoomDao roomDao = new RoomDaoImpl();
        String sql2 = "select * from Room where room_id = ?";
        Object[] param2 = {id};
        ArrayList<Room> roomArrayList = roomDao.selectRoom(sql2,param2);
        Room room = roomArrayList.get(0);

        //如果不重复的话，把场次表更新。
        FilmFrame filmFrame = new FilmFrame(room.getCinemaId(),id,getFilm,onTime,offTime,price,room.getSeatRows(),room.getSeatColomns(),room.getSeat());
        filmFrameDao.addFilmFrame(filmFrame);

        //找到指定房间，将指定房间的场次取出，添加新场次，放入原来的那个room然后更新
        ArrayList<FilmFrame> filmFrameArrayList1 = room.getScheduleTime();
        filmFrameArrayList1.add(filmFrame);
        room.setScheduleTime(filmFrameArrayList1);
        roomDao.updateRoom(room);

        return true;
    }

    //得到自增主键下一个值
    public int getNextRoomNum(){
        RoomDao roomDao = new RoomDaoImpl();
        ArrayList<Room> roomArrayList = roomDao.getAllRoom();
        int result;
        if(roomArrayList.size() == 0){
            result = 1;
        }else{
            result = roomArrayList.get(roomArrayList.size()-1).getRoomId() + 1;
        }
        return result;
    }


}
