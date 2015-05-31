package comma;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

public class MainPanel extends JFrame {
	final static int GAP = 10;
	public String[] menuLabelStrings = { "Menu Spec", "Menu Name", "Cooking Time", "Cost"};
	public String[] personLabelStrings = { "Name", "Address", "Phone Number", "Position", "Restaurant" };
	public String[] storeLabelStrings = { "Brand Name", "Restaurant Name", "Big Location", "Small Location", "Menu Spec", "Atmosphere" };

	private JPanel mPanel = null;
	
	public JTextField[] menuSearchText= new JTextField[4];
	public JTextField[] personSearchText= new JTextField[5];
	public JTextField[] storeSearchText= new JTextField[6];
	public JTextField[] menuInsertText= new JTextField[4];
	public JTextField[] personInsertText= new JTextField[5];
	public JTextField[] storeInsertText= new JTextField[6];
	public JTextField[] menuUpdateTextBefore= new JTextField[4];
	public JTextField[] storeUpdateTextBefore= new JTextField[5];
	public JTextField[] personUpdateTextBefore= new JTextField[6];
	public JTextField[] menuUpdateTextAfter= new JTextField[4];
	public JTextField[] personUpdateTextAfter= new JTextField[5];
	public JTextField[] storeUpdateTextAfter= new JTextField[6];
	
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

	public MainPanel() {
		initUI();
		
//		openJDBC();
	}

	private void initUI() {
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
		return panel;
	}
	
