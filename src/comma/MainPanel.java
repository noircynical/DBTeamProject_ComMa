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
	public String[] menuLabelStrings = { "분야", "이름", "조리시간", "단가"};
	public String[] personLabelStrings = { "이름", "주소", "핸드폰번호", "직책", "매장이름" };
	public String[] storeLabelStrings = { "브랜드명", "매장명", "지역", "세부지역", "메뉴분야", "분위기" };

	private JPanel mPanel = null;
	
	private Vector<String> serachPersonColumn= null;
	private DefaultTableModel searchPersonTableModel= null; 

	public MainPanel() {
		initUI();
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
		
		JComponent[] panelRightComponent = new JComponent[menuLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[menuLabelStrings.length];
		int fieldNumber = 0;

		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);

		for (int i = 0; i < menuLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(panelRightComponent[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(panelRightComponent[i]);
		}
		
		JButton button= new JButton("Clear");
		panel.add(button);
		button= new JButton("Search");
		panel.add(button);
		
		makeCompactGrid(panel, menuLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}
	private JComponent makeSearchPersonPanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		JComponent[] panelRightComponent = new JComponent[personLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[personLabelStrings.length];
		int fieldNumber = 0;

		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);

		for (int i = 0; i < personLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(personLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(panelRightComponent[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(panelRightComponent[i]);
		}
		
		JButton button= new JButton("Clear");
		panel.add(button);
		button= new JButton("Search");
		panel.add(button);
		
		makeCompactGrid(panel, personLabelStrings.length+1, 2, GAP, GAP, 20, GAP);
		return panel;
	}
	private JComponent makeSearchStorePanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		JComponent[] panelRightComponent = new JComponent[storeLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[storeLabelStrings.length];
		int fieldNumber = 0;

		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);

		for (int i = 0; i < storeLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(storeLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(panelRightComponent[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(panelRightComponent[i]);
		}
		
		JButton button= new JButton("Clear");
		panel.add(button);
		button= new JButton("Search");
		panel.add(button);
		
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
		
		JComponent[] panelRightComponent = new JComponent[menuLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[menuLabelStrings.length];
		int fieldNumber = 0;

		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);

		for (int i = 0; i < menuLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(menuLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(panelRightComponent[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(panelRightComponent[i]);
		}
		
		JButton button= new JButton("Insert");
		panel.add(button);
		button= new JButton("Delete");
		panel.add(button);
		
		makeCompactGrid(panel, menuLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}
	private JComponent makeInsertPersonPanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		JComponent[] panelRightComponent = new JComponent[personLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[personLabelStrings.length];
		int fieldNumber = 0;

		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);

		for (int i = 0; i < personLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(personLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(panelRightComponent[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(panelRightComponent[i]);
		}
		
		JButton button= new JButton("Insert");
		panel.add(button);
		button= new JButton("Delete");
		panel.add(button);
		
		makeCompactGrid(panel, personLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}
	private JComponent makeInsertStorePanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		JComponent[] panelRightComponent = new JComponent[storeLabelStrings.length];
		JComponent[] panelLeftComponent= new JComponent[storeLabelStrings.length];
		int fieldNumber = 0;

		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);
		panelRightComponent[fieldNumber] = new JTextField();
		((JTextField) panelRightComponent[fieldNumber++]).setColumns(20);

		for (int i = 0; i < storeLabelStrings.length; i++) {
			panelLeftComponent[i] = new JLabel(storeLabelStrings[i], JLabel.TRAILING);
			((JLabel)panelLeftComponent[i]).setLabelFor(panelRightComponent[i]);
			panel.add(panelLeftComponent[i]);
			panel.add(panelRightComponent[i]);
		}
		
		JButton button= new JButton("Insert");
		panel.add(button);
		button= new JButton("Delete");
		panel.add(button);
		
		makeCompactGrid(panel, storeLabelStrings.length+1, 2, GAP, GAP, GAP, GAP);
		return panel;
	}

	private JComponent makeUpdatePanel() {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1, 1));

		JTabbedPane mCondPane = new JTabbedPane();
		JComponent panel1 = makeTextPanel("Menu");
		JComponent panel2 = makeTextPanel("Person");
		JComponent panel3 = makeTextPanel("Store");

		mCondPane.addTab("Menu", null, panel1, "search for menu");
		mCondPane.addTab("Person", null, panel2, "search for person");
		mCondPane.addTab("Store", null, panel3, "search for Store");

		panel.add(mCondPane);
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

//	private void createTopBar() {
//		JMenuBar menubar = new JMenuBar();
//
//		JMenu file = new JMenu("Help");
//		file.setMnemonic(KeyEvent.VK_F);
//
//		JMenuItem mMenualItem = new JMenuItem("Menual");
//		mMenualItem.setMnemonic(KeyEvent.VK_E);
//		mMenualItem.setToolTipText("Menual for this program");
//		mMenualItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				System.exit(0);
//			}
//		});
//		JMenuItem mInfoItem = new JMenuItem("Information");
//		mMenualItem.setMnemonic(KeyEvent.VK_E);
//		mMenualItem.setToolTipText("Information about Developer");
//		mMenualItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				System.exit(0);
//			}
//		});
//
//		file.add(mMenualItem);
//		file.add(mInfoItem);
//		menubar.add(file);
//
//		setJMenuBar(menubar);
//	}
//
//	private void createLayout() {
//
//	}

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
}
