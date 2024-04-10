package ru.courseproject.project.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMesTest {
    @Autowired
    private UserMesRepository userMesRepo;

    @Test
    public void findAllByUserTelgId() {
        var result = userMesRepo.findAllUserMesByUserTelgId(1L);

        assertTrue(result.size() == 0);
    }

    @Test
    public void existsUserMes_find() {

        var userMes = userMesRepo.findAll().stream().findFirst().orElseThrow();

        var result = userMesRepo.existsUserMes(userMes.getIdUser(), userMes.getMes());

        assertTrue(result>0);
    }


    @Test
    public void existsUserMes_notFind() {

        var userMes = userMesRepo.findAll().stream().findFirst().orElseThrow();

        var result = userMesRepo.existsUserMes(userMes.getIdUser(), userMes.getMes() + "_");

        assertFalse(result>0);
    }


    @Test
    public void existsUserMes_IllegalUserId() {

        var userMes = userMesRepo.findAll().stream().findFirst().orElseThrow();

        var result = userMesRepo.existsUserMes(userMes.getIdUser() + 100000, userMes.getMes());

        assertFalse(result>0);
    }
}
