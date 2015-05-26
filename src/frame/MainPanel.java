package frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainPanel extends JFrame{
	
	private JPanel mPanel= null;

	public MainPanel(){
		initUI();
		
		createTopBar();
		createLayout();
	}
	
	private void initUI(){
		if(mPanel == null) mPanel= new JPanel();
		mPanel.setLayout(new GridLayout(1, 1, 0, 0));
//		panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		JTabbedPane mTopPane= new JTabbedPane();
		JComponent mSearchPane= makeSearchPanel();
		JComponent mInsertPane= makeInsertPanel();
		JComponent mUpdatePane= makeUpdatePanel();
		
		mTopPane.addTab("Search", null, mSearchPane, "Search Panel");
		mTopPane.addTab("Insert & Delete", null, mInsertPane, "Insert & Delete Panel");
		mTopPane.addTab("Update", null, mUpdatePane, "Update Panel");
		
		mPanel.add(mTopPane);
		
//		mPanel.setLayout(new GridLayout(2, 1, 10, 0));
//		
//		// add panel
//		
//		// JTabbed Pane
//		JTabbedPane tabbedPane = new JTabbedPane();
//		JComponent panel1 = makeTextPanel("Menu");
//		JComponent panel2 = makeTextPanel("Person");
//		JComponent panel3 = makeTextPanel("Employee");
//		JComponent panel4 = makeTextPanel("Brand");
//		
//		tabbedPane.addTab("Menu", null, panel1, "search for menu");
//		tabbedPane.addTab("Person", null, panel2, "search for person");
//		tabbedPane.addTab("Employee", null, panel3, "search for employee");
//		tabbedPane.addTab("Brand", null, panel4, "search for brand");
//		
//		mPanel.add(tabbedPane);
//        add(mPanel);
		add(mPanel);
		
		setTitle("Team :: Com,Ma");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JComponent makeSearchPanel(){
		JPanel panel= new JPanel(false);
		panel.setLayout(new GridLayout(2, 1, 10, 0));
		
		JTabbedPane mCondPane= new JTabbedPane();
		
		panel.add(mCondPane);
		return panel;
	}
	private JComponent makeInsertPanel(){
		JPanel panel= new JPanel(false);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		return panel;
	}
	private JComponent makeUpdatePanel(){
		JPanel panel= new JPanel(false);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
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
	
	private void createTopBar(){
		JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem mMenualItem = new JMenuItem("Menual");
        mMenualItem.setMnemonic(KeyEvent.VK_E);
        mMenualItem.setToolTipText("Menual for this program");
        mMenualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        JMenuItem mInfoItem = new JMenuItem("Information");
        mMenualItem.setMnemonic(KeyEvent.VK_E);
        mMenualItem.setToolTipText("Information about Developer");
        mMenualItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(mMenualItem);
        file.add(mInfoItem);
        menubar.add(file);

        setJMenuBar(menubar);
	}
	private void createLayout(){
		
	}
}
