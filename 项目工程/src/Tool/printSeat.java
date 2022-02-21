package Tool;

import Entity.Seat;

public class printSeat {
    public void display(Seat[][] seats){
        SeatToInt seatToInt = new SeatToInt();
        int[][] Intseats = seatToInt.seatToInt(seats);
        for(int i=0;i<Intseats.length;i++){
            for(int j=0;j<Intseats[i].length;j++){
                if(Intseats[i][j] == 0){
                    System.out.print("✗");
                }else if(Intseats[i][j] == 1){
                    System.out.print("☆");
                }else if(Intseats[i][j] == 2)
                    System.out.print("★");
            }
            System.out.println();
        }
        System.out.println("其中，☆代表座位未被占用，★代表已有顾客选座");
    }
}
