package comma;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class MainPanel extends JFrame {
	// Class MainPanel : Display the ui, execute query;
	
	final static int GAP = 10;
	public String[] menuLabelStrings = { "Menu Spec", "Menu Name", "Cooking Time", "Cost"};
	public String[] personLabelStrings = { "Name", "Address", "Phone Number", "Position", "Restaurant" };
	public String[] storeLabelStrings = { "Brand Name", "Restaurant Name", "Location", "Menu Spec", "Atmosphere" };

	// graphic interface
	private JPanel mPanel = null;
	
	public JTextField[] menuSearchText= new JTextField[4];
	public JTextField[] personSearchText= new JTextField[5];
	public JTextField[] storeSearchText= new JTextField[5];
	public JTextField[] menuInsertText= new JTextField[3];
	public JTextField[] personInsertText= new JTextField[3];
	public JTextField[] storeInsertText= new JTextField[5];
	public JTextField[] menuUpdateTextBefore= new JTextField[3];
	public JTextField[] storeUpdateTextBefore= new JTextField[3];
	public JTextField[] personUpdateTextBefore= new JTextField[5];
	public JTextField[] menuUpdateTextAfter= new JTextField[3];
	public JTextField[] personUpdateTextAfter= new JTextField[3];
	public JTextField[] storeUpdateTextAfter= new JTextField[5];
	
	public JButton menuSearchClear, menuSearchAccept;
	public JButton personSearchClear, personSearchAccept;
	public JButton storeSearchClear, storeSearchAccept;
	public JButton menuInsertInsert, menuInsertDelete;
	public JButton personInsertInsert, personInsertDelete;
	public JButton storeInsertInsert, storeInsertDelete;
	public JButton menuUpdateClear, menuUpdateAccept;
	public JButton personUpdateClear, personUpdateAccept;
	public JButton storeUpdateClear, storeUpdateAccept;
	
	public JTable searchResultTable= null;
	
	private Vector<String> serachPersonColumn= null;
	private DefaultTableModel searchPersonTableModel= null; 

	// panel constuctor >> draw the display
	public MainPanel() {
		initUI();
	}

	// initializ the ui of display
	private void initUI() {
		createMenuBar();
		
		if (mPanel == null)
			mPanel = new JPanel();
		mPanel.setLayout(new GridLayout(1, 1, 0, 0));

		JTabbedPane mTopPane = new JTabbedPane();
		JComponent mSearchPane = makeSearchPanel();
		JComponent mInsertPane = makeInsertPanel();
		JComponent mUpdatePane = makeUpdatePanel();
		JComponent mHelpPane = makeHelpPanel();

		mTopPane.addTab("Search", null, mSearchPane, "Search Panel");
		mTopPane.addTab("Insert & Delete", null, mInsertPane,
				"Insert & Delete Panel");
		mTopPane.addTab("Update", null, mUpdatePane, "Update Panel");
		mTopPane.addTab("Help", null, mHelpPane, "Help Panel");

		mPanel.add(mTopPane);
		add(mPanel);

		setTitle("Team :: Com,Ma");
		setSize(700, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// create the menu bar on the top
	private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Data");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eLoadItem = new JMenuItem("Load");
        eLoadItem.setMnemonic(KeyEvent.VK_E);
        eLoadItem.setToolTipText("Load Data");
        eLoadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	try{
            		// open the file and read the data >> execute the query
            		Scanner in = new Scanner(new FileReader("/Users/noirCynical/Workspace/DB_ComMa/query_histo.txt"));
            		String str= in.nextLine();
            		JDBC.executeInitiate(str);
            		System.out.println(str);
            		while((str= in.nextLine()) != null && str.length()>3) JDBC.executeUpdate(str);
            	}catch(FileNotFoundException e){
            		e.printStackTrace();
            	}catch(NoSuchElementException e){}
            }
        });
        JMenuItem eDropItem = new JMenuItem("Drop");
        eDropItem.setMnemonic(KeyEvent.VK_E);
        eDropItem.setToolTipText("Drop Data");
        eDropItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	// drop the table of database 'comma'
            	JDBC.executeDropTable("drop table dbcourse_reservation;");
            	JDBC.executeDropTable("drop table dbcourse_customer;");
            	JDBC.executeDropTable("drop table dbcourse_evaluation;");
            	JDBC.executeDropTable("drop table dbcourse_employee;");
            	JDBC.executeDropTable("drop table dbcourse_position;");
            	JDBC.executeDropTable("drop table dbcourse_restaurant;");
            	JDBC.executeDropTable("drop table dbcourse_atmosphere;");
            	JDBC.executeDropTable("drop table dbcourse_person;");
            	JDBC.executeDropTable("drop table dbcourse_menu;");
            	JDBC.executeDropTable("drop table dbcourse_menu_spec;");
            	JDBC.executeDropTable("drop table dbcourse_location;");
            	JDBC.executeDropTable("drop table dbcourse_brand;");
            }
        });

        file.add(eLoadItem);
        file.add(eDropItem);
        menubar.add(file);

        setJMenuBar(menubar);
    }

	private JComponent makeSearchPanel() {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(2, 1, 10, 0));

		JTabbedPane mCondPane = new JTabbedPane();
		JComponent panel1 = makeSearchMenuPanel();
		JComponent panel2 = makeSearchPersonPanel();
		JComponent panel3 = makeSearchStorePanel();

		mCondPane.addTab("Menu", null, panel1, "search for menu");
		mCondPane.addTab("Person", null, panel2, "search for person");
		mCondPane.addTab("Store", null, panel3, "search for Store");

		panel.add(mCondPane);
		searchResultTable= new JTable();
		JScrollPane scrPane= new JScrollPane();
		scrPane.setViewportView(searchResultTable);
		
		panel.add(scrPane);
		return panel;
	}

	private JComponent makeSearchMenuPanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		menuSearchText= new JTextField[menuLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[menuLabelStrings.length];
		int fieldNumber = 0;

		menuSearchText[fieldNumber] = new JTextField();
		((JTextField) menuSearchText[fieldNumber++]).setColumns(20);
		menuSearchText[fieldNumber] = new JTextField();
		((JTextField) menuSearchText[fieldNumber++]).setColumns(20);
		menuSearchText[fieldNumber] = new JTextField();
		((JTextField) menuSearchText[fieldNumber++]).setColumns(20);
		menuSearchText[fieldNumber] = new JTextField();
		((JTextField) menuSearchText[fieldNumber++]).setColumns(20);

		for (int i = 0; i < menuLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(menuSearchText[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(menuSearchText[i]);
		}
		
		menuSearchClear= new JButton("Clear");
		menuSearchClear.addActionListener(listener);
		panel.add(menuSearchClear);
		menuSearchAccept= new JButton("Search");
		menuSearchAccept.addActionListener(listener);
		panel.add(menuSearchAccept);
		
		makeCompactGrid(panel, menuLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}
	private JComponent makeSearchPersonPanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		personSearchText= new JTextField[personLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[personLabelStrings.length];
		int fieldNumber = 0;

		personSearchText[fieldNumber] = new JTextField();
		((JTextField) personSearchText[fieldNumber++]).setColumns(20);
		personSearchText[fieldNumber] = new JTextField();
		((JTextField) personSearchText[fieldNumber++]).setColumns(20);
		personSearchText[fieldNumber] = new JTextField();
		((JTextField) personSearchText[fieldNumber++]).setColumns(20);
		personSearchText[fieldNumber] = new JTextField();
		((JTextField) personSearchText[fieldNumber++]).setColumns(20);
		personSearchText[fieldNumber] = new JTextField();
		((JTextField) personSearchText[fieldNumber++]).setColumns(20);

		for (int i = 0; i < personLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(personLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(personSearchText[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(personSearchText[i]);
		}
		
		personSearchClear= new JButton("Clear");
		personSearchClear.addActionListener(listener);
		panel.add(personSearchClear);
		personSearchAccept= new JButton("Search");
		personSearchAccept.addActionListener(listener);
		panel.add(personSearchAccept);
		
		makeCompactGrid(panel, personLabelStrings.length+1, 2, GAP, GAP, 20, GAP);
		return panel;
	}
	private JComponent makeSearchStorePanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		storeSearchText= new JTextField[storeLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[storeLabelStrings.length];
		int fieldNumber = 0;

		storeSearchText[fieldNumber] = new JTextField();
		((JTextField) storeSearchText[fieldNumber++]).setColumns(20);
		storeSearchText[fieldNumber] = new JTextField();
		((JTextField) storeSearchText[fieldNumber++]).setColumns(20);
		storeSearchText[fieldNumber] = new JTextField();
		((JTextField) storeSearchText[fieldNumber++]).setColumns(20);
		storeSearchText[fieldNumber] = new JTextField();
		((JTextField) storeSearchText[fieldNumber++]).setColumns(20);
		storeSearchText[fieldNumber] = new JTextField();
		((JTextField) storeSearchText[fieldNumber++]).setColumns(20);

		for (int i = 0; i < storeLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(storeLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(storeSearchText[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(storeSearchText[i]);
		}
		
		storeSearchClear= new JButton("Clear");
		storeSearchClear.addActionListener(listener);
		panel.add(storeSearchClear);
		storeSearchAccept= new JButton("Search");
		storeSearchAccept.addActionListener(listener);
		panel.add(storeSearchAccept);
		
		makeCompactGrid(panel, storeLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}

	private JComponent makeInsertPanel() {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1, 1));

		JTabbedPane mCondPane = new JTabbedPane();
		JComponent panel1 = makeInsertMenuPanel();
		JComponent panel2 = makeInsertPersonPanel();
		JComponent panel3 = makeInsertStorePanel();

		mCondPane.addTab("Menu", null, panel1, "search for menu");
		mCondPane.addTab("Person", null, panel2, "search for person");
		mCondPane.addTab("Store", null, panel3, "search for Store");

		panel.add(mCondPane);
		return panel;
	}
	private JComponent makeInsertMenuPanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		menuInsertText= new JTextField[menuLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[menuLabelStrings.length];
		int fieldNumber = 0;

		menuInsertText[fieldNumber] = new JTextField();
		((JTextField) menuInsertText[fieldNumber++]).setColumns(20);
		menuInsertText[fieldNumber] = new JTextField();
		((JTextField) menuInsertText[fieldNumber++]).setColumns(20);
		menuInsertText[fieldNumber] = new JTextField();
		((JTextField) menuInsertText[fieldNumber++]).setColumns(20);

		for (int i = 1; i < menuLabelStrings.length; i++) {
			panelLeftComponent[i-1] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i-1]).setLabelFor(menuInsertText[i-1]);
			panel.add(panelLeftComponent[i-1]);
			panel.add(menuInsertText[i-1]);
		}
		
		menuInsertInsert= new JButton("Insert");
		menuInsertInsert.addActionListener(listener);
		panel.add(menuInsertInsert);
		menuInsertDelete= new JButton("Delete");
		menuInsertDelete.addActionListener(listener);
		panel.add(menuInsertDelete);
		
		makeCompactGrid(panel, menuLabelStrings.length, 2, GAP, GAP, GAP, GAP);
		return panel;
	}
	private JComponent makeInsertPersonPanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		personInsertText= new JTextField[personLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[personLabelStrings.length];
		int fieldNumber = 0;

		personInsertText[fieldNumber] = new JTextField();
		((JTextField) personInsertText[fieldNumber++]).setColumns(20);
		personInsertText[fieldNumber] = new JTextField();
		((JTextField) personInsertText[fieldNumber++]).setColumns(20);
		personInsertText[fieldNumber] = new JTextField();
		((JTextField) personInsertText[fieldNumber++]).setColumns(20);

		for (int i = 0; i < personLabelStrings.length-2; i++) {
			panelLeftComponent[i] = new JLabel(personLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(personInsertText[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(personInsertText[i]);
		}
		
		personInsertInsert= new JButton("Insert");
		personInsertInsert.addActionListener(listener);
		panel.add(personInsertInsert);
		personInsertDelete= new JButton("Delete");
		personInsertDelete.addActionListener(listener);
		panel.add(personInsertDelete);
		
		makeCompactGrid(panel, personLabelStrings.length-1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}
	private JComponent makeInsertStorePanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		storeInsertText= new JTextField[storeLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[storeLabelStrings.length];
		int fieldNumber = 0;

		storeInsertText[fieldNumber] = new JTextField();
		((JTextField) storeInsertText[fieldNumber++]).setColumns(20);
		storeInsertText[fieldNumber] = new JTextField();
		((JTextField) storeInsertText[fieldNumber++]).setColumns(20);
		storeInsertText[fieldNumber] = new JTextField();
		((JTextField) storeInsertText[fieldNumber++]).setColumns(20);
		storeInsertText[fieldNumber] = new JTextField();
		((JTextField) storeInsertText[fieldNumber++]).setColumns(20);
		storeInsertText[fieldNumber] = new JTextField();
		((JTextField) storeInsertText[fieldNumber++]).setColumns(20);

		for (int i = 0; i < storeLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(storeLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(storeInsertText[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(storeInsertText[i]);
		}
		
		storeInsertInsert= new JButton("Insert");
		storeInsertInsert.addActionListener(listener);
		panel.add(storeInsertInsert);
		storeInsertDelete= new JButton("Delete");
		storeInsertDelete.addActionListener(listener);
		panel.add(storeInsertDelete);
		
		makeCompactGrid(panel, storeLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}

	private JComponent makeUpdatePanel() {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1, 1));

		JTabbedPane mCondPane = new JTabbedPane();
		JComponent panel1 = makeMenuUpdatePanel();
		JComponent panel2 = makePersonUpdatePanel();
		JComponent panel3 = makeStoreUpdatePanel();

		mCondPane.addTab("Menu", null, panel1, "search for menu");
		mCondPane.addTab("Person", null, panel2, "search for person");
		mCondPane.addTab("Store", null, panel3, "search for Store");

		panel.add(mCondPane);
		return panel;
	}
	
	private JComponent makeMenuUpdatePanel(){
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(2, 1, 10, 0));
		
		JPanel inner1= new JPanel(new SpringLayout());
		inner1.setBorder(BorderFactory.createTitledBorder("Before"));
		menuUpdateTextBefore= new JTextField[menuLabelStrings.length-1];
		JComponent[] panelLeftComponent1= new JComponent[menuLabelStrings.length];
		int fieldNumber = 0;
		menuUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextBefore[fieldNumber++]).setColumns(20);
		menuUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextBefore[fieldNumber++]).setColumns(20);
		menuUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextBefore[fieldNumber++]).setColumns(20);
		for (int i = 1; i < menuLabelStrings.length; i++) {
			panelLeftComponent1[i-1] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent1[i-1]).setLabelFor(menuUpdateTextBefore[i-1]);
			inner1.add(panelLeftComponent1[i-1]);
			inner1.add(menuUpdateTextBefore[i-1]);
		}
		inner1.add(new JLabel());
		inner1.add(new JLabel());
		makeCompactGrid(inner1, menuLabelStrings.length, 2, GAP, GAP, GAP, GAP);
		
		JPanel inner2= new JPanel(new SpringLayout());
		inner2.setBorder(BorderFactory.createTitledBorder("After"));
		menuUpdateTextAfter= new JTextField[menuLabelStrings.length-1];
		JComponent[] panelLeftComponent2= new JComponent[menuLabelStrings.length-1];
		fieldNumber = 0;
		menuUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextAfter[fieldNumber++]).setColumns(20);
		menuUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextAfter[fieldNumber++]).setColumns(20);
		menuUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextAfter[fieldNumber++]).setColumns(20);
		for (int i = 1; i < menuLabelStrings.length; i++) {
			panelLeftComponent2[i-1] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent2[i-1]).setLabelFor(menuUpdateTextAfter[i-1]);
			inner2.add(panelLeftComponent2[i-1]);
			inner2.add(menuUpdateTextAfter[i-1]);
		}
		menuUpdateClear= new JButton("Clear");
		menuUpdateClear.addActionListener(listener);
		inner2.add(menuUpdateClear);
		menuUpdateAccept= new JButton("Update");
		menuUpdateAccept.addActionListener(listener);
		inner2.add(menuUpdateAccept);
		makeCompactGrid(inner2, menuLabelStrings.length, 2, GAP, GAP, GAP, GAP);
		
		panel.add(inner1);
		panel.add(inner2);
		return panel;
	}
	private JComponent makePersonUpdatePanel(){
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(2, 1, 10, 0));
		
		JPanel inner1= new JPanel(new SpringLayout());
		inner1.setBorder(BorderFactory.createTitledBorder("Before"));
		personUpdateTextBefore= new JTextField[personLabelStrings.length-2];
		JComponent[] panelLeftComponent1= new JComponent[personLabelStrings.length-2];
		int fieldNumber = 0;
		personUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextBefore[fieldNumber++]).setColumns(20);
		personUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextBefore[fieldNumber++]).setColumns(20);
		personUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextBefore[fieldNumber++]).setColumns(20);
		for (int i = 0; i < personLabelStrings.length-2; i++) {
			panelLeftComponent1[i] = new JLabel(personLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent1[i]).setLabelFor(personUpdateTextBefore[i]);
			inner1.add(panelLeftComponent1[i]);
			inner1.add(personUpdateTextBefore[i]);
		}
		inner1.add(new JLabel());
		inner1.add(new JLabel());
		makeCompactGrid(inner1, personLabelStrings.length-1, 2, GAP, GAP, GAP, GAP);
		
		JPanel inner2= new JPanel(new SpringLayout());
		inner2.setBorder(BorderFactory.createTitledBorder("After"));
		personUpdateTextAfter= new JTextField[personLabelStrings.length-2];
		JComponent[] panelLeftComponent2= new JComponent[personLabelStrings.length-2];
		fieldNumber = 0;
		personUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextAfter[fieldNumber++]).setColumns(20);
		personUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextAfter[fieldNumber++]).setColumns(20);
		personUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextAfter[fieldNumber++]).setColumns(20);
		for (int i = 0; i < personLabelStrings.length-2; i++) {
			panelLeftComponent2[i] = new JLabel(personLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent2[i]).setLabelFor(personUpdateTextAfter[i]);
			inner2.add(panelLeftComponent2[i]);
			inner2.add(personUpdateTextAfter[i]);
		}
		personUpdateClear= new JButton("Clear");
		personUpdateClear.addActionListener(listener);
		inner2.add(personUpdateClear);
		personUpdateAccept= new JButton("Update");
		personUpdateAccept.addActionListener(listener);
		inner2.add(personUpdateAccept);
		makeCompactGrid(inner2, personLabelStrings.length-1, 2, GAP, GAP, GAP, GAP);
		
		panel.add(inner1);
		panel.add(inner2);
		return panel;
	}
	private JComponent makeStoreUpdatePanel(){
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(2, 1, 10, 0));
		
		JPanel inner1= new JPanel(new SpringLayout());
		inner1.setBorder(BorderFactory.createTitledBorder("Before"));
		storeUpdateTextBefore= new JTextField[storeLabelStrings.length];
		JComponent[] panelLeftComponent1= new JComponent[storeLabelStrings.length];
		int fieldNumber = 0;
		storeUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextBefore[fieldNumber++]).setColumns(20);
		storeUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextBefore[fieldNumber++]).setColumns(20);
		storeUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextBefore[fieldNumber++]).setColumns(20);
		storeUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextBefore[fieldNumber++]).setColumns(20);
		storeUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextBefore[fieldNumber++]).setColumns(20);
		for (int i = 0; i < storeLabelStrings.length; i++) {
			panelLeftComponent1[i] = new JLabel(storeLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent1[i]).setLabelFor(storeUpdateTextBefore[i]);
			inner1.add(panelLeftComponent1[i]);
			inner1.add(storeUpdateTextBefore[i]);
		}
		inner1.add(new JLabel());
		inner1.add(new JLabel());
		makeCompactGrid(inner1, storeLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		
		JPanel inner2= new JPanel(new SpringLayout());
		inner2.setBorder(BorderFactory.createTitledBorder("After"));
		storeUpdateTextAfter= new JTextField[storeLabelStrings.length];
		JComponent[] panelLeftComponent2= new JComponent[storeLabelStrings.length];
		fieldNumber = 0;
		storeUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextAfter[fieldNumber++]).setColumns(20);
		storeUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextAfter[fieldNumber++]).setColumns(20);
		storeUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextAfter[fieldNumber++]).setColumns(20);
		storeUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextAfter[fieldNumber++]).setColumns(20);
		storeUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) storeUpdateTextAfter[fieldNumber++]).setColumns(20);
		for (int i = 0; i < storeLabelStrings.length; i++) {
			panelLeftComponent2[i] = new JLabel(storeLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent2[i]).setLabelFor(storeUpdateTextAfter[i]);
			inner2.add(panelLeftComponent2[i]);
			inner2.add(storeUpdateTextAfter[i]);
		}
		storeUpdateClear= new JButton("Clear");
		storeUpdateClear.addActionListener(listener);
		inner2.add(storeUpdateClear);
		storeUpdateAccept= new JButton("Update");
		storeUpdateAccept.addActionListener(listener);
		inner2.add(storeUpdateAccept);
		makeCompactGrid(inner2, storeLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		
		panel.add(inner1);
		panel.add(inner2);
		return panel;
	}
	
	private JComponent makeHelpPanel() {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1, 1, 0, 0));

		JTabbedPane mCondPane = new JTabbedPane();
		JComponent panel1 = makeManualPanel();
		JComponent panel2 = makeInformationPanel();

		mCondPane.addTab("Menual", null, panel1, "Menual for this program");
		mCondPane.addTab("Information", null, panel2,
				"Information about developer");

		panel.add(mCondPane);

		return panel;
	}
	
	private JComponent makeManualPanel(){
		JPanel panel= new JPanel();
		panel.setLayout(new GridLayout(1,1,0,0));
		
		JLabel label= new JLabel();
		
		String str= "<html>(you can use it with some conditions.)<br>"
				+ "1. Search<br>"
				+ "You can find information about 'Menu', 'Person', 'Store' which satisfy the input conditions.<br>"
				+ "- When you write all conditions that you want, click the button 'Search'. <br>Then the program shows the entities which are matched the conditions.<br>"
				+ "- The button 'Clear' means delete all conditions of a section which you clicked.<br>"
				+ "e.g ] If you click the button 'Clear' of Menu section, the conditions of Menu will be deleted.<br>"
				+ "- You can search for Menu, Person, Store at a time. <br>The program will respectively indicates the results.<br>"
				+ "2. Insert& Delete<br>"
				+ "You can insert or delete the entities of Menu, person, store repectively, which satisfy the input conditions.<br>"
				+ "Insert<br>"
				+ "- When you write all conditions that you want to insert, click the button 'Insert'.<br> Then the program insert what you wrote.<br>"
				+ "Delete<br>"
				+ "- When you write all conditions that you want to delete, click the button 'Delete'. <br>Then the program delete the entities which are satisfy the conditions.<br>"
				+ "3. Update<br>"
				+ "You can update the entities of Menu, person, store repectively, which satisfy the input conditions.<br>"
				+ "- Write the conditions into Before_view, to find what you want to update. <br>Then write the conditions into After_view, to change the conditions of found entities.<br>"
				+ "4. HELP<br>"
				+ "- Menual : How to use the program<br>"
				+ "- Information : about Comma<br>"
				+ "5.OTHER TIP<br>"
				+ "- For all sections, you can write just some conditions to use the program.</html>";
		label.setText(str);
		panel.add(label);
		return panel;
	}
	private JComponent makeInformationPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1,0,0));
		
		JLabel label= new JLabel();
		String str= "<html>Team Name : Team - Com,Ma<br>"
				+"Team-Leader: 1215009, Jaeun-Ku<br>"
				+"Team-Mate  : 1202046, Nayun-Seo<br>"
				+"Team-Mate  : 1202051, Yeonsoo-Song<br>"
				+"Team-Mate  : 1215118, Yuyung-Cha<br>"
				+"Team-Mate  : 1315012, Minjee-Kim<br>";
		label.setText(str);
		panel.add(label);
		return panel;
	}
	
	ActionListener listener= new ActionListener(){
		public void actionPerformed(ActionEvent e){ 
			Object obj= e.getSource();
			
			if(obj == menuSearchClear){
				for(int i=0; i<menuLabelStrings.length; i++) menuSearchText[i].setText("");
			}else if(obj == menuSearchAccept){
				String query= "select * from dbcourse_menu";
				String[] text= new String[menuLabelStrings.length];
				int start= -1;
				
				if(menuSearchText[0].getText().toString().length() > 0){
					start= 0;
					query += ",dbcourse_menu_spec where "
							+ "dbcourse_menu_spec.menu_specid=dbcourse_menu.menu_specid "
							+ "and dbcourse_menu_spec.menu_specname=\'"+menuSearchText[0].getText().toString()+"\'";
				}
				if(menuSearchText[1].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 1;
					query += "dbcourse_menu.menu_specname=\'"+menuSearchText[1].getText().toString()+"\'";
				}
				if(menuSearchText[2].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 2;
					query += "dbcourse_menu.menu_time="+menuSearchText[2].getText().toString();
				}
				if(menuSearchText[3].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					query += "dbcourse_menu.menu_cost="+menuSearchText[3].getText().toString();
				}
				query+=";";
				
				System.out.println(query);
				JDBC.executeSelect(searchResultTable, query, JDBC.MENU);
			}else if(obj == personSearchClear){
				for(int i=0; i<personLabelStrings.length; i++) personSearchText[i].setText("");
			}else if(obj == personSearchAccept){
				String query= "select * from dbcourse_person";
				String[] text= new String[personLabelStrings.length];
				int start= -1;

				if(personSearchText[0].getText().toString().length() > 0){
					query += " where ";
					start= 0;
					query +=  "dbcourse_person.person_name=\'"+personSearchText[0].getText().toString()+"\'";          ///이거
				}
				if(personSearchText[1].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_location where ";
					else {
						query.replace(" where ", ",dbcourse_location where ");
						query += " and ";
					}
					start= 1;
					query += "dbcourse_location.location_sname=\'"+personSearchText[1].getText().toString()
							+"\' and dbcourse_location.location_sid=dbcourse_person.location_sid";
				}
				if(personSearchText[2].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 2;
					query += "dbcourse_person.person_phonenum=\'"+personSearchText[2].getText().toString()+"\'";
				}
				if(personSearchText[3].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_employee,dbcourse_position where ";
					else {
						query.replace(" where ", ",dbcourse_employee,dbcourse_position where ");
						query += " and ";
					}
					start= 3;
					query += "dbcourse_position.position_name=\'"+personSearchText[3].getText().toString()
							+"\' and dbcourse_position.position_id=dbcourse_employee.position_id"
							+" and dbcourse_employee.person_id=dbcourse_person.person_id";
				}
				if(personSearchText[4].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_employee where ";
					else {
						if(!query.contains("employee")) query.replace(" where ", ",dbcourse_employee where ");
						query += " and ";
					}
					query += "dbcourse_employee.restaurant_name=\'"+personSearchText[4].getText().toString()+"\'";
				}
				query+=";";
				
				System.out.println(query);
				JDBC.executeSelect(searchResultTable, query, JDBC.PERSON);
			}else if(obj == storeSearchClear){
				for(int i=0; i<storeLabelStrings.length; i++) storeSearchText[i].setText("");
			}else if(obj == storeSearchAccept){
				String query= "select distinct * from dbcourse_restaurant";
				String[] text= new String[storeLabelStrings.length];
				int start= -1;

				if(storeSearchText[0].getText().toString().length() > 0){
					start= 0;
					query += " where dbcourse_restaurant.brand_name=\'"+storeSearchText[0].getText().toString()+"\'";
				}
				if(storeSearchText[1].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 1;
					query += "dbcourse_restaurant.restaurant_name=\'"+storeSearchText[1].getText().toString()+"\'";
				}
				if(storeSearchText[2].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_location where ";
					else {
						query.replace(" where ", ",dbcourse_location where ");
						query += " and ";
					}
					start= 3;
					query += "dbcourse_location.location_sname=\'"+storeSearchText[2].getText().toString()
							+"\' and dbcourse_location.location_sid=dbcourse_restaurant.location_sid";
				}
				if(storeSearchText[3].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_menu_spec where ";
					else {
						query.replace(" where ", ",dbcourse_menu_spec where ");
						query += " and ";
					}
					query += "dbcourse_menu_spec.menu_specname=\'"+storeSearchText[3].getText().toString()
							+"\' and dbcourse_menu_spec.menu_specid=dbcourse_restaurant.menu_specid";
				}
				if(storeSearchText[4].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_atmosphere where ";
					else {
						query.replace(" where ", ",dbcourse_atmosphere where ");
						query += " and ";
					}
					query += "dbcourse_atmosphere.atmosphere_name=\'"+storeSearchText[4].getText().toString()
							+"\' and dbcourse_atmosphere.atmosphere_id=dbcourse_restaurant.atmosphere_id";
				}
				query+=";";

				System.out.println(query);
				JDBC.executeSelect(searchResultTable, query, JDBC.STORE);
			}else if(obj == menuInsertInsert){
				String query= "insert into dbcourse_menu(menu_specid,menu_name,menu_id,menu_time,menu_cost) values (";
				String specid= Integer.toString(((int)Math.random()*9+1)*1000);
				String name= "\'"+menuInsertText[0].getText().toString()+"\'";
				String time= menuInsertText[1].getText().toString();
				String cost= menuInsertText[2].getText().toString();
				String menuid= specid.substring(0, 0)+Integer.toString((int)Math.random()*1000);
				query+= (specid+","+name+","+menuid+","+time+","+cost);
				query+= ");";
				
				System.out.println(query);
				JDBC.executeUpdate(query);
			}else if(obj == menuInsertDelete){
				String query= "delete from dbcourse_menu";
				int start= -1;
				
				if(menuInsertText[0].getText().toString().length() > 0){
					start= 0;
					query += " where dbcourse_menu.menu_name=\'"+menuInsertText[0].getText().toString()+"\'";
				}
				if(menuInsertText[1].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 1;
					query += "dbcourse_menu.menu_time="+menuInsertText[1].getText().toString();
				}
				if(menuInsertText[2].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 2;
					query += "dbcourse_menu.menu_cost="+menuInsertText[2].getText().toString();
				}
				
				query+=";";
				System.out.println(query);
				JDBC.executeUpdate(query);
			}else if(obj == personInsertInsert){
				String query= "insert into dbcourse_person(person_id,person_name,location_sid,person_phonenum) values (";
				String specid= Integer.toString(((int)Math.random()*3+6)*10000+(int)Math.random()*10000);
				String name= "\'"+personInsertText[0].getText().toString()+"\'";
				String address= personInsertText[1].getText().toString();
				String phonenum= "\'"+personInsertText[2].getText().toString()+"\'";
				query+= (specid+","+name+",11111,"+phonenum);
				query+= ");";
				JDBC.executeUpdate(query);
			}else if(obj == personInsertDelete){
				String query= "delete from dbcourse_person";
				String[] text= new String[personLabelStrings.length];
				int start= -1;

				if(personInsertText[0].getText().toString().length() > 0){
					query += " where ";
					start= 0;
					query +=  "dbcourse_person.person_name=\'"+personInsertText[0].getText().toString()+"\'";          ///이거
				}
				if(personInsertText[1].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_location where ";
					else {
						query.replace(" where ", ",dbcourse_location where ");
						query += " and ";
					}
					start= 1;
					query += "dbcourse_location.location_sname=\'"+personInsertText[1].getText().toString()
							+"\' and dbcourse_location.location_sid=dbcourse_person.location_sid";
				}
				if(personInsertText[2].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 2;
					query += "dbcourse_person.person_phonenum=\'"+personInsertText[2].getText().toString()+"\'";
				}
				if(personInsertText[3].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_employee,dbcourse_position where ";
					else {
						query.replace(" where ", ",dbcourse_employee,dbcourse_position where ");
						query += " and ";
					}
					start= 3;
					query += "dbcourse_position.position_name=\'"+personInsertText[3].getText().toString()
							+"\' and dbcourse_position.position_id=dbcourse_employee.position_id"
							+" and dbcourse_employee.person_id=dbcourse_person.person_id";
				}
				if(personInsertText[4].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_employee where ";
					else {
						if(!query.contains("employee")) query.replace(" where ", ",dbcourse_employee where ");
						query += " and ";
					}
					query += "dbcourse_employee.restaurant_name=\'"+personInsertText[4].getText().toString()+"\'";
				}
				query+=";";
				
				System.out.println(query);
				JDBC.executeUpdate(query);
			}else if(obj == storeInsertInsert){
				String query= "insert into dbcourse_restaurant(brand_name,restaurant_id,restaurant_name,location_sid) values (";
				String specid= Integer.toString(((int)Math.random()*3+6)*10000+(int)Math.random()*10000);
				String brand= "\'"+storeInsertText[0].getText().toString()+"\'";
				String name= "\'"+storeInsertText[1].getText().toString()+"\'";
				String address= storeInsertText[2].getText().toString();
				query+= (brand+","+specid+","+name+",11107");
				query+= ");";
				
				System.out.println(query);
				JDBC.executeUpdate(query);
			}else if(obj == storeInsertDelete){
				String query= "delete from dbcourse_restaurant";
				String[] text= new String[storeLabelStrings.length];
				int start= -1;

				if(storeInsertText[0].getText().toString().length() > 0){
					start= 0;
					query += " where dbcourse_restaurant.brand_name=\'"+storeInsertText[0].getText().toString()+"\'";
				}
				if(storeInsertText[1].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 1;
					query += "dbcourse_restaurant.restaurant_name=\'"+storeInsertText[1].getText().toString()+"\'";
				}
				if(storeInsertText[2].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_location where ";
					else {
						query.replace(" where ", ",dbcourse_location where ");
						query += " and ";
					}
					start= 3;
					query += "dbcourse_location.location_sname=\'"+storeInsertText[2].getText().toString()
							+"\' and dbcourse_location.location_sid=dbcourse_restaurant.location_sid";
				}
				if(storeInsertText[3].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_menu_spec where ";
					else {
						query.replace(" where ", ",dbcourse_menu_spec where ");
						query += " and ";
					}
					query += "dbcourse_menu_spec.menu_specname=\'"+storeInsertText[3].getText().toString()
							+"\' and dbcourse_menu_spec.menu_specid=dbcourse_restaurant.menu_specid";
				}
				if(storeInsertText[4].getText().toString().length() > 0){
					if(start == -1) query += ",dbcourse_atmosphere where ";
					else {
						query.replace(" where ", ",dbcourse_atmosphere where ");
						query += " and ";
					}
					query += "dbcourse_atmosphere.atmosphere_name=\'"+storeInsertText[4].getText().toString()
							+"\' and dbcourse_atmosphere.atmosphere_id=dbcourse_restaurant.atmosphere_id";
				}
				query+=";";

				System.out.println(query);
				JDBC.executeUpdate(query);
			}else if(obj == menuUpdateClear){
				for(int i=0; i<menuLabelStrings.length; i++){
					menuUpdateTextBefore[i].setText("");
					menuUpdateTextAfter[i].setText("");
				}
			}else if(obj == menuUpdateAccept){
				String query= "update dbcourse_menu set ";
				
				String inner= " where (select * from dbcourse_menu";
				int start= -1;
				if(menuUpdateTextBefore[0].getText().toString().length() > 0){
					start= 0;
					inner += " where dbcourse_menu.menu_specname=\'"+menuUpdateTextBefore[1].getText().toString()+"\'";
				}
				if(menuUpdateTextBefore[1].getText().toString().length() > 0){
					if(start == -1) inner += " where ";
					else inner += " and ";
					start= 1;
					inner += "dbcourse_menu.menu_time="+menuUpdateTextBefore[1].getText().toString();
				}
				if(menuUpdateTextBefore[2].getText().toString().length() > 0){
					if(start == -1) inner += " where ";
					else inner += " and ";
					inner += "dbcourse_menu.menu_cost="+menuUpdateTextBefore[2].getText().toString();
				}
				inner+=");";
				
				String set="";
				start= -1;
				if(menuUpdateTextAfter[0].getText().toString().length() > 0){
					start= 0;
					set+= "menu_name=\'"+menuUpdateTextAfter[0].getText().toString()+"\'";
				}
				if(menuUpdateTextAfter[1].getText().toString().length() > 0){
					if(start != -1) set += " and ";
					start= 1;
					set += "menu_time="+menuUpdateTextAfter[1].getText().toString();
				}
				if(menuUpdateTextAfter[2].getText().toString().length() > 0){
					if(start != -1) set += " and ";
					set += "menu_cost="+menuUpdateTextAfter[2].getText().toString()+" ";
				}
				query += set+inner;
				
				System.out.println(query);
				JDBC.executeUpdate(query);
			}else if(obj == personUpdateClear){
				for(int i=0; i<personLabelStrings.length; i++){
					personUpdateTextBefore[i].setText("");
					personUpdateTextAfter[i].setText("");
				}
			}else if(obj == personUpdateAccept){
				String query= "update dbcourse_person set ";
				
				String inner= " where (select * from dbcourse_person";
				int start= -1;
				if(personUpdateTextBefore[0].getText().toString().length() > 0){
					start= 0;
					inner += " where dbcourse_person.person_name=\'"+personUpdateTextBefore[0].getText().toString()+"\'";
				}
				if(personUpdateTextBefore[2].getText().toString().length() > 0){
					if(start == -1) inner += " where ";
					else inner += " and ";
					inner += "dbcourse_person.person_phonenum=\'"+personUpdateTextBefore[2].getText().toString()+"\'";
				}
				inner+=");";
				
				String set="";
				start= -1;
				if(personUpdateTextAfter[0].getText().toString().length() > 0){
					start= 0;
					set+= "person_name=\'"+personUpdateTextAfter[0].getText().toString()+"\'";
				}
				if(personUpdateTextAfter[2].getText().toString().length() > 0){
					if(start != -1) set += " and ";
					set += "person_phonenum=\'"+personUpdateTextAfter[2].getText().toString()+"\' ";
				}
				query += set+inner;
				
				System.out.println(query);
				JDBC.executeUpdate(query);
			}else if(obj == storeUpdateClear){
				for(int i=0; i<storeLabelStrings.length; i++){
					storeUpdateTextBefore[i].setText("");
					storeUpdateTextAfter[i].setText("");
				}
			}else if(obj == storeUpdateAccept){
				
			}
			
		}
	};

	private void makeCompactGrid(Container parent, int rows, int cols, int initialX, int initialY, int xPad, int yPad) {
		SpringLayout layout;
		try {
			layout = (SpringLayout) parent.getLayout();
		} catch (ClassCastException exc) {
			exc.printStackTrace();
			return;
		}

		Spring x = Spring.constant(initialX);
		for (int c = 0; c < cols; c++) {
			Spring width = Spring.constant(0);
			for (int r = 0; r < rows; r++) width = Spring.max(width, getConstraintsForCell(r, c, parent, cols).getWidth());
			for (int r = 0; r < rows; r++) {
				SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
				constraints.setX(x);
				constraints.setWidth(width);
			}
			x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
		}

		Spring y = Spring.constant(initialY);
		for (int r = 0; r < rows; r++) {
			Spring height = Spring.constant(0);
			for (int c = 0; c < cols; c++)
				height = Spring.max(height, getConstraintsForCell(r, c, parent, cols).getHeight());
			for (int c = 0; c < cols; c++) {
				SpringLayout.Constraints constraints = getConstraintsForCell(r, c, parent, cols);
				constraints.setY(y);
				constraints.setHeight(height);
			}
			y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
		}

		SpringLayout.Constraints pCons = layout.getConstraints(parent);
		pCons.setConstraint(SpringLayout.SOUTH, y);
		pCons.setConstraint(SpringLayout.EAST, x);
	}

	private SpringLayout.Constraints getConstraintsForCell(int row, int col, Container parent, int cols) {
		SpringLayout layout = (SpringLayout) parent.getLayout();
		Component c = parent.getComponent(row * cols + col);
		return layout.getConstraints(c);
	}
}
