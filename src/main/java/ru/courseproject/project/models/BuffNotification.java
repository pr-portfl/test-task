package ru.courseproject.project.models;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class BuffNotification {
    private Integer id;
    private LocalTime time;
    private LocalDate date;
    private String mes;

    public BuffNotification(Notification notification) {
        this.id = notification.getId();
        this.time = notification.getDateMes().toLocalTime();
        this.date = notification.getDateMes().toLocalDate();
        this.mes = notification.getMes();
    }
}
