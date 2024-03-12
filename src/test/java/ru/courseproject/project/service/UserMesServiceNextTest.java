package ru.courseproject.project.service;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.courseproject.project.models.UserMes;
import ru.courseproject.project.repository.UserMesRepository;
import ru.courseproject.project.service.anyService.UserMesServImp;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserMesServiceNextTest {

    @MockBean
    private UserMesRepository userMesRepo;

    @Autowired
    private UserMesServImp userMesServImp;

    @Test
    public void addUserMes_withException() {
        var chartId = 1L;
        var firstName = "user";
        var mes = "01.03.2024 10:1 Сообщение от пользователя";

        var userMes = new UserMes();
        userMes.setId(null);
        userMes.setIdUser(chartId);
        userMes.setMes(mes);
        userMes.setDateMes(LocalDate.now());

        when(userMesRepo.existsUserMes(any(Long.class), any(String.class) )).thenReturn(1);
        when(userMesRepo.save(any(UserMes.class))).thenReturn(userMes);

        var result = userMesServImp.addUserMes(chartId, firstName, mes);

        assertFalse(result.RESULT);
        assertEquals("Формат не опознан", result.MESSAGE);
    }

    @Test
    public void addUserMes_withNoExistsUserMes() {
        var chartId = 1L;
        var firstName = "user";
        var mes = "01.03.2024 10:15 Сообщение от пользователя";

        var userMes = new UserMes();
        userMes.setId(null);
        userMes.setIdUser(chartId);
        userMes.setMes(mes);
        userMes.setDateMes(LocalDate.now());

        when(userMesRepo.existsUserMes(any(Long.class), any(String.class) )).thenReturn(0);
        when(userMesRepo.save(any(UserMes.class))).thenReturn(userMes);

        var result = userMesServImp.addUserMes(chartId, firstName, mes);

        assertTrue(result.RESULT);
        assertEquals("ok", result.MESSAGE);
    }

    @Test
    public void addUserMes_withExistsUserMes() {
        var chartId = 1L;
        var firstName = "user";
        var mes = "01.03.2024 10:15 Сообщение от пользователя";

        var userMes = new UserMes();
        userMes.setId(null);
        userMes.setIdUser(chartId);
        userMes.setMes(mes);
        userMes.setDateMes(LocalDate.now());

        when(userMesRepo.existsUserMes(any(Long.class), any(String.class) )).thenReturn(1);
        when(userMesRepo.save(any(UserMes.class))).thenReturn(userMes);

        var result = userMesServImp.addUserMes(chartId, firstName, mes);

        assertFalse(result.RESULT);
        assertEquals("Повторный ввод сообщения", result.MESSAGE);
    }

}
