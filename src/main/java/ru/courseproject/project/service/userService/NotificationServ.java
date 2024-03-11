package ru.courseproject.project.service.userService;

import ru.courseproject.project.models.dto.ResultMethod;

public interface NotificationServ {

    ResultMethod verifyFormatMessage(String mes);

    boolean verifyExistsUserTelegr(Long id);

    ResultMethod createUserTelegram(Long id, String firstName);

    boolean verifyDuplicatesMessage(Long id, String firstName);



}
