package com.modikon35.telegramtestbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
public class MyBotController {

    private MyTestBot myTestBot;

    @Autowired
    public MyBotController(MyTestBot myTestBot) {
        this.myTestBot = myTestBot;
    }

    @PostMapping("/webhook")
    public void webhook(@RequestBody Update update) throws TelegramApiException {
        myTestBot.execute(myTestBot.onWebhookUpdateReceived(update));
    }
}
