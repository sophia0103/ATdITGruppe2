package gemuese4you;

import java.util.Locale;
import java.util.ResourceBundle;

import view.LoginScreenView;

public class Starter {
	public static ResourceBundle content;
	// start of the program
		public static void main(String[] args) {
			LoginScreenView log = new LoginScreenView();
			String language = new String(args[0]);
			String country = new String(args[1]);
			Locale currentLocale = new Locale(language, country);
			content = ResourceBundle.getBundle("Content", currentLocale);
		}
	
}
