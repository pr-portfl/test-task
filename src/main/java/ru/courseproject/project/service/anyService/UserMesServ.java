package ru.courseproject.project.service.anyService;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.courseproject.project.models.UserMes;
import ru.courseproject.project.models.dto.ResultMethod;

import java.util.Collection;

public interface UserMesServ {
    Collection<UserMes> getListUserMes(Long id);

    ResultMethod addUserMes(Long chartId, String firstName, String strUserMes);

    ResultMethod deleteAllUserMesByUserTelegrId(Long id );

    ResultMethod deleteUserMesByUserTelegrId(Long id );

}
