package com.modikon35.telegramtestbot;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyTestBot extends TelegramWebhookBot {
    @Override
    public String getBotUsername() {
        return "MY NAME IS BOT";
    }

    @Override
    public String getBotToken() {
        return null;
    }

    @Override
    public void onRegister() {

    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return null;
    }
}
