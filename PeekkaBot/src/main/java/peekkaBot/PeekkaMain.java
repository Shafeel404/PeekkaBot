package peekkaBot;

import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import botConstants.BotToken;

@SuppressWarnings("deprecation")
public class PeekkaMain extends TelegramLongPollingBot {

	private boolean screaming = false;
	private InlineKeyboardMarkup keyboardM1;
	private InlineKeyboardMarkup keyboardM2;
	String token = BotToken.readTokenFromFile();

	@Override
	public void onUpdateReceived(Update update) {
		var next = InlineKeyboardButton.builder().text("Next").url("https://www.instagram.com").build();

		var back = InlineKeyboardButton.builder().text("Back").url("https://www.facebook.com").build();

		var url = InlineKeyboardButton.builder().text("Tutorial").url("https://core.telegram.org/bots/api").build();

		keyboardM1 = InlineKeyboardMarkup.builder().keyboardRow(List.of(next)).build();

		// Buttons are wrapped in lists since each keyboard is a set of button rows
		keyboardM2 = InlineKeyboardMarkup.builder().keyboardRow(List.of(back)).keyboardRow(List.of(url)).build();
		var msg = update.getMessage();
		var user = msg.getFrom();

		if (msg.isCommand()) {
			if (msg.getText().equals("/scream")) // If the command was /scream, we switch gears
				screaming = true;
			else if (msg.getText().equals("/whisper")) // Otherwise, we return to normal
				screaming = false;
			else if (msg.getText().equals("/menu1"))
				sendMenu(user.getId(), "<b>Menu 1</b>", keyboardM1);
			else if (msg.getText().equals("/menu2"))
				sendMenu(user.getId(), "<b>Menu 2</b>", keyboardM2);

			return; // We don't want to echo commands, so we exit
		}
		if (screaming) // If we are screaming
			scream(user.getId(), update.getMessage()); // Call a custom method
		else
			copyMessage(user.getId(), msg.getMessageId()); // Else proceed normally

	}

	public void sendText(Long who, String what) {
		SendMessage sm = SendMessage.builder().chatId(who.toString()) // Who are we sending a message to
				.text(what).build(); // Message content
		try {
			execute(sm); // Actually sending the message
		} catch (TelegramApiException e) {
			throw new RuntimeException(e); // Any error will be printed here
		}
	}

	public void copyMessage(Long who, Integer msgId) {
		CopyMessage cm = CopyMessage.builder().fromChatId(who.toString()) // We copy from the user
				.chatId(who.toString()) // And send it back to him
				.messageId(msgId) // Specifying what message
				.build();
		try {
			execute(cm);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	private void scream(Long id, Message msg) {
		if (msg.hasText())
			sendText(id, msg.getText().toUpperCase());
		else
			copyMessage(id, msg.getMessageId()); // We can't really scream a sticker
	}

	public void sendMenu(Long who, String txt, InlineKeyboardMarkup kb) {
		SendMessage sm = SendMessage.builder().chatId(who.toString()).parseMode("HTML").text(txt).replyMarkup(kb)
				.build();

		try {
			execute(sm);
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "peekka423_bot";
	}

	@Override
	public String getBotToken() {

		return token;
	}

}
