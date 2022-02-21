package Tool;

import Entity.*;

public class SeatToInt {

    public int[][] seatToInt(Seat[][] seats){
        if(seats!=null){
            int [][] a = new int[seats.length][seats[0].length];
            for(int i = 0;i < seats.length;i++)
            {
                for(int j = 0; j < seats[0].length;j++){
                    a[i][j] = seats[i][j].getStatus();
                }
            }
            return a;
        }
        return null;
    }
}
