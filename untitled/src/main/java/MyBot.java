import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;



public class MyBot extends TelegramLongPollingBot {

    List<TelegramState> users=new ArrayList<>();
    @Override
    public void onUpdateReceived(Update update) {

        try {
            Long chatId=update.getMessage().getChatId();
            String text=update.getMessage().getText();
            TelegramState currentUser=findByChatId(chatId);
            if (text.equals("/start")){
                SendMessage sendMessage=new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Assalomu aleykum bo'timizga hush kelibsiz \uD83E\uDD1D . Ismingini kriting:");
                execute(sendMessage);
                users.add(currentUser);
                currentUser.setState(UserState.FIRST_NAME);
            }else {
                if (currentUser.getState().equals(UserState.FIRST_NAME)){
                    currentUser.getUser().setFirstName(text);
                    SendMessage sendMessage=new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Maxsulotni Tanlang");
                    ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
                    List<KeyboardRow>rows=new ArrayList<>();
                    KeyboardRow row=new KeyboardRow();
                    KeyboardButton button1=new KeyboardButton();
                    KeyboardButton button2=new KeyboardButton();
                    button1.setText("Unversitetga");
                    button2.setText("prizdent Maktabiga");
                    row.add(button1);
                    row.add(button2);
                    rows.add(row);
                    replyKeyboardMarkup.setKeyboard(rows);
                    execute(sendMessage);
                    currentUser.setState(UserState.SELECT_PRODUCT);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(update.getMessage().getChatId());

    }

    private TelegramState findByChatId(Long chatId) {
       for (TelegramState user:users){
           if (user.getChatId().equals(chatId)){
               return user;
           }
       }
       TelegramState telegramState=new TelegramState();
       telegramState.setChatId(chatId);
       telegramState.setState(UserState.START);
       return telegramState;
    }


    @Override
    public String getBotUsername() {
        return "sobirjonovacademybot";
    }

    @Override
    public String getBotToken() {
        return "6620568801:AAEDiTsxC3lgepmN9zOgbaH1SNbXc_Epzn8";
    }
}
