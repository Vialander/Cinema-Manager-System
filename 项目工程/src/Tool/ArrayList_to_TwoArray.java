package Tool;

import Entity.Seat;

import java.util.ArrayList;

public class ArrayList_to_TwoArray {

    public static int[][] trans(ArrayList tmp){
        int row = (int)tmp.get(0);
        int colomn = (int)tmp.get(1);

        int[][]resultArray = new int[row][colomn];
        int counter = 2;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < colomn; j++){
                if(tmp.get(counter) == null){
                    break;
                }
                resultArray[i][j] = (int)tmp.get(counter);
                counter++;
            }
        }

        return resultArray;
    }

    public static Seat[][] trans(ArrayList<Seat> tmp, int length, int wide){
        int row = length;
        int colomn = wide;
        Seat[][] seats = new Seat[row][colomn];
        int Counter = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < colomn; j++){
                seats[i][j] = tmp.get(Counter);
                Counter++;
            }
        }
        //System.out.println("成功转换");
        return seats;
    }


}
