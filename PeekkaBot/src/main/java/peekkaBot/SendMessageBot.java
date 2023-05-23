package peekkaBot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vdurmont.emoji.EmojiParser;
public class SendMessageBot extends PeekkaMain {

	public String sentMessage(Update update) {
		SendMessage message = new SendMessage();

		message.setText("Hi");
		message.setChatId(update.getMessage().getChatId());
		try {
			execute(message);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void sentMsg(Update update) {
		String command = update.getMessage().getText();
		SendMessage message = new SendMessage();
		Long chat_id = update.getMessage().getChatId();
		if (command.equals("/start")) {
			System.out.println(command);
			String answer = EmojiParser.parseToUnicode(":alien:");

			message.setText("PEEKKABOT SUCCESSFULLY STARTED " + answer);
			message.setChatId(update.getMessage().getChatId());
			sentMsg(message);

	
		}else if (command.equals("/findNumber")) {
			String firstname = update.getMessage().getFrom().getFirstName();
			message.setText(firstname);
			message.setChatId(update.getMessage().getChatId());
			sentMsg(message);
		}else if (command.equals("/myname")) {
			String firstname = update.getMessage().getFrom().getFirstName();
			message.setText(firstname);
			message.setChatId(update.getMessage().getChatId());
			sentMsg(message);
		} else if (command.equals("/mylastname")) {
			String lastname = update.getMessage().getFrom().getLastName();
			if (lastname == null) {
				message.setText("you don't have a last name");
				message.setChatId(update.getMessage().getChatId());
				sentMsg(message);
			}
			message.setText(lastname);
			message.setChatId(update.getMessage().getChatId());
			sentMsg(message);
		} else if (command.equals("/myfullname")) {
			String lastname = update.getMessage().getFrom().getLastName();
			if (lastname == null) {
				String fullname = update.getMessage().getFrom().getFirstName();
				message.setText(fullname);
				message.setChatId(update.getMessage().getChatId());
				sentMsg(message);
			} else {
				String fullname = update.getMessage().getFrom().getFirstName() + " "
						+ update.getMessage().getFrom().getLastName();
				message.setText(fullname);
				message.setChatId(update.getMessage().getChatId());
				sentMsg(message);
			}

		} else if (command.equals("/send1000hi")) {
			for (int i = 1; i <= 5; i++) {
				sentMessage(update);
			}
		} else if (command.equals("/pic")) {
			SendPhoto msg = new SendPhoto();
			msg.setChatId(update.getMessage().getChatId());
			msg.setPhoto(new InputFile(
					"AgACAgUAAxkBAAIC62RnZpuKigdk9u-EEGQivPSSQIroAAKxuDEbIHU5Vz3aKt4a0kodAQADAgADeAADLwQ"));
			msg.setCaption("Photo");
			try {
				execute(msg); // Call method to send the photo
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		} else if (command.equals("/button")) {
			System.out.println(command);
			message.setChatId(update.getMessage().getChatId());
			message.setText("Here is your keyboard");
			// Create ReplyKeyboardMarkup object
			ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
			// Create the keyboard (list of keyboard rows)
			List<KeyboardRow> keyboard = new ArrayList<>();
			// Create a keyboard row
			KeyboardRow row = new KeyboardRow();
			// Set each button, you can also use KeyboardButton objects if you need
			// something else than text
			row.add("/myname");
			row.add("/mylastname");
			row.add("/myfullname");
			// Add the first row to the keyboard
			keyboard.add(row);
			// Create another keyboard row
			row = new KeyboardRow();
			// Set each button for the second line
			row.add("/send1000hi");
			row.add("/pic");
			row.add("/hide");
			// Add the second row to the keyboard
			keyboard.add(row);
			// Set the keyboard to the markup
			keyboardMarkup.setKeyboard(keyboard);
			// Add it to the message
			message.setReplyMarkup(keyboardMarkup);
			sentMsg(message);
		} else if (command.equals("/hide")) {
			message.setChatId(update.getMessage().getChatId());
			message.setText("Keyboard hidden");
			ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove(true);
			message.setReplyMarkup(keyboardMarkup);
			sentMsg(message);
		} else {
			// Unknown command
			message.setChatId(chat_id);
			message.setText("Unknown command");
			try {
				execute(message); // Sending our message object to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}

	}

	public void sentMsg(SendMessage message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
