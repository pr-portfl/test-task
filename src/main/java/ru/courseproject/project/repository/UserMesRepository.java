package ru.courseproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.courseproject.project.models.UserMes;

import java.util.Collection;

public interface UserMesRepository extends JpaRepository<UserMes, Long> {
    @Query("select u From UserMes u where u.idUser = :id")
    public Collection<UserMes> findAllUserMesByUserTelgId(Long id);

    @Query("Select count(*) from UserMes u where u.idUser = :id ")
    public int existsUserMes(Long id);

}
