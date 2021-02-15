package com.modikon35.telegramtestbot;

import com.modikon35.untitled.DictionaryParser;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

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
        List<String> definitions;

        try {
            definitions = DictionaryParser.getDefinitions(update.getMessage().getText());
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(), e.getMessage());
        }

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
    public String getBotPath() {
        return USERNAME;
    }
}
