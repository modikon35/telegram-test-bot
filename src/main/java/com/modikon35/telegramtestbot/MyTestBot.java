package com.modikon35.telegramtestbot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();

        return sendMessageBuilder
                .chatId(update.getMessage().getChatId().toString())
                .text("TEXT")
                .build();
    }

    @Override
    public String getBotPath() {
        return USERNAME;
    }
}
