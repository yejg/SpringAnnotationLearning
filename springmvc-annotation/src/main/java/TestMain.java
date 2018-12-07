import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * ����˵��: <br>
 * ϵͳ�汾: v1.0<br>
 * ������Ա: @author yejg<br>
 * ����ʱ��: 2018��12��07��<br>
 */
public class TestMain {

    public static void main(String[] args) {
        Date d  = new Date(1513932494707L);
        LocalDateTime localDateTime = date2LocalDateTime(d);

        System.out.println(localDateTime);
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }
}
