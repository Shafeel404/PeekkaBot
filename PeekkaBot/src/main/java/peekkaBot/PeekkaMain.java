package peekkaBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import botConstants.BotToken;

@SuppressWarnings("deprecation")
public class PeekkaMain extends TelegramLongPollingBot{
	
	 String token = BotToken.readTokenFromFile();
     

	@Override
	public void onUpdateReceived(Update update) {
		Boolean isCommand = update.getMessage().hasText();
		Boolean isPhoto = update.getMessage().hasPhoto();

		if (isCommand) {
			SendMessageBot bot = new SendMessageBot();
			bot.sentMsg(update);
		} else if (isPhoto) {
			SendPhotoBot bot = new SendPhotoBot();
//			long chat_id = update.getMessage().getChatId();
			bot.sentPhoto(update);

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
