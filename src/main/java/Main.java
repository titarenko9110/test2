import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MyMac on 9/20/16.
 */
public class Main {
    
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Utils utils = new Utils();
        String out = utils.getOutputString();
        if (out != null) {
            logger.info("Console out - " + out);
            System.out.println(out);
        } else {
            logger.error("Console out - " + out);
        }
    }

}
