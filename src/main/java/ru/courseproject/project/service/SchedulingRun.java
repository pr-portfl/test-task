package ru.courseproject.project.service;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import ru.courseproject.project.models.ListDataNotification;
import ru.courseproject.project.repository.NotificationRepository;

import java.time.LocalDateTime;

@Component
public class SchedulingRun {
    @Autowired
    private ExchangeRatesBot exchangeRatesBot;

    @Autowired
    private NotificationRepository repo;

    private ListDataNotification listDataNotification;
    @PostConstruct
    private void init() {

        listDataNotification = new ListDataNotification();

        var dt1 = LocalDateTime.now().format(FormaterDateTime.dateTimeFormatter);
        var lsLoad = repo.getList(dt1);

        lsLoad.stream().forEach( item -> listDataNotification.addItem(item) );
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void run() {

        if (exchangeRatesBot.getChatID() == null || listDataNotification.getMap().size() == 0) {
            return;
        }

        var keys = listDataNotification.getMap().keySet().stream().toList() ;
        var item = keys.get(0);

        var mes = listDataNotification.getMap().get(item).getMes();

        var token = exchangeRatesBot.getChatID();
        exchangeRatesBot.setMessage(String.valueOf(token), mes);

        listDataNotification.getMap().remove(item);

    }

}
