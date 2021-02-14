package com.modikon35.telegramtestbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BotController {

    private MyTestBot myTestBot;

    @Autowired
    public BotController(MyTestBot myTestBot) {
        this.myTestBot = myTestBot;
    }

    @RequestMapping("/webhook")
    @ResponseBody
    public String answer() {
        return myTestBot.getBotUsername();
    }
}
