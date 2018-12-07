import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author yejg<br>
 * 开发时间: 2018年12月07日<br>
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
