package com.modikon35.telegramtestbot;

import com.modikon35.DictionaryParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.util.WebhookUtils;

import java.util.List;

@Component
public class MyTestBot extends TelegramWebhookBot {

    private static final String USERNAME = "modikon35_form_bot";
    private static final String TOKEN = System.getenv("TOKEN");

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public BotApiMethod<Message> onWebhookUpdateReceived(Update update) {
        List<String> definitions = DictionaryParser.getDefinitions(update.getMessage().getText());

        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();

        StringBuilder stringBuilder = new StringBuilder();

        for (String str : definitions) {
            stringBuilder.append(str);
        }

        return sendMessageBuilder
                .chatId(update.getMessage().getChatId().toString())
                .text(stringBuilder.toString())
                .build();
    }

    @Override
    public void onRegister() {
        try {
            WebhookUtils.clearWebhook(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        try {
            WebhookUtils.setWebhook(this, new SetWebhook("https://modikon35formbot.herokuapp.com/webhook"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotPath() {
        return USERNAME;
    }
}
