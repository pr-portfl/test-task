package ru.courseproject.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.courseproject.project.models.UserTelegr;

public interface UserTelegrRepository extends JpaRepository<UserTelegr, Long> {

    @Query("select count(*) from UserTelegr t where t.id = :id and t.firstName = :firstName ")
    public int verifyDublicateUserTelegr(Long id, String firstName);

}
