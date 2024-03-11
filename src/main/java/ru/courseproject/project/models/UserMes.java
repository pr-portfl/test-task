package ru.courseproject.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_mes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "date_mes", columnDefinition = "DATE")
    private LocalDate dateMes;

    @Column(columnDefinition = "character varying(200)")
    private String mes;
}
