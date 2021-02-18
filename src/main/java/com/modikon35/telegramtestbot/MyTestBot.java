package com.modikon35.telegramtestbot;

import com.modikon35.telegramtestbot.repositories.users.ChatUser;
import com.modikon35.telegramtestbot.repositories.users.UsersRepository;
import com.modikon35.untitled.TranslationController;
import com.modikon35.untitled.translation.DetailedTranslation;
import com.modikon35.untitled.translation.Meaning;
import com.modikon35.untitled.translation.Translation;
import com.modikon35.untitled.translation.TranslationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

public class MyTestBot extends TelegramWebhookBot {

    private static final String USERNAME = "modikon35_form_bot";
    private static final String TOKEN = System.getenv("BOT_TOKEN");

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public BotApiMethod<Message> onWebhookUpdateReceived(Update update) {
        Translation translation;

        Message incomingMessage = update.getMessage();

        User user = incomingMessage.getFrom();

        ChatUser telegramChatUser = usersRepository.findById(user.getId()).orElse(null);

        if (telegramChatUser == null) {
            telegramChatUser = new ChatUser(
                    user.getId(),
                    incomingMessage.hasViaBot(),
                    user.getFirstName(),
                    user.getUserName(),
                    incomingMessage.getChatId().intValue());

            usersRepository.save(telegramChatUser);
        }

        try {
            translation = TranslationController.getTranslation(update.getMessage().getText());
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(), "Не удалось обработать слово..");
        }

        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();

        StringBuilder stringBuilder = new StringBuilder();

        TranslationResult translationResult = translation.getTranslationResult();

        if (translationResult == null) {
            return new SendMessage(update.getMessage().getChatId().toString(), "Не удалось найти перевод..");
        }

        List<Meaning> meanings = translationResult.getMeanings();

        for (DetailedTranslation str : meanings.get(0).getDetailedTranslations()) {
            stringBuilder.append(str.getTranslation()).append('\n');
        }

        return sendMessageBuilder
                .chatId(update.getMessage().getChatId().toString())
                .text(stringBuilder.toString())
                .build();
    }

    @Override
    public String getBotPath() {
        return USERNAME;
    }
}
