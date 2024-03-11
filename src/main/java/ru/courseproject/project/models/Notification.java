package ru.courseproject.project.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_mes", columnDefinition = "timestamp without time zone")
    private LocalDateTime dateMes;

    @Column(columnDefinition="character varying(200)")
    private String mes;

    @Column(columnDefinition = "boolean")
    private boolean status;
}
