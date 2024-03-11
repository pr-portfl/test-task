package ru.courseproject.project.service;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.courseproject.project.repository.UserTelegrRepository;
import ru.courseproject.project.service.anyService.UserMesServImp;

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {

    private UserMesServImp userMesServImp;
    private UserTelegrRepository userTelegrRepo;

    public ExchangeRatesBot(@Value("${bot.token}") String botToken,
                            UserMesServImp userMesServImp,
                            UserTelegrRepository userTelegrRepository )   {
        super(botToken);
        this.userMesServImp = userMesServImp;
        this.userTelegrRepo = userTelegrRepository;
    }

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRatesBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        var chatID = update.getMessage().getChatId();
        var text = update.getMessage().getText();
        var firstName = update.getMessage().getChat().getFirstName();

        switch (text) {
            case "start":
            case "/start":
                startCommand(chatID, firstName);
                break;
            default: anyMessageFromUser(chatID, firstName, text);
                break;
        }

    }

    private void anyMessageFromUser(long chatId, String firstName, String text) {

        var res = userMesServImp.addUserMes(chatId, firstName, text);

        if (!res.RESULT) {
            if (res.MESSAGE.equalsIgnoreCase("Формат не опознан")) {
                sendMessage(chatId, res.MESSAGE);
            } else {
                sendMessage(chatId, "Запрос отклонен");
            }
        } else {
            sendMessage(chatId, "Сообщение обработано");
        }

    }

    private void startCommand(Long chatId, String userName) {
        var text = String.format("Добро пожаловать в бот, %s!", userName);
        sendMessage(chatId, text);
    }

    public void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    @Override
    public String getBotUsername() {
        return "sprBootCorseProBot";
    }

}
