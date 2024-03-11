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
    public void existsUserMes() {
        var result = userMesRepo.existsUserMes(1L);

        assertEquals(0, result);
    }


}
