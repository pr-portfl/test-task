package ru.courseproject.project.models;


import jakarta.persistence.*;

import java.util.Collection;

public class UserTelegr {
    @Id
    private Long id;

    @Column(name = "first_name", columnDefinition = "character varying(100)")
    private String firstName;

    @OneToMany(orphanRemoval=true)
    @JoinColumn(name = "id_user")
    private Collection<UserMes> lsUserMes;
}
