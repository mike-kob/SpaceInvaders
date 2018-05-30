package levelpac;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import smthTipaProgi.Const;
import smthTipaProgi.Game;
import smthTipaProgi.Mp3Player;

public class GameManager {

	public static final JFrame frame = new JFrame();
	public static final JLayeredPane lp = frame.getLayeredPane();
	static MenuBar bar;
	static Game currGame;
	public static boolean musicOn = true;

	public static void main(String[] args) {
		drawWindow();
		bar = new MenuBar();
		bar.setLocation((frame.getWidth() - 350) / 2, (frame.getHeight() - 300) / 2);
		lp.add(bar, Const.MENU_LAYER);

		musicFactory();
	}

	public static void drawWindow() {
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

	protected static void musicFactory() {
		new Thread() {
			public void run() {
				while (musicOn) {
					Mp3Player mp = new Mp3Player(Const.MUSIC_PATH);
					mp.play();
				}
			}
		}.start();
	}

	public static void startNewGame() {
		new Thread() {
			public void run() {
				lp.remove(bar);
				currGame = new Game(frame, lp, 0, 1, 3);
				currGame.start();
			}
		}.start();
	}

	public static void askName() {

	}

	public static void continueGame(int score, int level, int lives) {
		lp.removeAll();
		lp.repaint();
		currGame = new Game(frame, lp, score, level + 1, lives);
		currGame.start();
	}

	public static Game getCurrentGame() {
		return currGame;
	}

	public static JFrame getFrame() {
		return frame;
	}
}
