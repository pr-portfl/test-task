package ru.courseproject.project.models;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class ListDataNotification {

    @Getter
    private Map<LocalTime, BuffNotification> map;

    public ListDataNotification() {
        map = new TreeMap<>();
    }

    public void addItem(BuffNotification item) {
        map.put(item.getTime(), item);
    }

    public Map<LocalTime, BuffNotification> getMap() {
        return map;
    }

}
