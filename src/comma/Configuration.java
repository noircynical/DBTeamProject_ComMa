package comma;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Configuration {
	public static String DB_CONNECTION = null;
	public static String DB_USER = null;
	public static String DB_PASSWORD = null;

	public Configuration() {
		File configuration = new File("C:/DBCOURSE/configuration1215009.txt");
		try {
			Scanner in = new Scanner(configuration);
			DB_CONNECTION = in.nextLine();
			DB_USER = in.nextLine();
			DB_PASSWORD = in.nextLine();
		} catch (IOException ex) { System.out.println(ex.getMessage()); }
	}
}
