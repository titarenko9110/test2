import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Locale;

/**
 * Created by MyMac on 9/21/16.
 */
public class TestMain {

    private static final Utils utils = new Utils();


    @Test
    public void getLocaleTest() {
        Locale locale = Locale.getDefault();
        Assert.assertEquals(utils.getLocale().toString(), locale.toString());
    }

    @Test
    public void convertToUtf8Test() {
        String day = utils.convertToUtf8("Ð\u0094Ð¾Ð±Ñ\u0080Ñ\u008BÐµ Ð´ÐµÐ½Ñ\u008C, Ð\u009CÐ¸Ñ\u0080!","UTF-8");
        Assert.assertEquals(day, "Добрые день, Мир!");
    }

    @Test
    public void currentMessageTest() {
        Locale ru = new  Locale ( "RU" , "us" );
        Assert.assertEquals(utils.getCurrentMessage(ru, "day"), "Добрый день, Мир!");
        Locale en = new  Locale ( "EN" , "us" );
        Assert.assertEquals(utils.getCurrentMessage(en, "day"), "Good day, World!");
        Locale ru2 = new  Locale ( "RU" , "ua" );
        Assert.assertEquals(utils.getCurrentMessage(ru2, "day"), "Добрый день, Мир!");
        Locale fr = new  Locale ( "FR" , "fr" );
        Assert.assertEquals(utils.getCurrentMessage(fr, "day"), "Good day, World!");
    }

    @Test
    public void getPeriodTest(){
        LocalTime localTime1 = LocalTime.of(5, 59, 59);
        Assert.assertEquals(utils.getPeriod(localTime1), "night");
        LocalTime localTime2 = LocalTime.of(6, 00, 00);
        Assert.assertEquals(utils.getPeriod(localTime2), "morning");
        LocalTime localTime3 = LocalTime.of(8, 59, 59);
        Assert.assertEquals(utils.getPeriod(localTime3), "morning");
        LocalTime localTime4 = LocalTime.of(9, 00, 00);
        Assert.assertEquals(utils.getPeriod(localTime4), "day");
        LocalTime localTime5 = LocalTime.of(18, 59, 59);
        Assert.assertEquals(utils.getPeriod(localTime5), "day");
        LocalTime localTime6 = LocalTime.of(19, 00, 00);
        Assert.assertEquals(utils.getPeriod(localTime6), "evening");
        LocalTime localTime7 = LocalTime.of(22, 59, 59);
        Assert.assertEquals(utils.getPeriod(localTime7), "evening");
        LocalTime localTime8 = LocalTime.of(23, 00, 00);
        Assert.assertEquals(utils.getPeriod(localTime8), "night");
    }

}
