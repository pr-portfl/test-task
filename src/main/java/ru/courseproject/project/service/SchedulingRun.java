package ru.courseproject.project.service;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.courseproject.project.models.BuffNotification;
import ru.courseproject.project.models.ListDataNotification;
import ru.courseproject.project.models.UserTelegr;
import ru.courseproject.project.repository.NotificationRepository;
import ru.courseproject.project.repository.UserTelegrRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchedulingRun {
    @Autowired
    private ExchangeRatesBot exchangeRatesBot;

    @Autowired
    private NotificationRepository notificationPepo;

    @Autowired
    private UserTelegrRepository userTelegrRepo;
    private ListDataNotification listDataNotification;

    List<BuffNotification> lsMailMes;
    private int indexLsMailMes = 0;
    private List<UserTelegr> lsUserTelegr;

    @PostConstruct
    public void init() {
        lsMailMes = notificationPepo.getListNotification();

        lsUserTelegr = userTelegrRepo.findAll()
                .stream().filter(item-> item.getId() > 100000L)
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void run() {

        if (lsMailMes.size() == 0 || indexLsMailMes == lsMailMes.size()) {
            return;
        }

        lsUserTelegr.stream().forEach(item -> {
            var buffNotification = lsMailMes.get(indexLsMailMes++);
            exchangeRatesBot.sendMessage(item.getId(), buffNotification.getMes());
        });

    }

}
