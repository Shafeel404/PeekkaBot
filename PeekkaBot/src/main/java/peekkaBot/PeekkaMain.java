package peekkaBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@SuppressWarnings("deprecation")
public class PeekkaMain extends TelegramLongPollingBot{

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
		return "5996127555:AAHj4B0xWu1-MIqdz3jzPf_-Huk0FfT7t6Y";
	}

}
