package ru.courseproject.project.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.courseproject.project.models.UserMes;
import ru.courseproject.project.models.UserTelegr;

import java.util.List;

@SpringBootTest
public class UserTelegTest {

    @Autowired
    private UserTelegrRepository userTelegrRep;

    @Test
    public void existsUserTelegrById() {
        var result = userTelegrRep.existsById(0L);

        assertFalse(result);
    }

    @Test
    public void createUserTelegram() {

        List<UserMes> ls = List.of();
        var userTelegr = new UserTelegr(1L, "Имя пользователя", ls);

        var res = userTelegrRep.save(userTelegr);
    }


    @Test
    public void verifyExistsUserTelegram() {

        var res = userTelegrRep.verifyDublicateUserTelegr(1L, "Имя пользователя");

        assertTrue(res>0);
    }

}
