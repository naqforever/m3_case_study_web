package m3.furama.util;

public class CommonUtil {
    public static int toInt(String val){
        try {
            return Integer.parseInt(val);
        }
        catch (Exception e){
            return 0;
        }
    }

    public static Double toDouble(String val){
        try {
            return Double.parseDouble(val);
        }
        catch (Exception e){
            return 0.0;
        }
    }
}
