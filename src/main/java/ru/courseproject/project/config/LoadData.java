package ru.courseproject.project.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import ru.courseproject.project.repository.NotificationRepository;
import ru.courseproject.project.models.Notification;

@Service
public class LoadData implements CommandLineRunner{
    private class UtilsDateTime {

        @Getter
        private int numSeconds;
        @Getter
        private int index;
        private LocalDateTime date;
        private final String BASE_MES = "Сделать домашнюю работу. Напоминание ";

        public String getMessage() {
            return BASE_MES + index++;
        }

        public UtilsDateTime() {
            index = 1;
            numSeconds = 0;
            date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        }

        public LocalDateTime addSecconds(int num) {
            numSeconds += num;
            return date.plusSeconds(numSeconds);
        }
    }

    @Autowired
    private NotificationRepository notificationRepo;

    @Override
    public void run(String... args) {

        var utilsDateTime = new UtilsDateTime();

        try {
            if (notificationRepo.count() > 0) {
                return;
                //notificationRepo.deleteAll();
            }

            List<Notification> ls = List.of(
                    new Notification(null, utilsDateTime.addSecconds(10), utilsDateTime.getMessage(), false),
                    new Notification(null, utilsDateTime.addSecconds(10), utilsDateTime.getMessage(), false),
                    new Notification(null, utilsDateTime.addSecconds(10), utilsDateTime.getMessage(), false),
                    new Notification(null, utilsDateTime.addSecconds(10), utilsDateTime.getMessage(), false)
            );

            notificationRepo.saveAll(ls);

        } catch (Exception ex) {
            // TODO: заменить выводом в лог
            System.out.println("Ошибка загрузки начальных данных:\n" + ex.getMessage());
        }

    }
}
