package comma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
	public static void executeSelect(JTable table, String query, int type){
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost",
					"root", "dark1902");
			Statement st = con.createStatement();
			st.executeQuery("use comma;");
			ResultSet rs = st.executeQuery(query);
			if (st.execute(query)) rs = st.getResultSet();
			
			DefaultTableModel model;
			if(type == MENU){
				model = new DefaultTableModel(new String[]{"Menu Spec","Menu Name", "Cooking Time", "Cost"}, 0);
			}
			else if(type == PERSON)
				model = new DefaultTableModel(new String[]{"Name","Address", "Phone Number", "Position", "Restaurant"}, 0);
			else
				model = new DefaultTableModel(new String[]{"Brand Name","Restaurant Name", "Location", "Menu Spec", "Atmosphere"}, 0);
			while (rs.next()) {
				if(type == MENU){
					String[] column= new String[4];
					column[0]= rs.getString("menu_specid");
					column[1]= rs.getString("menu_name");
					column[2]= rs.getString("menu_time");
					column[3]= rs.getString("menu_cost");
				    model.addRow(new Object[]{column[0], column[1], column[2], column[3]});
				}
				else if(type == PERSON){
					String[] column= new String[3];
					column[0]= rs.getString("person_name");
					column[1]= rs.getString("location_sid");
					column[2]= rs.getString("person_phonenum");
				    model.addRow(new Object[]{column[0], column[1], column[2]});
				}
				else{
					String[] column= new String[5];
					column[0]= rs.getString("brand_name");
					column[1]= rs.getString("restaurant_name");
					column[2]= rs.getString("location_sid");
					column[3]= rs.getString("menu_specid");
					column[4]= rs.getString("atmosphere_id");
				    model.addRow(new Object[]{column[0], column[1], column[2], column[3], column[4]});
				}
			}
			if(model != null) table.setModel(model);
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
