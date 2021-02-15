package com.modikon35.telegramtestbot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.util.WebhookUtils;

@Configuration
public class ConfigurationClass {

    @Bean
    public MyTestBot myTestBot() {
        MyTestBot myTestBot = new MyTestBot();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(myTestBot, new SetWebhook());
            WebhookUtils.clearWebhook(myTestBot);
            WebhookUtils.setWebhook(myTestBot, new SetWebhook("https://modikon35formbot.herokuapp.com/webhook"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return myTestBot;
    }
}
