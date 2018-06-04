package levelpac;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import smthTipaProgi.Const;
import smthTipaProgi.Game;
import smthTipaProgi.Mp3Player;

public class GameManager {

	public static final JFrame frame = new JFrame();
	public static final JLayeredPane lp = frame.getLayeredPane();
	public static ListOfRecords list;

	private static MenuBar bar;
	private static Leaderboard board;
	private static Game currGame;
	private static boolean musicOn = true;

	public static Game getCurrentGame() {
		return currGame;
	}

	public static void main(String[] args) {
		drawWindow();
		try {
			list = new ListOfRecords("results.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawMenu();
		musicFactory();
	}

	public static void startNewGame() {
		new Thread() {
			public void run() {
				frame.setFocusable(true);
				frame.requestFocusInWindow();
				if (bar != null)
					lp.remove(bar);
				if (board != null)
					lp.remove(board);
				currGame = new Game(frame, lp, 0, 1, 3);
				currGame.start();
			}
		}.start();
	}

	public static void continueGame(int score, int level, int lives) {
		lp.removeAll();
		lp.repaint();
		currGame = new Game(frame, lp, score, level + 1, lives);
		currGame.start();
	}

	public static void drawMenu() {
		if (board != null)
			lp.remove(board);
		lp.repaint();
		bar = new MenuBar(true);
		bar.setLocation((frame.getWidth() - 350) / 2, (frame.getHeight() - 300) / 2);
		lp.add(bar, Const.MENU_LAYER);
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

	public static void drawLeader(int page) {
		if (bar != null)
			lp.remove(bar);
		board = new Leaderboard(page);
		board.setLocation((frame.getWidth() - board.getWidth()) / 2, (frame.getHeight() - board.getHeight()) / 2);
		lp.add(board, Const.MENU_LAYER);
	}

	public static void deleteLeader(JLabel main) {
		lp.remove(main);
		lp.repaint();
	}

	public static void askName(int level, int score) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(300, 200);
		panel.setLocation((frame.getWidth() - currGame.getMsg().getWidth()) / 2 + currGame.getMsg().getWidth() / 10,
				(frame.getHeight() - currGame.getMsg().getHeight()) / 2 + 20);
		panel.setBackground(new Color(43, 186, 174));

		JLabel name = new JLabel("Enter your name");
		name.setFont(new Font("Courier New", Font.BOLD, 20));
		name.setLocation(panel.getWidth() / 3 - panel.getWidth() / 15, panel.getHeight() / 10);
		name.setSize(name.getPreferredSize());
        
		JButton but = new JButton("Ok");
		but.setLocation(panel.getWidth() / 3 + panel.getWidth() / 10, panel.getHeight() - panel.getHeight() / 4);
		but.setSize(panel.getWidth() / 6, panel.getHeight() / 7);
		but.setBackground(Color.WHITE);
		

	
		JTextField jtf = new JTextField();
		jtf.setLocation(panel.getWidth() / 30, panel.getHeight() / 3);
		jtf.setSize(panel.getWidth() - panel.getWidth() / 15, panel.getHeight() / 7);

		panel.add(name);
		panel.add(jtf);
		panel.add(but);
		but.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				RecordField rec = new RecordField(jtf.getText(), String.valueOf(level), String.valueOf(score));

				try {
					list.add(rec);
					int page = checker(list.getIndex(rec));
					lp.remove(panel);
					lp.remove(currGame.getMsg());
					currGame.getPointsCont().delete();
					currGame.getLivesCont().delete();
					lp.remove(currGame.getMenulabel());
					lp.remove(currGame.levelTxt());
					lp.repaint();

					GameManager.drawLeader(page);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		lp.add(panel, Const.FINAL_MSG_LAYER);
	}

	protected static int checker(int index) {
		int page = 0;
		while (index >= 10) {
			index -= 10;
			page++;
		}
		return page;
	}

	private static void musicFactory() {
		new Thread() {
			public void run() {
				while (musicOn) {
					Mp3Player mp = new Mp3Player(Const.MUSIC_PATH);
					mp.play();
				}
			}
		}.start();
	}

}
