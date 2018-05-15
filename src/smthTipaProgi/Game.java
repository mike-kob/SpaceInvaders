package smthTipaProgi;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game {
	static JFrame frame = new JFrame();
	static JLayeredPane lp = frame.getLayeredPane();;
	static Rocket fighter = new Rocket();
	static JPanel grid;
	
	public static Integer BACK_LAYER = 1;
	public static Integer ROCKET_LAYER = 2;
	public static Integer ALIEN_LAYER = 3;
	

	private static int ALIEN_ROWS = 5;
	private static int ALIEN_COLUMNS = 7;

	static Alien[][] aliens = new Alien[ALIEN_ROWS][ALIEN_COLUMNS];

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				drawEverything();
				frame.addKeyListener(keyL);
			}
		});
	}

	private static void drawEverything() {
		frame.setSize(1280, 720);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l = new JLabel(new ImageIcon("res/background.png"));
		l.setLocation(0, 0);
		l.setSize(1280, 720);
		lp.add(l, BACK_LAYER);

		lp.add(fighter, Game.ROCKET_LAYER);

		grid = new JPanel(new GridLayout(ALIEN_ROWS, ALIEN_COLUMNS));
		
		for (int i = 0; i < ALIEN_ROWS; i++) {
			for (int j = 0; j < ALIEN_COLUMNS; j++) {
				Alien cur = new Alien();
				aliens[i][j] = cur;
				grid.add(cur);
			}
		}
		grid.setOpaque(true);
		grid.setLocation(10, 10);
		grid.setSize(400,200);
		grid.setVisible(true);
		lp.add(grid, Game.ALIEN_LAYER);
		grid.remove(aliens[2][2]);
		grid.remove(aliens[2][1]);
	}

	static KeyListener keyL = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				fighter.left();
				
			} else if (key == KeyEvent.VK_RIGHT) {
				fighter.right();
			}
		}

	};
}
