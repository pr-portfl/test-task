package ru.courseproject.project.AnyUtils;

import org.junit.jupiter.api.Test;
import static ru.courseproject.project.service.FormaterDateTime.dateTimeFormatterFull;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


public class ConvertStringInotDateTimeTest {

    @Test
    public void dateTimeFormatterFull() {
        var strDateTime = "10.03.2024 10:15";
        var dateTime = LocalDateTime.parse(strDateTime, dateTimeFormatterFull);

        assertInstanceOf(LocalDateTime.class, dateTime);
        assertEquals(10, dateTime.getDayOfMonth());
        assertEquals(10, dateTime.getHour());

    }

}
