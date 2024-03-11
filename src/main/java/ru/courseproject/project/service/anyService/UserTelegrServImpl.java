package ru.courseproject.project.service.anyService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import ru.courseproject.project.models.UserTelegr;
import ru.courseproject.project.models.dto.ResultMethod;
import ru.courseproject.project.repository.UserTelegrRepository;

@Service
public class UserTelegrServImpl implements UserTelegrServ {

    private UserTelegrRepository userTelegrRepo;

    public UserTelegrServImpl(UserTelegrRepository repo) {
        userTelegrRepo = repo;
    }

    @Override
    public ResultMethod verifyFormatMessage(String mes) {

        ResultMethod result = null;

        var pattern = "([0-9\\.\\:\\s]{16})\\s+(.+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(mes);

        if (m.find()) {

            List<String> lsData = List.of(
                    m.group(1),
                    m.group(2) );

            result = new ResultMethod(lsData);
        } else {
            result = new ResultMethod("Формат не опознан");
        }

        return  result;
    }

    @Override
    public boolean verifyExistsUserTelegr(Long id) {
        return userTelegrRepo.existsById(id);
    }

    @Override
    public ResultMethod createUserTelegram(Long id, String firstName) {
        ResultMethod result = null;

        if (verifyExistsUserTelegr(id)) {
            UserTelegr userTelegr;

            try {
                userTelegr = userTelegrRepo.findById(id).orElseThrow();
                return new ResultMethod(userTelegr);
            } catch (Exception ex) {
                return new ResultMethod(ex.getMessage());
            }

        } else {
            var item = new UserTelegr();
            item.setId(id);
            item.setFirstName(firstName);

            // TODO: убрать вариант null
            item.setLsUserMes(null);

            try {
                return new ResultMethod(userTelegrRepo.save(item));
            } catch (Exception ex) {
                return new ResultMethod(ex.getMessage());
            }
        }
    }

    @Override
    public boolean verifyDuplicatesMessage(Long id, String firstName) {
        return userTelegrRepo.verifyDublicateUserTelegr(id, firstName) > 0;
    }

}
