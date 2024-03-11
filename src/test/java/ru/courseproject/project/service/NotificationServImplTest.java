package ru.courseproject.project.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.courseproject.project.models.UserTelegr;
import ru.courseproject.project.models.dto.ResultMethod;
import ru.courseproject.project.repository.UserTelegrRepository;
import ru.courseproject.project.service.userService.NotificationServImpl;

@SpringBootTest
public class NotificationServImplTest {

    @Autowired
    private NotificationServImpl notificationServ;

    @Autowired
    private UserTelegrRepository userTelegrRepo;

    @Test
    public void createUserTelegram() {
        var res = notificationServ.createUserTelegram(2L, "Имя пользователя ботом");

        assertInstanceOf(ResultMethod.class, res);
        assertTrue(res.RESULT);

        userTelegrRepo.deleteById(((UserTelegr) res.OBJ).getId());
    }

    @Test
    public void verifyExistsUserTelegr() {
        assertTrue(notificationServ.verifyExistsUserTelegr(1L));
    }

    @Test
    public void verifyDuplicatesMessage() {
        var res = notificationServ.verifyDuplicatesMessage(1L, "Имя пользователя");

        assertTrue(res);
    }

    @Test
    public void verifyFormatMessage() {
        var strMes = "04.05.2024 10:15 Проверочное сообщение";

        var res = notificationServ.verifyFormatMessage(strMes);

        assertTrue(res.RESULT);
        assertInstanceOf(ResultMethod.class, res);
    }

    @Test
    public void verifyFormatMessage_IllegalArg() {
        var strMes = "04.05.2024 10: Проверочное сообщение";

        var res = notificationServ.verifyFormatMessage(strMes);

        assertInstanceOf(ResultMethod.class, res);
        assertFalse(res.RESULT);
        assertEquals("Формат не опознан", res.MESSAGE);
    }

}
