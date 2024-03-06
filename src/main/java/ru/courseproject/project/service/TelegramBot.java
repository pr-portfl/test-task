package ru.courseproject.project.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ru.courseproject.project.config.BotConfig;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String mesText = update.getMessage().getText();
            String userName = update.getMessage().getChat().getFirstName();

            long chatId = update.getMessage().getChatId();
            switch (mesText) {
                case "/start" :
                    startCommandReceived(chatId, userName);
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }


    private void startCommandReceived(long chatId, String nameUser) {
        String answer = String.format("Привет, %s, рад встретить тебя", nameUser);

        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String mes) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(mes);

        try {
            execute(message);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
}
