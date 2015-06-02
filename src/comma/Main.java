package comma;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	// Class Main : Start the program
	private static MainPanel mPanel= null;
	
	public static void main(String[] args) {
		// create main display
		if(mPanel == null) mPanel= new MainPanel();
		mPanel.setVisible(true);
	}
}
