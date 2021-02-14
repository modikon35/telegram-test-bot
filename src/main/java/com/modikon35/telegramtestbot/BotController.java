package com.modikon35.telegramtestbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@RestController
public class BotController {

    private MyTestBot myTestBot;

    @Autowired
    public BotController(MyTestBot myTestBot) {
        this.myTestBot = myTestBot;
    }

    @RequestMapping("/webhook")
    public String answer() {
        return myTestBot.getBotUsername();
    }
}
