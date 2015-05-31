package comma;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
	private static MainPanel mPanel= null;
	
	public static void main(String[] args) {
		if(mPanel == null) mPanel= new MainPanel();
		mPanel.setVisible(true);
	}
	
	public static void callMain(){
		System.out.println("");
	}
}
