package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * @author Sabitov Danil
 * @version 1.0
 * Class describing date and time
 */
public class DataChecker {
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
