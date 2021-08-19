import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

public class MessageList {

    public SendMessage mainMenu(String chatId) {
        SendMessage msg = new SendMessage(chatId, "[Головне меню]");

        // Клавиатура
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        // Все строчки кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // Все кнопки строчки 1
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Продукти");
        button1.setCallbackData("products");
        rowInline1.add(button1);
        rowsInline.add(rowInline1);

        // Все кнопки строчки 2
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Логіни та паролі");
        button2.setCallbackData("logins_and_passwords");
        rowInline2.add(button2);
        rowsInline.add(rowInline2);

        // Все кнопки строчки 2
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("FAQ");
        button3.setCallbackData("faq");
        rowInline3.add(button3);
        rowsInline.add(rowInline3);


        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        return msg;
    }

    public EditMessageText backToMainMenu(String chatId, int messageId) {
        EditMessageText msg = new EditMessageText("[Головне меню]");
        msg.setChatId(chatId);
        msg.setMessageId(messageId);

        // Клавиатура
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        // Все строчки кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // Все кнопки строчки 1
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Продукти");
        button1.setCallbackData("products");
        rowInline1.add(button1);
        rowsInline.add(rowInline1);

        // Все кнопки строчки 2
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Логіни та паролі");
        button2.setCallbackData("logins_and_passwords");
        rowInline2.add(button2);
        rowsInline.add(rowInline2);

        // Все кнопки строчки 2
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("FAQ");
        button3.setCallbackData("faq");
        rowInline3.add(button3);
        rowsInline.add(rowInline3);

        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        return msg;
    }

    public EditMessageText products(String chatId, int messageId) {
        EditMessageText msg = new EditMessageText("Виберіть компанію");
        msg.setChatId(chatId);
        msg.setMessageId(messageId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton kf = new InlineKeyboardButton();
        kf.setText("КФ.ЮА");
        kf.setCallbackData("products_kf");
        rowInline1.add(kf);

        InlineKeyboardButton forza = new InlineKeyboardButton();
        forza.setText("Forza");
        forza.setCallbackData("products_forza");
        rowInline1.add(forza);
        rowsInline.add(rowInline1);

        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton back = new InlineKeyboardButton();
        back.setText("« Назад");
        back.setCallbackData("main_menu");
        rowInline.add(back);
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        return msg;
    }

    public EditMessageText productsKF(String chatId, int messageId) {
        EditMessageText msg = new EditMessageText("Список всіх продуктів КФ.ЮА");
        msg.setChatId(chatId);
        msg.setMessageId(messageId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowEdit = new ArrayList<>();
        InlineKeyboardButton edit = new InlineKeyboardButton();
        edit.setText("Редагувати");
        edit.setCallbackData("edit_product_kf");
        rowEdit.add(edit);
        rowsInline.add(rowEdit);

        List<InlineKeyboardButton> rowAddDel = new ArrayList<>();
        InlineKeyboardButton add = new InlineKeyboardButton();
        add.setText("Додати");
        add.setCallbackData("add_product_kf");
        rowAddDel.add(add);


        InlineKeyboardButton delete = new InlineKeyboardButton();
        delete.setText("Видалити");
        delete.setCallbackData("delete_product_kf");
        rowAddDel.add(delete);
        rowsInline.add(rowAddDel);

        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton back = new InlineKeyboardButton();
        back.setText("« Назад");
        back.setCallbackData("products");
        rowInline.add(back);
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        return msg;
    }

    public EditMessageText productsForza(String chatId, int messageId) {
        EditMessageText msg = new EditMessageText("Список всіх продуктів Forza");
        msg.setChatId(chatId);
        msg.setMessageId(messageId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowEdit = new ArrayList<>();
        InlineKeyboardButton edit = new InlineKeyboardButton();
        edit.setText("Редагувати");
        edit.setCallbackData("edit_product_forza");
        rowEdit.add(edit);
        rowsInline.add(rowEdit);

        List<InlineKeyboardButton> rowAddDel = new ArrayList<>();
        InlineKeyboardButton add = new InlineKeyboardButton();
        add.setText("Додати");
        add.setCallbackData("add_product_forza");
        rowAddDel.add(add);


        InlineKeyboardButton delete = new InlineKeyboardButton();
        delete.setText("Видалити");
        delete.setCallbackData("delete_product_forza");
        rowAddDel.add(delete);
        rowsInline.add(rowAddDel);

        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton back = new InlineKeyboardButton();
        back.setText("« Назад");
        back.setCallbackData("products");
        rowInline.add(back);
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        return msg;
    }

    public EditMessageText loginsAndPasswords(String chatId, int messageId) {
        EditMessageText msg = new EditMessageText("Список всіх сервісів.");
        msg.setChatId(chatId);
        msg.setMessageId(messageId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowEdit = new ArrayList<>();
        InlineKeyboardButton edit = new InlineKeyboardButton();
        edit.setText("Редагувати");
        edit.setCallbackData("edit_password");
        rowEdit.add(edit);
        rowsInline.add(rowEdit);

        List<InlineKeyboardButton> rowAddDel = new ArrayList<>();
        InlineKeyboardButton add = new InlineKeyboardButton();
        add.setText("Додати");
        add.setCallbackData("add_password");
        rowAddDel.add(add);


        InlineKeyboardButton delete = new InlineKeyboardButton();
        delete.setText("Видалити");
        delete.setCallbackData("delete_password");
        rowAddDel.add(delete);
        rowsInline.add(rowAddDel);

        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton back = new InlineKeyboardButton();
        back.setText("« Назад");
        back.setCallbackData("main_menu");
        rowInline.add(back);
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        return msg;
    }

    public EditMessageText faq(String chatId, int messageId) {
        EditMessageText msg = new EditMessageText("Що Вас цікавить?");
        msg.setChatId(chatId);
        msg.setMessageId(messageId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowEdit = new ArrayList<>();
        InlineKeyboardButton edit = new InlineKeyboardButton();
        edit.setText("Редагувати");
        edit.setCallbackData("edit_faq");
        rowEdit.add(edit);
        rowsInline.add(rowEdit);

        List<InlineKeyboardButton> rowAddDel = new ArrayList<>();
        InlineKeyboardButton add = new InlineKeyboardButton();
        add.setText("Додати");
        add.setCallbackData("add_faq");
        rowAddDel.add(add);


        InlineKeyboardButton delete = new InlineKeyboardButton();
        delete.setText("Видалити");
        delete.setCallbackData("delete_faq");
        rowAddDel.add(delete);
        rowsInline.add(rowAddDel);

        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton back = new InlineKeyboardButton();
        back.setText("« Назад");
        back.setCallbackData("main_menu");
        rowInline.add(back);
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        return msg;
    }
}
