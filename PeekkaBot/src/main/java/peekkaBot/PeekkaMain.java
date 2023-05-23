package peekkaBot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@SuppressWarnings("deprecation")
public class PeekkaMain extends TelegramLongPollingBot{
	
	 String token = readTokenFromFile();
//     getToken(token);
     
     private static String readTokenFromFile() {
         String token = null;
         try (BufferedReader reader = new BufferedReader(new FileReader("token.txt"))) {
             token = reader.readLine();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return token;
     }

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
