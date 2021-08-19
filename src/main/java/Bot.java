import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    private static final String TOKEN = System.getenv("TOKEN");
    private static final String USERNAME = System.getenv("USERNAME");
    private Update update;

    @Override
    public void onUpdateReceived(Update update) {
        this.update = update;

        try {
            if (update.getMessage() != null && update.getMessage().hasText()) {
                executeCommand(update.getMessage().getText());

            } else if (update.hasCallbackQuery()) {
                responseToRequest(update.getCallbackQuery());
            }
        } catch (TelegramApiException e) { e.printStackTrace(); }
    }

    public void executeCommand(String command) throws TelegramApiException {
        String chatId = update.getMessage().getChatId().toString();

        switch (command) {
            case "/start":
                execute(new MessageList().mainMenu(chatId));
                break;

            case "/send_photo":
                execute(new SendPhoto(chatId, new InputFile(new File("D:/image.jpg"))));
                break;
        }

//        SheetWorker sheetWorker = new SheetWorker();
//        sheetWorker.remove("key3");
//        String[] arr = command.split("=");
//        sheetWorker.add("buttons", Arrays.asList(Arrays.asList(arr[0], arr[1])));
//        sheetWorker.setValue("buttons", arr[0], arr.length<2 ? "null" : arr[1]);
//        sheetWorker.deleteRow();
//        System.out.println(chatId);
    }

    public void responseToRequest(CallbackQuery callbackQuery) throws TelegramApiException {
        String chatId = callbackQuery.getMessage().getChatId().toString();
        int messageId = callbackQuery.getMessage().getMessageId();

        switch (callbackQuery.getData()) {
            case "main_menu":
                execute(new MessageList().backToMainMenu(chatId, messageId));
                break;

            case "products":
                execute(new MessageList().products(chatId, messageId));
                break;

            case "products_kf":
                execute(new MessageList().productsKF(chatId, messageId));
                break;

            case "products_forza":
                execute(new MessageList().productsForza(chatId, messageId));
                break;

            case "logins_and_passwords":
                execute(new MessageList().loginsAndPasswords(chatId, messageId));
                break;

            case "faq":
                execute(new MessageList().faq(chatId, messageId));
                break;
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
}
