package com.modikon35.telegramtestbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class MyBotController {

    private TelegramWebhookBot myTestBot;

    @Autowired
    public MyBotController(TelegramWebhookBot myTestBot) {
        this.myTestBot = myTestBot;
    }

    @PostMapping("/webhook")
    public void webhook(@RequestBody Update update) {
        myTestBot.onWebhookUpdateReceived(update);
    }
}
