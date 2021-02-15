package com.modikon35.telegramtestbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class MyBotController {

    private TelegramWebhookBot myTestBot;

    @Autowired
    public MyBotController(TelegramWebhookBot myTestBot) {
        this.myTestBot = myTestBot;
    }

    @PostMapping("/webhook")
    public Message webhook(@RequestBody Update update) {
        Message message = new Message();
        message.setText("TEXT");
        message.setChat(update.getMessage().getChat());
        return message;
        //myTestBot.onWebhookUpdateReceived(update);
    }
}
