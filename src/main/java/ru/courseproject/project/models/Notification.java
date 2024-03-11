package ru.courseproject.project.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "date_mes")
    private LocalDateTime dateMes;

    private String mes;

}
