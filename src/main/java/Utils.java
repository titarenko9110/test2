import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyMac on 23.09.16.
 */
public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class);

    public  String getPeriod(LocalTime currentTime) {
        String period = null;
        if (currentTime.isAfter(LocalTime.of(5, 59, 59)) && currentTime.isBefore(LocalTime.of(9, 0, 0))) {
            period = "morning";
        } else if (currentTime.isAfter(LocalTime.of(8, 59, 59)) && currentTime.isBefore(LocalTime.of(19, 0, 0))) {
            period = "day";
        } else if (currentTime.isAfter(LocalTime.of(18, 59, 59)) && currentTime.isBefore(LocalTime.of(23, 0, 0))) {
            period = "evening";
        } else if (currentTime.isAfter(LocalTime.of(22, 59, 59)) || currentTime.isBefore(LocalTime.of(6, 0, 0))) {
            period = "night";
        } else {
            logger.error("Period - null");
        }
        logger.info("Period - " + period);
        return period;

    }

    public  String getOutputString() {
        Locale locale = getLocale();
        LocalTime currentTime = getCurrentTime();
        String period = getPeriod(currentTime);
        return getCurrentMessage(locale, period);

    }


    public  String getCurrentMessage(Locale locale, String period) {
        if (locale.getLanguage().equals("ru")) {
            return convertToUtf8(getBundle(locale).getString(period), "UTF-8");
        } else {
            Locale engLocale = new Locale("en");
            return getBundle(engLocale).getString(period);
        }
    }

    public  LocalTime getCurrentTime() {
        LocalTime now = LocalTime.now();
        logger.info("Current time is - " + now.toString());
        return now;
    }

    public  Locale getLocale() {
        Locale locale = Locale.getDefault();
        logger.info("User locale - " + locale.toString());
        return locale;
    }

    public  ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle("message", locale);
    }

    public  String convertToUtf8(String iso, String encodingType) {
        try {
            String convert = new String(iso.getBytes("ISO-8859-1"), encodingType);
            logger.info("Convert message to UTF-8. Result - " + convert);
            return convert;
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        }
        return iso;
    }
}
