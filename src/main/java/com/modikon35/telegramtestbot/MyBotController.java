package com.modikon35.telegramtestbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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
    public void webhook(@RequestBody Update update) {
        try {
            myTestBot.execute(myTestBot.onWebhookUpdateReceived(update));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                String chatId = update.getMessage()
                        .getChatId()
                        .toString();

                myTestBot.execute(
                        new SendMessage(
                                chatId,
                                "Не удалось обработать сообщение(WebHook).."));
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }
    }

    @PostMapping("/localwebhook")
    public BotApiMethod<Message> localwebhook(@RequestBody Update update) {
        try {
            return myTestBot.onWebhookUpdateReceived(update);
        } catch (Exception e) {
            e.printStackTrace();
            String chatId = update.getMessage()
                    .getChatId()
                    .toString();
            return new SendMessage(chatId, "Не удалось обработать сообщение(WebHook)..");
        }
    }
}
