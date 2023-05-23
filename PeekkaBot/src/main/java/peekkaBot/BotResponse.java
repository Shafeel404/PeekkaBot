package peekkaBot;

import org.telegram.telegrambots.meta.api.objects.Update;

import botConstants.BotAnswerBack;

public class BotResponse {

	public void sendResponse(Update update) {
		var msg = update.getMessage();
		var user = msg.getFrom();
		System.out.println(user);
		BotAnswerBack.sentMessage(update);
	}

}
