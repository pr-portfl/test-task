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

@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {

    @Value("${bot.token}") String botToken;

    @Getter
    private Long chatID;

    public String getToken() {
        return botToken;
    }

    public ExchangeRatesBot(@Value("${bot.token}") String botToken)   {
        super(botToken);
    }

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRatesBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        var message = update.getMessage().getText();
        if (getChatID() == null) {
            chatID = update.getMessage().getChatId();
        }

        switch (message) {
            case "start":
            case "/start":
                var userName = update.getMessage().getChat().getFirstName();
                startCommand(chatID, userName);
                break;
            default: unknownCommand(chatID);
                break;
        }

    }

    private void startCommand(Long chatId, String userName) {
        var text = String.format("Добро пожаловать в бот, %s!", userName);
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }

    private void unknownCommand(Long chatId) {
        var chatIdStr = String.valueOf(chatId);
        var text = "Команда не распознана";
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

    public void setMessage(String chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения: ", e.getMessage());
        }
    }
}
