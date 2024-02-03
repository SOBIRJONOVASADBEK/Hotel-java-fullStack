package com.example.telegrambot.service;

import com.example.telegrambot.config.BotConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

   final BotConfig config;
   List<BotCommand> commandList=new ArrayList<>();


    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() &&update.getMessage().hasText()){
            String messageText=update.getMessage().getText();
            long chatId=update.getMessage().getChatId();
            switch (messageText){
                    case "/start":
                    startedCommandReceived(chatId,update.getMessage().getChat().getFirstName());break;
                    default:
                    sendMessage(chatId,"sorry command was not recognized");

            }
        }
    }
    private void startedCommandReceived(long chatId,String name) {

        String answer="Salom "+ name + ", bo'timizga hush kelibsiz ";
        log.info("replied to user "+name);
        sendMessage(chatId,answer);
    }
    private void sendMessage(long chatId, String textToSend){
        SendMessage message=new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        }catch (TelegramApiException e){
            log.error("Error occurred: "+e.getMessage());
        }

    }
}
