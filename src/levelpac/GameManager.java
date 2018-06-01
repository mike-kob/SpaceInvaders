package levelpac;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	static MenuBar bar;
	static Leaderboard board;
	static Game currGame;
	public static boolean musicOn = true;
	public static ListOfRecords list;

	public static void main(String[] args) {
		drawWindow();
		try {
			list = new ListOfRecords("results.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drawMenu();
		musicFactory();
	}
	
	public static void drawMenu() {
		bar = new MenuBar();
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

	public static void drawLeader() {
		if(bar!=null) lp.remove(bar);
		board = new Leaderboard();
		board.setLocation((frame.getWidth() - board.getWidth()) / 2, (frame.getHeight() - board.getHeight()) / 2);
		lp.add(board, Const.MENU_LAYER);
	}
	
	public static void deleteLeader(JLabel main) {
		lp.remove(main);
		lp.repaint();
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

	public static void startNewGame() {
		new Thread() {
			public void run() {
				if(bar!=null) lp.remove(bar);
				if(board!=null) lp.remove(board);
				currGame = new Game(frame, lp, 0, 1, 3);
				currGame.start();
			}
		}.start();
	}

	public static void askName(int level,int score) {
		JLabel name  = new JLabel("Tell your name");
		JButton but = new JButton("ok");
		
		JPanel	panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JTextField	jtf = new JTextField();
		panel.add(name, BorderLayout.NORTH);
		panel.add(jtf,BorderLayout.CENTER);
		panel.add(but,BorderLayout.SOUTH);
		but.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent arg0) {
		RecordField rec = new RecordField(jtf.getText(),String.valueOf(level),String.valueOf(score));
		try {
			list.add(rec);
			list.getIndex(rec);
			lp.remove(panel);
			lp.remove(currGame.getMsg());
			currGame.getPointsCont().delete();
			currGame.getLivesCont().delete();
			lp.repaint();
			
			GameManager.drawLeader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}}
			);
		panel.setSize(100,100);
		panel.setLocation((frame.getWidth() - currGame.getMsg().getWidth()) / 2,(frame.getHeight() - currGame.getMsg().getHeight()) / 2 + 200);
		lp.add(panel, Const.FINAL_MSG_LAYER);	
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
