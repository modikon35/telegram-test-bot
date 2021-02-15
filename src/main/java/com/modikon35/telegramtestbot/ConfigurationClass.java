package com.modikon35.telegramtestbot;

import com.modikon35.telegramtestbot.commands.NewTestCommand;
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
            //myTestBot.register(new NewTestCommand());
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            String url = "https://modikon35formbot.herokuapp.com/" + System.getenv("TOKEN") + "webhook";
            telegramBotsApi.registerBot(myTestBot, new SetWebhook(url));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return myTestBot;
    }
}
