package ru.courseproject.project.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "user_telegr")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTelegr {
    @Id
    private Long id;

    @Column(name = "first_name", columnDefinition = "character varying(100)")
    private String firstName;

    @OneToMany(orphanRemoval=true)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private Collection<UserMes> lsUserMes;
}
