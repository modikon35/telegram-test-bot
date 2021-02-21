package com.modikon35.telegramtestbot.repositories.users;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class ChatUser {
    @Id
    private int id;

    @Column(name = "is_bot")
    private boolean isBot;

    @Column(name = "first_name")
    private String firstName;

    private String username;

    @Column(name = "chat_id")
    private int chatId;

    public ChatUser() {
    }

    public ChatUser(Message message) {
        User user = message.getFrom();
        this.id = user.getId();
        this.isBot = message.hasViaBot();
        this.firstName = user.getFirstName();
        this.username = user.getUserName();
        this.chatId = message.getChatId().intValue();
    }
}
