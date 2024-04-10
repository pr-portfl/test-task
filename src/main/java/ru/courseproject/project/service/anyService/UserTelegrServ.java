package ru.courseproject.project.service.anyService;

import ru.courseproject.project.models.dto.ResultMethod;

public interface UserTelegrServ {

    ResultMethod verifyFormatMessage(String mes);

    boolean verifyExistsUserTelegr(Long id);

    ResultMethod createUserTelegram(Long id, String firstName);

    boolean verifyDuplicatesMessage(Long id, String firstName);



}
