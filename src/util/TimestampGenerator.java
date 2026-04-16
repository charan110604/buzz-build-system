package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampGenerator {
    public static String getTimeStamp(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