	private DefaultTableModel makeTableModel(String[] labels){
		Vector<String> temp= new Vector<String>();
		for(int i=0; i<labels.length; i++) temp.add(labels[i]);
		return new DefaultTableModel(temp, 0);
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
		menuInsertText[fieldNumber] = new JTextField();
		((JTextField) menuInsertText[fieldNumber++]).setColumns(20);

		for (int i = 0; i < menuLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(menuInsertText[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(menuInsertText[i]);
		}
		
		menuInsertInsert= new JButton("Insert");
		menuInsertInsert.addActionListener(listener);
		panel.add(menuInsertInsert);
		menuInsertDelete= new JButton("Delete");
		menuInsertDelete.addActionListener(listener);
		panel.add(menuInsertDelete);
		
		makeCompactGrid(panel, menuLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
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
		personInsertText[fieldNumber] = new JTextField();
		((JTextField) personInsertText[fieldNumber++]).setColumns(20);
		personInsertText[fieldNumber] = new JTextField();
		((JTextField) personInsertText[fieldNumber++]).setColumns(20);

		for (int i = 0; i < personLabelStrings.length; i++) {
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
		
		makeCompactGrid(panel, personLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
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
		menuUpdateTextBefore= new JTextField[menuLabelStrings.length];
		JComponent[] panelLeftComponent1= new JComponent[menuLabelStrings.length];
		int fieldNumber = 0;
		menuUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextBefore[fieldNumber++]).setColumns(20);
		menuUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextBefore[fieldNumber++]).setColumns(20);
		menuUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextBefore[fieldNumber++]).setColumns(20);
		menuUpdateTextBefore[fieldNumber] = new JTextField();
		for (int i = 0; i < menuLabelStrings.length; i++) {
			panelLeftComponent1[i] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent1[i]).setLabelFor(menuUpdateTextBefore[i]);
			inner1.add(panelLeftComponent1[i]);
			inner1.add(menuUpdateTextBefore[i]);
		}
		inner1.add(new JLabel());
		inner1.add(new JLabel());
		makeCompactGrid(inner1, menuLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		
		JPanel inner2= new JPanel(new SpringLayout());
		inner2.setBorder(BorderFactory.createTitledBorder("After"));
		menuUpdateTextAfter= new JTextField[menuLabelStrings.length];
		JComponent[] panelLeftComponent2= new JComponent[menuLabelStrings.length];
		fieldNumber = 0;
		menuUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextAfter[fieldNumber++]).setColumns(20);
		menuUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextAfter[fieldNumber++]).setColumns(20);
		menuUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) menuUpdateTextAfter[fieldNumber++]).setColumns(20);
		menuUpdateTextAfter[fieldNumber] = new JTextField();
		for (int i = 0; i < menuLabelStrings.length; i++) {
			panelLeftComponent2[i] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent2[i]).setLabelFor(menuUpdateTextAfter[i]);
			inner2.add(panelLeftComponent2[i]);
			inner2.add(menuUpdateTextAfter[i]);
		}
		menuUpdateClear= new JButton("Clear");
		menuUpdateClear.addActionListener(listener);
		inner2.add(menuUpdateClear);
		menuUpdateAccept= new JButton("Update");
		menuUpdateAccept.addActionListener(listener);
		inner2.add(menuUpdateAccept);
		makeCompactGrid(inner2, menuLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		
		panel.add(inner1);
		panel.add(inner2);
		return panel;
	}
	private JComponent makePersonUpdatePanel(){
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(2, 1, 10, 0));
		
		JPanel inner1= new JPanel(new SpringLayout());
		inner1.setBorder(BorderFactory.createTitledBorder("Before"));
		personUpdateTextBefore= new JTextField[personLabelStrings.length];
		JComponent[] panelLeftComponent1= new JComponent[personLabelStrings.length];
		int fieldNumber = 0;
		personUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextBefore[fieldNumber++]).setColumns(20);
		personUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextBefore[fieldNumber++]).setColumns(20);
		personUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextBefore[fieldNumber++]).setColumns(20);
		personUpdateTextBefore[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextBefore[fieldNumber++]).setColumns(20);
		personUpdateTextBefore[fieldNumber] = new JTextField();
		for (int i = 0; i < personLabelStrings.length; i++) {
			panelLeftComponent1[i] = new JLabel(personLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent1[i]).setLabelFor(personUpdateTextBefore[i]);
			inner1.add(panelLeftComponent1[i]);
			inner1.add(personUpdateTextBefore[i]);
		}
		inner1.add(new JLabel());
		inner1.add(new JLabel());
		makeCompactGrid(inner1, personLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		
		JPanel inner2= new JPanel(new SpringLayout());
		inner2.setBorder(BorderFactory.createTitledBorder("After"));
		personUpdateTextAfter= new JTextField[personLabelStrings.length];
		JComponent[] panelLeftComponent2= new JComponent[personLabelStrings.length];
		fieldNumber = 0;
		personUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextAfter[fieldNumber++]).setColumns(20);
		personUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextAfter[fieldNumber++]).setColumns(20);
		personUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextAfter[fieldNumber++]).setColumns(20);
		personUpdateTextAfter[fieldNumber] = new JTextField();
		((JTextField) personUpdateTextAfter[fieldNumber++]).setColumns(20);
		personUpdateTextAfter[fieldNumber] = new JTextField();
		for (int i = 0; i < personLabelStrings.length; i++) {
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
		makeCompactGrid(inner2, personLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		
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
		storeUpdateTextBefore[fieldNumber] = new JTextField();
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
		storeUpdateTextAfter[fieldNumber] = new JTextField();
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
		JComponent panel1 = makeTextPanel("Menual");
		JComponent panel2 = makeInformationPanel();

		mCondPane.addTab("Menual", null, panel1, "Menual for this program");
		mCondPane.addTab("Information", null, panel2,
				"Information about developer");

		panel.add(mCondPane);

		return panel;
	}

	private JComponent makeInformationPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JLabel label1 = new JLabel("Username :", JLabel.RIGHT);
		JLabel label2 = new JLabel("Password :", JLabel.RIGHT);
		JLabel label3 = new JLabel("Confirm Password :", JLabel.RIGHT);
		JLabel label4 = new JLabel("Remember Me!", JLabel.LEFT);
		JLabel label5 = new JLabel("Hello.", JLabel.CENTER);

		label5.setVerticalAlignment(JLabel.TOP);
		label5.setToolTipText("A tool tip with me!");

		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);

		return panel;
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	
//	public JTextField[] menuSearchText= new JTextField[4];
//	public JTextField[] personSearchText= new JTextField[4];
//	public JTextField[] storeSearchText= new JTextField[4];
//	public JTextField[] menuInsertText= new JTextField[4];
//	public JTextField[] personInsertText= new JTextField[4];
//	public JTextField[] storeInsertText= new JTextField[4];
//	public JTextField[] menuUpdateTextBefore= new JTextField[4];
//	public JTextField[] storeUpdateTextBefore= new JTextField[4];
//	public JTextField[] personUpdateTextBefore= new JTextField[4];
//	public JTextField[] menuUpdateTextAfter= new JTextField[4];
//	public JTextField[] personUpdateTextAfter= new JTextField[4];
//	public JTextField[] storeUpdateTextAfter= new JTextField[4];
	
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
				JDBC.executeSelect(query);
			}else if(obj == personSearchClear){
				for(int i=0; i<personLabelStrings.length; i++) personSearchText[i].setText("");
			}else if(obj == personSearchAccept){
				String query= "select * from dbcourse_person,dbcourse_employee,dbcourse_customer";
				String[] text= new String[personLabelStrings.length];
				int start= -1;
				
				if(personSearchText[0].getText().toString().length() > 0){
					query += " where ";
					start= 0;
					query += "dbcourse_person.person_name=\'"+personSearchText[0].getText().toString()+"\'";
				}
				if(personSearchText[1].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 1;
					query += "dbcourse_person.menu_specname=\'"+personSearchText[1].getText().toString()+"\'";
				}
				if(personSearchText[2].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 2;
					query += "dbcourse_person.menu_time="+personSearchText[2].getText().toString();
				}
				if(personSearchText[3].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 3;
					query += "dbcourse_person.person_phonenum=\'"+personSearchText[3].getText().toString()+"\'";
				}
				if(personSearchText[4].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					query += "dbcourse_person.menu_cost="+personSearchText[4].getText().toString();
				}
				query+=";";
				
				System.out.println(query);
				JDBC.executeSelect(query);
			}else if(obj == storeSearchClear){
				for(int i=0; i<storeLabelStrings.length; i++) storeSearchText[i].setText("");
			}else if(obj == storeSearchAccept){
				String query= "select * from dbcourse_brand,dbcourse_restaurant";
				String[] text= new String[storeLabelStrings.length];
				int start= -1;
				
				if(storeSearchText[0].getText().toString().length() > 0){
					query += " where ";
					start= 0;
					query += "dbcourse_person.person_name=\'"+storeSearchText[0].getText().toString()+"\'";
				}
				if(storeSearchText[1].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 1;
					query += "dbcourse_person.menu_specname=\'"+storeSearchText[1].getText().toString()+"\'";
				}
				if(storeSearchText[2].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 2;
					query += "dbcourse_person.menu_time="+storeSearchText[2].getText().toString();
				}
				if(storeSearchText[3].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 3;
					query += "dbcourse_person.person_phonenum=\'"+storeSearchText[3].getText().toString()+"\'";
				}
				if(storeSearchText[4].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					start= 4;
					query += "dbcourse_person.menu_cost="+storeSearchText[4].getText().toString();
				}
				if(storeSearchText[5].getText().toString().length() > 0){
					if(start == -1) query += " where ";
					else query += " and ";
					query += "dbcourse_person.menu_cost="+storeSearchText[5].getText().toString();
				}
				query+=";";
				
				System.out.println(query);
				JDBC.executeSelect(query);
			}else if(obj == menuInsertInsert){
				
			}else if(obj == menuInsertDelete){
				
			}else if(obj == personInsertInsert){
				
			}else if(obj == personInsertDelete){
				
			}else if(obj == storeInsertInsert){
				
			}else if(obj == storeInsertDelete){
				
			}else if(obj == menuUpdateClear){
				for(int i=0; i<menuLabelStrings.length; i++){
					menuUpdateTextBefore[i].setText("");
					menuUpdateTextAfter[i].setText("");
				}
			}else if(obj == menuUpdateAccept){
				
			}else if(obj == personUpdateClear){
				for(int i=0; i<personLabelStrings.length; i++){
					personUpdateTextBefore[i].setText("");
					personUpdateTextAfter[i].setText("");
				}
			}else if(obj == personUpdateAccept){
				
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
			System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
			return;
		}

		Spring x = Spring.constant(initialX);
		for (int c = 0; c < cols; c++) {
			Spring width = Spring.constant(0);
			for (int r = 0; r < rows; r++) {
				width = Spring.max(width,
						getConstraintsForCell(r, c, parent, cols).getWidth());
			}
			for (int r = 0; r < rows; r++) {
				SpringLayout.Constraints constraints = getConstraintsForCell(r,
						c, parent, cols);
				constraints.setX(x);
				constraints.setWidth(width);
			}
			x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
		}

		Spring y = Spring.constant(initialY);
		for (int r = 0; r < rows; r++) {
			Spring height = Spring.constant(0);
			for (int c = 0; c < cols; c++) {
				height = Spring.max(height,
						getConstraintsForCell(r, c, parent, cols).getHeight());
			}
			for (int c = 0; c < cols; c++) {
				SpringLayout.Constraints constraints = getConstraintsForCell(r,
						c, parent, cols);
				constraints.setY(y);
				constraints.setHeight(height);
			}
			y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
		}

		// Set the parent's size.
		SpringLayout.Constraints pCons = layout.getConstraints(parent);
		pCons.setConstraint(SpringLayout.SOUTH, y);
		pCons.setConstraint(SpringLayout.EAST, x);
	}

	private SpringLayout.Constraints getConstraintsForCell(int row, int col, Container parent, int cols) {
		SpringLayout layout = (SpringLayout) parent.getLayout();
		Component c = parent.getComponent(row * cols + col);
		return layout.getConstraints(c);
	}
	
	private void openJDBC(){
		JDBC jdbc= new JDBC();
	}
}
