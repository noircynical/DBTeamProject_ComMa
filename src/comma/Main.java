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
//		try {
//			Connection con = null;
//
//			con = DriverManager.getConnection("jdbc:mysql://localhost",
//					"root", "dark1902");
//
//			Statement st = null;
//			ResultSet rs = null;
//			st = con.createStatement();
//			rs = st.executeQuery("SHOW DATABASES");
//
//			if (st.execute("SHOW DATABASES")) rs = st.getResultSet();
//
//			while (rs.next()) {
//				String str = rs.getNString(1);
//				System.out.println(str);
//			}
//		} catch (SQLException sqex) {
//			System.out.println("SQLException: " + sqex.getMessage());
//			System.out.println("SQLState: " + sqex.getSQLState());
//		}
	}
	
	public static void callMain(){
		System.out.println("");
	}
}
