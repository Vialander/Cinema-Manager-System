package Tool;

import Entity.Seat;

import java.util.ArrayList;

public class TwoArray_to_ArrayList {

    public static ArrayList trans(int[][]tmp){
        ArrayList resultList = new ArrayList<>();
        int row = tmp.length;//行数
        int colomn = tmp[0].length;//列数
        resultList.add(row);
        resultList.add(colomn);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < colomn; j++){
                resultList.add(tmp[i][j]);
            }
        }
        return resultList;
    }
    public static ArrayList<Seat> trans(Seat[][]tmp){
        ArrayList<Seat> seatArrayList = new ArrayList<Seat>();
        int row = tmp.length;
        int colomn = tmp[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < colomn; j++){
                seatArrayList.add(tmp[i][j]);
            }
        }
        return seatArrayList;
    }

}
