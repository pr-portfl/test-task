package ru.courseproject.project.service;


import io.micrometer.observation.ObservationTextPublisher;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.courseproject.project.models.UserMes;
import ru.courseproject.project.models.dto.ResultMethod;
import ru.courseproject.project.repository.UserMesRepository;
import ru.courseproject.project.repository.UserTelegrRepository;
import ru.courseproject.project.service.anyService.UserMesServImp;
import ru.courseproject.project.service.anyService.UserTelegrServ;

import java.util.List;

@SpringBootTest
public class UserMesServiceTest {

    @Autowired
    private UserMesServImp userMesServImp;

    @Autowired
    private UserMesRepository userMesRepo;

    @Autowired
    private UserTelegrRepository userTelegrRepo;

    @Test
    public void addUserMes_thenDeleteAll() {

        long id = 1L;

        userMesRepo.deleteAll();
        userTelegrRepo.deleteAll();

        var strFormat = "10.02.2024 15:30 Сообщение от пользователя";

        var resAdd = userMesServImp.addUserMes(id, "Имя пользователя", strFormat);

        assertInstanceOf(ResultMethod.class, resAdd);
        assertTrue(resAdd.RESULT);

        var resDelete = userMesServImp.deleteAllUserMesByUserTelegrId(id);
        assertTrue(resDelete.RESULT);
    }

    @Test
    public void addUserMes_thenDeleteOne() {

        long id = 1L;

        userMesRepo.deleteAll();
        userTelegrRepo.deleteAll();

        var strFormat = "10.02.2024 15:30 Сообщение от пользователя";

        var resAdd = userMesServImp.addUserMes(id, "Имя пользователя", strFormat);

        assertInstanceOf(ResultMethod.class, resAdd);
        assertTrue(resAdd.RESULT);

        var idUserMes = (long) ((UserMes) resAdd.OBJ).getId();
        var resDelete = userMesServImp.deleteUserMesByUserTelegrId(idUserMes);

        assertTrue(resDelete.RESULT);
    }


}
