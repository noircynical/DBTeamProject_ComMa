import frame.MainPanel;


public class Main {
	private static MainPanel mPanel= null;
	
	public static void main(String[] args) {
		if(mPanel == null) mPanel= new MainPanel();
		mPanel.setVisible(true);
	}
}
