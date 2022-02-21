package Test;

import java.text.*;
import java.util.*;
import java.text.ParseException;


import java.util.*;
import Entity.*;
import Entity.Room;
import org.apache.commons.collections.map.HashedMap;

//public class SeatChooseImpl interface SeatChoose{


//}

public class Main {

    public static FilmFrame  AotuChoose(FilmFrame filmFrame){
        Seat seats[][] = filmFrame.getSeatStatus();
        int row = filmFrame.getSeatRows();
        int column = filmFrame.getSeatColomns();

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
            return filmFrame;
        }
        else {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (value[i][j] == num) {
                        seats[i][j].setStatus(2);
                        System.out.println("选择" + (i + 1) + "排" + (j + 1) + "列");
                        flag = 2;
                        break;
                    }
                }
                if (flag == 2) {
                    break;
                }
            }
            filmFrame.setSeatStatus(seats);
            return filmFrame;
        }
    }

    public static void showSeats(FilmFrame filmFrame){
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

    public static void main(String[] args) {
        Seat[][] seats = new Seat[3][3];

        seats[0][0] = new Seat(1, "cxk", 1, "范神影院", 1, 1, 1);
        seats[0][1] = new Seat(1, "cxk", 1, "范神影院", 1, 1, 2);
        seats[0][2] = new Seat(1, "cxk", 1, "范神影院", 1, 1, 3);
        seats[1][0] = new Seat(1, "cxk", 1, "范神影院", 1, 2, 1);
        seats[1][1] = new Seat(1, "cxk", 1, "范神影院", 1, 2, 2);
        seats[1][2] = new Seat(1, "cxk", 1, "范神影院", 3, 2, 3);
        seats[2][0] = new Seat(1, "cxk", 1, "范神影院", 1, 3, 1);
        seats[2][1] = new Seat(1, "cxk", 1, "范神影院", 1, 3, 2);
        seats[2][2] = new Seat(1, "cxk", 1, "范神影院", 0, 3, 3);
        Date date1 = new Date();
        Date date2 = new Date();
        Date date3 = new Date();

        Film film = new Film(1, "电影名字", "主演1/主演2", 9.27, "惊悚", 118, "美国", "http：//aaaa", date1,date2, date3);
        FilmFrame filmFrame = new FilmFrame(1,1, film, date1, date2, 1000, 3, 3, seats);

        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);
        filmFrame = AotuChoose(filmFrame);
        showSeats(filmFrame);


//        int row = filmFrame.getSeatRows();
//        int column = filmFrame.getSeatColomns();
//
//        int[][] value = new int[row][column];
//        List<Integer> nums = new ArrayList<Integer>();
//        int num;
//        int flag = 1;
//        for(int i = 0;i < row ;i++){
//            for(int j = 0;j < column ;j++) {
//                if (seats[i][j].getStatus() == 1) {
//                    value[i][j] = Math.abs(row - 1 - 2 * i) + Math.abs(column - 1 - 2 * j);
//                    nums.add(value[i][j]);
//                }else{
//                    value[i][j] = 99999;
//                    nums.add(value[i][j]);
//                }
//            }
//        }
//        num = Collections.min(nums);
//        for(int i = 0;i < row ;i++){
//            for(int j = 0;j < column ;j++)
//            {
//               if(value[i][j] == num){
//                   seats[i][j].setStatus(2);
//                   System.out.println("选择"+(i+1)+"排"+(j+1)+"列");
//                   flag = 2;
//                   break;
//               }
//            }
//            if(flag == 2){
//                break;
//            }
//        }





//    public static void main(String[] args){
////        Date date = new Date();
////
////        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
////        String dateTime = df.format(date); // Formats a Date into a date/time string.
////        System.out.println(dateTime);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date date = new Date();
//            String dateTime = df.format(date); // Formats a Date into a date/time string.
//            System.out.println(dateTime);
//
//             date = df.parse(dateTime);
//            System.out.println(date);
//            date.setTime(date.getTime() + 1000);
//            System.out.println("当前时间      ："+df.format(date));
//        }catch (ParseException e){
//            e.printStackTrace();
//        }finally {
//
//        }
//
//
//    }
	}
}
