package botConstants;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import peekkaBot.PeekkaMain;

public class BotAnswerBack {

	public static String sentMessage(Update update) {
		SendMessage message = new SendMessage();
		PeekkaMain main = new PeekkaMain();
		message.setText("Hi");
		message.setChatId(update.getMessage().getChatId());
		try {
			main.execute(message);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
