package comma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	public static int MENU= 0;
	public static int PERSON= 1;
	public static int STORE= 2;
	
	public static void executeInitiate(String query){
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "dark1902");
			Statement st = con.createStatement();
			st.executeUpdate(query);
//			ResultSet rs = st.executeQuery(query);
//			if (st.execute(query)) rs = st.getResultSet();
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}
	public static void executeUpdate(String query){
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "dark1902");
			Statement st = con.createStatement();
			st.executeQuery("use comma;");
			st.executeUpdate(query);
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}
	public static void executeDropTable(String query){
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "dark1902");
			Statement st = con.createStatement();
			st.executeQuery("use comma;");
			st.executeUpdate("SET foreign_key_checks = 0;");
			st.executeUpdate(query);
			st.executeUpdate("SET foreign_key_checks = 1;");
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}
	
	public static void executeQuery(String query){
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "dark1902");
			Statement st = con.createStatement();
			st.executeQuery("use comma;");
			st.executeQuery(query);
			ResultSet rs = st.executeQuery(query);
			if (st.execute(query)) rs = st.getResultSet();

			while (rs.next()) {
//				String str= rs.getString("menu_name");
//				System.out.println(str);
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}
	
	public static void executeSelect(String query, int type){
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost",
					"root", "dark1902");
			Statement st = con.createStatement();
			st.executeQuery("use comma;");
			ResultSet rs = st.executeQuery(query);
			if (st.execute(query)) rs = st.getResultSet();

			while (rs.next()) {
				// 밑에줄 menu_name부분에 원하는 컬럼값 입력하면 출력댐!
				String str;
				if(type == MENU)
					str= rs.getString("menu_name");
				else if(type == PERSON)
					str= rs.getString("person_name");
				else
					str= rs.getString("restaurant_name");
				System.out.println(str);
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}
	public static void executeInsertMenu(String query, String menu){
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "dark1902");
			Statement st = con.createStatement();
			st.executeQuery("use comma;");
			ResultSet rs=st.executeQuery("select menu_specid from dbcourse_menu_spec where dbcourse_menu_spec.menu_specname=\'"+menu+"\'");
			String key= rs.getString("menu_specid");
			rs= st.executeQuery("");
			if (st.execute(query)) rs = st.getResultSet();
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}
	}

}
