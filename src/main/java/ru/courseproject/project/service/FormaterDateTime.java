package ru.courseproject.project.service;

import java.time.format.DateTimeFormatter;

public class FormaterDateTime {
    public static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static DateTimeFormatter dateTimeFormatterFull =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
}
