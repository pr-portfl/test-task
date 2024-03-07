package ru.courseproject.project.scheduling;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RunScheduling {

    @Scheduled(cron = "*/2 * * * * *")
    public static void run() {
        System.out.println("sheduled run");
    }
}
