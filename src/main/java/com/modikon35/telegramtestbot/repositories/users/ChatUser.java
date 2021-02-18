package com.modikon35.telegramtestbot.repositories.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class ChatUser {
    @Id
    private long id;

    @Column(name = "is_bot")
    private boolean isBot;

    @Column(name = "first_name")
    private String firstName;

    private String username;

    @Column(name = "chat_id")
    private int chatId;

    public ChatUser(long id, boolean isBot, String firstName, String username, int chatId) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.username = username;
        this.chatId = chatId;
    }
}
