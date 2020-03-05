package web.util;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Check {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2020-02-28T00:00:00");
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
