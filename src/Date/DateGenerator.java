package Date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateGenerator {
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}