package com.modikon35.telegramtestbot;

import com.modikon.untitled.DictionaryService;
import com.modikon.untitled.translationcontrollers.TranslationController;
import com.modikon.untitled.translationcontrollers.TranslationControllerFactory;
import com.modikon35.telegramtestbot.repositories.users.ChatUser;
import com.modikon35.telegramtestbot.repositories.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Map;

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
        Message incomingMessage = update.getMessage();

        User user = incomingMessage.getFrom();

        ChatUser telegramChatUser = usersRepository.findById(user.getId()).orElse(null);

        if (telegramChatUser == null) {
            telegramChatUser = new ChatUser(incomingMessage);

            usersRepository.save(telegramChatUser);
        }

        String response;
        try {
            response = prepareMessageToResponse(incomingMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return new SendMessage(incomingMessage.getChatId().toString(), "Не удалось обработать слово..");
        }

        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();

        return sendMessageBuilder
                .chatId(update.getMessage().getChatId().toString())
                .parseMode("html")
                .text(response)
                .build();
    }

    private static String prepareMessageToResponse(Message message) {
        Map<String, List<String>> translations;

        TranslationController translationController = TranslationControllerFactory
                .createTranslationController(DictionaryService.YANDEX_API, message.getText());
        translations = translationController.getTranslations();

        StringBuilder stringBuilder = new StringBuilder();

        translations.forEach((k, v) -> stringBuilder
                .append("<i>").append(k).append("</i>")
                .append("\n")
                .append(String.join(", ", v))
                .append("\n"));

        return stringBuilder.toString();
    }

    @Override
    public String getBotPath() {
        return USERNAME;
    }
}
