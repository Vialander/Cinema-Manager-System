package Manager;

import Entity.*;
import Service.AdministratorService;
import Service.Impl.AdministratorServiceImpl;
import Tool.printSeat;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdministratorManager {
    private int idOfCinema = 0;
    private int idOfRoom = 0;
    private String CinemaName = "";
    public void AdministratorStartMenu(){
        System.out.println("1.影院管理");
        System.out.println("2.影片管理");
        System.out.println("3.退出登录");
        System.out.print("请选择：");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        try{
            choice = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("请正确输入！");
            AdministratorStartMenu();
        }
        switch (choice){
            case 1:
                CinemaMenu();
                break;
            case 2:
                FilmMenu();
                break;
            case 3:
                System.out.println("退出登录成功！\n");
                break;
            default:
                System.out.println("服务选项错误！");
                AdministratorStartMenu();
        }
    }

    public void CinemaMenu(){
        System.out.println("\n*****影院管理*****");
        System.out.println("1.查看影院信息");
        System.out.println("2.管理影院信息");
        System.out.println("3.增加影院");
        System.out.println("4.删除影院");
        System.out.println("5.退出影院管理");
        System.out.print("请选择：");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        try{
            choice = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("请正确输入！");
            CinemaMenu();
        }
        switch (choice){
            case 1:
                showCinemaInfoResult();
                break;
            case 2:
                SelectCinemaInfoManager();
                break;
            case 3:
                addCinemaInfo();
                return;
            case 4:
                deleteCinemaInfo();
                break;
            case 5:
                System.out.println("退出影院管理成功！\n");
                AdministratorStartMenu();
                break;
            default:
                System.out.println("服务选项错误！");
        }
    }

    public void showCinemaInfo(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        ArrayList<CinemaSeats> result = administratorService.seeNULLSeat();
        if(result.size() == 0){
            System.out.println("暂时还没有影院！");
        }else{
            System.out.println("影院名\t地点\t座位数");
            for(int i=0;i<result.size();i++){
                System.out.println(result.get(i).getCinemaName() + "\t" + result.get(i).getLocation() + "\t" + result.get(i).getAllSeatNum());
            }
        }
    }

    public void showCinemaInfoResult(){
        showCinemaInfo();
        returnCinemaMenu();
    }


    public void addCinemaInfo(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        String cinemaName;
        String location;
        ArrayList<Room> roomList = new ArrayList<Room>();

        while (true){
            System.out.println("请输入影院名：");
            cinemaName = input.next();
            if(administratorService.JudgeCinemaExist(cinemaName)){
                System.out.println("影院名重复！请重新输入！");
            }else{
                break;
            }
        }

        System.out.println("请输入影院地点：");
        location = input.next();

        administratorService.addNewCinema(new Cinema(cinemaName,location,roomList));
        System.out.println("添加成功！");
        returnCinemaMenu();
    }

    public void deleteCinemaInfo(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        String cinemaName;

        System.out.println("请输入影院名：");
        cinemaName = input.next();
        if(!administratorService.JudgeCinemaExist(cinemaName)){
            System.out.println("影院不存在！请重新输入！");
            returnCinemaMenu();
        }
        else{
            administratorService.deleteOldCinema(cinemaName);
            System.out.println("删除成功！");
            returnCinemaMenu();
        }
    }

    public void returnCinemaMenu(){
        Scanner input = new Scanner(System.in);
        System.out.print("输入0返回：");
        try {
            if(input.nextInt() == 0) {
                CinemaMenu();
            }else{
                System.out.println("输入错误, 异常终止！");
                CinemaMenu();
            }
        }catch(InputMismatchException e) {
            System.out.println("输入错误, 异常终止！");
            CinemaMenu();
        }
    }

    public void SelectCinemaInfoManager(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        String cinemaName;

        System.out.println("\n*****影院信息管理*****");
        showCinemaInfo();
        ArrayList<CinemaSeats> result = administratorService.seeNULLSeat();
        if(result.size() != 0){
            while (true){
                System.out.print("请输入你要管理的电影院：");
                cinemaName = input.next();
                if(!administratorService.JudgeCinemaExist(cinemaName)){
                    System.out.println("影院不存在！请重新输入！");
                }else{
                    break;
                }
            }
            this.idOfCinema = administratorService.getCinemaInfo(cinemaName).getCinemaId();
            this.CinemaName = administratorService.getCinemaInfo(cinemaName).getCinemaName();
            CinemaInfoManagerMenu();
        }
        returnCinemaMenu();
    }
    public void CinemaInfoManagerMenu(){
        System.out.println("\n*****影院信息管理*****");
        System.out.println("当前管理影院名为：" + CinemaName);
        System.out.println("1.查看影厅信息");
        System.out.println("2.影厅排片");
        System.out.println("3.增加影厅");
        System.out.println("4.删除影厅");
        System.out.println("5.退出影院信息管理");
        System.out.print("请选择：");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        try{
            choice = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("请正确输入！");
            CinemaInfoManagerMenu();
        }
        switch (choice){
            case 1:
                showRoomInfoResult();
                break;
            case 2:
                SelectRoomInfoManager();
                break;
            case 3:
                addRoomInfo();
                return;
            case 4:
                deleteRoomInfo();
                break;
            case 5:
                System.out.println("退出影院信息管理成功！\n");
                CinemaMenu();
                break;
            default:
                System.out.println("服务选项错误！");
        }
    }

    public void showAllRoomInfo(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        ArrayList<Room> result = administratorService.seeCinema(this.CinemaName);
        if(result.size() == 0){
            System.out.println("暂时还没有影厅！");
        }else{
            System.out.println("影厅ID\t座位数\t影厅长度\t影厅宽度");
            for(int i=0;i<result.size();i++){
                int tempSeatNum = 0;
                for(int k=0;k<result.get(i).getSeatColomns();k++){
                    for(int l=0;l<result.get(i).getSeatRows();l++){
                        if(result.get(i).getSeat()[l][k].getStatus() == 1 || result.get(i).getSeat()[l][k].getStatus() == 2){
                            tempSeatNum++;
                        }
                    }
                }
                System.out.println(result.get(i).getRoomId() + "\t\t" +tempSeatNum + "\t\t" + result.get(i).getSeatRows() + "\t\t" + result.get(i).getSeatColomns());
            }
        }
    }

    public void showOneRoomInfo(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        int choice = 0;
        boolean flag = true;
        while (flag){
            try{
                System.out.print("请选择你要查看的影厅ID：");
                choice = input.nextInt();
                if(administratorService.getCertionRoom(choice,this.idOfCinema).size() == 0){
                    System.out.println("影厅不存在！请正确输入！");
                }else{
                    flag = false;
                }
            } catch (InputMismatchException e){
                System.out.println("请正确输入！");
                input = new Scanner(System.in);
            }
        }
        this.idOfRoom = choice;
        Room room = administratorService.getCertionRoom(choice,this.idOfCinema).get(0);
        ArrayList<FilmFrame> scheduleTime = room.getScheduleTime();
        if(scheduleTime.size() == 0){
            System.out.println("暂无场次安排！");
        }else{
            System.out.println("电影名\t\t开场时间\t\t\t\t散场时间\t\t\t\t票价\t座位数\t空座数");
            for(int i=0;i<scheduleTime.size();i++){
                int seatTemp1 = 0;
                int seatTemp2 = 0;
                int seatSum = 0;
                for(int k=0;k<room.getSeatColomns();k++){
                    for(int l=0;l<room.getSeatRows();l++){
                        if(room.getSeat()[l][k].getStatus() == 1){
                            seatTemp1++;
                        }else if(room.getSeat()[l][k].getStatus() == 2){
                            seatTemp2++;
                        }
                    }
                }
                seatSum = seatTemp1 + seatTemp2;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                System.out.println(scheduleTime.get(i).getOnFilm().getFilmName() + "\t" + df.format(scheduleTime.get(i).getTimeOn()) + "\t" + df.format(scheduleTime.get(i).getTimeOff())+ "\t" + scheduleTime.get(i).getPrice() + "\t" + seatSum + "\t\t" + seatTemp1);
            }
        }
    }

    public void showRoomInfoResult(){
        AdministratorService administratorService = new AdministratorServiceImpl();
        showAllRoomInfo();
        ArrayList<Room> result = administratorService.seeCinema(this.CinemaName);
        if(result.size() != 0){
            showOneRoomInfo();
        }
        returnCinemaInfoManagerMenu();
    }
    public void addRoomInfo(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        Seat[][] seat = null;
        ArrayList<FilmFrame> scheduleTime = new ArrayList<FilmFrame>();
        int midColomn = 0;//中线
        int lengthRoom = 0;//纵向座位个数
        int wideRoom = 0;//横向座位个数
        boolean flag = true;
        while (flag){
            try {
                System.out.println("请输入纵向座位数：");
                lengthRoom = input.nextInt();
                System.out.println("请输入横向座位数：");
                wideRoom = input.nextInt();
                if(lengthRoom<0||wideRoom<0){
                    System.out.println("座位数不得小于0！");
                }else{
                    flag = false;
                }
            }catch(InputMismatchException e) {
                System.out.println("请正确输入！");
                input = new Scanner(System.in);
            }
        }
        seat = new Seat[lengthRoom][wideRoom];

        for(int i=0;i<lengthRoom;i++){
            for(int j=0;j<wideRoom;j++){
                seat[i][j] = new Seat(0, null, administratorService.getNextRoomNum(), this.CinemaName, 1, lengthRoom, wideRoom);
            }
        }
        System.out.println("当前座位排布如下：");
        printSeat seatPrint = new printSeat();
        seatPrint.display(seat);
        System.out.println("请问影厅中存在禁用座位吗？(输入y禁用座位)");
        String judge = input.next();
        if(judge.equals("y")){
            boolean flag1 = true;
            while(flag1){
                try {
                    String judge1;
                    int rowTemp;
                    int columeTemp;
                    System.out.println("请输入禁用座位的行数：");
                    ;
                    rowTemp = input.nextInt() - 1;
                    System.out.println("请输入禁用座位的列数：");
                    columeTemp = input.nextInt() - 1;

                    if(rowTemp<0||columeTemp<0||rowTemp+1>lengthRoom||columeTemp+1>wideRoom){
                        System.out.println("请输入正确数字！");
                    }else{
                        seat[rowTemp][columeTemp].setStatus(0);
                        System.out.println("当前座位排布如下：");
                        seatPrint.display(seat);
                        System.out.println("是否停止录入(输入y停止)：");
                        judge1 = input.next();
                        if(judge1.equals("y")){
                            flag1 = false;
                        }
                    }
                }catch(InputMismatchException e){
                    System.out.println("请正确输入！");
                    input = new Scanner(System.in);
                }
            }
            administratorService.addRoom(new Room(this.CinemaName,this.idOfCinema,seat,scheduleTime,lengthRoom,wideRoom));
            System.out.println("添加成功！");
        }else{
            administratorService.addRoom(new Room(this.CinemaName,this.idOfCinema,seat,scheduleTime,lengthRoom,wideRoom));
            System.out.println("添加成功！");
        }
        returnCinemaInfoManagerMenu();
    }

    public void deleteRoomInfo(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        showAllRoomInfo();
        ArrayList<Room> result = administratorService.seeCinema(this.CinemaName);
        if(result.size()!=0)
        {
            int id = 0;
            boolean flag = true;
            while (flag){
                try {
                    System.out.println("请输入删除影厅ID：");
                    id = input.nextInt();
                    flag = false;
                }catch(InputMismatchException e){
                    System.out.println("请正确输入！");
                    input = new Scanner(System.in);
                }
            }


            if(administratorService.getCertionRoom(id,idOfCinema).size() == 0){
                System.out.println("影厅不存在！请重新输入！");
            }
            else{
                administratorService.delRoom(id,idOfCinema);
                System.out.println("删除成功！");
            }
        }
        returnCinemaInfoManagerMenu();
    }

    public void SelectRoomInfoManager(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        String datestr = "";
        String filmName = "";
        double price = 0;

        showAllRoomInfo();
        ArrayList<Room> result = administratorService.seeCinema(this.CinemaName);
        if(result.size() != 0){
            showOneRoomInfo();
            System.out.println();
            System.out.println("当前上架电影为：");
            showFilmInfo();
            ArrayList<Film> resultFilmList = administratorService.getAllSelectedFilm();
            if(resultFilmList.size() != 0){
                boolean flag = true;
                while(flag){
                    System.out.println("请输入排片电影名称：");
                    filmName = input.nextLine();
                    for(int i=0;i<resultFilmList.size();i++){
                        if(resultFilmList.get(i).getFilmName().equals(filmName)){
                            flag = false;
                            break;
                        }
                        if(i == resultFilmList.size()-1 && !resultFilmList.get(i).getFilmName().equals(filmName)){
                            System.out.println("未找到该影片，请重新输入！");
                        }
                    }
                }

                boolean flag1 = true;
                while (flag1){
                    datestr = "";
                    try{
                        System.out.print("输入排片时间(yyyy-MM-dd HH:mm:ss)：");
                        datestr = input.nextLine();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date dateTest = format.parse(datestr);
                        flag1 = false;
                    } catch (InputMismatchException e){
                        System.out.println("请正确输入！");
                        input = new Scanner(System.in);
                    } catch (Exception e){
                        System.out.println("请输入正确日期格式！");
                    }
                }

                boolean flag2 = true;
                while (flag2){
                    try{
                        System.out.println("请输入该场次票价：");
                        price = input.nextDouble();
                        if(price < 0){
                            System.out.println("余额不得小于0！");
                        }else{
                            flag2 = false;
                        }
                    } catch (InputMismatchException e){
                        System.out.println("请正确输入！");
                        input = new Scanner(System.in);
                    }
                }
                boolean finalResult = administratorService.addFilmFrame(datestr,idOfRoom,filmName,price);
                if(finalResult){
                    System.out.println("排片成功！");
                }else{
                    System.out.println("排片失败！请检查时间冲突！");
                }
            }
        }
        returnCinemaInfoManagerMenu();
    }
    public void returnCinemaInfoManagerMenu(){
        Scanner input = new Scanner(System.in);
        System.out.print("输入0返回：");
        try {
            if(input.nextInt() == 0) {
                CinemaInfoManagerMenu();
            }else{
                System.out.println("输入错误, 异常终止！");
                CinemaInfoManagerMenu();
            }
        }catch(InputMismatchException e) {
            System.out.println("输入错误, 异常终止！");
            CinemaInfoManagerMenu();
        }
    }

    public void FilmMenu() {
        System.out.println("1.上架影片查看");
        System.out.println("2.影片上架");
        System.out.println("3.影片下架");
        System.out.println("4.退出影片管理");
        System.out.print("请选择：");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        try{
            choice = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("请正确输入！");
            FilmMenu();
        }
        switch (choice){
            case 1:
                showFilmInfoResult();
                break;
            case 2:
                setOnFilm();
                break;
            case 3:
                setDownFilm();
                break;
            case 4:
                System.out.println("退出影片管理成功！\n");
                AdministratorStartMenu();
                break;
            default:
                System.out.println("服务选项错误！");
                returnFilmMenu();
        }
    }
    public void showFilmInfoResult(){
        showFilmInfo();
        returnFilmMenu();
    }
    public void showFilmInfo(){
        AdministratorService administratorService = new AdministratorServiceImpl();
        ArrayList<Film> resultFilmList = administratorService.getAllSelectedFilm();
        if(resultFilmList.size() == 0){
            System.out.println("暂无上架电影！");
        }else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("电影\t\t主演\t\t\t\t\t\t\t评分\t类型\t\t时长\t国家\t上架日期");
            for(int i=0;i<resultFilmList.size();i++){
                System.out.println(resultFilmList.get(i).getFilmName() + "\t\t" + resultFilmList.get(i).getMainActor() + "\t" + resultFilmList.get(i).getScore() + "\t" + resultFilmList.get(i).getType() + "\t" + resultFilmList.get(i).getTimeLong() + "\t" + resultFilmList.get(i).getCountry() + "\t" + df.format(resultFilmList.get(i).getOnDate()) + "~"+ df.format(resultFilmList.get(i).getDownDate()));

            }
        }
    }

    public void setDownFilm(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        ArrayList<Film> resultFilmList = administratorService.getAllSelectedFilm();
        if(resultFilmList.size() == 0){
            System.out.println("暂无上架电影！");
        }else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("电影\t\t主演\t\t\t\t\t\t\t评分\t类型\t\t时长\t国家\t上架日期");
            for(int i=0;i<resultFilmList.size();i++){
                System.out.println(resultFilmList.get(i).getFilmName() + "\t\t" + resultFilmList.get(i).getMainActor() + "\t" + resultFilmList.get(i).getScore() + "\t" + resultFilmList.get(i).getType() + "\t" + resultFilmList.get(i).getTimeLong() + "\t" + resultFilmList.get(i).getCountry() + "\t" + df.format(resultFilmList.get(i).getOnDate()) + "~"+ df.format(resultFilmList.get(i).getDownDate()));
            }
            boolean flag = true;
            while(flag){
                System.out.println("请输入要下架的电影：");
                String filmName = input.nextLine();
                boolean result = administratorService.downFilm(filmName);
                if(result){
                    System.out.println("下架成功！");
                    flag = false;
                }else{
                    System.out.println("未找到该电影！");
                }
            }
        }
        returnFilmMenu();
    }

    public void setOnFilm(){
        Scanner input = new Scanner(System.in);
        AdministratorService administratorService = new AdministratorServiceImpl();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Film> resultFilmList = administratorService.getAllOnFilm(now);
        System.out.println("电影\t\t主演\t\t\t\t\t\t\t评分\t类型\t时长\t\t国家\t上架日期");
        for(int i=0;i<resultFilmList.size();i++){
            System.out.println(resultFilmList.get(i).getFilmName() + "\t\t" + resultFilmList.get(i).getMainActor() + "\t" + resultFilmList.get(i).getScore() + "\t" + resultFilmList.get(i).getType() + "\t" + resultFilmList.get(i).getTimeLong() + "\t" + resultFilmList.get(i).getCountry() + "\t" + df.format(resultFilmList.get(i).getOnDate()) + "~"+ df.format(resultFilmList.get(i).getDownDate()));

        }
        boolean flag = true;
        while(flag){
            System.out.println("请输入要上架的电影：");
            String filmName = input.nextLine();
            int result = administratorService.onFilm(filmName);
            if(result == 1){
                System.out.println("该电影已上架！");
                flag = false;
            }else if(result == 2){
                System.out.println("未找到该电影！");
            }else{
                System.out.println("上架成功！");
                flag = false;
            }
        }
        returnFilmMenu();
    }

    public void returnFilmMenu(){
        Scanner input = new Scanner(System.in);
        System.out.print("输入0返回：");
        try {
            if(input.nextInt() == 0) {
                FilmMenu();
            }else{
                System.out.println("输入错误, 异常终止！");
                FilmMenu();
            }
        }catch(InputMismatchException e) {
            System.out.println("输入错误, 异常终止！");
            FilmMenu();
        }
    }



}
