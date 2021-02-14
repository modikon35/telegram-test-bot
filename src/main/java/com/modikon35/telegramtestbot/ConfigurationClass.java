package com.modikon35.telegramtestbot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ConfigurationClass {

    @Bean
    public MyTestBot myTestBotInit() {
        MyTestBot myTestBot = new MyTestBot();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(myTestBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return myTestBot;
    }
}
