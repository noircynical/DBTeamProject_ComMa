package frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainPanel extends JFrame{
	
	private JPanel mPanel= null;

	public MainPanel(){
		initUI();
		
		createTopBar();
		createLayout();
	}
	
	private void initUI(){
		
		if(mPanel == null) mPanel= new JPanel();
		mPanel.setLayout(new GridLayout(2, 1, 10, 0));
		
		// add panel
		
        add(mPanel);
		
		setTitle("Team :: Com,Ma");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
