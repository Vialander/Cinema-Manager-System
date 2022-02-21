package Tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_trans {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date trans(String str){
        try{
            Date getDate = format.parse(str);
            return getDate;
        } catch (Exception e){

        }
        return null;
    }

    public String trans(Date date){
        return format.format(date);
    }
}
