package levelpac;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import smthTipaProgi.Const;
import smthTipaProgi.Game;

public class GameManager {
	
	public static final JFrame frame = new JFrame();
	public static final JLayeredPane lp = frame.getLayeredPane();
	static MenuBar bar;
	
	public static void main(String [] args) {
		init();
		bar = new MenuBar();
		bar.setLocation((frame.getWidth()-350)/2, (frame.getHeight()-300)/2);
		bar.drawStart();
		lp.add(bar, new Integer(20));
	}
	
	public static void startNewGame() {
		new Thread() {
			public void run() {
				lp.remove(bar);
				Game.init(frame, lp);
			}
		}.start();
	}
	
	public static void init () {
			frame.setSize(1280, 980);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			
			JLabel background = new JLabel(new ImageIcon(Const.BACKGROUND_PATH));
			background.setLocation(0, 0);
			background.setVisible(true);
			background.setSize(1280, 980);
			lp.add(background, Const.BACKGROUND_LAYER);
	}
}
