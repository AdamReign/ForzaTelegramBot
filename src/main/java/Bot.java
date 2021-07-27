import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private static final String TOKEN = System.getenv("TOKEN");
    private static final String USERNAME = System.getenv("USERNAME");
    public Update update;

    @Override
    public void onUpdateReceived(Update update) {
        this.update = update;

        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();

            if (update.getMessage().getText().equals("/start")) {
                try { execute(new SendMessage(chatId, "Пук")); }
                catch (TelegramApiException e) { e.printStackTrace(); }
            }


        }
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    public Bot() {

    }
}
