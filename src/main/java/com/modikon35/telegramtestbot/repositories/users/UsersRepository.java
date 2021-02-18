package com.modikon35.telegramtestbot.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<ChatUser, Integer> {
}
