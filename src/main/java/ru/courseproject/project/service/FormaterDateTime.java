package ru.courseproject.project.service;

import java.time.format.DateTimeFormatter;

public class FormaterDateTime {
    public static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
