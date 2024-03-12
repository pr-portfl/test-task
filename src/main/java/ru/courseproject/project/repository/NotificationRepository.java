package ru.courseproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.courseproject.project.models.BuffNotification;
import ru.courseproject.project.models.Notification;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
/*    @Query("select new ru.courseproject.project.models.BuffNotification (t)"
            + " from Notification t"
            + " where to_char(DATE_TRUNC('day', t.dateMes), 'YYYY-MM-DD') = :strDate")
    public List<BuffNotification> getList(String strDate);*/

    /*    @Query("select new ru.courseproject.project.models.BuffNotification (t)" +
            " from Notification t where t.status = false")
    public List<BuffNotification> getListNotificationNotSended();*/

    @Query("select new ru.courseproject.project.models.BuffNotification (t)" +
            " from Notification t where t.status = false")
    public List<BuffNotification> getListNotification();


}
