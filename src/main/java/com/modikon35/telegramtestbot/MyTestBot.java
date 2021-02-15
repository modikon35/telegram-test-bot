package com.modikon35.telegramtestbot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTestBot extends TelegramWebhookBot {

    @Override
    public String getBotUsername() {
        return "FORM BOT V0.0.1";
    }

    @Override
    public void setWebhook(SetWebhook setWebhook) throws TelegramApiException {
        String url = "https://modikon35formbot.herokuapp.com/webhook";
        setWebhook.setUrl(url);
        super.setWebhook(setWebhook);
    }

    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
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
        return "modikon35_form_bot";
    }
}
