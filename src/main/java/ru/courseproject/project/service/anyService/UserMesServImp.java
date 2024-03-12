package ru.courseproject.project.service.anyService;

import org.springframework.stereotype.Service;
import ru.courseproject.project.models.UserMes;
import ru.courseproject.project.models.UserTelegr;
import ru.courseproject.project.models.dto.ResultMethod;
import ru.courseproject.project.repository.UserMesRepository;
import ru.courseproject.project.repository.UserTelegrRepository;
import ru.courseproject.project.service.FormaterDateTime;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Service
public class UserMesServImp implements UserMesServ {

    private UserMesRepository userMesRep;
    private UserTelegrServ userTelegrServ;
    private UserTelegrRepository userTelegrRepo;

    public UserMesServImp(UserMesRepository repo,
                          UserTelegrServImpl userTelegrServ,
                          UserTelegrRepository userTelegrRepo) {
        userMesRep = repo;
        this.userTelegrServ = userTelegrServ;
        this.userTelegrRepo = userTelegrRepo;

    }

    @Override
    public Collection<UserMes> getListUserMes(Long id) {
        return null;
    }

    @Override
    public ResultMethod addUserMes(Long chartId, String firstName, String strUserMes) {

        var resVerify = userTelegrServ.verifyFormatMessage(strUserMes.trim());

        try {

            if (!resVerify.RESULT) {
                throw new NumberFormatException(resVerify.MESSAGE);
            }

            var lsFromVerify = (List<String>) resVerify.OBJECT;
            LocalDateTime dateTime;
            String strTextMes;

            dateTime = LocalDateTime
                    .parse(lsFromVerify.get(0), FormaterDateTime.dateTimeFormatterFull);
            strTextMes = lsFromVerify.get(1);

            if (verifyExistsUserMes(chartId, strTextMes)) {
                throw new Exception("Повторный ввод сообщения");
            }

            var resCreate = userTelegrServ.createUserTelegram(chartId, firstName);
            if (!resCreate.RESULT) {
                throw new Exception(resCreate.MESSAGE);
            }

            var userMes = new UserMes();
            userMes.setId(null);
            userMes.setDateMes(dateTime.toLocalDate());
            userMes.setMes(strTextMes);
            userMes.setIdUser(((UserTelegr) resCreate.OBJECT).getId());

            return new ResultMethod(userMesRep.save(userMes));

        } catch (NumberFormatException ex) {
            return new ResultMethod(ex.getMessage());
        } catch (Exception ex) {
            return new ResultMethod(ex.getMessage());
        }
    }

    @Override
    public ResultMethod deleteAllUserMesByUserTelegrId(Long id) {

        var resFind = userMesRep.findAllUserMesByUserTelgId(id);

        if (resFind.size() == 0) {
            return new ResultMethod("Нет данных по сообщениям");
        }

        userMesRep.deleteAll(resFind);
        userTelegrRepo.deleteById(id);

        return new ResultMethod(true, "ok", null);
    }

    @Override
    public ResultMethod deleteUserMesByUserTelegrId(Long id) {
        var resFind = userMesRep.findById(id);

        if (resFind.isEmpty()) {
            return new ResultMethod("Нет данных по сообщению");
        }

        var item = resFind.orElseThrow();
        userMesRep.deleteById((long) item.getId());

        return new ResultMethod(item);
    }

    @Override
    public boolean verifyExistsUserMes(long userId, String mes) {
         return userMesRep.existsUserMes(userId, mes) > 0;
    }

}
