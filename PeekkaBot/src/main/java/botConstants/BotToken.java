package botConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BotToken {
	
	public static String readTokenFromFile() {
        String token = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("token.txt"))) {
            token = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

}
