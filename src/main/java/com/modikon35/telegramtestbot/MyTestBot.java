package com.modikon35.telegramtestbot;

import com.modikon35.untitled.DictionaryParser;
import com.modikon35.untitled.TranslationController;
import com.modikon35.untitled.YandexDictionaryParser;
import com.modikon35.untitled.translation.DetailedTranslation;
import com.modikon35.untitled.translation.Meaning;
import com.modikon35.untitled.translation.Translation;
import com.modikon35.untitled.translation.TranslationResult;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class MyTestBot extends TelegramWebhookBot {

    private static final String USERNAME = "modikon35_form_bot";
    private static final String TOKEN = System.getenv("TOKEN");

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
